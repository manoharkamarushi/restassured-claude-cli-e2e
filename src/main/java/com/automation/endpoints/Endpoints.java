package com.automation.endpoints;

public class Endpoints {

    // Auth Endpoints
    public static final String LOGIN = "/auth/login";
    public static final String LOGOUT = "/auth/logout";
    public static final String REGISTER = "/auth/register";
    public static final String REFRESH_TOKEN = "/auth/refresh-token";

    // User Endpoints
    public static final String USERS = "/users";
    public static final String GET_USER = "/users/{id}";
    public static final String CREATE_USER = "/users";
    public static final String UPDATE_USER = "/users/{id}";
    public static final String DELETE_USER = "/users/{id}";
    public static final String SEARCH_USERS = "/users/search";

    // Product Endpoints
    public static final String PRODUCTS = "/products";
    public static final String GET_PRODUCT = "/products/{id}";
    public static final String CREATE_PRODUCT = "/products";
    public static final String UPDATE_PRODUCT = "/products/{id}";
    public static final String DELETE_PRODUCT = "/products/{id}";
    public static final String SEARCH_PRODUCTS = "/products/search";

    // Order Endpoints
    public static final String ORDERS = "/orders";
    public static final String GET_ORDER = "/orders/{id}";
    public static final String CREATE_ORDER = "/orders";
    public static final String UPDATE_ORDER = "/orders/{id}";
    public static final String DELETE_ORDER = "/orders/{id}";
    public static final String GET_ORDER_ITEMS = "/orders/{id}/items";

    // CRUD Endpoints (Generic)
    public static final String CREATE = "/create";
    public static final String READ = "/read/{id}";
    public static final String UPDATE = "/update/{id}";
    public static final String DELETE = "/delete/{id}";
}
