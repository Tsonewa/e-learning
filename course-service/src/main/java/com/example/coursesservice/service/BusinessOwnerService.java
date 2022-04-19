package com.example.coursesservice.service;

import com.example.coursesservice.model.entity.BusinessOwnerEntity;
import com.example.coursesservice.model.view.EmployeesByCompanyOwnerView;

import java.util.List;

public interface BusinessOwnerService {

    BusinessOwnerEntity getBusinessOwnerByEmail(String companyOwnerEmail);

    boolean isBusinessOwnerExist(String businessOwnerEmail);

    void createBusinessOwner(String businessOwnerEmail);

    void save(BusinessOwnerEntity businessOwner);

    void addEmployeesToBusinessOwner(List<EmployeesByCompanyOwnerView> employeesByCompanyOwnerViews);


}






