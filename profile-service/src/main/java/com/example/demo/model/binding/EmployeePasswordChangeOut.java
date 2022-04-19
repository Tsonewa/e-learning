package com.example.demo.model.binding;

public class EmployeePasswordChangeOut {

    private String password;
    private String email;

    public EmployeePasswordChangeOut() {
    }

    public EmployeePasswordChangeOut(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public EmployeePasswordChangeOut setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmployeePasswordChangeOut setEmail(String email) {
        this.email = email;
        return this;
    }
}
