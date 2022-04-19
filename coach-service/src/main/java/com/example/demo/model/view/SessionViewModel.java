package com.example.demo.model.view;

import com.example.demo.model.entity.CoachEntity;

import java.time.LocalDateTime;

public class SessionViewModel {

    private String id;
    private LocalDateTime session;
    private Boolean available;

    public SessionViewModel() {
    }

    public String getId() {
        return id;
    }

    public SessionViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getSession() {
        return session;
    }

    public SessionViewModel setSession(LocalDateTime session) {
        this.session = session;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public SessionViewModel setAvailable(Boolean available) {
        this.available = available;
        return this;
    }
}
