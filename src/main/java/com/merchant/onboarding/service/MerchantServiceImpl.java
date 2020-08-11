package com.merchant.onboarding.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.merchant.onboarding.dao.MerchantDao;
import com.merchant.onboarding.dao.MerchantDaoImpl;
import com.merchant.onboarding.exception.MOSException;
import com.merchant.onboarding.model.Merchant;
import com.merchant.onboarding.validator.MerchantValidator;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class MerchantServiceImpl implements MerchantService {

    MerchantDao merchantDao;
    MerchantValidator merchantValidator;

    public MerchantServiceImpl() {
        merchantDao = new MerchantDaoImpl();
        merchantValidator = new MerchantValidator();
    }

    /**
     *
     * @param body
     * @return
     */
    @Override
    public Merchant populateMerchant(JsonNode body) {
        Merchant merchant = new Merchant();
        merchant.setOrganizationName(body.get("organizationName").asText());
        merchant.setMerchantName(body.get("merchantName").asText());
        merchant.setMerchantShortName(body.get("merchantShortName").asText());
        merchant.setLegalName(body.get("legalName").asText());
        merchant.setState(body.get("state").asText());
        merchant.setCountry(body.get("country").asText());
        merchant.setCity(body.get("city").asText());
        merchant.setStreetAddress(body.get("streetAddress").asText());
        merchant.setStreetAddress2(body.get("streetAddress2").asText());
        merchant.setZipCode(body.get("zipCode").asLong());
        merchant.setEmailAddress(body.get("email").asText());
        merchant.setPhone(body.get("phone").asText());
        merchant.setFax(body.get("fax").asText());
        merchant.setMerchantBusinessType(body.get("merchantBusinessType").asText());
        merchant.setMaximumTransactionAmount(body.get("maxTransactionAmount").asLong());
        merchant.setWebsite(body.get("website").asText());
        merchant.setCreatedBy("System");
        merchant.setKycDocName(body.get("kycDocName").asText());
        merchant.setKycDocNumber(body.get("kycDocNumber").asText());
        merchant.setKycDocType(body.get("kycDocType").asText());
        merchant.setCreateDate(new Date());
        merchant.setActivationDate(new Date());
        merchant.setMerchantStatus("ACTIVE");

        return merchant;
    }

    /**
     *
     * @param merchant
     * @throws MOSException
     */
    @Override
    public void validateMerchantData(Merchant merchant) throws MOSException {
        merchantValidator.validateName(merchant.getLegalName(), merchant.getKycDocName());
        merchantValidator.validateNumber(merchant.getKycDocNumber());
        merchantValidator.validateKycDocType(merchant.getKycDocType());
        merchantValidator.validateEmail(merchant.getEmailAddress());
        merchantValidator.validateNumber(Long.toString(merchant.getZipCode()));
        merchantValidator.validateNumber(Long.toString(merchant.getMaximumTransactionAmount()));
        merchantValidator.validateOrganisation(merchant.getOrganizationName());
        merchantValidator.validateNameAndShortName(merchant.getMerchantName(), merchant.getMerchantShortName());
        merchantValidator.validateWebsite(merchant.getWebsite());
        merchantValidator.validatePhoneNumber(merchant.getPhone());
    }

    /**
     *
     * @param populatedMerchant
     * @throws IOException
     * @throws MOSException
     */
    @Override
    public void saveMerchant(Merchant populatedMerchant) throws IOException, MOSException {
        merchantDao.saveMerchant(populatedMerchant);
    }

    /**
     *
     * @param merchant
     * @throws MOSException
     */
    @Override
    public void duplicateMerchantCheck(Merchant merchant) throws MOSException {
        merchantDao.getMerchant(merchant.getMerchantName());
    }

    /**
     *
     * @return
     * @throws IOException
     * @throws MOSException
     */
    @Override
    public List<Merchant> listMerchants() throws IOException, MOSException {
        List<Merchant> merchantList = merchantDao.listMerchants();
        return merchantList;
    }

}
