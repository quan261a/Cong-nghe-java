package com.example.webservice;

import lombok.Getter;

@Getter
public class LoginResponse {

    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

}
