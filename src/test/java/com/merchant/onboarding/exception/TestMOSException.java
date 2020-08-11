package com.merchant.onboarding.exception;

import com.merchant.onboarding.validator.MerchantValidator;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestMOSException {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void TestMOSException() throws MOSException {
        thrown.expect(MOSException.class);
        thrown.expectMessage(CoreMatchers.is("Legal Name & KYC Doc Name are not same"));

        MerchantValidator merchantValidator = new MerchantValidator();
        merchantValidator.validateName("Vinay", "Vicky");
    }
}
