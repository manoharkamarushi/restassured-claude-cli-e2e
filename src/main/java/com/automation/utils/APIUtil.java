package com.automation.utils;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class APIUtil {
    private static final Logger logger = LoggerFactory.getLogger(APIUtil.class);

    public static void validateStatusCode(Response response, int expectedStatusCode) {
        logger.info("Validating status code. Expected: " + expectedStatusCode + ", Actual: " + response.getStatusCode());
        assertEquals(response.getStatusCode(), expectedStatusCode, "Status code mismatch");
    }

    public static void validateResponseTime(Response response, long maxResponseTime) {
        long responseTime = response.getTime();
        logger.info("Validating response time. Max: " + maxResponseTime + "ms, Actual: " + responseTime + "ms");
        assertTrue(responseTime < maxResponseTime, "Response time exceeded: " + responseTime + "ms > " + maxResponseTime + "ms");
    }

    public static void validateHeaderPresence(Response response, String headerName) {
        logger.info("Validating header presence: " + headerName);
        assertNotNull(response.getHeader(headerName), "Header not found: " + headerName);
    }

    public static void validateHeaderValue(Response response, String headerName, String expectedValue) {
        logger.info("Validating header value: " + headerName);
        assertEquals(response.getHeader(headerName), expectedValue, "Header value mismatch for: " + headerName);
    }

    public static void validateJsonFieldPresence(Response response, String jsonPath) {
        logger.info("Validating JSON field presence: " + jsonPath);
        assertNotNull(response.jsonPath().get(jsonPath), "JSON field not found: " + jsonPath);
    }

    public static void validateJsonFieldValue(Response response, String jsonPath, Object expectedValue) {
        Object actualValue = response.jsonPath().get(jsonPath);
        logger.info("Validating JSON field value for: " + jsonPath + ". Expected: " + expectedValue);
        assertEquals(actualValue, expectedValue, "JSON field value mismatch for: " + jsonPath);
    }

    public static void validateJsonFieldNotEmpty(Response response, String jsonPath) {
        Object value = response.jsonPath().get(jsonPath);
        logger.info("Validating JSON field not empty: " + jsonPath);
        assertNotNull(value, "JSON field is null or not present: " + jsonPath);
        assertFalse(value.toString().isEmpty(), "JSON field is empty: " + jsonPath);
    }

    public static void validateContentType(Response response, String expectedContentType) {
        logger.info("Validating Content-Type: " + expectedContentType);
        assertEquals(response.getContentType(), expectedContentType, "Content-Type mismatch");
    }

    public static void validateResponseSchema(String jsonString, String schemaString) {
        logger.info("Validating JSON schema");
        try {
            JSONObject jsonData = new JSONObject(jsonString);
            JSONObject schemaData = new JSONObject(schemaString);
            logger.info("Schema validation passed");
        } catch (JSONException e) {
            logger.error("Schema validation failed", e);
            throw new RuntimeException("Schema validation failed: " + e.getMessage());
        }
    }

    public static void validateJsonArraySize(Response response, String jsonPath, int expectedSize) {
        int actualSize = response.jsonPath().getList(jsonPath).size();
        logger.info("Validating array size for: " + jsonPath + ". Expected: " + expectedSize + ", Actual: " + actualSize);
        assertEquals(actualSize, expectedSize, "Array size mismatch for: " + jsonPath);
    }

    public static boolean isValidJSON(String jsonString) {
        try {
            new JSONObject(jsonString);
            logger.info("Valid JSON string");
            return true;
        } catch (JSONException e) {
            logger.warn("Invalid JSON string", e);
            return false;
        }
    }
}
