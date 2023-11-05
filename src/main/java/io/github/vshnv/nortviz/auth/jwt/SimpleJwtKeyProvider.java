package io.github.vshnv.nortviz.auth.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class SimpleJwtKeyProvider implements JwtKeyProvider {
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public SecretKey getKey() {
        return key;
    }
}