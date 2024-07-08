package com.uneb.spring_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uneb.spring_api.models.User;
import com.uneb.spring_api.service.AuthService;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Long login(@RequestBody User user) {
        return authService.authenticateAndGetUserId(user.getLogin(), user.getSenha()); //pega o usuario a senha do usuario
    }
}
