package org.example.strbllauthservice.dal.repository;

import org.example.strbllauthservice.dal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findUserEntityByLogin(String login);
}
