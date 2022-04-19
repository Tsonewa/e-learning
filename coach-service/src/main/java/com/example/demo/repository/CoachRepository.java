package com.example.demo.repository;

import com.example.demo.model.entity.CategoryEntity;
import com.example.demo.model.entity.CoachEntity;
import com.example.demo.model.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CoachRepository  extends JpaRepository<CoachEntity,String> {



//    @Query("select c from CoachEntity c join CategoryEntity ca on c.languages")

//    @Query("select distinct c from CoachEntity c left join CategoryEntity as categories  join LanguageEntity as languages where categories.id in ?1 or languages.id in ?2")
    List<CoachEntity> findDistinctAllByCategoriesInOrLanguagesIn(List<CategoryEntity> categories, List<LanguageEntity> languages);

    @Query("select c from CoachEntity c ORDER BY c.created DESC")
    List<CoachEntity> findAllCoaches();

}
