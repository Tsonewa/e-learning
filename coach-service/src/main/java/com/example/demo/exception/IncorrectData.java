package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Provide valid data")
public class IncorrectData extends CustomEx {
    public IncorrectData(String message) {
        super(message);
    }
}
