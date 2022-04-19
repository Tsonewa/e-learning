package com.example.coursesservice.repository;

import com.example.coursesservice.model.entity.BusinessOwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessOwnerRepository extends JpaRepository<BusinessOwnerEntity, String> {

    BusinessOwnerEntity getBusinessOwnerEntityByEmail(String businessOwnerEmail);

    boolean existsByEmail(String businessOwnerEmail);
}
