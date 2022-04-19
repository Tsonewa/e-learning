package com.example.demo.model.enums;


public enum Language {

    ENGLISH("English"),
    SPANISH("Spanish"),
    GERMAN("German"),
    FRENCH("French");

    private final String language;

    Language(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
