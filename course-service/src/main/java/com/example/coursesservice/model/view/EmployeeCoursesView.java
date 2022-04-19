package com.example.coursesservice.model.view;

import com.example.coursesservice.model.service.EmployeeCoursesDto;

import java.util.List;

public class EmployeeCoursesView {
    List<EmployeeCoursesDto> courses;

    public EmployeeCoursesView() {
    }

    public EmployeeCoursesView(List<EmployeeCoursesDto> courses) {
        this.courses = courses;
    }

    public List<EmployeeCoursesDto> getCourses() {
        return courses;
    }

    public EmployeeCoursesView setCourses(List<EmployeeCoursesDto> courses) {
        this.courses = courses;
        return this;
    }
}
