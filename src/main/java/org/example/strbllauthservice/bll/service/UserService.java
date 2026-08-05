package org.example.strbllauthservice.bll.service;

import io.jsonwebtoken.JwtException;
import org.example.strbllauthservice.bll.model.User;
import org.example.strbllauthservice.dal.converter.UserConverter;
import org.example.strbllauthservice.dal.repository.UserRepository;
import org.example.strbllauthservice.ep.dto.SignInDto;
import org.example.strbllauthservice.ep.dto.SignUpDto;
import org.example.strbllauthservice.ep.dto.TokenResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    //TODO: add login and password checks
    public TokenResponse signUp(SignUpDto signUpDto) {
        if(userRepository.existsByLogin(signUpDto.getLogin()))
            throw new IllegalArgumentException("Login already exists");

        UUID id = UUID.randomUUID();
        User user = new User();
        user.setId(id);
        user.setLogin(signUpDto.getLogin());
        user.setPasswordHash(passwordEncoder.encode(signUpDto.getPassword()));
        String refreshToken = jwtService.generateRefreshToken(user);
        user.setRefreshToken(refreshToken);

        user = UserConverter.toModel(userRepository.save(UserConverter.toEntity(user)));

        return new TokenResponse(refreshToken, jwtService.generateAccessToken(user));
    }

    public TokenResponse signIn(SignInDto signInDto) {
        User user = UserConverter.toModel(userRepository.findUserEntityByLogin(signInDto.getLogin())
                .orElseThrow(() -> new BadCredentialsException("Invalid user credentials")));

        if(!passwordEncoder.matches(signInDto.getPassword(), user.getPasswordHash()))
            throw new BadCredentialsException(
                    "Invalid user credentials"
            );
        String refreshToken = jwtService.generateRefreshToken(user);
        user.setRefreshToken(refreshToken);
        user = UserConverter.toModel(userRepository.save(UserConverter.toEntity(user)));

        return new TokenResponse(refreshToken, jwtService.generateAccessToken(user));
    }

    //TODO: rotational refresh token
    public TokenResponse refresh(String refreshToken) {
        UUID id;
        try {
            id = jwtService.parseId(refreshToken);
        }
        catch (JwtException e) {
            throw new BadCredentialsException("Invalid refresh token");
        }

        User user = UserConverter.toModel(userRepository.findById(id)
                .orElseThrow(() -> new BadCredentialsException("Invalid refresh token")));

        if(!refreshToken.equals(user.getRefreshToken())) {
            throw new BadCredentialsException("Invalid refresh token");
        }

        String accessToken = jwtService.generateAccessToken(user);

        return new TokenResponse(refreshToken, accessToken);
    }
}
