package org.example.strbllauthservice.bll.service;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.Resource;
import org.example.strbllauthservice.bll.model.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {

    private final PrivateKey privateKey;

    public JwtService(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getLogin())
                //.claim("roles", user.getRoles()) TODO: roles
                .issuedAt(new Date())
                .expiration(Date.from(
                        Instant.now().plus(15, ChronoUnit.MINUTES)
                ))
                .signWith(privateKey, Jwts.SIG.RS256)
                .compact();
    }
}
