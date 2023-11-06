package io.github.vshnv.nortviz.service;

import io.github.vshnv.nortviz.entity.User;

/**
 * Provides access to user data
 */
public interface UserService {
    /**
     * Fetches user with specified username
     * @param username username if user to be fetched
     * @return User if present, null if not
     */
    User fetchUser(String username);
    /**
     * Fetches user with specified username
     * @param user user info to be registered
     * @return true, if registered. false, if validation failed or username collision
     */
    boolean registerUser(User user);
}
