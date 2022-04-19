package com.example.coursesservice.model.service;

import com.example.coursesservice.model.enums.LanguageEnum;

public class LanguageServiceDto {
    private String name;

    public LanguageServiceDto() {
    }

    public String getName() {
        return name;
    }

    public LanguageServiceDto setName(String name) {
        this.name = name;
        return this;
    }
}
