package io.github.vshnv.nortviz.auth;

import io.github.vshnv.nortviz.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public final class SimpleUserDetailsProvider implements UserDetailsProvider {
    @Override
    public UserDetails forUser(final User user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getHashedPassword())
                .authorities("*")
                .build();
    }
}