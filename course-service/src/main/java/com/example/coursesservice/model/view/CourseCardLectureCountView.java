package com.example.coursesservice.model.view;

import java.math.BigDecimal;

public class CourseCardLectureCountView {

    private String imageUrl;
    private String name;
    private String lector;
    private Integer lecturesCount;

    public CourseCardLectureCountView() {
    }


    public CourseCardLectureCountView(String imageUrl, String name, String lector, Integer lecturesCount) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.lector = lector;
        this.lecturesCount = lecturesCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CourseCardLectureCountView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public CourseCardLectureCountView setName(String name) {
        this.name = name;
        return this;
    }

    public String getLector() {
        return lector;
    }

    public CourseCardLectureCountView setLector(String lector) {
        this.lector = lector;
        return this;
    }

    public Integer getLecturesCount() {
        return lecturesCount;
    }

    public CourseCardLectureCountView setLecturesCount(Integer lecturesCount) {
        this.lecturesCount = lecturesCount;
        return this;
    }
}
