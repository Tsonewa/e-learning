package com.example.coursesservice.service.impl;

import com.example.coursesservice.model.service.CategoryServiceDto;
import com.example.coursesservice.model.service.LanguageServiceDto;
import com.example.coursesservice.repository.CategoryRepository;
import com.example.coursesservice.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<CategoryServiceDto> getAllCategories() {

        return   this.categoryRepository.findAll().stream()
                .map(l -> new CategoryServiceDto().setName(l.getName().toString().toLowerCase()))
                .collect(Collectors.toList());
    }
}
