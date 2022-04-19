package com.example.demo.model.view;

import java.time.LocalDateTime;

public class CoachDateTimeView {

    private String coachId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public CoachDateTimeView() {
    }

    public String getCoachId() {
        return coachId;
    }

    public CoachDateTimeView setCoachId(String coachId) {
        this.coachId = coachId;
        return this;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public CoachDateTimeView setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public CoachDateTimeView setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }
}
