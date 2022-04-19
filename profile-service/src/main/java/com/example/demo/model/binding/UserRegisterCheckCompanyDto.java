package com.example.demo.model.binding;

public class UserRegisterCheckCompanyDto {

    private String fullName;
    private String companyName;
    private String email;
    private String password;
    private boolean isCompanyExist;

    public UserRegisterCheckCompanyDto() {
    }

    public UserRegisterCheckCompanyDto(String fullName, String companyName, String email, String password) {
        this.fullName = fullName;
        this.companyName = companyName;
        this.email = email;
        this.password = password;
        this.isCompanyExist=false;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRegisterCheckCompanyDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public UserRegisterCheckCompanyDto setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterCheckCompanyDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterCheckCompanyDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isCompanyExist() {
        return isCompanyExist;
    }

    public UserRegisterCheckCompanyDto setCompanyExist(boolean companyExist) {
        isCompanyExist = companyExist;
        return this;
    }
}
