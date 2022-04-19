package com.example.demo.model.binding;

import java.io.Serializable;

public class EmployeeReceiveBindingModel implements Serializable {
    
    private String id;
    private String fullName;
    private String password;
    private String email;
    private String companyOwner;

    public EmployeeReceiveBindingModel() {
    }

    public String getId() {
        return id;
    }

    public EmployeeReceiveBindingModel setId(String id) {
        this.id = id;
        return this;
    }


    public String getPassword() {
        return password;
    }

    public EmployeeReceiveBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeReceiveBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public EmployeeReceiveBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getCompanyOwner() {
        return companyOwner;
    }

    public EmployeeReceiveBindingModel setCompanyOwner(String companyOwner) {
        this.companyOwner = companyOwner;
        return this;
    }
}
