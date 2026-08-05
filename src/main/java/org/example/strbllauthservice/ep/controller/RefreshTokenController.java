package org.example.strbllauthservice.ep.controller;

import org.example.strbllauthservice.bll.service.UserService;
import org.example.strbllauthservice.ep.dto.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefreshTokenController {
    private final UserService userService;

    public RefreshTokenController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<TokenResponse> refresh(String refreshToken) {
        TokenResponse tokenResponse = userService.refresh(refreshToken);
        return ResponseEntity.ok(tokenResponse);
    }
}
