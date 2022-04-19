package com.example.demo.model.view;

public class CreatedEmployeeView {

    private String id;
    private String username;
    private String password;
    private String role;

    public CreatedEmployeeView() {
    }

    public String getId() {
        return id;
    }

    public CreatedEmployeeView setId(String id) {
        this.id = id;

        return this;
    }

    public String getUsername() {
        return username;
    }

    public CreatedEmployeeView setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CreatedEmployeeView setPassword(String password) {
        this.password = password;

        return this;
    }

    public String getRole() {
        return role;
    }

    public CreatedEmployeeView setRole(String role) {
        this.role = role;

        return this;
    }
}
