package com.example.coursesservice.service.impl;

import com.example.coursesservice.exception.CourseDuplicationException;
import com.example.coursesservice.exception.CourseNotFoundException;
import com.example.coursesservice.model.binding.*;
import com.example.coursesservice.model.entity.*;
import com.example.coursesservice.model.enums.CategoryNameEnum;
import com.example.coursesservice.model.enums.LanguageEnum;
import com.example.coursesservice.model.enums.StatusNameEnum;
import com.example.coursesservice.model.service.EmployeeCoursesDto;
import com.example.coursesservice.model.view.*;
import com.example.coursesservice.repository.*;
import com.example.coursesservice.service.BusinessOwnerService;
import com.example.coursesservice.service.CloudinaryService;
import com.example.coursesservice.service.CourseService;
import com.example.coursesservice.stream.StreamChannelDispatcher;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.coursesservice.constant.AppConstants.*;

@Service
public class CourseServiceImpl implements CourseService {

    public static int RANDOM_EMPLOYEE_COURSES_UPPERBOUND = 2;

    private final ModelMapper modelMapper;
    private final LectureRepository lectureRepository;
    private final LanguageRepository languageRepository;
    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final BusinessOwnerRepository businessOwnerRepository;
    private final CloudinaryService cloudinaryService;
    private final BusinessOwnerService businessOwnerService;
    private final StreamChannelDispatcher streamChannelDispatcher;
    private final EmployeeRepository employeeRepository;

    public CourseServiceImpl(ModelMapper modelMapper, LectureRepository lectureRepository, LanguageRepository languageRepository, CategoryRepository categoryRepository, CourseRepository courseRepository, BusinessOwnerRepository businessOwnerRepository, CloudinaryService cloudinaryService, BusinessOwnerService businessOwnerService, StreamChannelDispatcher streamChannelDispatcher, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.lectureRepository = lectureRepository;
        this.languageRepository = languageRepository;
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
        this.businessOwnerRepository = businessOwnerRepository;
        this.cloudinaryService = cloudinaryService;
        this.businessOwnerService = businessOwnerService;
        this.streamChannelDispatcher = streamChannelDispatcher;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String add(CourseBindingModel courseBindingModel) throws CourseDuplicationException, IOException {

        if (isExist(courseBindingModel.getName())) {
            throw new CourseDuplicationException();
        }
        CourseEntity course = modelMapper.map(courseBindingModel, CourseEntity.class);

        List<LectureEntity> lectures = courseBindingModel.getLectures().stream()
                .map(lecture -> {
                    LectureEntity lectureEntity = modelMapper.map(lecture, LectureEntity.class);

                    this.lectureRepository.save(lectureEntity);
                    return lectureEntity;
                }).collect(Collectors.toList());

        List<LanguageEntity> languages = getLanguagesEnum(courseBindingModel);


        List<CategoryEntity> categories = courseBindingModel.getCategories().stream()
                .map(c -> this.categoryRepository.getCategoryByName
                        (CategoryNameEnum.valueOf(c.getName().toUpperCase()))).collect(Collectors.toList());

        course.setCategories(categories);
        course.setLanguages(languages);
        course.setLectures(lectures);

        CourseEntity save = this.courseRepository.save(course);
        return save.getCourseId();

    }

    private List<LanguageEntity> getLanguagesEnum(CourseBindingModel courseBindingModel) {
        return courseBindingModel.getLanguages().stream()
                .map(l -> this.languageRepository.getByName
                        (LanguageEnum.valueOf(l.getName().toUpperCase()))).collect(Collectors.toList());
    }


    @Override
    public void addPictureToCourse(String id, MultipartFile picture) throws IOException {
        CourseEntity courseForAddPicture = this.courseRepository.findById(id).get();
        courseForAddPicture.setImageUrl(this.cloudinaryService.upload(picture).getUrl());
        this.courseRepository.save(courseForAddPicture);
    }

    @Override
    public boolean deleteCourse(String id) {

        CourseEntity courseToDelete = this.courseRepository.getById(id);
        if (courseToDelete.getBusinessOwners().isEmpty()) {
            deleteCloudinaryPicture(this.courseRepository.getById(id));
            this.courseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private boolean deleteCloudinaryPicture(CourseEntity courseToDelete) {
        String publicId = courseToDelete.getImageUrl()
                .substring(courseToDelete.getImageUrl().lastIndexOf(CLOUDINARY_CUT_START) + CLOUDINARY_CUT_INDEX,
                        courseToDelete.getImageUrl().lastIndexOf(CLOUDINARY_CUT_END));

        return this.cloudinaryService.delete(publicId);

    }


    public void editCourse(CourseEditBindingModel courseEditBindingModel, String id) throws IOException {

        CourseEntity courseEntity = this.courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        List<LectureEditBindingModel> lecturesForEdit = courseEditBindingModel.getLectures();


        List<LectureEntity> lectures = lecturesForEdit.stream()
                .map(lecture -> {
                    LectureEntity lectureEntity = new LectureEntity();

                    if (null != lecture.getId()) {
                        lectureEntity = this.lectureRepository.getById(lecture.getId());
                    }

                    lectureEntity.setLectureName(lecture.getLectureName())
                            .setLectureDescription(lecture.getLectureDescription())
                            .setResourceUrl(lecture.getResourceUrl());

                    return this.lectureRepository.save(lectureEntity);
                }).collect(Collectors.toList());
        courseEntity.setLectures(lectures);


        List<LanguageEntity> languages = courseEditBindingModel.getLanguages().stream()
                .map(l -> this.languageRepository.getByName
                        (LanguageEnum.valueOf(l.getName().toUpperCase()))).collect(Collectors.toList());

        List<CategoryEntity> categories = courseEditBindingModel.getCategories().stream()
                .map(c -> this.categoryRepository.getCategoryByName
                        (CategoryNameEnum.valueOf(c.getName().toUpperCase()))).collect(Collectors.toList());

        courseEntity.setName(courseEditBindingModel.getName())
                .setLanguages(languages)
                .setCategories(categories)
                .setDescription(courseEditBindingModel.getDescription())
                .setDuration(courseEditBindingModel.getDuration())
                .setStartDate(courseEditBindingModel.getStartDate())
                .setEndDate(courseEditBindingModel.getEndDate())
                .setLector(courseEditBindingModel.getLector())
                .setLectorDescription(courseEditBindingModel.getLectorDescription())
                .setPrice(courseEditBindingModel.getPrice())
                .setSkills(courseEditBindingModel.getSkills())
                .setVideoUrl(courseEditBindingModel.getVideoUrl());
        this.courseRepository.save(courseEntity);
    }


    @Override
    public CourseEditViewModel findCourseById(String id) {
        CourseEntity courseEntity = this.courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));

        return modelMapper.map(courseEntity, CourseEditViewModel.class);
    }

    @Override
    public List<CourseCardPriceView> getAllCourses(HttpServletRequest request) {

        List<CourseCardPriceView> courses = this.courseRepository.findAll().stream()
                .map(c -> {
                    CourseCardPriceView map = modelMapper.map(c, CourseCardPriceView.class);
                    map.setImageUrl(c.getImageUrl());
                    map.setCourseStatus(COURSE_STATUS_DELETE);
                    return map;
                }).collect(Collectors.toList());

        if (request.getHeader("X-User-Roles").equals(BUSINESS_OWNER_ROLE)) {
            setCoursesStatus(courses, request);
        }
        return courses;
    }

    @Override
    public List<CourseCardPriceView> getAllCoursesByBusinessOwner(String companyOwnerEmail, int page, int limit) {

        if (page > 0) {
            page = page - 1;
        }

        Pageable pageableRequest = PageRequest.of(page, limit);

        return this.courseRepository
                .getAllByBusinessOwnersEmail(companyOwnerEmail, pageableRequest)
                .stream()
                .map(s -> {
                    CourseCardPriceView course = this.modelMapper.map(s, CourseCardPriceView.class);
                    course.setCourseStatus(COURSE_STATUS_REMOVE);

                    return course;
                }).collect(Collectors.toList());
    }

    @Override
    public void addBusinessOwnerToCourse(String courseId, String businessOwnerEmail) throws InvocationTargetException, IllegalAccessException, CourseDuplicationException {
        Optional<CourseEntity> courseEntity = this.courseRepository.getByBusinessOwner(courseId, businessOwnerEmail);

        if (courseEntity.isPresent()) {
            throw new CourseDuplicationException("Course already added");
        }
        CourseEntity course = this.courseRepository.getById(courseId);

        BusinessOwnerEntity businessOwner = this.businessOwnerRepository
                .getBusinessOwnerEntityByEmail(businessOwnerEmail);

        if (businessOwner == null) {
            throw new NoSuchElementException("not found");
        }

        course.getBusinessOwners().add(businessOwner);

        List<EmployeeEntity> employees = this.employeeRepository.getAllEmployeesByBusinessOwner(businessOwnerEmail);
        if (!employees.isEmpty()) {
            employees.forEach(e -> {
                EmployeeCourseEntity employeeCourseEntity = new EmployeeCourseEntity(e, course);
                employeeCourseEntity.setStatus(StatusNameEnum.ENROLL);
                e.getEmployeeCourses().add(employeeCourseEntity);

                this.employeeRepository.saveAndFlush(e);
            });
        }
        this.courseRepository.save(course);
    }

    @Override
    public List<CourseCardPriceView> searchCourseByLanguageAndCategories(SearchBindingModel searchBindingModel) {
        List<CategoryNameEnum> categoryNameEnumsList = searchBindingModel.getCategories().stream().map(c -> CategoryNameEnum.valueOf(c.getName().toUpperCase())).toList();
        List<LanguageEnum> languageEnumList = searchBindingModel.getLanguages().stream().map(l -> LanguageEnum.valueOf(l.getName().toUpperCase())).toList();
        Optional<CourseEntity[]> courses = this.courseRepository.SearchByCategoryAndLanguage(categoryNameEnumsList, languageEnumList);

        return Arrays.asList(this.modelMapper.map(courses.get(), CourseCardPriceView[].class));
    }

    @Override
    public List<CourseCardPriceView> searchCoursesByCategorysName(List<CategoryNameBindingModel> categories) {
        List<CategoryNameEnum> categoryEnumList = categories.stream().map(l -> CategoryNameEnum.valueOf(l.getName().toUpperCase())).toList();
        List<CourseEntity> allByLanguages = this.courseRepository.getAllByCategory(categoryEnumList);

        return allByLanguages.stream().map(c -> this.modelMapper.map(c, CourseCardPriceView.class)).collect(Collectors.toList());
    }

    @Override
    public List<CourseCardPriceView> searchCoursesByLanguages(List<LanguageNameBindingModel> languages) {
        List<LanguageEnum> languageEnumList = languages.stream().map(l -> LanguageEnum.valueOf(l.getName().toUpperCase())).toList();
        List<CourseEntity> allByLanguages = this.courseRepository.getAllByLanguages(languageEnumList);

        return allByLanguages.stream().map(c -> this.modelMapper.map(c, CourseCardPriceView.class)).collect(Collectors.toList());
    }

    @Override
    public void setCoursesStatus(List<CourseCardPriceView> courses, HttpServletRequest request) {

        String businessOwnerEmail = request.getHeader("X-User-Email");
        if (request.getHeader("X-User-Roles").equals(ADMIN_ROLE)) {
            courses.forEach(c -> c.setCourseStatus(COURSE_STATUS_DELETE));
        } else if (this.businessOwnerService.isBusinessOwnerExist(businessOwnerEmail)) {
            List<CourseEntity> businessOwnerCourses = this.businessOwnerService
                    .getBusinessOwnerByEmail(businessOwnerEmail).getCourses();

            for (CourseCardPriceView course : courses) {

                Optional<CourseEntity> enrolledCourse = businessOwnerCourses
                        .stream()
                        .filter(c -> c.getName().equals(course.getName())).findAny();

                if (enrolledCourse.isPresent()) {
                    course.setCourseStatus(COURSE_STATUS_REMOVE);
                } else {
                    course.setCourseStatus(COURSE_STATUS_ADD);
                }
            }
        } else {
            this.businessOwnerService.createBusinessOwner(businessOwnerEmail);
        }
    }

    @Override
    public void removeBusinessOwnerToCourse(String courseId, String businessOwnerEmail) {
        CourseEntity course = this.courseRepository.getById(courseId);

        BusinessOwnerEntity businessOwner = this.businessOwnerRepository
                .getBusinessOwnerEntityByEmail(businessOwnerEmail);

        course.getBusinessOwners().remove(businessOwner);
        businessOwner.getCourses().remove(course);
        this.courseRepository.save(course);
        this.businessOwnerService.save(businessOwner);
    }

    @Override
    public CourseDetailsView getCourseDetailsById(String id) {
        CourseEntity currentCourse = this.courseRepository.getById(id);

        CourseDetailsView courseDetailsView = this.modelMapper
                .map(currentCourse, CourseDetailsView.class);

        courseDetailsView.setLecturesCount(currentCourse.
                getLectures().size());
        return courseDetailsView;
    }

    @Override
    public CourseEntity getCourseEntityById(String id) {
        return this.courseRepository.getById(id);
    }

    @Override
    public void createEmployees(List<EmployeeBindingModel> employees) {
        String businessOwnerEmail = employees.get(0).getCompanyOwner();

        List<CourseEntity> courseEntityList = this.courseRepository.getAllByBusinessOwnersEmail(businessOwnerEmail, null);
        BusinessOwnerEntity businessOwner = this.businessOwnerRepository.getBusinessOwnerEntityByEmail(businessOwnerEmail);

        if (courseEntityList.isEmpty()) {
            employees.forEach(e -> {
                EmployeeEntity employeeEntity = this.modelMapper.map(e, EmployeeEntity.class);
                employeeEntity.setBusinessOwner(businessOwner);
                this.employeeRepository.saveAndFlush(employeeEntity);
            });
            return;
        }


        List<EmployeeEntity> employeeEntityList = employees.stream().map(e -> {
                    EmployeeEntity employee = this.modelMapper.map(e, EmployeeEntity.class);
                    employee.setBusinessOwner(businessOwner);
                    return employee;
                }
        ).toList();

        List<EmployeeEntity> employeeResult = employeeEntityList.stream().map(this.employeeRepository::saveAndFlush).toList();
        for (int i = 0; i < employeeEntityList.size(); i++) {
            int finalI = i;
            courseEntityList.stream().forEach(c -> {
                EmployeeCourseEntity employeeCourseEntity = new EmployeeCourseEntity(employeeResult.get(finalI), c);
                employeeCourseEntity.setStatus(StatusNameEnum.ENROLL);
                employeeResult.get(finalI).getEmployeeCourses().add(employeeCourseEntity);
            });

        }

        employeeResult.forEach(this.employeeRepository::saveAndFlush);
    }

    @Override
    public CourseStreamDetailsView getCourseLecturesDetails(String courseId) {
        CourseEntity currentCourse = this.getCourseEntityById(courseId);

        List<LecturesDetailsView> lectures = currentCourse.
                getLectures().stream()
                .map(l -> this.modelMapper.map(l, LecturesDetailsView.class))
                .collect(Collectors.toList());

        CourseStreamDetailsView courseStreamDetailsView = this.modelMapper.map(currentCourse, CourseStreamDetailsView.class)
                .setLectures(lectures);

        return courseStreamDetailsView;
    }

    @Override
    public CourseStatusView getEmployeeCourseStatus(String eId, String cId) {
        Optional<EmployeeCourseEntity> employeeCourseEntity = this.courseRepository.getEmployeeCourse(eId, cId);
        if (employeeCourseEntity.isEmpty()) {
            throw new NoSuchElementException("No such course or employee found");
        }
        return new CourseStatusView(employeeCourseEntity.get().getStatus().name());
    }

    @Override
    public void changeEmployeeCourseStatus(HttpServletRequest request, String courseId) {
        String employeeEmail = request.getHeader("X-User-Email");

        Optional<EmployeeCourseEntity> employeeCourseEntity = this.courseRepository.getEmployeeCourse(employeeEmail, courseId);
        if (employeeCourseEntity.isEmpty()) {
            throw new NoSuchElementException("Not found");
        }
        EmployeeCourseEntity employeeCourse = employeeCourseEntity.get();
        EmployeeEntity employee = this.employeeRepository.getByEmail(employeeEmail);
        List<EmployeeCourseEntity> employeeCourseList = employee.getEmployeeCourses().stream().filter(e -> !e.equals(employeeCourse)).collect(Collectors.toList());
        employeeCourseList.add(employeeCourse);
        StatusNameEnum status = employeeCourse.getStatus();
        if (status.equals(StatusNameEnum.CONTINUE)) {
            return;
        }
        employeeCourse.setStatus(status.getNext());
        employee.setEmployeeCourses(employeeCourseList);
        this.employeeRepository.saveAndFlush(employee);
    }

    @Override
    public EmployeeCoursesView getCoursesByEmployee(String employeeId) {
        List<EmployeeCoursesDto> coursesByEmployee = this.courseRepository.getAllCoursesByEmployee(employeeId);
        return new EmployeeCoursesView(coursesByEmployee);
    }

    @Override
    public List<EmployeeCoursesDto> getRandomCourses(HttpServletRequest request) {

        String employeeEmail = request.getHeader("X-User-Email");
        List<EmployeeCoursesDto> allCoursesByEmployee = this.courseRepository.getAllCoursesByEmployee(employeeEmail);

        List<EmployeeCoursesDto> randomCourses = new ArrayList<>();
        if(allCoursesByEmployee.size()<=2){
            return allCoursesByEmployee;
        }
        for (int i = 0; i < RANDOM_EMPLOYEE_COURSES_UPPERBOUND; i++) {
            int randomIndex = generateRandomNumberInBound(allCoursesByEmployee.size());
            randomCourses.add(allCoursesByEmployee.get(randomIndex));
            allCoursesByEmployee.remove(randomIndex);
        }

            return randomCourses;

    }

    private int generateRandomNumberInBound(int upperBound){
        Random rand = new Random();
        return rand.nextInt(upperBound);
        }


    private boolean isExist(String name) {
        return this.courseRepository.existsByName(name);
    }
}
