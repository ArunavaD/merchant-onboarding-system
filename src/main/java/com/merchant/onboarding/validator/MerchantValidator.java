package com.merchant.onboarding.validator;

import com.merchant.onboarding.exception.MOSException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MerchantValidator {

    final static List<String> ORGANISATION_NAME_LIST = new ArrayList<>(Arrays.asList("jio", "lenovo", "oneplus", "samsung", "airtel"));
    final static List<String> KYC_DOC_TYPE_LIST = new ArrayList<>(Arrays.asList("adhaar", "voter", "pan", "passport"));

    /**
     *
     * @param value
     * @return
     * @throws MOSException
     */
    public boolean validateNumber(String value) throws MOSException {
        char[] chars = value.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                throw new MOSException(value + " is invalid number format");
            }
        }
        return true;
    }

    /**
     *
     * @param legalName
     * @param kycDocName
     * @return
     * @throws MOSException
     */
    public boolean validateName(String legalName, String kycDocName) throws MOSException {
        if (!legalName.equals(kycDocName)) {
            throw new MOSException("Legal Name & KYC Doc Name are not same");
        }
        MerchantValidator mv = new MerchantValidator();
        if (!mv.validateNameFormat(legalName)) {
            throw new MOSException("Legal Name & KYC Doc Name does not have proper format");
        }
        return true;
    }

    /**
     *
     * @param org
     * @return
     * @throws MOSException
     */
    public boolean validateOrganisation(String org) throws MOSException {
        if (!ORGANISATION_NAME_LIST.contains(org.toLowerCase())) {
            throw new MOSException(org + " is not a registered Organisation");
        }
        return true;
    }

    /**
     *
     * @param kycDocType
     * @return
     * @throws MOSException
     */
    public boolean validateKycDocType(String kycDocType) throws MOSException {
        if (!KYC_DOC_TYPE_LIST.contains(kycDocType.toLowerCase())) {
            throw new MOSException(kycDocType + " is not an accepted KYC Document");
        }
        return true;
    }

    /**
     *
     * @param email
     * @return
     * @throws MOSException
     */
    public boolean validateEmail(String email) throws MOSException {
        if (email.startsWith("www.") || !email.endsWith(".com")) {
            throw new MOSException(email + " is an invalid email address");
        }
        return true;
    }

    /**
     *
     * @param name
     * @param shortName
     * @return
     * @throws MOSException
     */
    public boolean validateNameAndShortName(String name, String shortName) throws MOSException {
        if (shortName.length() >= name.length()) {
            throw new MOSException("ShortName is greater than Name");
        }
        return true;
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean validateNameFormat(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param website
     * @return
     * @throws MOSException
     */
    public boolean validateWebsite(String website) throws MOSException {
        if (!website.startsWith("www.") && !website.startsWith("https://") && !website.startsWith("http://")) {
            throw new MOSException("Website not in a proper format");
        }
        return true;
    }

    /**
     *
     * @param value
     * @return
     * @throws MOSException
     */
    public boolean validatePhoneNumber(String value) throws MOSException {
        char[] chars = value.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c) && c != '+' && c != '-') {
                throw new MOSException("Phone Number = " + value + " is not in proper format");
            }
        }
        return true;
    }
}
