package org.example.strbllauthservice.ep.dto;

import java.util.StringJoiner;

public class SignUpDto {
    private String login;
    private String password;

    public SignUpDto() {
        //default
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SignUpDto.class.getSimpleName() + "[", "]")
                .add("login='" + login + "'")
                .add("password='" + password + "'")
                .toString();
    }
}
