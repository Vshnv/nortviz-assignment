package io.github.vshnv.nortviz.auth.jwt;

import javax.crypto.SecretKey;

public interface JwtKeyProvider {
    SecretKey getKey();
}