package com.example.coursesservice.model.binding;

public class LectureBindingModel {

    private String lectureName;
    private String resourceUrl;
    private String lectureDescription;


    public LectureBindingModel() {
    }

    public LectureBindingModel(String name, String resourceUrl) {
        this.lectureName = name;
        this.resourceUrl = resourceUrl;
    }

    public String getLectureName() {
        return lectureName;
    }

    public LectureBindingModel setLectureName(String lectureName) {
        this.lectureName = lectureName;
        return this;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public LectureBindingModel setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
        return this;
    }

    public String getLectureDescription() {
        return lectureDescription;
    }

    public LectureBindingModel setLectureDescription(String lectureDescription) {
        this.lectureDescription = lectureDescription;
        return this;
    }
}
