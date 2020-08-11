package com.merchant.onboarding.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.merchant.onboarding.adapter.MOSDynamoDBAdapter;
import com.merchant.onboarding.exception.MOSException;
import com.merchant.onboarding.model.Merchant;
import com.merchant.onboarding.response.MOSResponseEnum;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class MerchantDaoImpl implements MerchantDao {

    private final Logger logger = Logger.getLogger(this.getClass());
    DynamoDBMapper merchantMapper = MOSDynamoDBAdapter.getMerchantMapper();

    /**
     * Method to get the list of onboarded merchants
     *
     * @return
     * @throws IOException
     * @throws MOSException
     */
    @Override
    public List<Merchant> listMerchants() throws IOException, MOSException {
        List<Merchant> results;
        try {
            DynamoDBScanExpression scanExp = new DynamoDBScanExpression();
            results = merchantMapper.scan(Merchant.class, scanExp);
            for (Merchant p : results) {
                logger.info("Merchants - listMerchants(): " + p.toString());
            }
        } catch (Exception ex) {
            logger.debug(ex);
            throw new MOSException("Error Fetching data from database");
        }
        return results;
    }

    /**
     * Save the merchant in database
     *
     * @param merchant
     * @throws IOException
     * @throws MOSException
     */
    @Override
    public void saveMerchant(Merchant merchant) throws IOException, MOSException {
        try {
            logger.info("Merchants - saveMerchant(): " + merchant.toString());
            merchantMapper.save(merchant);
        } catch (Exception ex) {
            logger.debug(ex);
            throw new MOSException("Error Saving Merchant Data");
        }
    }

    /**
     * Gets the merchant data to check for duplicate entry
     *
     * @param merchantName
     * @throws MOSException
     */
    @Override
    public void getMerchant(String merchantName) throws MOSException {
        HashMap<String, AttributeValue> av = new HashMap<String, AttributeValue>();
        av.put(":v1", new AttributeValue().withS(merchantName));

        DynamoDBScanExpression scanExp = new DynamoDBScanExpression()
                .withFilterExpression("merchantName = :v1")
                .withExpressionAttributeValues(av);

        List<Merchant> result = merchantMapper.scan(Merchant.class, scanExp);
        if (result.size() > 0) {
            throw new MOSException("Merchant <" + merchantName + "> already exists in database");
        }
    }
}
