package io.github.vshnv.nortviz.controller;

import io.github.vshnv.nortviz.entity.AuthToken;
import io.github.vshnv.nortviz.entity.User;
import io.github.vshnv.nortviz.entity.request.LoginRequest;
import io.github.vshnv.nortviz.entity.response.UserInfoResponse;
import io.github.vshnv.nortviz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public @ResponseBody ResponseEntity<UserInfoResponse> getUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated() || !(authentication instanceof UsernamePasswordAuthenticationToken)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        final UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
        final String username = ((UserDetails)authToken.getPrincipal()).getUsername();
        final User user = userService.fetchUser(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(new UserInfoResponse(user.getName(), user.getEmail(), user.getUsername()));
    }

}
