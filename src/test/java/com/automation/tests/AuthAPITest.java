package com.automation.tests;

import io.restassured.response.Response;
import com.automation.base.BaseTest;
import com.automation.clients.AuthClient;
import com.automation.models.request.LoginRequest;
import com.automation.models.request.RegisterRequest;
import com.automation.utils.APIUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AuthAPITest extends BaseTest {
    private AuthClient authClient;

    @BeforeClass
    public void setUp() {
        authClient = new AuthClient();
    }

    @Test(description = "Verify user login with valid credentials returns 200")
    public void testLoginPositive() {
        test.info("Logging in with valid credentials");
        LoginRequest loginRequest = new LoginRequest("user@example.com", "password123");
        Response response = authClient.login(loginRequest);

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 200 || statusCode == 201, "Expected 200 or 201, got: " + statusCode);
        APIUtil.validateContentType(response, "application/json");
        APIUtil.validateResponseTime(response, 3000);

        test.pass("User login successful");
    }

    @Test(description = "Verify login with invalid email returns 401 or 400")
    public void testLoginNegativeInvalidEmail() {
        test.info("Logging in with invalid email");
        LoginRequest loginRequest = new LoginRequest("invalidemail", "password123");
        Response response = authClient.login(loginRequest);

        int statusCode = response.getStatusCode();
        assertTrue(statusCode >= 400, "Expected 4xx error, got: " + statusCode);

        test.pass("Invalid email validation working correctly");
    }

    @Test(description = "Verify login with empty password returns 400 or 401")
    public void testLoginNegativeEmptyPassword() {
        test.info("Logging in with empty password");
        LoginRequest loginRequest = new LoginRequest("user@example.com", "");
        Response response = authClient.login(loginRequest);

        int statusCode = response.getStatusCode();
        assertTrue(statusCode >= 400, "Expected 4xx error, got: " + statusCode);

        test.pass("Empty password validation working correctly");
    }

    @Test(description = "Verify login with incorrect password returns 401")
    public void testLoginNegativeIncorrectPassword() {
        test.info("Logging in with incorrect password");
        LoginRequest loginRequest = new LoginRequest("user@example.com", "wrongpassword");
        Response response = authClient.login(loginRequest);

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 401 || statusCode == 400, "Expected 401 or 400, got: " + statusCode);

        test.pass("Incorrect password validation working correctly");
    }

    @Test(description = "Verify user registration with valid data returns 201")
    public void testRegisterPositive() {
        test.info("Registering new user");
        RegisterRequest registerRequest = new RegisterRequest(
            "New User",
            "newuser@example.com",
            "password123",
            "password123"
        );
        Response response = authClient.register(registerRequest);

        APIUtil.validateStatusCode(response, 201);
        APIUtil.validateJsonFieldPresence(response, "id");
        APIUtil.validateResponseTime(response, 3000);

        test.pass("User registration successful");
    }

    @Test(description = "Verify registration with existing email returns 409 or 400")
    public void testRegisterNegativeDuplicateEmail() {
        test.info("Registering with duplicate email");
        RegisterRequest registerRequest = new RegisterRequest(
            "Existing User",
            "existing@example.com",
            "password123",
            "password123"
        );
        Response response = authClient.register(registerRequest);

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 409 || statusCode == 400, "Expected 409 or 400, got: " + statusCode);

        test.pass("Duplicate email validation working correctly");
    }

    @Test(description = "Verify registration with mismatched passwords returns 400")
    public void testRegisterNegativeMismatchedPasswords() {
        test.info("Registering with mismatched passwords");
        RegisterRequest registerRequest = new RegisterRequest(
            "New User",
            "newuser@example.com",
            "password123",
            "password456"
        );
        Response response = authClient.register(registerRequest);

        int statusCode = response.getStatusCode();
        assertTrue(statusCode >= 400, "Expected 4xx error, got: " + statusCode);

        test.pass("Password mismatch validation working correctly");
    }

    @Test(description = "Verify registration with weak password returns 400")
    public void testRegisterNegativeWeakPassword() {
        test.info("Registering with weak password");
        RegisterRequest registerRequest = new RegisterRequest(
            "New User",
            "newuser@example.com",
            "123",
            "123"
        );
        Response response = authClient.register(registerRequest);

        int statusCode = response.getStatusCode();
        assertTrue(statusCode >= 400, "Expected 4xx error for weak password, got: " + statusCode);

        test.pass("Weak password validation working correctly");
    }

    @Test(description = "Verify user logout returns 200")
    public void testLogoutPositive() {
        test.info("Logging out user");
        Response response = authClient.logout();

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 200 || statusCode == 204, "Expected 200 or 204, got: " + statusCode);

        test.pass("User logout successful");
    }

    @Test(description = "Verify token refresh with valid token returns 200")
    public void testRefreshTokenPositive() {
        test.info("Refreshing token");
        String validToken = "sample_valid_token";
        Response response = authClient.refreshToken(validToken);

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 200 || statusCode == 201, "Expected 200 or 201, got: " + statusCode);

        test.pass("Token refresh successful");
    }

    @Test(description = "Verify token refresh with invalid token returns 401")
    public void testRefreshTokenNegativeInvalidToken() {
        test.info("Refreshing with invalid token");
        String invalidToken = "invalid_expired_token";
        Response response = authClient.refreshToken(invalidToken);

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 401 || statusCode == 400, "Expected 401 or 400, got: " + statusCode);

        test.pass("Invalid token handling working correctly");
    }
}
