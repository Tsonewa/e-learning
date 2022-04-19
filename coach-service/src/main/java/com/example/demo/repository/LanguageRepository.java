package com.example.demo.repository;

import com.example.demo.model.entity.LanguageEntity;
import com.example.demo.model.enums.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<LanguageEntity, Long> {

    LanguageEntity findByName(String name);

    LanguageEntity findByLanguage(Language language);
}
