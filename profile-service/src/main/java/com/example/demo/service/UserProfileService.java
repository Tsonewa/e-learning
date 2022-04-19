package com.example.demo.service;

import com.example.demo.error.UserNotFound;
import com.example.demo.model.binding.*;
import com.example.demo.model.view.CountEmployeesView;
import com.example.demo.model.view.EmployeesByCompanyOwnerView;
import com.example.demo.model.view.UserProfileView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface UserProfileService {

    <Т> void sendMessageOutBoundEmployeeInfo(Т kafkaModel);

    <Т> void sendMessageOutBoundEmployeePasswordChange(Т kafkaModel);

    <Т> void sendMessageOutBoundBusinessOwnerInfo(Т kafkaModel);

    void createUserProfile(UserProfileCreateBindingModel userProfile);

    UserProfileView getUserProfileById(String id) throws UserNotFound;

    UserProfileView editUserProfile(String email, UserProfileEditBindingModel userProfile, String password) throws IOException;

    void createEmployee(EmployeeReceiveBindingModelList employees);

    void sendEmployeePassword(EmployeePasswordChangeBindingModel employee);

    boolean findByEmailsEmployees(EmployeeBindingModelList employees);

    List<EmployeesByCompanyOwnerView> getAllEmployeesByCompanyOwner(String companyOwner, int page, int limit);

    UserProfileView getUserProfileByEmail(String userEmail) throws UserNotFound;

    void getCompanyOwnerEmail(String employeesEmail);

    String getEmployeeCompanyOwnerEmail(String employeeEmail);

    List<EmployeeBindingModel> getAllEmployeesByBusinessOwner(HttpServletRequest request,int page,int limit);

    CountEmployeesView getEmployeesCountByCompany(HttpServletRequest request);
}
