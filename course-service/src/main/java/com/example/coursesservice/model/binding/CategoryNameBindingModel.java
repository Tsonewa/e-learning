package com.example.coursesservice.model.binding;

import java.io.Serializable;

public class CategoryNameBindingModel implements Serializable {

    private String name;

    public CategoryNameBindingModel() {
    }

    public CategoryNameBindingModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public CategoryNameBindingModel setName(String name) {
        this.name = name;
        return this;
    }
}
