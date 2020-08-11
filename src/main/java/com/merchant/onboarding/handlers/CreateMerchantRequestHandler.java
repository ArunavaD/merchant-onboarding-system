package com.merchant.onboarding.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.merchant.onboarding.exception.MOSException;
import com.merchant.onboarding.model.Merchant;
import com.merchant.onboarding.response.MOSApiGatewayResponse;
import com.merchant.onboarding.response.MOSResponseEnum;
import com.merchant.onboarding.response.Response;
import com.merchant.onboarding.service.MerchantService;
import com.merchant.onboarding.service.MerchantServiceImpl;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class CreateMerchantRequestHandler implements RequestHandler<Map<String, Object>, MOSApiGatewayResponse> {

    MerchantService merchantService;

    public CreateMerchantRequestHandler() {
        merchantService = new MerchantServiceImpl();
    }

    /**
     * @param input
     * @param context
     * @return
     */
    @Override
    public MOSApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        Response response;
        LambdaLogger logger = context.getLogger();
        try {
            JsonNode body = new ObjectMapper().readTree((String) input.get("body"));

            Merchant populatedMerchant = merchantService.populateMerchant(body);
            logger.log("Merchant Data Populated Successfully");

            merchantService.duplicateMerchantCheck(populatedMerchant);
            merchantService.validateMerchantData(populatedMerchant);
            logger.log("Merchant Data Validated Successfully");

            merchantService.saveMerchant(populatedMerchant);
            logger.log("Merchant Data Saved Successfully");

            response = new Response("SUCCESS",
                    MOSResponseEnum.MERCHANT_CREATED.getCode(),
                    MOSResponseEnum.MERCHANT_CREATED.getDesc(), "Successfully Saved");

        } catch (IOException | MOSException ex) {
            logger.log(ex.getMessage() + ex);
            response = new Response("FAILED",
                    MOSResponseEnum.GENERIC_ERROR.getCode(),
                    MOSResponseEnum.GENERIC_ERROR.getDesc(), ex.getMessage());
        }
        return MOSApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(response)
                .setHeaders(Collections.singletonMap("X-Powered-By", "Merchant Onboarding System"))
                .build();
    }
}
