package com.event.ticketbooking.dto;

public class LoginResponse {

    private String message;
    private String token;
    private String role;
    private String email;

    public LoginResponse() {}

    public LoginResponse(String message, String token, String role, String email) {
        this.message = message;
        this.token = token;
        this.role = role;
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }
}