package com.uneb.spring_api.repositories;

import com.uneb.spring_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //procura o usuario pelas strings de login e senha
    User findByLoginAndSenha(String login, String senha);
}
