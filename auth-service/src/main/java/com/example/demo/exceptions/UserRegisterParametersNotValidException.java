package com.example.demo.exceptions;

public class UserRegisterParametersNotValidException extends RuntimeException{

    private final String message;

    public UserRegisterParametersNotValidException(String message) {
       this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
