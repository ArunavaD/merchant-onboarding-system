package com.merchant.onboarding.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class TestMerchant {

    public static Merchant getMerchantObject() {
        Merchant merchant = new Merchant();

        merchant.setMerchantId("123456789");
        merchant.setOrganizationName("Jio");
        merchant.setMerchantName("Jio Limited");
        merchant.setMerchantShortName("Jio");
        merchant.setLegalName("Vikas");
        merchant.setState("West Bengal");
        merchant.setCountry("India");
        merchant.setCity("Kolkata");
        merchant.setStreetAddress("Park");
        merchant.setStreetAddress2("Street");
        merchant.setZipCode(7485961);
        merchant.setEmailAddress("jio.limited@gmail.com");
        merchant.setPhone("+91748596231");
        merchant.setFax("748596123");
        merchant.setMerchantBusinessType("Telecom");
        merchant.setMaximumTransactionAmount(200000);
        merchant.setWebsite("www.jiolimited.oom");
        merchant.setCreatedBy("SYSTEM");
        merchant.setKycDocName("Vikas");
        merchant.setKycDocNumber("748596");
        merchant.setKycDocType("pan");
        merchant.setCreateDate(new Date());
        merchant.setActivationDate(new Date());
        merchant.setDeactivationDate(new Date());
        merchant.setMerchantStatus("ACTIVE");

        return merchant;
    }

    @Test
    public void testGetterSetter() {
        Merchant merchant = getMerchantObject();

        Assert.assertEquals("123456789", merchant.getMerchantId());
        Assert.assertEquals("Jio", merchant.getOrganizationName());
        Assert.assertEquals("Jio Limited", merchant.getMerchantName());
        Assert.assertEquals("Jio", merchant.getMerchantShortName());
        Assert.assertEquals("Vikas", merchant.getLegalName());
        Assert.assertEquals("West Bengal", merchant.getState());
        Assert.assertEquals("India", merchant.getCountry());
        Assert.assertEquals("Kolkata", merchant.getCity());
        Assert.assertEquals("Park", merchant.getStreetAddress());
        Assert.assertEquals("Street", merchant.getStreetAddress2());
        Assert.assertEquals(7485961, merchant.getZipCode());
        Assert.assertEquals("jio.limited@gmail.com", merchant.getEmailAddress());
        Assert.assertEquals("+91748596231", merchant.getPhone());
        Assert.assertEquals("748596123", merchant.getFax());
        Assert.assertEquals("Telecom", merchant.getMerchantBusinessType());
        Assert.assertEquals(200000, merchant.getMaximumTransactionAmount());
        Assert.assertEquals("www.jiolimited.oom", merchant.getWebsite());
        Assert.assertEquals("748596", merchant.getKycDocNumber());
        Assert.assertEquals("Vikas", merchant.getKycDocName());
        Assert.assertEquals("pan", merchant.getKycDocType());
        Assert.assertEquals("SYSTEM", merchant.getCreatedBy());
        Assert.assertEquals("ACTIVE", merchant.getMerchantStatus());
        Assert.assertEquals(new Date(), merchant.getActivationDate());
        Assert.assertEquals(new Date(), merchant.getCreateDate());
        Assert.assertEquals(new Date(), merchant.getDeactivationDate());
    }

    @Test
    public void testToString() {
        Merchant merchant = getMerchantObject();
        String toString = merchant.toString();

        Assert.assertTrue(toString.contains("merchantId='123456789'"));
        Assert.assertTrue(toString.contains("kycDocName='Vikas'"));
    }
}
