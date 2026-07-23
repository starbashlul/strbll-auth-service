package org.example.strbllauthservice.ep.dto;

import java.util.StringJoiner;

public class TokenResponse {
    private String refreshToken;
    private String accessToken;

    public TokenResponse(String refreshToken, String accessToken) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TokenResponse.class.getSimpleName() + "[", "]")
                .add("refreshToken='" + refreshToken + "'")
                .add("accessToken='" + accessToken + "'")
                .toString();
    }
}
