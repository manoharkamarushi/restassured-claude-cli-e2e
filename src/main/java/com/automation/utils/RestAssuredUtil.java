package com.automation.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class RestAssuredUtil {
    private static final Logger logger = LoggerFactory.getLogger(RestAssuredUtil.class);

    public static Response executeGet(RequestSpecification spec, String endpoint) {
        logger.info("Executing GET request: " + endpoint);
        return spec.when().get(endpoint).then().extract().response();
    }

    public static Response executePost(RequestSpecification spec, String endpoint, Object requestBody) {
        logger.info("Executing POST request: " + endpoint);
        return spec.body(requestBody).when().post(endpoint).then().extract().response();
    }

    public static Response executePut(RequestSpecification spec, String endpoint, Object requestBody) {
        logger.info("Executing PUT request: " + endpoint);
        return spec.body(requestBody).when().put(endpoint).then().extract().response();
    }

    public static Response executePatch(RequestSpecification spec, String endpoint, Object requestBody) {
        logger.info("Executing PATCH request: " + endpoint);
        return spec.body(requestBody).when().patch(endpoint).then().extract().response();
    }

    public static Response executeDelete(RequestSpecification spec, String endpoint) {
        logger.info("Executing DELETE request: " + endpoint);
        return spec.when().delete(endpoint).then().extract().response();
    }

    public static Response executeGetWithPathParams(RequestSpecification spec, String endpoint, Map<String, String> pathParams) {
        logger.info("Executing GET request with path parameters: " + endpoint);
        return spec.pathParams(pathParams).when().get(endpoint).then().extract().response();
    }

    public static Response executePostWithPathParams(RequestSpecification spec, String endpoint, Object requestBody, Map<String, String> pathParams) {
        logger.info("Executing POST request with path parameters: " + endpoint);
        return spec.body(requestBody).pathParams(pathParams).when().post(endpoint).then().extract().response();
    }

    public static Response executeGetWithQueryParams(RequestSpecification spec, String endpoint, Map<String, String> queryParams) {
        logger.info("Executing GET request with query parameters: " + endpoint);
        return spec.queryParams(queryParams).when().get(endpoint).then().extract().response();
    }

    public static Response executePostWithQueryParams(RequestSpecification spec, String endpoint, Object requestBody, Map<String, String> queryParams) {
        logger.info("Executing POST request with query parameters: " + endpoint);
        return spec.body(requestBody).queryParams(queryParams).when().post(endpoint).then().extract().response();
    }

    public static Response executePutWithPathParams(RequestSpecification spec, String endpoint, Object requestBody, Map<String, String> pathParams) {
        logger.info("Executing PUT request with path parameters: " + endpoint);
        return spec.body(requestBody).pathParams(pathParams).when().put(endpoint).then().extract().response();
    }

    public static Response executeDelete(RequestSpecification spec, String endpoint, Map<String, String> pathParams) {
        logger.info("Executing DELETE request with path parameters: " + endpoint);
        return spec.pathParams(pathParams).when().delete(endpoint).then().extract().response();
    }
}
