package com.example.coursesservice.model.view;

import com.example.coursesservice.model.binding.CategoryNameBindingModel;
import com.example.coursesservice.model.binding.LanguageNameBindingModel;
import com.example.coursesservice.model.binding.LectureEditBindingModel;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CourseEditViewModel {
    private String courseId;
    private String name;
    private BigDecimal price;
    private String description;
    private String videoUrl;
    private String imageUrl;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<CategoryNameBindingModel> categories;
    private List<LanguageNameBindingModel> languages;
    private List<LectureEditBindingModel> lectures;
    private String lector;
    private Integer duration;
    private String skills;
    private String lectorDescription;

    public CourseEditViewModel() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CourseEditViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getCourseId() {
        return courseId;
    }

    public CourseEditViewModel setCourseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    public String getName() {
        return name;
    }

    public CourseEditViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CourseEditViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CourseEditViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public CourseEditViewModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public CourseEditViewModel setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public CourseEditViewModel setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public List<CategoryNameBindingModel> getCategories() {
        return categories;
    }

    public CourseEditViewModel setCategories(List<CategoryNameBindingModel> categories) {
        this.categories = categories;
        return this;
    }

    public List<LanguageNameBindingModel> getLanguages() {
        return languages;
    }

    public CourseEditViewModel setLanguages(List<LanguageNameBindingModel> languages) {
        this.languages = languages;
        return this;
    }

    public List<LectureEditBindingModel> getLectures() {
        return lectures;
    }

    public CourseEditViewModel setLectures(List<LectureEditBindingModel> lectures) {
        this.lectures = lectures;
        return this;
    }

    public String getLector() {
        return lector;
    }

    public CourseEditViewModel setLector(String lector) {
        this.lector = lector;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public CourseEditViewModel setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String getSkills() {
        return skills;
    }

    public CourseEditViewModel setSkills(String skills) {
        this.skills = skills;
        return this;
    }

    public String getLectorDescription() {
        return lectorDescription;
    }

    public CourseEditViewModel setLectorDescription(String lectorDescription) {
        this.lectorDescription = lectorDescription;
        return this;
    }
}
