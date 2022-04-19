package com.example.demo.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class FeedbackEntity extends BaseEntity{

    private String employeeEmail;
    private String message;
    private String subjects;
    private CoachEntity coach;

    public FeedbackEntity() {
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public FeedbackEntity setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public FeedbackEntity setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getSubjects() {
        return subjects;
    }

    public FeedbackEntity setSubjects(String subjects) {
        this.subjects = subjects;
        return this;
    }

    @ManyToOne
    public CoachEntity getCoach() {
        return coach;
    }

    public FeedbackEntity setCoach(CoachEntity coach) {
        this.coach = coach;
        return this;
    }
}
