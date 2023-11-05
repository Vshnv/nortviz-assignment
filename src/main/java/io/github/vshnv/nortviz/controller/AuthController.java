package io.github.vshnv.nortviz.controller;

import io.github.vshnv.nortviz.auth.jwt.JwtGenerator;
import io.github.vshnv.nortviz.entity.AuthToken;
import io.github.vshnv.nortviz.entity.User;
import io.github.vshnv.nortviz.entity.request.LoginRequest;
import io.github.vshnv.nortviz.entity.request.RegisterRequest;
import io.github.vshnv.nortviz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1")
public class AuthController {
    private final long authExpirationTime;
    private final JwtGenerator generator;
    private final UserService userService;
    private final PasswordEncoder encoder;


    @Autowired
    public AuthController(@Value("${auth.expiration_seconds}") final long authExpirationTime, final JwtGenerator generator, final UserService userService, final PasswordEncoder encoder) {
        this.authExpirationTime = authExpirationTime;
        this.generator = generator;
        this.userService = userService;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<AuthToken> login(@RequestBody final LoginRequest loginRequest) {
        final String username = loginRequest.getUsername();
        final String requestedPassword = loginRequest.getPassword();
        final User user = userService.fetchUser(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        final String expectedPassword = user.getHashedPassword();
        if (!encoder.matches(requestedPassword, expectedPassword)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(generator.generateToken(loginRequest.getUsername(), authExpirationTime));
    }

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<String> create(@RequestBody final RegisterRequest registerRequest) {
        final User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setHashedPassword(encoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setName(registerRequest.getName());
        if (userService.registerUser(user)) {
            return ResponseEntity.ok("User created");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
