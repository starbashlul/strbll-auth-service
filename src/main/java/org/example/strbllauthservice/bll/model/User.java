package org.example.strbllauthservice.bll.model;

import java.util.Objects;
import java.util.StringJoiner;

public class User {
    private Integer id;

    private String login;

    private String passwordHash;

    private String refreshToken;

    public User() {
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
        if (!(o instanceof User user)) return false;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("login='" + login + "'")
                .add("passwordHash='" + passwordHash + "'")
                .add("refreshToken='" + refreshToken + "'")
                .toString();
    }
}
