package com.jsbin.hellospringsecurity.controller;

import com.jsbin.hellospringsecurity.domain.Role;
import com.jsbin.hellospringsecurity.domain.User;
import com.jsbin.hellospringsecurity.exception.GlobalException;
import com.jsbin.hellospringsecurity.repository.UserRepository;
import com.jsbin.hellospringsecurity.util.JWTUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.stream.Collectors;

@RestController
public class AuthenticationController {

    @Resource
    private UserRepository userRepository;

    @PostMapping("/auth/token")
    public String login(String username, String password) {
        User user = userRepository.getUserByUsername(username);
        if (user != null) {
            //login successful.
            return JWTUtils.generateToken(user.getUsername(), user.getRoleSet().stream().map(Role::getName).collect(Collectors.joining(",")));
        }
        throw new GlobalException(HttpStatus.BAD_REQUEST, "username or password error.");
    }
}
