package com.example.demo.exception;

public abstract class CustomEx extends RuntimeException {
    public CustomEx(String message) {
        super(message);
    }
}
