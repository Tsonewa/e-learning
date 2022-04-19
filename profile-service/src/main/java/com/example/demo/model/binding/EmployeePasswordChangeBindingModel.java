package com.example.demo.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class EmployeePasswordChangeBindingModel {

    private String password;
    private String passwordConfirm;
    private String email;

    public EmployeePasswordChangeBindingModel() {
    }

    @Length(min = 3,max = 50, message = "Please enter minimum 3 characters")
    @NotNull(message = "Please provide info")
    public String getPassword() {
        return password;
    }

    public EmployeePasswordChangeBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @NotNull(message = "Please provide info")
    public String getEmail() {
        return email;
    }

    public EmployeePasswordChangeBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    @Length(min = 3,max = 50, message = "Please enter minimum 3 characters")
    @NotNull(message = "Please provide info")
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public EmployeePasswordChangeBindingModel setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
        return this;
    }

    public boolean passwordMatch(){
        return this.password.equals(this.passwordConfirm);
    }
}
