package com.example.model;

import java.time.LocalDateTime;

public class User {
    private String username;
    private String passwordHash;
    private String email;
    private LocalDateTime createdAt; // Ngày tạo tài khoản

    // Constructor yêu cầu 4 tham số
    public User(String username, String passwordHash, String email, LocalDateTime createdAt) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.createdAt = createdAt;
    }

    // Getter và setter (có thể cần thêm các phương thức khác tùy theo nhu cầu)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
