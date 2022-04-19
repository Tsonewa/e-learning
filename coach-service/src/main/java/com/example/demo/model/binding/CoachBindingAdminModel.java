package com.example.demo.model.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CoachBindingAdminModel implements Serializable {

    private String name;
    private String email;
    private String company;
    private String topic;
//    private MultipartFile picture;
    private BigDecimal price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private String duration;
    private String introductionVideo;
    private String goals;
    private List<CategoryNameBindingModel> categories;
    private List<LanguageNameBindingModel> languages;
    private List<ResourceBindingModel> resources;

    public CoachBindingAdminModel() {
    }

    @Length(min = 3, max = 500, message = "Please provide info between 3-500 chars")
    @NotNull(message = "Please provide info")
    public String getName() {
        return name;
    }

    public CoachBindingAdminModel setName(String name) {
        this.name = name;
        return this;
    }

    @Email(message = "Please provide email")
    public String getEmail() {
        return email;
    }

    public CoachBindingAdminModel setEmail(String email) {
        this.email = email;
        return this;
    }

    @NotNull(message = "Please provide company")
    public String getCompany() {
        return company;
    }

    public CoachBindingAdminModel setCompany(String company) {
        this.company = company;
        return this;
    }

    @Length(min = 3, max = 5000, message = "Please provide info between 3-5000 chars")
    @NotNull(message = "Please provide info")
    public String getTopic() {
        return topic;
    }

    public CoachBindingAdminModel setTopic(String topic) {
        this.topic = topic;
        return this;
    }


//
//    public MultipartFile getPicture() {
//        return picture;
//    }
//
//    public CoachBindingAdminModel setPicture(MultipartFile picture) {
//        this.picture = picture;
//        return this;
//    }

    @DecimalMin(value = "1",message = "Provide value over 1lv.")
    @DecimalMax(value = "1000000", message = "Provide value under 1000000lv.")
    public BigDecimal getPrice() {
        return price;
    }

    public CoachBindingAdminModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public CoachBindingAdminModel setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public CoachBindingAdminModel setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    @Length(min = 3, max = 3000, message = "Please provide right info")
    public String getDescription() {
        return description;
    }

    public CoachBindingAdminModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotNull(message = "Please provide info")
    public String getDuration() {
        return duration;
    }

    public CoachBindingAdminModel setDuration(String duration) {
        this.duration = duration;
        return this;
    }

    @NotNull(message = "Please provide info")
    public String getIntroductionVideo() {
        return introductionVideo;
    }

    public CoachBindingAdminModel setIntroductionVideo(String introductionVideo) {
        this.introductionVideo = introductionVideo;
        return this;
    }

    @Length(min = 3, max = 5000, message = "Please provide info between 3-5000 chars")
    @NotNull(message = "Please provide info")
    public String getGoals() {
        return goals;
    }

    public CoachBindingAdminModel setGoals(String goals) {
        this.goals = goals;
        return this;
    }

    @Valid
    public List<CategoryNameBindingModel> getCategories() {
        return categories;
    }

    public CoachBindingAdminModel setCategories(List<CategoryNameBindingModel> categories) {
        this.categories = categories;
        return this;
    }

    @Valid
    public List<LanguageNameBindingModel> getLanguages() {
        return languages;
    }

    public CoachBindingAdminModel setLanguages(List<LanguageNameBindingModel> languages) {
        this.languages = languages;
        return this;
    }

    @Valid
    public List<ResourceBindingModel> getResources() {
        return resources;
    }

    public CoachBindingAdminModel setResources(List<ResourceBindingModel> resources) {
        this.resources = resources;
        return this;
    }
}
