package com.example.demo.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "business_owner_coach")
public class BO_Entity extends BaseEntity {

    private String BOEmail;
    private List<CoachEntity> coaches;

    public BO_Entity() {
    }

    @Column(name = "business_owner_email", nullable = false)
    public String getBOEmail() {
        return BOEmail;
    }

    public BO_Entity setBOEmail(String BOEmail) {
        this.BOEmail = BOEmail;
        return this;
    }

    @ManyToMany
    public List<CoachEntity> getCoaches() {
        return coaches;
    }

    public BO_Entity setCoaches(List<CoachEntity> coaches) {
        this.coaches = coaches;
        return this;
    }
}
