package com.example.coursesservice.model.binding;

import java.io.Serializable;

public class EmployeeBindingModel implements Serializable {
    private String fullName;
    private String companyOwner;
    private String email;

    public EmployeeBindingModel() {
    }

    public EmployeeBindingModel(String fullName, String companyOwner, String email) {
        this.fullName = fullName;
        this.companyOwner = companyOwner;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public EmployeeBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getCompanyOwner() {
        return companyOwner;
    }

    public EmployeeBindingModel setCompanyOwner(String companyOwner) {
        this.companyOwner = companyOwner;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
