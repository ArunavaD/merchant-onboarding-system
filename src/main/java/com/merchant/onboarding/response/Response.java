package com.merchant.onboarding.response;

public class Response {

    private final String responseMessage;
    private final String responseCode;
    private final String responseDescription;
    private final String status;

    /**
     *
     * @param status
     * @param responseCode
     * @param responseDescription
     * @param responseMessage
     */
    public Response(String status, String responseCode, String responseDescription, String responseMessage) {
        this.responseMessage = responseMessage;
        this.responseCode = responseCode;
        this.responseDescription = responseDescription;
        this.status = status;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public String getStatus() {
        return status;
    }
}
