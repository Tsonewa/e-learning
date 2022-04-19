package com.example.demo.model.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CoachEditViewModel {

    private String id;
    private String name;
    private String email;
    private String company;
    private String topic;
    private String picture;
    private BigDecimal price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public CoachEditViewModel() {
    }

    public String getId() {
        return id;
    }

    public CoachEditViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CoachEditViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CoachEditViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public CoachEditViewModel setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public CoachEditViewModel setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public CoachEditViewModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CoachEditViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public CoachEditViewModel setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public CoachEditViewModel setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }
}
