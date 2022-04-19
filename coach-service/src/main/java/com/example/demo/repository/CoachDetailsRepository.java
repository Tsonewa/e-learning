package com.example.demo.repository;

import com.example.demo.model.entity.CoachDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachDetailsRepository  extends JpaRepository<CoachDetailsEntity,String> {

    void deleteByCoachId(String coachId);
}
