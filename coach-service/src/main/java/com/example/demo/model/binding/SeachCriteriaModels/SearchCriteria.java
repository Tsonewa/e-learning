package com.example.demo.model.binding.SeachCriteriaModels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchCriteria implements Serializable {

    private List<LanguageBindingModel> languages;
    private List<CategoryBindingModel> categories;

    public SearchCriteria() {
    }

    public List<LanguageBindingModel> getLanguages() {
        return languages;
    }

    public SearchCriteria setLanguages(List<LanguageBindingModel> languages) {
        this.languages = languages;
        return this;
    }

    public List<CategoryBindingModel> getCategories() {
        return categories;
    }

    public SearchCriteria setCategories(List<CategoryBindingModel> categories) {
        this.categories = categories;
        return this;
    }
}
