package com.example.demo.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.annotation.RegEx;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    public String getCompanyOwner() {
        return companyOwner;
    }

    public EmployeeBindingModel setCompanyOwner(String companyOwner) {
        this.companyOwner = companyOwner;
        return this;
    }

    public EmployeeBindingModel(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    @Length(min = 3,message = "Enter minimum 3 characters.")
    @NotBlank(message = "Please provide information")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Email(message = "Please provide email")
    @NotBlank(message = "Please provide information")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
