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
        //coloca a data de cadastro
        user.setDataDeCadastro(LocalDateTime.now());
        //salva no banco de dados
        return userRepository.save(user);
    }

    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }
    //procura o usuario pelo id
    public Optional<User> verUsuario(Long id) {
        return userRepository.findById(id);
    }
    //atualiza o usuario
    public User atualizarUsuario(Long id, User user) {
        //procura o usuario por id
        Optional<User> usuarioExistente = userRepository.findById(id);
        //verifica se ele existe e atualiza
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
