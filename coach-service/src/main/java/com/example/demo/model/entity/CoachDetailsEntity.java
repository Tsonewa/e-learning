package com.example.demo.model.entity;


import com.example.demo.model.binding.GoalBinding;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "coach_details")
public class CoachDetailsEntity extends BaseEntity {

    private String description;
    private String duration;
    private String introductionVideo;
    private String goals;
    private CoachEntity coach;

    public CoachDetailsEntity() {
    }

    public String getDescription() {
        return description;
    }

    public CoachDetailsEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDuration() {
        return duration;
    }

    public CoachDetailsEntity setDuration(String duration) {
        this.duration = duration;
        return this;
    }

    public String getIntroductionVideo() {
        return introductionVideo;
    }

    public CoachDetailsEntity setIntroductionVideo(String introductionVideo) {
        this.introductionVideo = introductionVideo;
        return this;
    }

    public String getGoals() {
        return goals;
    }

    public CoachDetailsEntity setGoals(String goals) {
        this.goals = goals;
        return this;
    }

    @OneToOne
    public CoachEntity getCoach() {
        return coach;
    }

    public CoachDetailsEntity setCoach(CoachEntity coach) {
        this.coach = coach;
        return this;
    }
}
