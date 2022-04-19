package com.example.demo.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserRegisterDto {

    private String fullName;
    private String companyName;
    private String email;
    private String password;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String fullName, String companyName, String email, String password) {
        this.fullName = fullName;
        this.companyName = companyName;
        this.email = email;
        this.password = password;

    }

    @Length(min = 3, message = "Fullname must contains at least 3 symbols")
    public String getFullName() {
        return fullName;
    }

    public UserRegisterDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @NotNull(message = "Company can not be empty")
    public String getCompanyName() {
        return companyName;
    }

    public UserRegisterDto setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    @Email
    @NotNull(message = "Email can not be empty")
    public String getEmail() {
        return email;
    }

    public UserRegisterDto setEmail(String email) {
        this.email = email;
        return this;
    }

    @Length(min = 8, max=8, message = "Password must contains 8 symbols")
    @NotNull(message = "Password can not be empty")
    public String getPassword() {
        return password;
    }

    public UserRegisterDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
