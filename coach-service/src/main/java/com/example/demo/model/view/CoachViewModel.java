package com.example.demo.model.view;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class CoachViewModel {

    private String id;
    private String name;
    private String email;
    private String company;
    private String topic;
    private String picture;
    private BigDecimal price;

    public CoachViewModel() {
    }

    public String getId() {
        return id;
    }

    public CoachViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CoachViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CoachViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public CoachViewModel setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public CoachViewModel setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public CoachViewModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CoachViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
