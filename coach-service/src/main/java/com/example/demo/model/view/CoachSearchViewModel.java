package com.example.demo.model.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CoachSearchViewModel {


    private String id;
    private String name;
    private String email;
    private String company;
    private String topic;
    private String picture;
    private BigDecimal price;
    private LocalDateTime endDate;
    private Boolean owned;

    public CoachSearchViewModel() {
    }

    public String getId() {
        return id;
    }

    public CoachSearchViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CoachSearchViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CoachSearchViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public CoachSearchViewModel setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public CoachSearchViewModel setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public CoachSearchViewModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CoachSearchViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Boolean getOwned() {
        return owned;
    }

    public CoachSearchViewModel setOwned(Boolean owned) {
        this.owned = owned;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public CoachSearchViewModel setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }
}
