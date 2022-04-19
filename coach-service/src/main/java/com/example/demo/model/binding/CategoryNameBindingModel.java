package com.example.demo.model.binding;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CategoryNameBindingModel implements Serializable {

    private String category;

    public CategoryNameBindingModel() {
    }

    @NotNull(message = "Please provide message")
    public String getCategory() {
        return category;
    }

    public CategoryNameBindingModel setCategory(String category) {
        this.category = category;
        return this;
    }
}
