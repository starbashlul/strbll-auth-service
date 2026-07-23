package org.example.strbllauthservice.bll.service;

import org.example.strbllauthservice.bll.model.User;
import org.example.strbllauthservice.dal.converter.UserConverter;
import org.example.strbllauthservice.dal.repository.UserRepository;
import org.example.strbllauthservice.ep.dto.SignInDto;
import org.example.strbllauthservice.ep.dto.SignUpDto;
import org.example.strbllauthservice.ep.dto.TokenResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public TokenResponse signUp(SignUpDto signUpDto) {
        User user = new User();
        user.setLogin(signUpDto.getLogin());
        user.setPasswordHash(passwordEncoder.encode(signUpDto.getPassword()));
        user = UserConverter.toModel(userRepository.save(UserConverter.toEntity(user)));

        return new TokenResponse(jwtService.generateAccessToken(user), null);
    }

    public TokenResponse signIn(SignInDto signInDto) {
        User user = UserConverter.toModel(userRepository.findUserEntityByLogin(signInDto.getLogin()));

        if(!passwordEncoder.matches(signInDto.getPassword(), user.getPasswordHash()))
            throw new BadCredentialsException(
                    "Invalid credentials"
            );

        return new TokenResponse(jwtService.generateAccessToken(user), null);
    }

}
