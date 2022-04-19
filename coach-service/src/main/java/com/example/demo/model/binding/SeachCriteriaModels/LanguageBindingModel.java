package com.example.demo.model.binding.SeachCriteriaModels;

public class LanguageBindingModel {
    private String language;

    public LanguageBindingModel() {
    }

    public LanguageBindingModel(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public LanguageBindingModel setLanguage(String language) {
        this.language = language;
        return this;
    }
}
