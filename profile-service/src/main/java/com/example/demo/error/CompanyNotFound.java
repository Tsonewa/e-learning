package com.example.demo.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Company not found.")
public class CompanyNotFound extends Exception {

    private int statusCode;

    public CompanyNotFound() {
    }

    public CompanyNotFound(String message) {
        super(message);
        this.statusCode = HttpStatus.NOT_FOUND.value();
    }

    public int getStatusCode() {
        return statusCode;
    }
}
