package com.automation.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class OrderRequest {
    @JsonProperty("user_id")
    @SerializedName("user_id")
    private String userId;

    @JsonProperty("product_id")
    @SerializedName("product_id")
    private String productId;

    @JsonProperty("quantity")
    @SerializedName("quantity")
    private int quantity;

    @JsonProperty("total_amount")
    @SerializedName("total_amount")
    private double totalAmount;

    @JsonProperty("status")
    @SerializedName("status")
    private String status;

    public OrderRequest() {
    }

    public OrderRequest(String userId, String productId, int quantity, double totalAmount, String status) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
