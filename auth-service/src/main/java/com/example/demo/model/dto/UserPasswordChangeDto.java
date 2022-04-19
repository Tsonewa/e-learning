package com.example.demo.model.dto;

public class UserPasswordChangeDto {

    private String email;
    private String password;

    public UserPasswordChangeDto() {
    }

    public UserPasswordChangeDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public UserPasswordChangeDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserPasswordChangeDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
