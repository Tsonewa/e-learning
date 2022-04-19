package com.example.demo.model.binding.SeachCriteriaModels;

public class CategoryBindingModel {

    private String category;

    public CategoryBindingModel() {
    }

    public CategoryBindingModel(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }



    public CategoryBindingModel setCategory(String category) {
        this.category = category;
        return this;
    }


}
