package com.example.demo.service.impl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import static com.example.demo.constants.Constants.*;
import com.example.demo.model.dto.UserDetailAuthDto;
import com.example.demo.service.JWTService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;


@Service
public class JWTServiceImpl implements JWTService {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;

    @Override
    public String generateToken(UserDetailAuthDto userDetailLoginDto) {
        Algorithm algorithm = Algorithm.HMAC512(secret);
        Date now = new Date();

        return JWT.create()
                .withSubject(userDetailLoginDto.getEmail())
                .withIssuedAt(now)
                .withClaim(ROLE_CLAIM, userDetailLoginDto.getRoles())
                .withClaim(EMAIL_CLAIM, userDetailLoginDto.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .sign(algorithm);

    }




}
