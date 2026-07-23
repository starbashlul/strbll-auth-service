package org.example.strbllauthservice.dal.converter;

import org.example.strbllauthservice.bll.model.User;
import org.example.strbllauthservice.dal.entity.UserEntity;

public class UserConverter {
    private UserConverter() {
        //hide
    }

    public static User toModel(UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setLogin(entity.getLogin());
        model.setPasswordHash(entity.getPasswordHash());
        model.setRefreshToken(entity.getRefreshToken());
        return model;
    }

    public static UserEntity toEntity(User model) {
        UserEntity entity = new UserEntity();
        entity.setId(entity.getId());
        entity.setLogin(entity.getLogin());
        entity.setPasswordHash(entity.getPasswordHash());
        entity.setRefreshToken(entity.getRefreshToken());
        return entity;
    }
}
