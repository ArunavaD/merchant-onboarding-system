package com.merchant.onboarding.response;

public enum MOSResponseEnum {

    MERCHANT_CREATED("MOS000", "Merchant is onboarded successfully"),
    GENERIC_ERROR("MOS010", "Merchant Onboarding Failed"),
    EMPTY_DATABASE("MOS020", "No merchant data found in database"),
    MERCHANT_FETCHING_ERROR("MOS030", "Error fetching merchant data from database"),
    DUPLICATE_ENTRY_ERROR("MOS040", "Merchant Already Onboarded Previous");

    private String code;
    private String desc;

    MOSResponseEnum(String code, String desc) {
        this.desc = desc;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
