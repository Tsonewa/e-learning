package com.example.demo.model.dto;

import java.io.Serializable;

public class BusinessOwnerOutDto implements Serializable {

    private String fullName;
    private String companyName;
    private String email;

    public BusinessOwnerOutDto() {
    }

    public BusinessOwnerOutDto( String fullName, String companyName, String email) {
        this.fullName = fullName;
        this.companyName = companyName;
        this.email = email;
    }


    public String getFullName() {
        return fullName;
    }

    public BusinessOwnerOutDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public BusinessOwnerOutDto setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BusinessOwnerOutDto setEmail(String email) {
        this.email = email;
        return this;
    }
}
