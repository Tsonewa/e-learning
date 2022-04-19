package com.example.coursesservice.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="lectures")
public class LectureEntity extends BaseEntity{

    private String lectureName;
    private String resourceUrl;
    private String lectureDescription;

    public LectureEntity() {
    }

    public LectureEntity(String lectureName, String resourceUrl) {
        this.lectureName = lectureName;
        this.resourceUrl = resourceUrl;
    }

    public String getLectureName() {
        return lectureName;
    }

    public LectureEntity setLectureName(String lectureName) {
        this.lectureName = lectureName;
        return this;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public LectureEntity setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
        return this;
    }

    public String getLectureDescription() {
        return lectureDescription;
    }

    public LectureEntity setLectureDescription(String lectureDescription) {
        this.lectureDescription = lectureDescription;
        return this;
    }
}
