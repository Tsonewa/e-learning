package com.example.coursesservice.model.binding;

import java.io.Serializable;
import java.util.List;

public class EmployeeBindingModelList implements Serializable {
    private List<EmployeeBindingModel> employees;

    public EmployeeBindingModelList() {
    }

    public EmployeeBindingModelList(List<EmployeeBindingModel> employees) {
        this.employees = employees;
    }

    public List<EmployeeBindingModel> getEmployees() {
        return employees;
    }

    public EmployeeBindingModelList setEmployees(List<EmployeeBindingModel> employees) {
        this.employees = employees;
        return this;
    }
}
