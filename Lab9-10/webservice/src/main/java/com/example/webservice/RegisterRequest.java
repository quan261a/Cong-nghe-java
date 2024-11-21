package com.example.webservice;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public RegisterRequest() {
    }

    public RegisterRequest(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
