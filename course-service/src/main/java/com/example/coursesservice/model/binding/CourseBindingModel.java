package com.example.coursesservice.model.binding;


import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CourseBindingModel implements Serializable {

    private String name;
    private BigDecimal price;
    private String description;
    private String videoUrl;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<CategoryNameBindingModel> categories;
    private List<LanguageNameBindingModel> languages;
    private List<LectureBindingModel> lectures;
    private String lector;
    private Integer duration;
    private String skills;
    private String lectorDescription;

    public CourseBindingModel() {
    }

    public String getName() {
        return name;
    }

    public CourseBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CourseBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CourseBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public CourseBindingModel setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public CourseBindingModel setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")


    public List<CategoryNameBindingModel> getCategories() {
        return categories;
    }

    public CourseBindingModel setCategories(List<CategoryNameBindingModel> categories) {
        this.categories = categories;
        return this;
    }

    public List<LanguageNameBindingModel> getLanguages() {
        return languages;
    }

    public CourseBindingModel setLanguages(List<LanguageNameBindingModel> languages) {
        this.languages = languages;
        return this;
    }

    public List<LectureBindingModel> getLectures() {
        return lectures;
    }

    public CourseBindingModel setLectures(List<LectureBindingModel> lectures) {
        this.lectures = lectures;
        return this;
    }

    public String getLector() {
        return lector;
    }

    public CourseBindingModel setLector(String lector) {
        this.lector = lector;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public CourseBindingModel setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String getSkills() {
        return skills;
    }

    public CourseBindingModel setSkills(String skills) {
        this.skills = skills;
        return this;
    }

    public String getLectorDescription() {
        return lectorDescription;
    }

    public CourseBindingModel setLectorDescription(String lectorDescription) {
        this.lectorDescription = lectorDescription;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public CourseBindingModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }
}
