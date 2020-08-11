package com.merchant.onboarding.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Map;

public class MOSApiGatewayResponse {

    private final int statusCode;
    private final String body;
    private final Map<String, String> headers;

    public MOSApiGatewayResponse(int statusCode, String body, Map<String, String> headers) {
        this.statusCode = statusCode;
        this.body = body;
        this.headers = headers;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private static final Logger logger = Logger.getLogger(MOSApiGatewayResponse.Builder.class);

        private static final ObjectMapper objectMapper = new ObjectMapper();

        private int statusCode = 200;
        private Map<String, String> headers = Collections.emptyMap();
        private Object objectBody;

        public Builder setStatusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder setHeaders(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Builder setObjectBody(Object objectBody) {
            this.objectBody = objectBody;
            return this;
        }

        public MOSApiGatewayResponse build() {
            String body = null;
            if (objectBody != null) {
                try {
                    body = objectMapper.writeValueAsString(objectBody);
                } catch (JsonProcessingException e) {
                    logger.error("failed to serialize object", e);
                    throw new RuntimeException(e);
                }
            }
            return new MOSApiGatewayResponse(statusCode, body, headers);
        }
    }
}
