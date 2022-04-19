package com.example.coursesservice.repository;

import com.example.coursesservice.model.entity.CourseEntity;
import com.example.coursesservice.model.entity.EmployeeCourseEntity;
import com.example.coursesservice.model.enums.CategoryNameEnum;
import com.example.coursesservice.model.enums.LanguageEnum;
import com.example.coursesservice.model.service.EmployeeCoursesDto;
import com.example.coursesservice.model.view.CourseCardPriceView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, String> {
    boolean existsByName(String name);

    Optional<CourseEntity> getByName(String name);

    @Query("select c from CourseEntity as c left join c.businessOwners as b where b.email = :email")
    List<CourseEntity> getAllByBusinessOwnersEmail(String email, Pageable pageable);

    @Query("select distinct c from CourseEntity as c left join c.languages as l where l.name IN :languages")
    List<CourseEntity> getAllByLanguages(List<LanguageEnum> languages);

    @Query("select distinct c from CourseEntity as c left join c.categories as cat where cat.name IN :categories")
    List<CourseEntity> getAllByCategory(List<CategoryNameEnum> categories);

    @Query("select distinct c from CourseEntity as c left join c.categories as categories join c.languages as languages where categories.name in ?1 and languages.name in ?2")
    Optional<CourseEntity[]> SearchByCategoryAndLanguage(List<CategoryNameEnum> categoryNameEnumsList, List<LanguageEnum> languageEnumList);

    @Query("select c from CourseEntity as c join c.businessOwners as bo where c.courseId=:courseId and bo.email=:businessOwnerEmail")
    Optional<CourseEntity> getByBusinessOwner(String courseId, String businessOwnerEmail);

    @Query("select ec from CourseEntity as c join c.employeeCourses as ec where ec.employeeEntity.email=:eEmail and ec.courseEntity.courseId=:cId")
    Optional<EmployeeCourseEntity> getEmployeeCourse(String eEmail, String cId);

    @Query("select new com.example.coursesservice.model.service.EmployeeCoursesDto(c.courseId,c.imageUrl, c.name, c.lector, c.languages.size,ec.status) from CourseEntity as c join c.employeeCourses as ec where ec.employeeEntity.email= :employeeEmail")
    List<EmployeeCoursesDto> getAllCoursesByEmployee(String employeeEmail);

    @Query("select c from CourseEntity as c join c.employeeCourses as ec where ec.employeeEntity.email=:employeeEmail")
    List<CourseEntity> getAllByEmail(String employeeEmail);
}
