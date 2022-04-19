package com.example.coursesservice.repository;

import com.example.coursesservice.model.entity.CategoryEntity;
import com.example.coursesservice.model.enums.CategoryNameEnum;
import com.example.coursesservice.model.enums.LanguageEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    CategoryEntity getCategoryByName(CategoryNameEnum name);
}

