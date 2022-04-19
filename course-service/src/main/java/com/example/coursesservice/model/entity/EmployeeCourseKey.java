package com.example.coursesservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class EmployeeCourseKey  implements Serializable {
    private String employeeId;
    private String courseId;

    public EmployeeCourseKey() {
    }

    public EmployeeCourseKey(String id, String courseId) {
        this.employeeId=id;
        this.courseId=courseId;
    }

    @Column(name = "employee_id")
    public String getEmployeeId() {
        return employeeId;
    }

    public EmployeeCourseKey setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
        return this;
    }
    @Column(name ="course_id" )
    public String getCourseId() {
        return courseId;
    }

    public EmployeeCourseKey setCourseId(String courseId) {
        this.courseId = courseId;
        return this;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeCourseKey that = (EmployeeCourseKey) o;
        return Objects.equals(employeeId, that.employeeId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, courseId);
    }
}
