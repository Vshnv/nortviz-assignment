package io.github.vshnv.nortviz.service;

import io.github.vshnv.nortviz.repository.UserRepository;
import io.github.vshnv.nortviz.auth.UserDetailsProvider;
import io.github.vshnv.nortviz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Qualifier("Nortviz")
@Service
@Transactional
public class NortwizUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserDetailsProvider detailsProvider;

    @Autowired
    public NortwizUserDetailsService(final UserRepository userRepository, final UserDetailsProvider detailsProvider) {
        this.userRepository = userRepository;
        this.detailsProvider = detailsProvider;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        final User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not registered!");
        }
        return detailsProvider.forUser(user);
    }

    @Override
    public String toString() {
        return "NortwizUserDetailsService{}";
    }
}