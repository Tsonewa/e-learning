package com.example.demo.model.binding;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CoachEditBindingModel {

    private String name;
    private String email;
    private String company;
    private String topic;
    private BigDecimal price;
    private MultipartFile picture;
    private LocalDateTime startDate;
    private LocalDateTime endDate;


    @Length(min = 3, max = 50, message = "Please provide info between 3-50 chars")
    @NotNull(message = "Please provide info")
    public String getName() {
        return name;
    }

    public CoachEditBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @Email(message = "Please provide valid email")
    public String getEmail() {
        return email;
    }

    public CoachEditBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    @Length(min = 3, max = 50, message = "Please provide info between 3-50 chars")
    @NotNull(message = "Please provide info")
    public String getCompany() {
        return company;
    }

    public CoachEditBindingModel setCompany(String company) {
        this.company = company;
        return this;
    }

    @Length(min = 3, max = 50, message = "Please provide info between 3-50 chars")
    @NotNull(message = "Please provide info")
    public String getTopic() {
        return topic;
    }

    public CoachEditBindingModel setTopic(String topic) {
        this.topic = topic;
        return this;
    }




    public MultipartFile getPicture() {
        return picture;
    }

    public CoachEditBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CoachEditBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public CoachEditBindingModel setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public CoachEditBindingModel setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }
}
