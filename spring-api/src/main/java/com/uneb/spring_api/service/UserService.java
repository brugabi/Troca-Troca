package com.uneb.spring_api.service;

import com.uneb.spring_api.models.User;
import com.uneb.spring_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User criarUsuario(User user) {
        user.setDataDeCadastro(LocalDateTime.now());
        return userRepository.save(user);
    }

    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    public Optional<User> verUsuario(Long id) {
        return userRepository.findById(id);
    }

    public User atualizarUsuario(Long id, User user) {
        Optional<User> usuarioExistente = userRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            User usuarioParaAtualizar = usuarioExistente.get();
            usuarioParaAtualizar.setPrimeiroNome(user.getPrimeiroNome());
            usuarioParaAtualizar.setSobrenome(user.getSobrenome());
            usuarioParaAtualizar.setLogin(user.getLogin());
            usuarioParaAtualizar.setSenha(user.getSenha());
            usuarioParaAtualizar.setEmail(user.getEmail());
            usuarioParaAtualizar.setDataDeNascimento(user.getDataDeNascimento());
            return userRepository.save(usuarioParaAtualizar);
        } else {
            return null;
        }
    }

    public Void deletarUsuario(Long id) {
        userRepository.deleteById(id);
        return null;
    }
}
