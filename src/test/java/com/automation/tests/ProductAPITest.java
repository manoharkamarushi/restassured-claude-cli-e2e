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
        test.info("Getting all products");
        Response response = productClient.getAllProducts();

        APIUtil.validateStatusCode(response, 200);
        APIUtil.validateContentType(response, "application/json");
        APIUtil.validateResponseTime(response, 3000);

        test.pass("All products retrieved successfully");
    }

    @Test(description = "Verify getting product by valid ID returns 200")
    public void testGetProductByIdPositive() {
        test.info("Getting product with ID: 1");
        Response response = productClient.getProductById("1");

        APIUtil.validateStatusCode(response, 200);
        APIUtil.validateJsonFieldPresence(response, "id");

        test.pass("Product retrieved successfully");
    }

    @Test(description = "Verify getting product by invalid ID returns 404")
    public void testGetProductByIdNegative() {
        test.info("Getting product with invalid ID");
        Response response = productClient.getProductById("99999");

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 404 || statusCode == 400, "Expected 404 or 400, got: " + statusCode);

        test.pass("Invalid product ID handled correctly");
    }

    @Test(description = "Verify creating product with valid data returns 201")
    public void testCreateProductPositive() {
        test.info("Creating new product");
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

        test.pass("Product created successfully");
    }

    @Test(description = "Verify creating product with invalid price returns 400")
    public void testCreateProductNegativeInvalidPrice() {
        test.info("Creating product with invalid price");
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

        test.pass("Invalid price validation working correctly");
    }

    @Test(description = "Verify creating product with negative quantity returns 400")
    public void testCreateProductNegativeInvalidQuantity() {
        test.info("Creating product with negative quantity");
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

        test.pass("Invalid quantity validation working correctly");
    }

    @Test(description = "Verify updating product with valid data returns 200")
    public void testUpdateProductPositive() {
        test.info("Updating product with ID: 1");
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

        test.pass("Product updated successfully");
    }

    @Test(description = "Verify deleting product returns 204 or 200")
    public void testDeleteProductPositive() {
        test.info("Deleting product with ID: 1");
        Response response = productClient.deleteProduct("1");

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 204 || statusCode == 200, "Expected 204 or 200, got: " + statusCode);

        test.pass("Product deleted successfully");
    }

    @Test(description = "Verify deleting non-existent product returns 404")
    public void testDeleteProductNegative() {
        test.info("Deleting non-existent product");
        Response response = productClient.deleteProduct("99999");

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 404 || statusCode == 400, "Expected 404 or 400, got: " + statusCode);

        test.pass("Non-existent product deletion handled correctly");
    }
}
