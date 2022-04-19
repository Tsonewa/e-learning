package com.example.demo.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "session")
public class SessionEntity extends BaseEntity {

    private LocalDateTime session;
    private Boolean available;
    private CoachEntity coach;

    public SessionEntity() {
    }

    public LocalDateTime getSession() {
        return session;
    }

    public SessionEntity setSession(LocalDateTime session) {
        this.session = session;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public SessionEntity setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @ManyToOne
    public CoachEntity getCoach() {
        return coach;
    }

    public SessionEntity setCoach(CoachEntity coach) {
        this.coach = coach;
        return this;
    }

}
