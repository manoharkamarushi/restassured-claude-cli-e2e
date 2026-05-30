package com.automation.clients;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.automation.base.RequestSpecBuilderUtil;
import com.automation.endpoints.Endpoints;
import com.automation.models.request.UserRequest;
import com.automation.utils.RestAssuredUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class UserClient {
    private static final Logger logger = LoggerFactory.getLogger(UserClient.class);

    public Response getAllUsers() {
        logger.info("Getting all users");
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        return RestAssuredUtil.executeGet(spec, Endpoints.USERS);
    }

    public Response getUserById(String userId) {
        logger.info("Getting user by ID: " + userId);
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", userId);
        return RestAssuredUtil.executeGetWithPathParams(spec, Endpoints.GET_USER, pathParams);
    }

    public Response createUser(UserRequest userRequest) {
        logger.info("Creating user: " + userRequest.getName());
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        return RestAssuredUtil.executePost(spec, Endpoints.CREATE_USER, userRequest);
    }

    public Response updateUser(String userId, UserRequest userRequest) {
        logger.info("Updating user ID: " + userId);
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", userId);
        return RestAssuredUtil.executePutWithPathParams(spec, Endpoints.UPDATE_USER, userRequest, pathParams);
    }

    public Response deleteUser(String userId) {
        logger.info("Deleting user ID: " + userId);
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", userId);
        return RestAssuredUtil.executeDelete(spec, Endpoints.DELETE_USER, pathParams);
    }

    public Response searchUsers(Map<String, String> queryParams) {
        logger.info("Searching users with params: " + queryParams);
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        return RestAssuredUtil.executeGetWithQueryParams(spec, Endpoints.SEARCH_USERS, queryParams);
    }
}
