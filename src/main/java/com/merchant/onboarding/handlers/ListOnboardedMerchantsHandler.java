package com.merchant.onboarding.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.merchant.onboarding.exception.MOSException;
import com.merchant.onboarding.model.Merchant;
import com.merchant.onboarding.response.MOSApiGatewayResponse;
import com.merchant.onboarding.response.MOSResponseEnum;
import com.merchant.onboarding.response.Response;
import com.merchant.onboarding.service.MerchantService;
import com.merchant.onboarding.service.MerchantServiceImpl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ListOnboardedMerchantsHandler implements RequestHandler<Map<String, Object>, MOSApiGatewayResponse> {

    MerchantService merchantService;

    public ListOnboardedMerchantsHandler() {
        merchantService = new MerchantServiceImpl();
    }

    /**
     *
     * @param input
     * @param context
     * @return
     */
    @Override
    public MOSApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        Response response;
        LambdaLogger logger = context.getLogger();
        try {
            // get all merchants
            List<Merchant> merchants = merchantService.listMerchants();

            // send the response back
            if (!merchants.isEmpty()) {
                logger.log(merchants.size() + " merchant data Found");
                return MOSApiGatewayResponse.builder()
                        .setStatusCode(200)
                        .setObjectBody(merchants)
                        .setHeaders(Collections.singletonMap("X-Powered-By", "Merchant Onboarding System"))
                        .build();
            } else {
                response = new Response("FAILED",
                        MOSResponseEnum.EMPTY_DATABASE.getCode(),
                        MOSResponseEnum.EMPTY_DATABASE.getDesc(), "No Merchants Data Found");
            }
        } catch (IOException | MOSException ex) {
            logger.log(ex.getMessage() + ex);
            response = new Response("FAILED",
                    MOSResponseEnum.MERCHANT_FETCHING_ERROR.getCode(),
                    MOSResponseEnum.MERCHANT_FETCHING_ERROR.getDesc(), ex.getMessage());
        }
        return MOSApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(response)
                .setHeaders(Collections.singletonMap("X-Powered-By", "Merchant Onboarding System"))
                .build();
    }
}
