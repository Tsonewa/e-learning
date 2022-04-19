package com.example.coursesservice.model.entity;

import com.example.coursesservice.model.enums.LanguageEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "languages")
public class LanguageEntity extends BaseEntity {
    private LanguageEnum name;
    private List<CourseEntity> courses;

    public LanguageEntity() {
    }

    @Enumerated(value = EnumType.STRING)
    public LanguageEnum getName() {
        return name;
    }

    public LanguageEntity setName(LanguageEnum name) {
        this.name = name;
        return this;
    }

    @ManyToMany(mappedBy = "languages")
    public List<CourseEntity> getCourses() {
        return courses;
    }

    public LanguageEntity setCourses(List<CourseEntity> courses) {
        this.courses = courses;
        return this;
    }
}

