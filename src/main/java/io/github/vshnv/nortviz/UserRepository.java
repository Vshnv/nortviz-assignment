package io.github.vshnv.nortviz;

import io.github.vshnv.nortviz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(final String username);
}
