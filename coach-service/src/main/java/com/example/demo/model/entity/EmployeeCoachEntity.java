package com.example.demo.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "employee_coach")
public class EmployeeCoachEntity extends BaseEntity{

    private String emailEmployee;
    private List<CoachEntity> coaches;

    public EmployeeCoachEntity() {
    }

    public EmployeeCoachEntity(String emailEmployee, List<CoachEntity> coaches) {
        this.emailEmployee = emailEmployee;
        this.coaches = coaches;
    }

    public String getEmailEmployee() {
        return emailEmployee;
    }

    public EmployeeCoachEntity setEmailEmployee(String emailEmployee) {
        this.emailEmployee = emailEmployee;
        return this;
    }

    @ManyToMany
    public List<CoachEntity> getCoaches() {
        return coaches;
    }

    public EmployeeCoachEntity setCoaches(List<CoachEntity> coaches) {
        this.coaches = coaches;
        return this;
    }
}
