package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User was not created")
public class UserWrongCredentials extends RuntimeException {
    private int statusCode;

    public UserWrongCredentials() {
    }

    public UserWrongCredentials(String message) {
        super(message);
        this.statusCode = HttpStatus.BAD_REQUEST.value();
    }

    public int getStatusCode() {
        return statusCode;
    }
}
