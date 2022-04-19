package com.example.demo.service;

import com.example.demo.model.dto.UserDetailAuthDto;

public interface JWTService {
    public String generateToken(UserDetailAuthDto userDetailLoginDto);
}
