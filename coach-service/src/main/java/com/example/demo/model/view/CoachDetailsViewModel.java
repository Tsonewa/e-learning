package com.example.demo.model.view;

import com.example.demo.model.entity.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CoachDetailsViewModel {

    private String id;
    private String name;
    private String email;
    private String company;
    private String topic;
    private String picture;
    private String description;
    private String duration;
    private String introductionVideo;
    private String goals;
    private Integer resource;


    public CoachDetailsViewModel() {
    }

    public String getId() {
        return id;
    }

    public CoachDetailsViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CoachDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CoachDetailsViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public CoachDetailsViewModel setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public CoachDetailsViewModel setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public CoachDetailsViewModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CoachDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDuration() {
        return duration;
    }

    public CoachDetailsViewModel setDuration(String duration) {
        this.duration = duration;
        return this;
    }

    public String getIntroductionVideo() {
        return introductionVideo;
    }

    public CoachDetailsViewModel setIntroductionVideo(String introductionVideo) {
        this.introductionVideo = introductionVideo;
        return this;
    }

    public String getGoals() {
        return goals;
    }

    public CoachDetailsViewModel setGoals(String goals) {
        this.goals = goals;
        return this;
    }

    public Integer getResource() {
        return resource;
    }

    public CoachDetailsViewModel setResource(Integer resource) {
        this.resource = resource;
        return this;
    }
}
