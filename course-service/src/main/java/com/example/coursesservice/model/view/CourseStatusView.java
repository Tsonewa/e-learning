package com.example.coursesservice.model.view;

public class CourseStatusView {
    private String status;

    public CourseStatusView() {
    }

    public CourseStatusView(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public CourseStatusView setStatus(String status) {
        this.status = status;
        return this;
    }
}
