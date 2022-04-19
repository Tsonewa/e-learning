package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Provide valid data")
public class IncorrectData extends Exception {
    private int statusCode;

    public IncorrectData() {
    }

    public IncorrectData(String message) {
        super(message);
        this.statusCode = HttpStatus.BAD_REQUEST.value();
    }

    public int getStatusCode() {
        return statusCode;
    }
}
