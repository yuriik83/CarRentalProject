package com.example.carrental.dto;

import java.util.Map;

public class ApiInfoResponse {
    private String application;
    private String version;
    private String description;
    private Map<String, String> endpoints;
    private Map<String, String> features;

    public ApiInfoResponse(String application, String version, String description, 
                          Map<String, String> endpoints, Map<String, String> features) {
        this.application = application;
        this.version = version;
        this.description = description;
        this.endpoints = endpoints;
        this.features = features;
    }

    public String getApplication() { return application; }
    public String getVersion() { return version; }
    public String getDescription() { return description; }
    public Map<String, String> getEndpoints() { return endpoints; }
    public Map<String, String> getFeatures() { return features; }
}