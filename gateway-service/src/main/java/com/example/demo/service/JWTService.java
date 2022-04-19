package com.example.demo.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.dto.UserLoginRequestDto;

import java.util.Optional;

public interface JWTService {

    Optional<DecodedJWT> getDecodedJWT(String token);

    boolean isInBlackList(String token);

    void cacheIssuedHeaderToken(UserLoginRequestDto userLoginRequestDto, String headerToken);

    Optional<String> getCachedHeaderToken(UserLoginRequestDto userLoginRequestDto);

    Optional<DecodedJWT> decodeJWT(String token);

}
