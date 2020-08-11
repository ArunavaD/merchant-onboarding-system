package com.merchant.onboarding.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.merchant.onboarding.exception.MOSException;
import com.merchant.onboarding.model.Merchant;

import java.io.IOException;
import java.util.List;

public interface MerchantService {

    Merchant populateMerchant(JsonNode body);
    void validateMerchantData(Merchant merchant) throws MOSException;
    void saveMerchant(Merchant populatedMerchant) throws IOException, MOSException;
    void duplicateMerchantCheck(Merchant merchant) throws MOSException;

    List<Merchant> listMerchants() throws IOException, MOSException;
}
