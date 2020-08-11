package com.merchant.onboarding.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.merchant.onboarding.adapter.MOSDynamoDBAdapter;
import com.merchant.onboarding.dao.MerchantDao;
import com.merchant.onboarding.dao.MerchantDaoImpl;
import com.merchant.onboarding.exception.MOSException;
import com.merchant.onboarding.model.Merchant;
import com.merchant.onboarding.validator.MerchantValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestMerchantServiceImpl {

    private MerchantDao merchantDao;
    private MerchantServiceImpl objectUnderTest;

    @Before
    public void setup() {
        merchantDao = new MerchantDaoImpl();
        objectUnderTest = new MerchantServiceImpl();
    }

    public static Merchant getMerchantDummyObject() {
        Merchant merchant = new Merchant();
        merchant.setMerchantId("123456789");
        merchant.setOrganizationName("Jio");
        merchant.setMerchantName("Jio Limited");
        merchant.setMerchantShortName("V");
        merchant.setLegalName("Vikas");
        merchant.setState("West Bengal");
        merchant.setCountry("India");
        merchant.setCity("Kolkata");
        merchant.setStreetAddress("Park Street");
        merchant.setStreetAddress2("");
        merchant.setZipCode(7485961);
        merchant.setEmailAddress("vikas.kumar@gmail.com");
        merchant.setPhone("+91748596231");
        merchant.setFax("748596123");
        merchant.setMerchantBusinessType("Telecom");
        merchant.setMaximumTransactionAmount(200000);
        merchant.setWebsite("www.voda.oom");
        merchant.setCreatedBy("System");
        merchant.setKycDocName("Vikas");
        merchant.setKycDocNumber("748596");
        merchant.setKycDocType("pan");
        merchant.setCreateDate(new Date());
        merchant.setActivationDate(new Date());
        merchant.setMerchantStatus("ACTIVE");

        return merchant;
    }

    @Test
    public void testPopulateMerchant() throws MOSException, IOException {
        String json = "{\"merchantName\": \"Voda Limited\",\"merchantShortName\": \"AK\",\"legalName\": \"Abhi Kumar\",\"organizationName\": \"Jio\",\"merchantBusinessType\": \"Telecom\",\"maxTransactionAmount\": 1000000,\"country\": \"India\",\"state\": \"West Bengal\",\"city\": \"Kolkata\",\"streetAddress\": \"Park Street\",\"streetAddress2\": \"\",\"zipCode\": 700052,\"email\": \"voda25@gmail.com\",\"phone\": \"+91985647231\",\"fax\": \"147852385669\",\"website\": \"www.jiolimited.com\",\"kycDocType\": \"Pan\",\"kycDocNumber\": \"78459612\",\"kycDocName\": \"Abhi Kumar\"}";
        JsonNode jsonBody = new ObjectMapper().readTree(json);
        Merchant merchant = objectUnderTest.populateMerchant(jsonBody);

        Assert.assertNotNull(merchant);
        Assert.assertEquals(jsonBody.get("merchantName").asText(), merchant.getMerchantName());
    }

    @Test
    public void testValidateMerchantData() throws MOSException {
        Merchant merchant = getMerchantDummyObject();
        objectUnderTest.validateMerchantData(merchant);
    }

    /*@Test
    public void testSaveMerchant() throws MOSException, IOException {
        Merchant merchant = getMerchantDummyObject();
        objectUnderTest.saveMerchant(merchant);
    }

    @Test
    public void testDuplicateMerchantCheck() throws MOSException {
        Merchant merchant = getMerchantDummyObject();
        objectUnderTest.duplicateMerchantCheck(merchant);
    }

    @Test
    public void testListMerchants() throws MOSException, IOException {
        List<Merchant> merchantList = new ArrayList<>();
        merchantList.add(getMerchantDummyObject());
        merchantList.add(getMerchantDummyObject());

        MerchantDao mockMerchantDao = Mockito.mock(MerchantDaoImpl.class);
        Mockito.when(mockMerchantDao.listMerchants()).thenReturn(merchantList);
        List<Merchant> resultMerchantList = objectUnderTest.listMerchants();

        Assert.assertEquals(merchantList.size(), resultMerchantList.size());
    }*/
}
