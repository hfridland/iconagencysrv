package com.iconagency.quotes.payload.response;

import lombok.Getter;

@Getter
public class InvalidLoginResponse {
    private String username;
    private String password;
    private String message;

    public InvalidLoginResponse() {
        this.username = "Invalid Username";
        this.password = "Invalid Password";
        this.message = "Invalid Username or Password";
    }
}
