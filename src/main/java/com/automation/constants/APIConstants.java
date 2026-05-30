package com.automation.constants;

public class APIConstants {

    // HTTP Status Codes
    public static final int STATUS_OK = 200;
    public static final int STATUS_CREATED = 201;
    public static final int STATUS_ACCEPTED = 202;
    public static final int STATUS_NO_CONTENT = 204;
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int STATUS_UNAUTHORIZED = 401;
    public static final int STATUS_FORBIDDEN = 403;
    public static final int STATUS_NOT_FOUND = 404;
    public static final int STATUS_CONFLICT = 409;
    public static final int STATUS_INTERNAL_SERVER_ERROR = 500;
    public static final int STATUS_SERVICE_UNAVAILABLE = 503;

    // HTTP Headers
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final String HEADER_API_KEY = "X-API-Key";

    // Content Types
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_XML = "application/xml";
    public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";

    // Authentication Types
    public static final String AUTH_TYPE_BEARER = "Bearer";
    public static final String AUTH_TYPE_BASIC = "Basic";
    public static final String AUTH_TYPE_OAUTH2 = "Bearer";
    public static final String AUTH_TYPE_API_KEY = "X-API-Key";

    // Response Time Thresholds (in milliseconds)
    public static final long RESPONSE_TIME_THRESHOLD_FAST = 1000;
    public static final long RESPONSE_TIME_THRESHOLD_NORMAL = 3000;
    public static final long RESPONSE_TIME_THRESHOLD_SLOW = 5000;

    // Default Values
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String DEFAULT_TIMEZONE = "UTC";
    public static final int DEFAULT_REQUEST_TIMEOUT = 5000;
    public static final int DEFAULT_MAX_RETRY_COUNT = 3;

    // JSON Path Constants
    public static final String JSON_PATH_ID = "id";
    public static final String JSON_PATH_MESSAGE = "message";
    public static final String JSON_PATH_DATA = "data";
    public static final String JSON_PATH_STATUS = "status";
    public static final String JSON_PATH_ERROR = "error";

    // Test Data
    public static final String VALID_EMAIL = "test@example.com";
    public static final String INVALID_EMAIL = "invalid-email";
    public static final String VALID_PASSWORD = "Test@1234";
    public static final String WEAK_PASSWORD = "123";
}
