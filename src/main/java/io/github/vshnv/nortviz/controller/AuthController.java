package io.github.vshnv.nortviz.controller;

import io.github.vshnv.nortviz.auth.jwt.JwtGenerator;
import io.github.vshnv.nortviz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
public class AuthController {
    private final JwtGenerator generator;
    private final UserRepository repository;
    private final PasswordEncoder encoder;


    @Autowired
    public AuthController(final JwtGenerator generator, final UserRepository repository, final PasswordEncoder encoder) {
        this.generator = generator;
        this.repository = repository;
        this.encoder = encoder;
    }
}
