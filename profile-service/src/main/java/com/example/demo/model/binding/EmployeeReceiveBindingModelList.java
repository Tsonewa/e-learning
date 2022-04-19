package com.example.demo.model.binding;

import java.util.ArrayList;
import java.util.List;

public class EmployeeReceiveBindingModelList {

    List<EmployeeReceiveBindingModel> employees;

    public EmployeeReceiveBindingModelList() {
        this.employees = new ArrayList<>();
    }

    public List<EmployeeReceiveBindingModel> getEmployees() {
        return employees;
    }

    public EmployeeReceiveBindingModelList setEmployees(List<EmployeeReceiveBindingModel> employees) {
        this.employees = employees;
        return this;
    }
}
