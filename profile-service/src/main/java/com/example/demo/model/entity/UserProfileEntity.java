package com.example.demo.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_profiles")
public class UserProfileEntity extends BaseEntity {

    private String email;
    private String fullName;
    private String picture;
    private String summary;
    private CompanyEntity company;


    public UserProfileEntity() {
    }


    public String getPicture() {
        return picture;
    }

    public UserProfileEntity setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getSummary() {
        return summary;
    }

    public UserProfileEntity setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    @Column(nullable = false)
    public String getFullName() {
        return fullName;
    }

    public UserProfileEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @ManyToOne
    public CompanyEntity getCompany() {
        return company;
    }

    public UserProfileEntity setCompany(CompanyEntity company) {
        this.company = company;
        return this;
    }
}

