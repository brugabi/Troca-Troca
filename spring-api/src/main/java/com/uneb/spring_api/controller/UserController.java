package com.uneb.spring_api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uneb.spring_api.models.User;
import com.uneb.spring_api.service.UserService;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    //cria o usuario
    @PostMapping("/criar")
    public ResponseEntity<Map<String,Object>> createUser(@RequestBody User user, BindingResult erros) {
        //mapeamento para o recebimento de dados
        Map<String,Object> response = new HashMap<>();
        //erro de criacao de usuario e retorna status not acceptable
        if (erros.hasErrors()){
            response.put("message","Erro ao criar usuario");
            response.put("errors",erros);
            return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
        }
        //se for criado com sucesso, retorna a mensagem de criacao e o status de created
        userService.criarUsuario(user);
        response.put("message","Usuario criado com sucesso");
        response.put("errors",null);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    //procura o usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<User> obterUsuario(@PathVariable Long id) {
        Optional<User> usuario = userService.verUsuario(id);
        //se achar o usuario retorna status ok senao retorna not found
        return usuario.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //lista os usuarios cadastrados
    @GetMapping("/listarUsuarios")
    public ResponseEntity<List<User>> listarUsuarios() {
        List<User> usuarios = userService.listarUsuarios();
        return new ResponseEntity<>(usuarios,HttpStatus.OK);
    }
    //altera o usuario pelo id
    @PutMapping("/alterarUsuario/{id}")
    public ResponseEntity<Map<String, Object>> atualizarUsuario(@PathVariable Long id,@RequestBody User user) {
        //procura o usuario pelo id
        Optional<User> usuarioExistente = userService.verUsuario(id);
        //mapeamento
        Map<String, Object> response = new HashMap<>();
        //se o usuario existir o atualiza
        if (usuarioExistente.isPresent()) {
            User usuarioAtualizado = userService.atualizarUsuario(id, user);
            response.put("message","Usuario alterado com sucesso!");
            response.put("newUser",usuarioAtualizado);
            return new ResponseEntity<>(response,HttpStatus.OK);
        } else {
            response.put("message","Usuario nao encontrado."); //senao encontrar retorna usuario nao encontrado e status not found
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }
    //funcao para deletar o usuario pelo id
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Map<String, Object>> deletarUsuario(@PathVariable Long id) {
        //procura o usuario pelo id
        Optional<User> usuarioExistente = userService.verUsuario(id);
        Map<String, Object> response = new HashMap<>();
        //se o usuario nao existir, informa que nao existe e retorna status not found
        if (!usuarioExistente.isPresent()) {
            response.put("message","O id do usuario passado nao existe.");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        } else { //se existir entra no try catch
            try { //deleta o usuario e retorna usuario deletado e status ok
                userService.deletarUsuario(id);
                response.put("message","usuario deletado com sucesso!");
                return new ResponseEntity<>(response,HttpStatus.OK);
            } catch (Exception e) { //cospe a excecao
                response.put("message","Erro interno do Servidor, entrar em contato com o suporte tecnico.");
                return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
