package com.example.demo.service;

import com.example.demo.exceptions.CompanyDuplicationException;
import com.example.demo.model.dto.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

public interface AuthService {

    void loginUser(HttpServletResponse httpServletResponse, Optional<UserDetailAuthDto> user);

    List<EmployeeRegisterOutDto> registerEmployees(List<EmployeeBindingModel> employeeBindingModels) throws InvocationTargetException, IllegalAccessException;

    void changeEmployeePassword(UserPasswordChangeDto userPasswordChangeDto);

    Optional<UserDetailAuthDto> businessOwnerRegister(UserRegisterDto userRegisterDto) throws InvocationTargetException, IllegalAccessException;

}
