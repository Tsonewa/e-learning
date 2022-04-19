package com.example.coursesservice.model.entity;

import com.example.coursesservice.model.enums.CategoryNameEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {
    private CategoryNameEnum name;
    private List<CourseEntity>courses;

    public CategoryEntity() {
    }

    @Enumerated(value = EnumType.STRING)
    public CategoryNameEnum getName() {
        return name;
    }

    public CategoryEntity setName(CategoryNameEnum name) {
        this.name = name;
        return this;
    }

    @ManyToMany(mappedBy = "categories")
    public List<CourseEntity> getCourses() {
        return courses;
    }

    public CategoryEntity setCourses(List<CourseEntity> courses) {
        this.courses = courses;
        return this;
    }
}
