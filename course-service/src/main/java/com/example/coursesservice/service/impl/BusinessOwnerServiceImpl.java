package com.example.coursesservice.service.impl;

import com.example.coursesservice.model.entity.BusinessOwnerEntity;
import com.example.coursesservice.model.entity.CourseEntity;
import com.example.coursesservice.model.view.EmployeesByCompanyOwnerView;
import com.example.coursesservice.repository.BusinessOwnerRepository;
import com.example.coursesservice.repository.CourseRepository;
import com.example.coursesservice.service.BusinessOwnerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessOwnerServiceImpl implements BusinessOwnerService {

    private final BusinessOwnerRepository businessOwnerRepository;
    private final CourseRepository courseRepository;

    public BusinessOwnerServiceImpl(BusinessOwnerRepository businessOwnerRepository, CourseRepository courseRepository) {
        this.businessOwnerRepository = businessOwnerRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public BusinessOwnerEntity getBusinessOwnerByEmail(String companyOwnerEmail) {
        return this.businessOwnerRepository.getBusinessOwnerEntityByEmail(companyOwnerEmail);
    }

    @Override
    public boolean isBusinessOwnerExist(String businessOwnerEmail) {

        return this.businessOwnerRepository.existsByEmail(businessOwnerEmail);
    }

    @Override
    public void createBusinessOwner(String businessOwnerEmail) {

        BusinessOwnerEntity businessOwnerEntity = new BusinessOwnerEntity()
                .setEmail(businessOwnerEmail)
                .setCourses(new ArrayList<>());

        this.businessOwnerRepository.save(businessOwnerEntity);
    }

    @Override
    public void save(BusinessOwnerEntity businessOwner) {
        this.businessOwnerRepository.save(businessOwner);
    }

    @Override
    public void addEmployeesToBusinessOwner(List<EmployeesByCompanyOwnerView> employeesByCompanyOwnerViews) {


    }
}