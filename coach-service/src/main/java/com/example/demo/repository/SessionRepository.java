package com.example.demo.repository;

import com.example.demo.model.entity.CoachEntity;
import com.example.demo.model.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<SessionEntity, String> {

    List<SessionEntity> findAllByCoach_Id(String id);

    void deleteByCoachId(String coachId);

    void deleteByCoach(CoachEntity coach);

}
