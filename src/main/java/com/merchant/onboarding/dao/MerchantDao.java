package com.merchant.onboarding.dao;

import com.merchant.onboarding.exception.MOSException;
import com.merchant.onboarding.model.Merchant;

import java.io.IOException;
import java.util.List;

public interface MerchantDao {

    List<Merchant> listMerchants() throws IOException, MOSException;
    void saveMerchant(Merchant merchant) throws IOException, MOSException;
    void getMerchant(String merchantName) throws MOSException;
}
