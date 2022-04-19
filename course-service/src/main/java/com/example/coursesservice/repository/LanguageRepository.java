package com.example.coursesservice.repository;

import com.example.coursesservice.model.entity.LanguageEntity;
import com.example.coursesservice.model.enums.LanguageEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity, String> {

    LanguageEntity getByName(LanguageEnum name);
}

