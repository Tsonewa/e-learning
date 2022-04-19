package com.example.coursesservice.model.entity;

import com.example.coursesservice.model.enums.StatusNameEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employees_courses")
public class EmployeeCourseEntity implements Serializable {

    private EmployeeCourseKey key;
    private EmployeeEntity employeeEntity;
    private CourseEntity courseEntity;
    private StatusNameEnum status;

    public EmployeeCourseEntity() {
    }

    public EmployeeCourseEntity( EmployeeEntity employeeEntity, CourseEntity courseEntity, StatusNameEnum status) {
        this.key = new EmployeeCourseKey();
        this.employeeEntity = employeeEntity;
        this.courseEntity = courseEntity;
        this.status = status;
    }

    public EmployeeCourseEntity(EmployeeEntity employeeEntity, CourseEntity courseEntity) {
        this.key = new EmployeeCourseKey(employeeEntity.getEmployeeId(),courseEntity.getCourseId());
        this.employeeEntity = employeeEntity;
        this.courseEntity = courseEntity;
    }

    @EmbeddedId
    public EmployeeCourseKey getKey() {
        return key;
    }

    public EmployeeCourseEntity setKey(EmployeeCourseKey key) {
        this.key = key;
        return this;
    }

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    public EmployeeEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public EmployeeCourseEntity setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
        return this;
    }

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    public CourseEntity getCourseEntity() {
        return courseEntity;
    }

    public EmployeeCourseEntity setCourseEntity(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
        return this;
    }

    public StatusNameEnum getStatus() {
        return status;
    }

    public EmployeeCourseEntity setStatus(StatusNameEnum status) {
        this.status = status;
        return this;
    }
}
