package org.example.strbllauthservice.bll.service;

import io.jsonwebtoken.Jwts;
import org.example.strbllauthservice.bll.model.User;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public JwtService(PrivateKey privateKey, PublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
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

    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .subject(user.getLogin())
                .id(UUID.randomUUID().toString())
                .issuedAt(new Date())
                .expiration(Date.from(
                        Instant.now().plus(15, ChronoUnit.DAYS)
                ))
                .signWith(privateKey, Jwts.SIG.RS256)
                .compact();
    }

    public String parse(String token) {
        return Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
