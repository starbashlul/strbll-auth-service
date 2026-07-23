package org.example.strbllauthservice.bll.service;

import org.example.strbllauthservice.bll.model.User;
import org.example.strbllauthservice.dal.repository.UserRepository;
import org.example.strbllauthservice.ep.dto.SignUpDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(SignUpDto signUpDto) {
        User user = new User();
        user.setLogin(signUpDto.getLogin());
        user.setPasswordHash();


    }
}
