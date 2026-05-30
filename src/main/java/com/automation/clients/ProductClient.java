package com.automation.clients;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.automation.base.RequestSpecBuilderUtil;
import com.automation.endpoints.Endpoints;
import com.automation.models.request.ProductRequest;
import com.automation.utils.RestAssuredUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ProductClient {
    private static final Logger logger = LoggerFactory.getLogger(ProductClient.class);

    public Response getAllProducts() {
        logger.info("Getting all products");
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        return RestAssuredUtil.executeGet(spec, Endpoints.PRODUCTS);
    }

    public Response getProductById(String productId) {
        logger.info("Getting product by ID: " + productId);
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", productId);
        return RestAssuredUtil.executeGetWithPathParams(spec, Endpoints.GET_PRODUCT, pathParams);
    }

    public Response createProduct(ProductRequest productRequest) {
        logger.info("Creating product: " + productRequest.getName());
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        return RestAssuredUtil.executePost(spec, Endpoints.CREATE_PRODUCT, productRequest);
    }

    public Response updateProduct(String productId, ProductRequest productRequest) {
        logger.info("Updating product ID: " + productId);
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", productId);
        return RestAssuredUtil.executePutWithPathParams(spec, Endpoints.UPDATE_PRODUCT, productRequest, pathParams);
    }

    public Response deleteProduct(String productId) {
        logger.info("Deleting product ID: " + productId);
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", productId);
        return RestAssuredUtil.executeDelete(spec, Endpoints.DELETE_PRODUCT, pathParams);
    }

    public Response searchProducts(Map<String, String> queryParams) {
        logger.info("Searching products with params: " + queryParams);
        RequestSpecification spec = RequestSpecBuilderUtil.getRequestSpec();
        return RestAssuredUtil.executeGetWithQueryParams(spec, Endpoints.SEARCH_PRODUCTS, queryParams);
    }
}
