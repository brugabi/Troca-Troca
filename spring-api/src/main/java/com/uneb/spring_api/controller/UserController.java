package com.uneb.spring_api.controller;

import com.uneb.spring_api.models.User;
import com.uneb.spring_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/criar")
    public ResponseEntity<Map<String,Object>> createUser(@RequestBody @Valid User user, BindingResult erros) {
        Map<String,Object> response = new HashMap<>();
        if (erros.hasErrors()){
            response.put("message","Erro ao criar usuario");
            response.put("errors",erros);
            return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
        }
        User novoUsuario = userService.criarUsuario(user);
        response.put("message","Usuario criado com sucesso");
        response.put("errors",null);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> obterUsuario(@PathVariable Long id) {
        Optional<User> usuario = userService.verUsuario(id);
        return usuario.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/listarUsuarios")
    public ResponseEntity<List<User>> listarUsuarios() {
        List<User> usuarios = userService.listarUsuarios();
        return new ResponseEntity<>(usuarios,HttpStatus.OK);
    }

    @PutMapping("/alterarUsuario/{id}")
    public ResponseEntity<Map<String, Object>> atualizarUsuario(@PathVariable Long id,@RequestBody User user) {
        Optional<User> usuarioExistente = userService.verUsuario(id);
        Map<String, Object> response = new HashMap<>();
        if (usuarioExistente.isPresent()) {
            User usuarioAtualizado = userService.atualizarUsuario(id, user);
            response.put("message","Usuario alterado com sucesso!");
            response.put("newUser",usuarioAtualizado);
            return new ResponseEntity<>(response,HttpStatus.OK);
        } else {
            response.put("message","Usuario nao encontrado.");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Map<String, Object>> deletarUsuario(@PathVariable Long id) {
        Optional<User> usuarioExistente = userService.verUsuario(id);
        Map<String, Object> response = new HashMap<>();
        if (!usuarioExistente.isPresent()) {
            response.put("message","O id do usuario passado nao existe.");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        } else {
            try {
                userService.deletarUsuario(id);
                response.put("message","usuario deletado com sucesso!");
                return new ResponseEntity<>(response,HttpStatus.OK);
            } catch (Exception e) {
                response.put("message","Erro interno do Servidor, entrar em contato com o suporte tecnico.");
                return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
