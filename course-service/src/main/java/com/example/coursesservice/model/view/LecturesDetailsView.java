package com.example.coursesservice.model.view;

public class LecturesDetailsView {

    private String resourceUrl;
    private String lectureDescription;
    private String lectureName;

    public LecturesDetailsView() {
    }


    public LecturesDetailsView(String resourceUrl, String lectureDescription, String lectureName) {
        this.resourceUrl = resourceUrl;
        this.lectureDescription = lectureDescription;
        this.lectureName = lectureName;
    }

    public String getLectureName() {
        return lectureName;
    }

    public LecturesDetailsView setLectureName(String lectureName) {
        this.lectureName = lectureName;
        return this;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public LecturesDetailsView setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
        return this;
    }

    public String getLectureDescription() {
        return lectureDescription;
    }

    public LecturesDetailsView setLectureDescription(String lectureDescription) {
        this.lectureDescription = lectureDescription;
        return this;
    }
}
