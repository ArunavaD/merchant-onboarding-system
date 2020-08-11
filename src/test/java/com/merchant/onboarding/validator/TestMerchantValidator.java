package com.merchant.onboarding.validator;

import com.merchant.onboarding.exception.MOSException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMerchantValidator {

    private MerchantValidator objectUnderTest;

    @Before
    public void setup() {
        objectUnderTest = new MerchantValidator();
    }

    @Test
    public void TestValidateNumber() throws MOSException {
        boolean result = objectUnderTest.validateNumber("123456789");
        Assert.assertTrue(result);
    }

    @Test(expected = MOSException.class)
    public void TestValidateNumberException() throws MOSException {
        objectUnderTest.validateNumber("123456789A");
    }

    @Test
    public void TestValidateName() throws MOSException {
        boolean result = objectUnderTest.validateName("Vinay", "Vinay");
        Assert.assertTrue(result);
    }

    @Test(expected = MOSException.class)
    public void TestValidateNameExceptionNamesNotEqual() throws MOSException {
        objectUnderTest.validateName("Vinay", "Vicky");
    }

    @Test(expected = MOSException.class)
    public void TestValidateNameExceptionNamesNotProperFormat() throws MOSException {
        objectUnderTest.validateName("Vinay12", "Vinay12");
    }

    @Test
    public void TestValidateOrganisation() throws MOSException {
        boolean result = objectUnderTest.validateOrganisation("Airtel");
        Assert.assertTrue(result);
    }

    @Test(expected = MOSException.class)
    public void TestValidateOrganisationException() throws MOSException {
        objectUnderTest.validateOrganisation("Aero");
    }

    @Test
    public void TestValidateKycDocType() throws MOSException {
        boolean result = objectUnderTest.validateKycDocType("Adhaar");
        Assert.assertTrue(result);
    }

    @Test(expected = MOSException.class)
    public void TestValidateKycDocTypeException() throws MOSException {
        objectUnderTest.validateKycDocType("Ration");
    }

    @Test
    public void TestValidateNameAndShortName() throws MOSException {
        boolean result = objectUnderTest.validateNameAndShortName("Vinay Kumar", "VK");
        Assert.assertTrue(result);
    }

    @Test(expected = MOSException.class)
    public void TestValidateNameAndShortNameException() throws MOSException {
        objectUnderTest.validateNameAndShortName("Vinay", "Vinay Kumar");
    }

    @Test
    public void TestValidateEmail() throws MOSException {
        boolean result = objectUnderTest.validateEmail("vinay.kumar@gmail.com");
        Assert.assertTrue(result);
    }

    @Test(expected = MOSException.class)
    public void TestValidateEmailException() throws MOSException {
        objectUnderTest.validateEmail("vinay.kumar@gmail");
    }

    @Test
    public void TestValidateWebsite() throws MOSException {
        boolean result = objectUnderTest.validateWebsite("https://vinay.kumar.com");
        Assert.assertTrue(result);
    }

    @Test(expected = MOSException.class)
    public void TestValidateWebsiteException() throws MOSException {
        objectUnderTest.validateWebsite("vinay.kumar.com");
    }

    @Test
    public void TestValidatePhoneNumber() throws MOSException {
        boolean result = objectUnderTest.validatePhoneNumber("+91987459625");
        Assert.assertTrue(result);
    }

    @Test(expected = MOSException.class)
    public void TestValidatePhoneNumberException() throws MOSException {
        objectUnderTest.validatePhoneNumber("+91123456789A");
    }
}
