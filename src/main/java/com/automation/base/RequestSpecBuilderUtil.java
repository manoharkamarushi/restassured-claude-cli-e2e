package com.automation.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestSpecBuilderUtil {
    private static final Logger logger = LoggerFactory.getLogger(RequestSpecBuilderUtil.class);

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getBaseUri())
                .setContentType("application/json")
                .setAccept("application/json")
                .build();
    }

    public static RequestSpecification getRequestSpecWithAuth(String token) {
        logger.info("Building request spec with Bearer token authentication");
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getBaseUri())
                .setContentType("application/json")
                .setAccept("application/json")
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }

    public static RequestSpecification getRequestSpecWithBasicAuth(String username, String password) {
        logger.info("Building request spec with Basic authentication");
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getBaseUri())
                .setContentType("application/json")
                .setAccept("application/json")
                .setAuth(io.restassured.RestAssured.basic(username, password))
                .build();
    }

    public static RequestSpecification getRequestSpecWithApiKey(String apiKey) {
        logger.info("Building request spec with API Key authentication");
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getBaseUri())
                .setContentType("application/json")
                .setAccept("application/json")
                .addHeader("X-API-Key", apiKey)
                .build();
    }

    public static RequestSpecification getRequestSpecWithOAuth(String bearerToken) {
        logger.info("Building request spec with OAuth2 token");
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getBaseUri())
                .setContentType("application/json")
                .setAccept("application/json")
                .addHeader("Authorization", "Bearer " + bearerToken)
                .build();
    }
}
