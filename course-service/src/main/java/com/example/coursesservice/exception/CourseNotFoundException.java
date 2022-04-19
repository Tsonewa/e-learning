package com.example.coursesservice.exception;

public class CourseNotFoundException extends RuntimeException {
    private final String courseId;

    public CourseNotFoundException(String courseId) {
        super("Course with id " + courseId + " was not found");
        this.courseId = courseId;
    }

    public String getPositionId() {
        return courseId;
    }


}
