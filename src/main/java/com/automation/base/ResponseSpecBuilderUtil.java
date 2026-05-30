package com.automation.base;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.lessThan;

public class ResponseSpecBuilderUtil {

    public static ResponseSpecification getResponseSpec(int expectedStatusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(expectedStatusCode)
                .expectContentType("application/json")
                .build();
    }

    public static ResponseSpecification getResponseSpecWithTimeOut(int expectedStatusCode, long maxResponseTime) {
        return new ResponseSpecBuilder()
                .expectStatusCode(expectedStatusCode)
                .expectContentType("application/json")
                .expectResponseTime(lessThan(maxResponseTime))
                .build();
    }

    public static ResponseSpecification getBaseResponseSpec() {
        return new ResponseSpecBuilder()
                .expectContentType("application/json")
                .build();
    }
}
