package com.example.demo.repository;

import com.example.demo.model.entity.CompanyEntity;
import com.example.demo.model.entity.UserProfileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {

    @Transactional(readOnly = true)
    Page<CompanyEntity> findAll(Pageable page);

    Optional<CompanyEntity> findByName(String name);

    Optional<CompanyEntity> findCompanyEntityByCompanyOwner(String email);

    Optional<CompanyEntity> findCompanyEntityByEmail(String email);


    @Query("select c.id from CompanyEntity as c where c.email=?1")
    String getCompanyId(String companyOwnerEmail);

}
