package com.example.coursesservice.model.view;

public class CourseDetailsView {

    private String name;
    private String lector;
    private String description;
    private String skills;
    private Integer duration;
    private Integer lecturesCount;

    public CourseDetailsView() {
    }

    public CourseDetailsView(String name, String lector, String description, String skills, Integer duration, Integer lecturesCount) {
        this.name = name;
        this.lector = lector;
        this.description = description;
        this.skills = skills;
        this.duration = duration;
        this.lecturesCount = lecturesCount;
    }

    public String getName() {
        return name;
    }

    public CourseDetailsView setName(String name) {
        this.name = name;
        return this;
    }

    public String getLector() {
        return lector;
    }

    public CourseDetailsView setLector(String lector) {
        this.lector = lector;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CourseDetailsView setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSkills() {
        return skills;
    }

    public CourseDetailsView setSkills(String skills) {
        this.skills = skills;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public CourseDetailsView setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public Integer getLecturesCount() {
        return lecturesCount;
    }

    public CourseDetailsView setLecturesCount(Integer lecturesCount) {
        this.lecturesCount = lecturesCount;
        return this;
    }
}
