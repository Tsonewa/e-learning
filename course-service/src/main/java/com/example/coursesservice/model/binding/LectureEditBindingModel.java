package com.example.coursesservice.model.binding;

public class LectureEditBindingModel {
    private String id;
    private String lectureName;
    private String resourceUrl;
    private String lectureDescription;


    public LectureEditBindingModel() {
    }

    public LectureEditBindingModel(String id, String lectureName, String resourceUrl, String lectureDescription) {
        this.id = id;
        this.lectureName = lectureName;
        this.resourceUrl = resourceUrl;
        this.lectureDescription = lectureDescription;
    }

    public String getId() {
        return id;
    }

    public LectureEditBindingModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getLectureName() {
        return lectureName;
    }

    public LectureEditBindingModel setLectureName(String lectureName) {
        this.lectureName = lectureName;
        return this;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public LectureEditBindingModel setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
        return this;
    }

    public String getLectureDescription() {
        return lectureDescription;
    }

    public LectureEditBindingModel setLectureDescription(String lectureDescription) {
        this.lectureDescription = lectureDescription;
        return this;
    }
}
