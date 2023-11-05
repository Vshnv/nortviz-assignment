package io.github.vshnv.nortviz.service;

import io.github.vshnv.nortviz.entity.User;

public interface UserService {
    User fetchUser(String username);
    boolean registerUser(User user);
}
