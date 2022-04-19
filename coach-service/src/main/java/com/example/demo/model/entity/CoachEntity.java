package com.example.demo.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "coaches")
public class CoachEntity extends BaseEntity{

    private String name;
    private String email;
    private String company;
    private String topic;
    private String picture;
    private BigDecimal price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<CategoryEntity> categories;
    private List<LanguageEntity> languages;
    private CoachDetailsEntity details;
    private List<ResourceEntity> resources;
    private List<FeedbackEntity> feedback;
    private List<SessionEntity> sessions;

    public CoachEntity() {
    }


    public String getName() {
        return name;
    }

    public CoachEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CoachEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public CoachEntity setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public CoachEntity setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public CoachEntity setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CoachEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public CoachEntity setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public CoachEntity setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    @ManyToMany
    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public CoachEntity setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
        return this;
    }

    @ManyToMany
    public List<LanguageEntity> getLanguages() {
        return languages;
    }

    public CoachEntity setLanguages(List<LanguageEntity> languages) {
        this.languages = languages;
        return this;
    }

    @OneToOne
    public CoachDetailsEntity getDetails() {
        return details;
    }

    public CoachEntity setDetails(CoachDetailsEntity details) {
        this.details = details;
        return this;
    }

    @OneToMany
    public List<ResourceEntity> getResources() {
        return resources;
    }

    public CoachEntity setResources(List<ResourceEntity> resources) {
        this.resources = resources;
        return this;
    }

    @OneToMany
    public List<FeedbackEntity> getFeedback() {
        return feedback;
    }

    public CoachEntity setFeedback(List<FeedbackEntity> feedback) {
        this.feedback = feedback;
        return this;
    }

    @OneToMany
    public List<SessionEntity> getSessions() {
        return sessions;
    }

    public CoachEntity setSessions(List<SessionEntity> sessions) {
        this.sessions = sessions;
        return this;
    }
}
