package com.example.webservice;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void register(RegisterRequest request);
    String login(LoginRequest request);
}


