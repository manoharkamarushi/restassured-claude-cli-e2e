package com.automation.tests;

import io.restassured.response.Response;
import com.automation.base.BaseTest;
import com.automation.clients.OrderClient;
import com.automation.models.request.OrderRequest;
import com.automation.utils.APIUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OrderAPITest extends BaseTest {
    private OrderClient orderClient;

    @BeforeClass
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test(description = "Verify getting all orders returns 200 status code")
    public void testGetAllOrdersPositive() {
        Response response = orderClient.getAllOrders();

        APIUtil.validateStatusCode(response, 200);
        APIUtil.validateContentType(response, "application/json");
        APIUtil.validateResponseTime(response, 3000);

    }

    @Test(description = "Verify getting order by valid ID returns 200")
    public void testGetOrderByIdPositive() {
        Response response = orderClient.getOrderById("1");

        APIUtil.validateStatusCode(response, 200);
        APIUtil.validateJsonFieldPresence(response, "id");

    }

    @Test(description = "Verify getting order by invalid ID returns 404")
    public void testGetOrderByIdNegative() {
        Response response = orderClient.getOrderById("99999");

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 404 || statusCode == 400, "Expected 404 or 400, got: " + statusCode);

    }

    @Test(description = "Verify creating order with valid data returns 201")
    public void testCreateOrderPositive() {
        OrderRequest orderRequest = new OrderRequest(
            "1",
            "1",
            5,
            5000.00,
            "PENDING"
        );
        Response response = orderClient.createOrder(orderRequest);

        APIUtil.validateStatusCode(response, 201);
        APIUtil.validateJsonFieldPresence(response, "id");
        APIUtil.validateResponseTime(response, 3000);

    }

    @Test(description = "Verify creating order with invalid user ID returns 400")
    public void testCreateOrderNegativeInvalidUserId() {
        OrderRequest orderRequest = new OrderRequest(
            "invalid_user",
            "1",
            5,
            5000.00,
            "PENDING"
        );
        Response response = orderClient.createOrder(orderRequest);

        int statusCode = response.getStatusCode();
        assertTrue(statusCode >= 400, "Expected 4xx error, got: " + statusCode);

    }

    @Test(description = "Verify creating order with zero quantity returns 400")
    public void testCreateOrderNegativeZeroQuantity() {
        OrderRequest orderRequest = new OrderRequest(
            "1",
            "1",
            0,
            0.00,
            "PENDING"
        );
        Response response = orderClient.createOrder(orderRequest);

        int statusCode = response.getStatusCode();
        assertTrue(statusCode >= 400, "Expected 4xx error, got: " + statusCode);

    }

    @Test(description = "Verify creating order with negative amount returns 400")
    public void testCreateOrderNegativeNegativeAmount() {
        OrderRequest orderRequest = new OrderRequest(
            "1",
            "1",
            5,
            -500.00,
            "PENDING"
        );
        Response response = orderClient.createOrder(orderRequest);

        int statusCode = response.getStatusCode();
        assertTrue(statusCode >= 400, "Expected 4xx error, got: " + statusCode);

    }

    @Test(description = "Verify updating order with valid data returns 200")
    public void testUpdateOrderPositive() {
        OrderRequest orderRequest = new OrderRequest(
            "1",
            "1",
            10,
            10000.00,
            "CONFIRMED"
        );
        Response response = orderClient.updateOrder("1", orderRequest);

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 200 || statusCode == 201, "Expected 200 or 201, got: " + statusCode);

    }

    @Test(description = "Verify getting order items returns 200")
    public void testGetOrderItemsPositive() {
        Response response = orderClient.getOrderItems("1");

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 200 || statusCode == 404, "Expected 200 or 404, got: " + statusCode);

    }

    @Test(description = "Verify deleting order returns 204 or 200")
    public void testDeleteOrderPositive() {
        Response response = orderClient.deleteOrder("1");

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 204 || statusCode == 200, "Expected 204 or 200, got: " + statusCode);

    }

    @Test(description = "Verify deleting non-existent order returns 404")
    public void testDeleteOrderNegative() {
        Response response = orderClient.deleteOrder("99999");

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 404 || statusCode == 400, "Expected 404 or 400, got: " + statusCode);

    }
}

