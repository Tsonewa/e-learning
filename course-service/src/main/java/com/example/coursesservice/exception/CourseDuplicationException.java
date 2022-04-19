package com.example.coursesservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Course already exists")
public class CourseDuplicationException extends Exception {

    private int statusCode;

    public CourseDuplicationException() {
    }

    public CourseDuplicationException(String message) {
        super(message);
        this.statusCode = HttpStatus.BAD_REQUEST.value();
    }

    public int getStatusCode() {
        return statusCode;
    }

}
