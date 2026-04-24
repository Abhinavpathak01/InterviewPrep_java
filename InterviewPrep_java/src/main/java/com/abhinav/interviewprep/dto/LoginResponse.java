package com.abhinav.interviewprep.dto;

public class LoginResponse {

    private String message;
    private boolean status;
    private String token;

    public LoginResponse(String message, boolean status, String token) {
        this.message = message;
        this.status = status;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }
}