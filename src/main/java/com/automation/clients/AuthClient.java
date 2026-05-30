package com.automation.clients;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.automation.base.RequestSpecBuilderUtil;
import com.automation.endpoints.Endpoints;
import com.automation.models.request.LoginRequest;
import com.automation.models.request.RegisterRequest;
import com.automation.utils.RestAssuredUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthClient {
    private static final Logger logger = LoggerFactory.getLogger(AuthClient.class);

    public Response login(LoginRequest loginRequest) {
        logger.info("Logging in user: " + loginRequest.getEmail());
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        return RestAssuredUtil.executePost(spec, Endpoints.LOGIN, loginRequest);
    }

    public Response register(RegisterRequest registerRequest) {
        logger.info("Registering new user: " + registerRequest.getEmail());
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        return RestAssuredUtil.executePost(spec, Endpoints.REGISTER, registerRequest);
    }

    public Response logout() {
        logger.info("Logging out user");
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        return RestAssuredUtil.executePost(spec, Endpoints.LOGOUT, null);
    }

    public Response refreshToken(String token) {
        logger.info("Refreshing token");
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpecWithAuth(token);
        return RestAssuredUtil.executePost(spec, Endpoints.REFRESH_TOKEN, null);
    }
}
