package io.github.vshnv.nortviz.auth;

import java.util.Optional;

public interface BearerTokenExtractor {
    Optional<String> extractToken(final String bearer);
}
