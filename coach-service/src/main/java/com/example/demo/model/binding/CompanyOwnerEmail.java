package com.example.demo.model.binding;

public class CompanyOwnerEmail {
    
    String emailCompanyOwner;
    String emailEmployee;

    public CompanyOwnerEmail() {
    }

    public String getEmailCompanyOwner() {
        return emailCompanyOwner;
    }

    public CompanyOwnerEmail setEmailCompanyOwner(String emailCompanyOwner) {
        this.emailCompanyOwner = emailCompanyOwner;
        return this;
    }

    public String getEmailEmployee() {
        return emailEmployee;
    }

    public CompanyOwnerEmail setEmailEmployee(String emailEmployee) {
        this.emailEmployee = emailEmployee;
        return this;
    }
}
