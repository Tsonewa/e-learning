package com.example.demo.model.enums;

public enum Category {
    ART("Art"),
    DESIGN("Design"),
    MARKETING("Marketing"),
    LEADERSHIP("Leadership"),
    PERSONAL_DEVELOPMENT("Personal development"),
    DATA_SCIENCE("Data science"),
    COMPUTER_SCIENCE("Computer science");

    private final String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
