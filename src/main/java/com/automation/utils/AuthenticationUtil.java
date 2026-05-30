package com.automation.utils;

import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class AuthenticationUtil {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationUtil.class);

    public static RequestSpecification addBearerToken(RequestSpecification spec, String token) {
        logger.info("Adding Bearer token to request specification");
        return spec.header("Authorization", "Bearer " + token);
    }

    public static RequestSpecification addBasicAuth(RequestSpecification spec, String username, String password) {
        logger.info("Adding Basic authentication to request specification");
        return spec.auth().basic(username, password);
    }

    public static RequestSpecification addApiKey(RequestSpecification spec, String apiKey) {
        logger.info("Adding API Key to request specification");
        return spec.header("X-API-Key", apiKey);
    }

    public static RequestSpecification addOAuth2Token(RequestSpecification spec, String token) {
        logger.info("Adding OAuth2 token to request specification");
        return spec.header("Authorization", "Bearer " + token);
    }

    public static RequestSpecification addCustomHeader(RequestSpecification spec, String headerName, String headerValue) {
        logger.info("Adding custom header: " + headerName);
        return spec.header(headerName, headerValue);
    }

    public static String generateBearerTokenFromCredentials(String tokenEndpoint, String clientId, String clientSecret) {
        logger.info("Generating Bearer token from credentials");
        try {
            String response = given()
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("client_id", clientId)
                    .formParam("client_secret", clientSecret)
                    .formParam("grant_type", "client_credentials")
                    .when()
                    .post(tokenEndpoint)
                    .then()
                    .extract()
                    .asString();
            logger.info("Token generated successfully");
            return response;
        } catch (Exception e) {
            logger.error("Failed to generate token", e);
            throw new RuntimeException("Token generation failed: " + e.getMessage());
        }
    }
}
