package com.example.demo.service;

public interface PasswordManagementService {

    String decodePassword(String encodePassword);

    String encodePassword(String encodePassword);

}
