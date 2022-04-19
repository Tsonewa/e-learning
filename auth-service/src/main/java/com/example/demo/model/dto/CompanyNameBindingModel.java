package com.example.demo.model.dto;

import java.io.Serializable;

public class CompanyNameBindingModel implements Serializable {
    private String companyName;

    public CompanyNameBindingModel() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public CompanyNameBindingModel setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

}
