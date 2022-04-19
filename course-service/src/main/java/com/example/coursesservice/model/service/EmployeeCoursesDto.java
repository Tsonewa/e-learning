package com.example.coursesservice.model.service;

import com.example.coursesservice.model.enums.StatusNameEnum;

public class EmployeeCoursesDto {
    private String courseId;
    private String imageUrl;
    private String name;
    private String lector;
    private Integer lectureCount;
    private StatusNameEnum status;

    public EmployeeCoursesDto() {
    }
    public EmployeeCoursesDto(String courseId, String imageUrl, String name, String lector, Integer lectureCount, StatusNameEnum status) {
        this.courseId = courseId;
        this.imageUrl = imageUrl;
        this.name = name;
        this.lector = lector;
        this.lectureCount = lectureCount;
        this.status = status;
    }

    public String getCourseId() {
        return courseId;
    }

    public EmployeeCoursesDto setCourseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public EmployeeCoursesDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public EmployeeCoursesDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getLector() {
        return lector;
    }

    public EmployeeCoursesDto setLector(String lector) {
        this.lector = lector;
        return this;
    }

    public Integer getLectureCount() {
        return lectureCount;
    }

    public EmployeeCoursesDto setLectureCount(Integer lectureCount) {
        this.lectureCount = lectureCount;
        return this;
    }

    public StatusNameEnum getStatus() {
        return status;
    }

    public EmployeeCoursesDto setStatus(StatusNameEnum status) {
        this.status = status;
        return this;
    }
}
