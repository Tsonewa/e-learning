package com.example.demo.init;

import com.example.demo.model.entity.CategoryEntity;
import com.example.demo.model.entity.LanguageEntity;
import com.example.demo.model.enums.Category;
import com.example.demo.model.enums.Language;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInit implements CommandLineRunner {
    private final LanguageRepository languageRepository;
    private final CategoryRepository categoryRepository;

    public DataInit(LanguageRepository languageRepository, CategoryRepository categoryRepository) {
        this.languageRepository = languageRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        if(this.categoryRepository.count() == 0) {
            initCategory();
        }
        if(this.languageRepository.count()==0){
            initLanguage();
        }
    }

    private void initLanguage() {
        Arrays.stream(Language.values()).map(l -> {
            LanguageEntity language = new LanguageEntity();

            language.setLanguage(l)
                    .setName(l.getLanguage());

            return language;
        }).forEach(languageRepository::save);
    }

    private void initCategory() {
        Arrays.stream(Category.values()).map(c -> {
            CategoryEntity category = new CategoryEntity();

            category
                    .setCategory(c)
                    .setName(c.getCategory());

            return category;
        }).forEach(categoryRepository::save);
    }
}