package com.example.demo.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.constant.Constants;
import com.example.demo.dto.UserLoginRequestDto;
import com.example.demo.service.JWTService;
import org.redisson.api.RMapCache;
import org.redisson.api.RSetCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Service
public class JWTServiceImpl implements JWTService {
    private final RMapCache<String, DecodedJWT> validToken;
    private final RMapCache<UserLoginRequestDto, String> activeTokens;
    private final RSetCache<String> blockList;

    private final RedissonClient redissonClient;

    private final String secret;

    public JWTServiceImpl(RedissonClient redissonClient, @Value("${secret.value}") String secret) {
        this.redissonClient = redissonClient;
        this.secret = secret;
        this.validToken = redissonClient.getMapCache("valid_tokens");
        this.activeTokens = redissonClient.getMapCache("active_tokens");
        this.blockList = redissonClient.getSetCache("block_tokens");
    }

    public Optional<DecodedJWT> decodeJWT(String token) {
        try{
            DecodedJWT decodedToken = JWT.require(Algorithm.HMAC512(secret)).build().verify(token);

            this.validToken.put(token, decodedToken, decodedToken.getExpiresAt().getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);

            return Optional.of(decodedToken);
        }catch (Exception ex){
            this.blockList.add(token);

            return Optional.empty();
        }
    }

    @Override
    public Optional<DecodedJWT> getDecodedJWT(String token) {
        Optional<DecodedJWT> cachedDecodeJWT = this.getCachedDecodeJWT(token);

        return cachedDecodeJWT.isEmpty() ? this.decodeJWT(token) : cachedDecodeJWT;
    }

    @Override
    public boolean isInBlackList(String token) {
        return this.blockList.contains(token);
    }

    @Override
    public void cacheIssuedHeaderToken(UserLoginRequestDto userLoginRequestDto, String headerToken) {
        Optional<DecodedJWT> decodedJWT = this.getDecodedJWT(headerToken.replace(Constants.TOKEN_PREFIX, headerToken));

        long expirationTime = decodedJWT.get().getExpiresAt().getTime() - System.currentTimeMillis();

        this.activeTokens.put(userLoginRequestDto, headerToken, expirationTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public Optional<String> getCachedHeaderToken(UserLoginRequestDto userLoginRequestDto) {
        return Optional.ofNullable(this.activeTokens.get(userLoginRequestDto));
    }

    private Optional<DecodedJWT> getCachedDecodeJWT(String token) {
        return Optional.ofNullable(this.validToken.get(token));
    }
}
