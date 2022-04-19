package com.example.coursesservice.model.entity;

import com.example.coursesservice.model.enums.StatusNameEnum;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "courses")
public class CourseEntity  {
    private String courseId;
    private String name;
    private BigDecimal price;
    private StatusNameEnum status;
    private String description;
    private String videoUrl;
    private String imageUrl;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer duration;
    private List<CategoryEntity> categories;
    private List<LanguageEntity> languages;
    private List<LectureEntity> lectures;
    private String lector;
    private String lectorDescription;
    private List<BusinessOwnerEntity> businessOwners;
    private String skills;
    private List<EmployeeCourseEntity> employeeCourses = new ArrayList<>();


    public CourseEntity() {
    }

    public CourseEntity(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(name = "uuid-string", strategy = "org.hibernate.id.UUIDGenerator")
    public String getCourseId() {
        return courseId;
    }

    public CourseEntity setCourseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CourseEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @ManyToMany
    public List<BusinessOwnerEntity> getBusinessOwners() {
        return businessOwners;
    }

    public CourseEntity setBusinessOwners(List<BusinessOwnerEntity> businessOwners) {
        this.businessOwners = businessOwners;
        return this;
    }

    @Column(unique = true)
    public String getName() {
        return name;
    }

    public CourseEntity setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CourseEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public StatusNameEnum getStatus() {
        return status;
    }

    public CourseEntity setStatus(StatusNameEnum status) {
        this.status = status;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CourseEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getLector() {
        return lector;
    }

    public CourseEntity setLector(String lector) {
        this.lector = lector;
        return this;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public CourseEntity setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public CourseEntity setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public CourseEntity setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String getSkills() {
        return skills;
    }

    public CourseEntity setSkills(String skills) {
        this.skills = skills;
        return this;
    }

    @ManyToMany
    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public CourseEntity setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
        return this;
    }

    @ManyToMany
    public List<LanguageEntity> getLanguages() {
        return languages;
    }

    public CourseEntity setLanguages(List<LanguageEntity> languages) {
        this.languages = languages;
        return this;
    }

    @OneToMany(cascade = CascadeType.REMOVE)
    public List<LectureEntity> getLectures() {
        return lectures;
    }

    public CourseEntity setLectures(List<LectureEntity> lectures) {
        this.lectures = lectures;
        return this;
    }

    public String getLectorDescription() {
        return lectorDescription;
    }

    public CourseEntity setLectorDescription(String lectorDescription) {
        this.lectorDescription = lectorDescription;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public CourseEntity setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    @OneToMany(mappedBy = "courseEntity", cascade = CascadeType.ALL)
    public List<EmployeeCourseEntity> getEmployeeCourses() {
        return employeeCourses;
    }

    public CourseEntity setEmployeeCourses(List<EmployeeCourseEntity> employeeCourses) {
        this.employeeCourses = employeeCourses;
        return this;
    }
}
