package com.example.coursesservice.model.service;

public class EmployeeCreateServiceModel {
    private String fullName;
    private String companyOwner;
    private String email;

    public EmployeeCreateServiceModel() {
    }

    public EmployeeCreateServiceModel(String fullName, String companyOwner, String email) {
        this.fullName = fullName;
        this.companyOwner = companyOwner;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public EmployeeCreateServiceModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getCompanyOwner() {
        return companyOwner;
    }

    public EmployeeCreateServiceModel setCompanyOwner(String companyOwner) {
        this.companyOwner = companyOwner;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeCreateServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
