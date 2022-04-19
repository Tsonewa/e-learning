package com.example.coursesservice.repository;

import com.example.coursesservice.model.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
    @Query("select e from EmployeeEntity as e where e.businessOwner.email=:businessOwnerEmail")
    List<EmployeeEntity> getAllEmployeesByBusinessOwner(String businessOwnerEmail);

    @Query("select e from EmployeeEntity as e where e.email=:employeeEmail")
    EmployeeEntity getByEmail(String employeeEmail);
}
