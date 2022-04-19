package com.example.demo.model.view;

public class UserProfileView {

    private String id;
    private String fullName;
    private String email;
    private String picture;
    private String summary;
    private String company;

    public UserProfileView() {
    }

    public String getId() {
        return id;
    }

    public UserProfileView setId(String id) {
        this.id = id;
        return this;
    }


    public String getFullName() {
        return fullName;
    }

    public UserProfileView setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileView setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public UserProfileView setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public UserProfileView setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public UserProfileView setCompany(String company) {
        this.company = company;
        return this;
    }
}
