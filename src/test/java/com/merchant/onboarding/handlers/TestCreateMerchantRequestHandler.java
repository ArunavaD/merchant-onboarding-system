package com.merchant.onboarding.handlers;

import com.merchant.onboarding.service.MerchantService;
import com.merchant.onboarding.service.MerchantServiceImpl;
import org.junit.Before;

public class TestCreateMerchantRequestHandler {

    private MerchantService merchantService;

    @Before
    public void setup() {
        merchantService = new MerchantServiceImpl();
    }
}
