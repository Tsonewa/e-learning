package com.example.demo.model.binding;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBindingModelList implements Serializable {

    @Valid
    private List<EmployeeBindingModel> employees;


    public EmployeeBindingModelList() {
        this.employees = new ArrayList<>();
    }

    public EmployeeBindingModelList(List<EmployeeBindingModel> employees) {
        this.employees = employees;
    }

    public EmployeeBindingModelList setEmployees(List<EmployeeBindingModel> employees) {
        this.employees = employees;
        return this;
    }

    public List<EmployeeBindingModel> getEmployees() {
        return employees;
    }

}
