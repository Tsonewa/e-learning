package com.example.coursesservice.service;

import com.example.coursesservice.model.entity.LanguageEntity;
import com.example.coursesservice.model.service.LanguageServiceDto;

import java.util.List;

public interface LanguageService {
    List<LanguageServiceDto> getAllLanguages();
}
