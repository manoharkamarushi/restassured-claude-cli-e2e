package com.automation.tests;

import io.restassured.response.Response;
import com.automation.base.BaseTest;
import com.automation.clients.UserClient;
import com.automation.models.request.UserRequest;
import com.automation.utils.APIUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserAPITest extends BaseTest {
    private UserClient userClient;

    @BeforeClass
    public void setUp() {
        userClient = new UserClient();
    }

    @Test(description = "Verify getting all users returns 200 status code")
    public void testGetAllUsersPositive() {
        Response response = userClient.getAllUsers();

        APIUtil.validateStatusCode(response, 200);
        APIUtil.validateContentType(response, "application/json");
        APIUtil.validateResponseTime(response, 3000);

    }

    @Test(description = "Verify getting user by valid ID returns 200 status code")
    public void testGetUserByIdPositive() {
        Response response = userClient.getUserById("1");

        APIUtil.validateStatusCode(response, 200);
        APIUtil.validateContentType(response, "application/json");
        APIUtil.validateJsonFieldPresence(response, "id");

    }

    @Test(description = "Verify getting user by invalid ID returns 404")
    public void testGetUserByIdNegative() {
        Response response = userClient.getUserById("99999");

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 404 || statusCode == 400, "Expected 404 or 400, got: " + statusCode);

    }

    @Test(description = "Verify creating user with valid data returns 201")
    public void testCreateUserPositive() {
        UserRequest userRequest = new UserRequest("Tejas Kumar", "QA Engineer", "tejas@example.com");
        Response response = userClient.createUser(userRequest);

        APIUtil.validateStatusCode(response, 201);
        APIUtil.validateJsonFieldPresence(response, "id");
        APIUtil.validateJsonFieldNotEmpty(response, "name");
        APIUtil.validateResponseTime(response, 3000);

    }

    @Test(description = "Verify creating user with missing required field returns 400")
    public void testCreateUserNegativeMissingField() {
        UserRequest userRequest = new UserRequest(null, "QA Engineer");
        Response response = userClient.createUser(userRequest);

        int statusCode = response.getStatusCode();
        assertTrue(statusCode >= 400, "Expected 4xx error, got: " + statusCode);

    }

    @Test(description = "Verify updating user with valid data returns 200")
    public void testUpdateUserPositive() {
        UserRequest userRequest = new UserRequest("Tejas Kumar Updated", "Senior QA Engineer");
        Response response = userClient.updateUser("1", userRequest);

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 200 || statusCode == 201, "Expected 200 or 201, got: " + statusCode);

    }

    @Test(description = "Verify deleting existing user returns 204 or 200")
    public void testDeleteUserPositive() {
        Response response = userClient.deleteUser("1");

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 204 || statusCode == 200, "Expected 204 or 200, got: " + statusCode);

    }

    @Test(description = "Verify deleting non-existent user returns 404")
    public void testDeleteUserNegative() {
        Response response = userClient.deleteUser("99999");

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 404 || statusCode == 400, "Expected 404 or 400, got: " + statusCode);

    }
}

