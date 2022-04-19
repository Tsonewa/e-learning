package com.example.demo.model.view;

import java.math.BigDecimal;

public class CoachViewModelByAdmin {

    private String id;
    private String name;
    private String company;
    private String topic;
    private String picture;
    private BigDecimal price;

    public CoachViewModelByAdmin() {
    }

    public String getId() {
        return id;
    }

    public CoachViewModelByAdmin setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CoachViewModelByAdmin setName(String name) {
        this.name = name;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public CoachViewModelByAdmin setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public CoachViewModelByAdmin setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public CoachViewModelByAdmin setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CoachViewModelByAdmin setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
