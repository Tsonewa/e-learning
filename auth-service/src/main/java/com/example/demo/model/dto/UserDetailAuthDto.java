package com.example.demo.model.dto;

import java.io.Serializable;
import java.util.List;

public class UserDetailAuthDto implements Serializable {


    private String email;
    private String password;
    private List<String> roles;

    public UserDetailAuthDto() {
    }

    public UserDetailAuthDto(String id, String email, String password, List<String> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }


    public String getEmail() {
        return email;
    }

    public UserDetailAuthDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDetailAuthDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public UserDetailAuthDto setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }
}
