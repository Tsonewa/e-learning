package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Wrong credentials!")
public class UnauthorizedAccessException extends Exception{

    private int statusCode;

    public UnauthorizedAccessException() {
    }

    public UnauthorizedAccessException(String message) {
        super(message);
        this.statusCode = HttpStatus.UNAUTHORIZED.value();
    }

    public int getStatusCode() {
        return statusCode;
    }

}
