package org.example.strbllauthservice.ep.controller;

import org.example.strbllauthservice.bll.service.UserService;
import org.example.strbllauthservice.ep.dto.SignInDto;
import org.example.strbllauthservice.ep.dto.SignUpDto;
import org.example.strbllauthservice.ep.dto.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<TokenResponse> signInUser(@RequestBody SignInDto signInDto) {
        return ResponseEntity.ok(userService.signIn(signInDto));
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<TokenResponse> signUpUser(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(userService.signUp(signUpDto));
    }
    //TODO: add user info change
}
