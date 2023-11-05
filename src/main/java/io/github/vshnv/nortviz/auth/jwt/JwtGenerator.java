package io.github.vshnv.nortviz.auth.jwt;

import io.github.vshnv.nortviz.entity.AuthToken;

public interface JwtGenerator {
    AuthToken generateToken(final String uid, final long expirationSeconds);
}
