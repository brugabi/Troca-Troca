package com.uneb.spring_api.service;

import com.uneb.spring_api.models.User;
import com.uneb.spring_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public Long authenticateAndGetUserId(String login, String senha) {
        User user = userRepository.findByLoginAndSenha(login, senha); //procura pelo login e senha
        if (user != null) {
            return user.getId(); //retrona o id para conectividade com o front
        }
        return null; // se nao existir retorna null
    }
}
