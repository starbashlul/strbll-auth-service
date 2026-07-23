package org.example.strbllauthservice.dal.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(name = "id_seq", sequenceName = "id_seq", allocationSize = 1, initialValue = 10000)
    private Integer id;

    private String login;

    private String passwordHash;

    private String refreshToken;

    public UserEntity() {
        //default
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof UserEntity that)) return false;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("login='" + login + "'")
                .add("passwordHash='" + passwordHash + "'")
                .add("refreshToken='" + refreshToken + "'")
                .toString();
    }
}
