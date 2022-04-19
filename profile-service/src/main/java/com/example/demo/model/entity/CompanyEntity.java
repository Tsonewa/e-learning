package com.example.demo.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
public class CompanyEntity extends BaseEntity {

    private String name;
    private String email;
    private String companyOwner;
    private List<UserProfileEntity> userProfile;

    public CompanyEntity() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public CompanyEntity setName(String name) {
        this.name = name;
        return this;
    }


    @Column(nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public CompanyEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @OneToMany
    public List<UserProfileEntity> getUserProfile() {
        return userProfile;
    }

    public CompanyEntity setUserProfile(List<UserProfileEntity> userProfile) {
        this.userProfile = userProfile;
        return this;
    }

    public String getCompanyOwner() {
        return companyOwner;
    }

    public CompanyEntity setCompanyOwner(String companyOwner) {
        this.companyOwner = companyOwner;
        return this;
    }
}

