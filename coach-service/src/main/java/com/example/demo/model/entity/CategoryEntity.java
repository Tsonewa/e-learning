package com.example.demo.model.entity;

import com.example.demo.model.enums.Category;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
public class CategoryEntity extends BaseEntity{
    private String name;
    private Category category;

    public CategoryEntity() {
    }

    public String getName() {
        return name;
    }

    public CategoryEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public Category getCategory() {
        return category;
    }

    public CategoryEntity setCategory(Category category) {
        this.category = category;
        return this;
    }
}
