package com.example.coursesservice.service;

import com.example.coursesservice.model.service.CategoryServiceDto;

import java.util.List;

public interface CategoryService {
    List<CategoryServiceDto> getAllCategories();

}
