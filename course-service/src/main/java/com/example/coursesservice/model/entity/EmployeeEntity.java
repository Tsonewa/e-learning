package com.example.coursesservice.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "employees")
public class EmployeeEntity {
    private String employeeId;
    private String email;
    private String fullName;
    private List<EmployeeCourseEntity> employeeCourses = new ArrayList<>();
    private BusinessOwnerEntity businessOwner;

    public EmployeeEntity() {
    }

    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(name = "uuid-string", strategy = "org.hibernate.id.UUIDGenerator")
    public String getEmployeeId() {
        return employeeId;
    }

    public EmployeeEntity setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public EmployeeEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @OneToMany(mappedBy = "employeeEntity", cascade = CascadeType.ALL)
    public List<EmployeeCourseEntity> getEmployeeCourses() {
        return employeeCourses;
    }

    public EmployeeEntity setEmployeeCourses(List<EmployeeCourseEntity> employeeCourses) {
        this.employeeCourses = employeeCourses;
        return this;
    }

    @ManyToOne
    public BusinessOwnerEntity getBusinessOwner() {
        return businessOwner;
    }

    public EmployeeEntity setBusinessOwner(BusinessOwnerEntity businessOwner) {
        this.businessOwner = businessOwner;
        return this;
    }
}
