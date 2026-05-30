package com.automation.tests;

import io.restassured.response.Response;
import com.automation.base.BaseTest;
import com.automation.clients.ProductClient;
import com.automation.models.request.ProductRequest;
import com.automation.utils.APIUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductAPITest extends BaseTest {
    private ProductClient productClient;

    @BeforeClass
    public void setUp() {
        productClient = new ProductClient();
    }

    @Test(description = "Verify getting all products returns 200 status code")
    public void testGetAllProductsPositive() {
        Response response = productClient.getAllProducts();

        APIUtil.validateStatusCode(response, 200);
        APIUtil.validateContentType(response, "application/json");
        APIUtil.validateResponseTime(response, 3000);

    }

    @Test(description = "Verify getting product by valid ID returns 200")
    public void testGetProductByIdPositive() {
        Response response = productClient.getProductById("1");

        APIUtil.validateStatusCode(response, 200);
        APIUtil.validateJsonFieldPresence(response, "id");

    }

    @Test(description = "Verify getting product by invalid ID returns 404")
    public void testGetProductByIdNegative() {
        Response response = productClient.getProductById("99999");

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 404 || statusCode == 400, "Expected 404 or 400, got: " + statusCode);

    }

    @Test(description = "Verify creating product with valid data returns 201")
    public void testCreateProductPositive() {
        ProductRequest productRequest = new ProductRequest(
            "Laptop",
            "High-performance laptop",
            999.99,
            10,
            "Electronics"
        );
        Response response = productClient.createProduct(productRequest);

        APIUtil.validateStatusCode(response, 201);
        APIUtil.validateJsonFieldPresence(response, "id");
        APIUtil.validateJsonFieldNotEmpty(response, "name");
        APIUtil.validateResponseTime(response, 3000);

    }

    @Test(description = "Verify creating product with invalid price returns 400")
    public void testCreateProductNegativeInvalidPrice() {
        ProductRequest productRequest = new ProductRequest(
            "Laptop",
            "High-performance laptop",
            -100,
            10,
            "Electronics"
        );
        Response response = productClient.createProduct(productRequest);

        int statusCode = response.getStatusCode();
        assertTrue(statusCode >= 400, "Expected 4xx error for invalid price, got: " + statusCode);

    }

    @Test(description = "Verify creating product with negative quantity returns 400")
    public void testCreateProductNegativeInvalidQuantity() {
        ProductRequest productRequest = new ProductRequest(
            "Laptop",
            "High-performance laptop",
            999.99,
            -5,
            "Electronics"
        );
        Response response = productClient.createProduct(productRequest);

        int statusCode = response.getStatusCode();
        assertTrue(statusCode >= 400, "Expected 4xx error for invalid quantity, got: " + statusCode);

    }

    @Test(description = "Verify updating product with valid data returns 200")
    public void testUpdateProductPositive() {
        ProductRequest productRequest = new ProductRequest(
            "Laptop Updated",
            "Updated description",
            1099.99,
            15,
            "Electronics"
        );
        Response response = productClient.updateProduct("1", productRequest);

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 200 || statusCode == 201, "Expected 200 or 201, got: " + statusCode);

    }

    @Test(description = "Verify deleting product returns 204 or 200")
    public void testDeleteProductPositive() {
        Response response = productClient.deleteProduct("1");

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 204 || statusCode == 200, "Expected 204 or 200, got: " + statusCode);

    }

    @Test(description = "Verify deleting non-existent product returns 404")
    public void testDeleteProductNegative() {
        Response response = productClient.deleteProduct("99999");

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 404 || statusCode == 400, "Expected 404 or 400, got: " + statusCode);

    }
}

