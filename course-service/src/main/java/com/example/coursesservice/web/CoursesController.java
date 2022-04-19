package com.example.coursesservice.web;

import com.example.coursesservice.exception.CourseDuplicationException;
import com.example.coursesservice.model.binding.*;
import com.example.coursesservice.model.service.CategoryServiceDto;
import com.example.coursesservice.model.service.EmployeeCoursesDto;
import com.example.coursesservice.model.service.LanguageServiceDto;
import com.example.coursesservice.model.view.*;
import com.example.coursesservice.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static com.example.coursesservice.constant.AppConstants.*;


@RestController
@RequestMapping("/courses")
public class CoursesController {

    private final CourseService courseService;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final LanguageService languageService;
    private final BusinessOwnerService businessOwnerService;

    public CoursesController(CourseService courseService, ModelMapper modelMapper, CategoryService categoryService, LanguageService languageService, BusinessOwnerService businessOwnerService) {
        this.courseService = courseService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.languageService = languageService;
        this.businessOwnerService = businessOwnerService;
    }


    @PostMapping(value = "/create")
    public ResponseEntity<?> createCourse(@RequestBody CourseBindingModel courseBindingModel) throws CourseDuplicationException, IOException {

        String courseId = courseService.add(courseBindingModel);

        return ResponseEntity.status(201).body(courseId);
    }

    @PutMapping("/create/picture")
    public ResponseEntity<?> addPicture(@RequestPart(name = "imageUrl") MultipartFile imageUrl,
                                        @RequestPart(name = "id") String id) throws IOException {
        this.courseService.addPictureToCourse(id, imageUrl);
        return ResponseEntity.status(200).build();
    }


    @PostMapping("/edit/{id}")
    public ResponseEntity<?> editCourse(@RequestBody CourseEditBindingModel courseEditBindingModel,
                                        @PathVariable String id) throws IOException {

        courseService.editCourse(courseEditBindingModel, id);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {

        boolean isDeleted = this.courseService.deleteCourse(id);

        return ResponseEntity.ok().body(isDeleted);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<?> coursesDetailsList(@PathVariable String id) {

        CourseDetailsView courseDetailsView = this.courseService.getCourseDetailsById(id);

        return ResponseEntity.ok(courseDetailsView);
    }

    @GetMapping("/lectures/{courseId}")
    public ResponseEntity<?> getCourseLecturesList(@PathVariable String courseId) {

        CourseStreamDetailsView courseStreamDetailsView = this.courseService.getCourseLecturesDetails(courseId);

        return ResponseEntity.ok(courseStreamDetailsView);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable String id) {
        CourseEditViewModel courseToEdit = this.courseService.findCourseById(id);
        return ResponseEntity.ok(courseToEdit);
    }

    @GetMapping("/languages")
    public List<LanguageServiceDto> getAllLanguages() {
        return this.languageService.getAllLanguages();
    }

    @GetMapping("/categories")
    public List<CategoryServiceDto> getAllCategories() {
        return this.categoryService.getAllCategories();
    }

    @GetMapping()
    public List<CourseCardPriceView> getAllCourses(HttpServletRequest request) {
        return this.courseService.getAllCourses(request);
    }

    @GetMapping("/employee/dashboard")
    public List<EmployeeCoursesDto> getRandomCourses(HttpServletRequest request){
        return this.courseService.getRandomCourses(request);
    }

    @GetMapping("/company/catalog")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<?> getAllCoursesByBusinessOwner(
            HttpServletRequest request,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "4") int limit) {

        String companyOwnerEmail = request.getHeader("X-User-Email");

        List<CourseCardPriceView> allCoursesByBusinessOwner = this.courseService
                .getAllCoursesByBusinessOwner(companyOwnerEmail,
                        page, limit);

        return ResponseEntity.ok(allCoursesByBusinessOwner);
    }


    @PostMapping("/add/{courseId}")
    public ResponseEntity<?> addCourseToBusinessOwner(@PathVariable String courseId,
                                                      HttpServletRequest request) throws InvocationTargetException, IllegalAccessException, CourseDuplicationException {

        String businessOwnerEmail = request.getHeader("X-User-Email");

        this.courseService.addBusinessOwnerToCourse(courseId,
                businessOwnerEmail);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/remove/{courseId}")
    public ResponseEntity<?> removeCourseToBusinessOwner(@PathVariable String courseId,
                                                         HttpServletRequest request) {

        String businessOwnerEmail = request.getHeader("X-User-Email");
        this.courseService.removeBusinessOwnerToCourse(courseId,
                businessOwnerEmail);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchBy(@RequestBody SearchBindingModel searchBindingModel, HttpServletRequest request) {

        List<CourseCardPriceView> courses;

        if (searchBindingModel.getCategories().isEmpty()) {
            courses = this.courseService.searchCoursesByLanguages(searchBindingModel.getLanguages());
        } else if (searchBindingModel.getLanguages().isEmpty()) {
            courses = this.courseService.searchCoursesByCategorysName(searchBindingModel.getCategories());
        } else {
            courses = this.courseService.searchCourseByLanguageAndCategories(searchBindingModel);
        }

        this.courseService.setCoursesStatus(courses, request);

        return ResponseEntity.ok(courses);
    }


    @StreamListener(EMPLOYEES_CREATE_IN)
    public void employeeCreate(@Payload EmployeeBindingModelList employeeBindingModelList) {
        this.courseService.createEmployees(employeeBindingModelList.getEmployees());

    }

    @StreamListener(BUSINESS_OWNER_CREATE_IN)
    public void businessOwnerCreate(@Payload BusinessOwnerCreateBindilgModel ownerBindingModel) {
        this.businessOwnerService.createBusinessOwner(ownerBindingModel.getEmail());
    }

    @GetMapping("/get/status/{employeeId}/{courseId}")
    public ResponseEntity<CourseStatusView> getEmployeeCourseStatus(@PathVariable("employeeId") String employeeId, @PathVariable("courseId") String courseId) {
        CourseStatusView status = this.courseService.getEmployeeCourseStatus(employeeId, courseId);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/change/status/{courseId}")
    public ResponseEntity<CourseStatusView> changeEmployeeCourseStatus(HttpServletRequest request, @PathVariable("courseId") String courseId) {
        this.courseService.changeEmployeeCourseStatus(request, courseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/get-by-employee",
            produces = "application/json")
    public ResponseEntity<EmployeeCoursesView> getCoursesByEmployeeEmail(HttpServletRequest request) {
        String email = request.getHeader("X-User-Email");
        EmployeeCoursesView employeeCoursesViewList = this.courseService.getCoursesByEmployee(email);

        return ResponseEntity.ok(employeeCoursesViewList);
    }
}
