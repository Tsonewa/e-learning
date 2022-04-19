package com.example.demo.model.view;

public class CountEmployeesView {
    private int countEmployees;

    public CountEmployeesView() {
    }

    public CountEmployeesView(int countEmployees) {
        this.countEmployees = countEmployees;
    }

    public int getCountEmployees() {
        return countEmployees;
    }

    public CountEmployeesView setCountEmployees(int countEmployees) {
        this.countEmployees = countEmployees;
        return this;
    }
}
