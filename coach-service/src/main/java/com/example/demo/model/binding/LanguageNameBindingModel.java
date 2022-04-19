package com.example.demo.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class LanguageNameBindingModel implements Serializable {

    private String language;

    public LanguageNameBindingModel() {
    }

    @Length(min = 3, max = 50, message = "Please provide info between 3-50 chars")
    @NotNull(message = "Please provide info")
    public String getLanguage() {
        return language;
    }

    public LanguageNameBindingModel setLanguage(String language) {
        this.language = language;
        return this;
    }
}
