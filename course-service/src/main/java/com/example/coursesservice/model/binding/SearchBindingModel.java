package com.example.coursesservice.model.binding;

import java.util.List;

public class SearchBindingModel {
    List<CategoryNameBindingModel> categories;
    List<LanguageNameBindingModel>languages;

    public SearchBindingModel() {
    }


    public List<CategoryNameBindingModel> getCategories() {
        return categories;
    }

    public SearchBindingModel setCategories(List<CategoryNameBindingModel> categories) {
        this.categories = categories;
        return this;
    }

    public List<LanguageNameBindingModel> getLanguages() {
        return languages;
    }

    public SearchBindingModel setLanguages(List<LanguageNameBindingModel> languages) {
        this.languages = languages;
        return this;
    }
}
