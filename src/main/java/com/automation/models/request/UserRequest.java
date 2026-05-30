package com.automation.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class UserRequest {
    @JsonProperty("name")
    @SerializedName("name")
    private String name;

    @JsonProperty("job")
    @SerializedName("job")
    private String job;

    @JsonProperty("email")
    @SerializedName("email")
    private String email;

    public UserRequest() {
    }

    public UserRequest(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public UserRequest(String name, String job, String email) {
        this.name = name;
        this.job = job;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
