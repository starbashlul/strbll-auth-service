package org.example.strbllauthservice.dal.repository;

import org.example.strbllauthservice.dal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findUserEntityByLogin(String login);
    boolean existsByLogin(String login);
}
