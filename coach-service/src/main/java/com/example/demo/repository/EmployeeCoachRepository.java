package com.example.demo.repository;

import com.example.demo.model.entity.CoachEntity;
import com.example.demo.model.entity.EmployeeCoachEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeCoachRepository extends JpaRepository<EmployeeCoachEntity, String> {

       EmployeeCoachEntity findByEmailEmployee(String email);

       void deleteByCoachesContains(CoachEntity coach);

       boolean existsEmployeeCoachEntityByCoachesId(String id);

}
