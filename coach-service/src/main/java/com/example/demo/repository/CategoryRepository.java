package com.example.demo.repository;

import com.example.demo.model.entity.CategoryEntity;
import com.example.demo.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByName(String name);

    CategoryEntity findByCategory(Category category);
}
