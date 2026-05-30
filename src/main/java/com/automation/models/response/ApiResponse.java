package com.automation.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    @JsonProperty("id")
    @SerializedName("id")
    private String id;

    @JsonProperty("message")
    @SerializedName("message")
    private String message;

    @JsonProperty("success")
    @SerializedName("success")
    private boolean success;

    @JsonProperty("timestamp")
    @SerializedName("timestamp")
    private String timestamp;

    public ApiResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
