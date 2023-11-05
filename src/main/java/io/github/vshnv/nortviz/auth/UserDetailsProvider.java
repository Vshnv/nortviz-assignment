package io.github.vshnv.nortviz.auth;

import io.github.vshnv.nortviz.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsProvider {
    UserDetails forUser(final User user);
}
