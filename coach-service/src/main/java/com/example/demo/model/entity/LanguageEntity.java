package com.example.demo.model.entity;

import com.example.demo.model.enums.Language;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
public class LanguageEntity extends BaseEntity {
    private String name;
    private Language language;

    public LanguageEntity() {
    }

    public String getName() {
        return name;
    }

    public LanguageEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public Language getLanguage() {
        return language;
    }

    public LanguageEntity setLanguage(Language language) {
        this.language = language;
        return this;
    }
}
