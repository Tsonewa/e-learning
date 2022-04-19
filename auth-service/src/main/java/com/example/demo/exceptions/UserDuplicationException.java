package com.example.demo.exceptions;

public class UserDuplicationException extends RuntimeException{

    private final String email;

    public UserDuplicationException(String email) {
        super("User with email " + email + " already exist");
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
