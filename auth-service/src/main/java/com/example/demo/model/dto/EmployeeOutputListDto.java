package com.example.demo.model.dto;

import java.util.List;

public class EmployeeOutputListDto {

    private List<EmployeeRegisterOutDto> employees;

    public EmployeeOutputListDto() {
    }

    public EmployeeOutputListDto(List<EmployeeRegisterOutDto> employees) {
        this.employees = employees;
    }

    public List<EmployeeRegisterOutDto> getEmployees() {
        return employees;
    }

    public EmployeeOutputListDto setEmployees(List<EmployeeRegisterOutDto> employees) {
        this.employees = employees;
        return this;
    }
}
