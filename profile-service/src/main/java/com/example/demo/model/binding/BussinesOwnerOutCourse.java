package com.example.demo.model.binding;

public class BussinesOwnerOutCourse {
    private String email;

    public BussinesOwnerOutCourse() {
    }

    public BussinesOwnerOutCourse(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public BussinesOwnerOutCourse setEmail(String email) {
        this.email = email;
        return this;
    }
}
