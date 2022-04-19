package com.example.coursesservice.init;

import com.example.coursesservice.model.binding.LanguageNameBindingModel;
import com.example.coursesservice.model.entity.CategoryEntity;
import com.example.coursesservice.model.entity.LanguageEntity;
import com.example.coursesservice.model.enums.CategoryNameEnum;
import com.example.coursesservice.model.enums.LanguageEnum;
import com.example.coursesservice.repository.CategoryRepository;
import com.example.coursesservice.repository.LanguageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InitDatabase implements CommandLineRunner {

    private final LanguageRepository languageRepository;
    private final CategoryRepository categoryRepository;

    public InitDatabase(LanguageRepository languageRepository, CategoryRepository categoryRepository) {
        this.languageRepository = languageRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(this.categoryRepository.count() > 0 || this.languageRepository.count() > 0){
            return;
        }

        initCategories();
        initLanguages();
    }

    private void initLanguages() {

        if(languageRepository.count()>0){
            return;
        }

        Arrays.stream(LanguageEnum.values())
                .forEach(l -> {
                    LanguageEntity languageEntity = new LanguageEntity()
                            .setName(l);

                    this.languageRepository.save(languageEntity);
                });
    }

    private void initCategories() {
        if(this.categoryRepository.count()>0){
            return;
        }

        Arrays.stream(CategoryNameEnum.values())
                .forEach(c -> {
                    CategoryEntity categoryEntity = new CategoryEntity()
                            .setName(c);

                    this.categoryRepository.save(categoryEntity);
                });
    }
}
