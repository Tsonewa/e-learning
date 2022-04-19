package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Email already exists")
public class DuplicateEmailEmployees extends Exception {
    private int statusCode;

    public DuplicateEmailEmployees() {
    }

    public DuplicateEmailEmployees(String message) {
        super(message);
        this.statusCode = HttpStatus.BAD_REQUEST.value();
    }

    public int getStatusCode() {
        return statusCode;
    }
}

