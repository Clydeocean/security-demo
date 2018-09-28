package com.jsbin.hellospringsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/users")
    public String getUsers() {
        return "users";
    }

    @PostMapping("/logout")
    public void logout() {
        System.out.println("logout!");
    }
}
