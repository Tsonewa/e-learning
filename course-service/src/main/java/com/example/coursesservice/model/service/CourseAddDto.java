package com.example.coursesservice.model.service;

public class CourseAddDto {

    private String name;
    private String status;

    public CourseAddDto() {
    }

    public CourseAddDto(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public CourseAddDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public CourseAddDto setStatus(String status) {
        this.status = status;
        return this;
    }
}
