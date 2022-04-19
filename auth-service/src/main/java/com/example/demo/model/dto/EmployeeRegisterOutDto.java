package com.example.demo.model.dto;

public class EmployeeRegisterOutDto {

    private String id;
    private String email;
    private String companyOwner;
    private String fullName;
    private String password;

    public EmployeeRegisterOutDto() {
    }

    public EmployeeRegisterOutDto(String id, String email, String companyOwner, String fullName, String password) {
        this.id = id;
        this.email = email;
        this.companyOwner = companyOwner;
        this.fullName = fullName;
        this.password = password;
    }

    public String getCompanyOwner() {
        return companyOwner;
    }

    public EmployeeRegisterOutDto setCompanyOwner(String companyOwner) {
        this.companyOwner = companyOwner;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public EmployeeRegisterOutDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getId() {
        return id;
    }

    public EmployeeRegisterOutDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeRegisterOutDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public EmployeeRegisterOutDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
