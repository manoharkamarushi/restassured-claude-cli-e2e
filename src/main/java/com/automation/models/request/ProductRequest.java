package com.automation.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class ProductRequest {
    @JsonProperty("name")
    @SerializedName("name")
    private String name;

    @JsonProperty("description")
    @SerializedName("description")
    private String description;

    @JsonProperty("price")
    @SerializedName("price")
    private double price;

    @JsonProperty("quantity")
    @SerializedName("quantity")
    private int quantity;

    @JsonProperty("category")
    @SerializedName("category")
    private String category;

    public ProductRequest() {
    }

    public ProductRequest(String name, String description, double price, int quantity, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
