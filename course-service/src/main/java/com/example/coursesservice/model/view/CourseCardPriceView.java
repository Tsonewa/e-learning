package com.example.coursesservice.model.view;

import java.io.Serializable;
import java.math.BigDecimal;

public class CourseCardPriceView implements Serializable {

    private String courseId;
    private String imageUrl;
    private String name;
    private String lector;
    private BigDecimal price;
    private String courseStatus;
    private Integer lecturesCount;

    public CourseCardPriceView() {
    }

    public CourseCardPriceView(String courseId, String imageUrl, String name, String lector, BigDecimal price, String courseStatus, Integer lecturesCount) {
        this.courseId = courseId;
        this.imageUrl = imageUrl;
        this.name = name;
        this.lector = lector;
        this.price = price;
        this.courseStatus = courseStatus;
        this.lecturesCount = lecturesCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CourseCardPriceView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getLecturesCount() {
        return lecturesCount;
    }

    public CourseCardPriceView setLecturesCount(Integer lecturesCount) {
        this.lecturesCount = lecturesCount;
        return this;
    }

    public String getCourseId() {
        return courseId;
    }

    public CourseCardPriceView setCourseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    public String getName() {
        return name;
    }

    public CourseCardPriceView setName(String name) {
        this.name = name;
        return this;
    }

    public String getLector() {
        return lector;
    }

    public CourseCardPriceView setLector(String lector) {
        this.lector = lector;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CourseCardPriceView setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public CourseCardPriceView setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
        return this;
    }
}
