package com.example.coursesservice.service;

import com.example.coursesservice.exception.CourseDuplicationException;
import com.example.coursesservice.model.binding.*;
import com.example.coursesservice.model.entity.BusinessOwnerEntity;
import com.example.coursesservice.model.entity.CourseEntity;
import com.example.coursesservice.model.service.EmployeeCoursesDto;
import com.example.coursesservice.model.service.EmployeeCreateServiceModel;
import com.example.coursesservice.model.view.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {
    String add(CourseBindingModel courseBindingModel) throws CourseDuplicationException, IOException;

    boolean deleteCourse(String id);


    void editCourse(CourseEditBindingModel courseEditBindingModel, String id) throws IOException;

    CourseEditViewModel findCourseById(String id);

    List<CourseCardPriceView> getAllCourses(HttpServletRequest request);

    List<CourseCardPriceView> getAllCoursesByBusinessOwner(String companyOwnerEmail, int page, int limit);

    void addBusinessOwnerToCourse(String courseId, String businessOwnerEmail) throws InvocationTargetException, IllegalAccessException, CourseDuplicationException;

    List<CourseCardPriceView> searchCourseByLanguageAndCategories(SearchBindingModel searchBindingModel);

    List<CourseCardPriceView> searchCoursesByCategorysName(List<CategoryNameBindingModel> categories);

    List<CourseCardPriceView> searchCoursesByLanguages(List<LanguageNameBindingModel> languages);

    void setCoursesStatus(List<CourseCardPriceView> courses, HttpServletRequest request);

    void removeBusinessOwnerToCourse(String courseId, String businessOwnerEmail);

    CourseDetailsView getCourseDetailsById(String id);

    CourseEntity getCourseEntityById(String id);

    void createEmployees(List<EmployeeBindingModel> employees);

    CourseStreamDetailsView getCourseLecturesDetails(String courseId);

    CourseStatusView getEmployeeCourseStatus(String eId, String cId);

    void changeEmployeeCourseStatus(HttpServletRequest request, String courseId);

    EmployeeCoursesView getCoursesByEmployee(String employeeId);

    void addPictureToCourse(String id, MultipartFile picture) throws IOException;

    List<EmployeeCoursesDto> getRandomCourses(HttpServletRequest request);
}







