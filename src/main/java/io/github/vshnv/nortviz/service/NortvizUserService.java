package io.github.vshnv.nortviz.service;

import io.github.vshnv.nortviz.entity.User;
import io.github.vshnv.nortviz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

/**
 * {@inheritDoc}
 */
@Service
@Transactional
public class NortvizUserService implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public NortvizUserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public @Nullable User fetchUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean registerUser(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (RuntimeException exception) {
            return false;
        }
    }
}
