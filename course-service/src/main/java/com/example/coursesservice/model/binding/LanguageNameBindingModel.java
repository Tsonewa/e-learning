package com.example.coursesservice.model.binding;

public class LanguageNameBindingModel {

    private String name;

    public LanguageNameBindingModel() {
    }

    public LanguageNameBindingModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public LanguageNameBindingModel setName(String name) {
        this.name = name;
        return this;
    }
}
