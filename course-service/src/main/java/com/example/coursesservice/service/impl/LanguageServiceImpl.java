package com.example.coursesservice.service.impl;

import com.example.coursesservice.model.entity.LanguageEntity;
import com.example.coursesservice.model.enums.LanguageEnum;
import com.example.coursesservice.model.service.LanguageServiceDto;
import com.example.coursesservice.repository.LanguageRepository;
import com.example.coursesservice.service.LanguageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public List<LanguageServiceDto> getAllLanguages() {
        return this.languageRepository.findAll().stream()
                .map(l -> new LanguageServiceDto().setName(l.getName().toString().toLowerCase()))
                .collect(Collectors.toList());
    }
}
