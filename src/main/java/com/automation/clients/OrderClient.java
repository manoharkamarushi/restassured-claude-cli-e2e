package com.automation.clients;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.automation.base.RequestSpecBuilderUtil;
import com.automation.endpoints.Endpoints;
import com.automation.models.request.OrderRequest;
import com.automation.utils.RestAssuredUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class OrderClient {
    private static final Logger logger = LoggerFactory.getLogger(OrderClient.class);

    public Response getAllOrders() {
        logger.info("Getting all orders");
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        return RestAssuredUtil.executeGet(spec, Endpoints.ORDERS);
    }

    public Response getOrderById(String orderId) {
        logger.info("Getting order by ID: " + orderId);
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", orderId);
        return RestAssuredUtil.executeGetWithPathParams(spec, Endpoints.GET_ORDER, pathParams);
    }

    public Response createOrder(OrderRequest orderRequest) {
        logger.info("Creating order for user: " + orderRequest.getUserId());
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        return RestAssuredUtil.executePost(spec, Endpoints.CREATE_ORDER, orderRequest);
    }

    public Response updateOrder(String orderId, OrderRequest orderRequest) {
        logger.info("Updating order ID: " + orderId);
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", orderId);
        return RestAssuredUtil.executePutWithPathParams(spec, Endpoints.UPDATE_ORDER, orderRequest, pathParams);
    }

    public Response deleteOrder(String orderId) {
        logger.info("Deleting order ID: " + orderId);
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", orderId);
        return RestAssuredUtil.executeDelete(spec, Endpoints.DELETE_ORDER, pathParams);
    }

    public Response getOrderItems(String orderId) {
        logger.info("Getting order items for order ID: " + orderId);
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", orderId);
        return RestAssuredUtil.executeGetWithPathParams(spec, Endpoints.GET_ORDER_ITEMS, pathParams);
    }
}
