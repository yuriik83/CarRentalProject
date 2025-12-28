package com.example.carrental.dto;

public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    private String type = "Bearer";
    private String username;
    private long expiresIn;

    public JwtResponse(String accessToken, String refreshToken, String username, long expiresIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.username = username;
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() { return accessToken; }
    public String getRefreshToken() { return refreshToken; }
    public String getType() { return type; }
    public String getUsername() { return username; }
    public long getExpiresIn() { return expiresIn; }
}