package com.merchant.onboarding.adapter;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

public class MOSDynamoDBAdapter {

    private static MOSDynamoDBAdapter mosDynamoDbAdapter = null;
    private final AmazonDynamoDB client;
    private DynamoDBMapper mapper;

    // creating the dynamo db client
    private MOSDynamoDBAdapter() {
        this.client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    public static MOSDynamoDBAdapter getInstance() {
        if (mosDynamoDbAdapter == null)
            mosDynamoDbAdapter = new MOSDynamoDBAdapter();

        return mosDynamoDbAdapter;
    }

    public AmazonDynamoDB getDbClient() {
        return this.client;
    }

    /**
     * creating the mapper with the mapper config
     *
     * @param mapperConfig
     * @return
     */
    public DynamoDBMapper createDbMapper(DynamoDBMapperConfig mapperConfig) {
        if (this.client != null)
            mapper = new DynamoDBMapper(this.client, mapperConfig);

        return this.mapper;
    }

    /**
     *  gets the created dynamo db mapper
     *
     * @return
     */
    public static DynamoDBMapper getMerchantMapper() {
        String MERCHANTS_TABLE_NAME = System.getenv("MERCHANTS_TABLE_NAME");
        // build the mapper config
        DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
                .withTableNameOverride(new DynamoDBMapperConfig.TableNameOverride(MERCHANTS_TABLE_NAME))
                .build();
        // get the db adapter
        MOSDynamoDBAdapter db_adapter = MOSDynamoDBAdapter.getInstance();
        AmazonDynamoDB client = db_adapter.getDbClient();
        // create the mapper with config
        DynamoDBMapper mapper = db_adapter.createDbMapper(mapperConfig);

        return mapper;
    }
}
