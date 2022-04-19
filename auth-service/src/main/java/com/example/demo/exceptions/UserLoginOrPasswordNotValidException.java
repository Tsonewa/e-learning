package com.example.demo.exceptions;

public class UserLoginOrPasswordNotValidException extends RuntimeException{

    private final String message;

    public UserLoginOrPasswordNotValidException(String message) {
       this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
