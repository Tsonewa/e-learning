package com.example.demo.repository;

import com.example.demo.model.binding.GoalBinding;
import com.example.demo.model.entity.BO_Entity;
import com.example.demo.model.entity.CoachEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessOwnerRepository extends JpaRepository<BO_Entity, String> {

    BO_Entity findByBOEmail(String userBO_email);

    boolean existsBO_EntitiesByCoachesId(String coachId);

}
