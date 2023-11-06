package io.github.vshnv.nortviz.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AuthToken {
    private String token;
    private long expirationSeconds;

    public AuthToken(String token, long expirationSeconds) {
        this.token = token;
        this.expirationSeconds = expirationSeconds;
    }

    public AuthToken() {

    }

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    @JsonProperty("secondsToExpiration")
    public long getExpirationSeconds() {
        return expirationSeconds;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpirationSeconds(long expirationSeconds) {
        this.expirationSeconds = expirationSeconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthToken that = (AuthToken) o;
        return getExpirationSeconds() == that.getExpirationSeconds() &&
                getToken().equals(that.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken(), getExpirationSeconds());
    }
}
