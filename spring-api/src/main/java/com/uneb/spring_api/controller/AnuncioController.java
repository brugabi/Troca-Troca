package com.uneb.spring_api.controller;

import com.uneb.spring_api.dto.AnuncioDTO;
import com.uneb.spring_api.models.Anuncio;
import com.uneb.spring_api.models.User;
import com.uneb.spring_api.service.AnuncioService;
import com.uneb.spring_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @Autowired
    private UserService userService;

    @PostMapping("/criarAnuncio")
    public ResponseEntity<Map<String, Object>> createAnuncio(@Valid @RequestBody AnuncioDTO anuncioDTO) {
        Optional<User> usuario = userService.verUsuario(anuncioDTO.getIdCriador());
        Map<String, Object> response = new HashMap<>();
        if (usuario.isPresent()) {
            User usuarioCriador = usuario.get();

            // Criar um novo objeto Anuncio com os dados recebidos
            Anuncio novoAnuncio = new Anuncio();
            novoAnuncio.setTitulo(anuncioDTO.getTitulo());
            novoAnuncio.setDescricao(anuncioDTO.getDescricao());
            novoAnuncio.setCriador(usuarioCriador);

            // Salvar o anuncio
            anuncioService.criarAnuncio(novoAnuncio);

            response.put("message", "Anúncio criado com sucesso!");
            response.put("anuncio", novoAnuncio);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            // Usuario não encontrada
            response.put("message", "Usuário não encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> verAnuncio(@PathVariable Long id) {
        Optional<Anuncio> anuncio = anuncioService.verAnuncio(id);
        Map<String,Object> response = new HashMap<>();
        response.put("datetime",LocalDateTime.now());
        if (anuncio.isPresent()) {
            response.put("anuncio",anuncio.get());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            response.put("Error","Anuncio nao encontrado.");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listarAnuncios")
    public ResponseEntity<Map<String, Object>> listarAnuncio() {
        Map<String,Object> response = new HashMap<>();
        response.put("Datetime", LocalDateTime.now());
        response.put("Lista",anuncioService.listarAnuncios());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/listarAnuncios/{userId}")
    public ResponseEntity<Map<String,Object>> getAnunciosByUserId(@PathVariable Long userId) {
        Optional<User> criador = userService.verUsuario(userId);
        Map<String,Object> response = new HashMap<>();
        response.put("timestamp",LocalDateTime.now());
        if (criador.isPresent()) {
            response.put("anuncios",anuncioService.getAnunciosByUser(criador.get()));
            return new ResponseEntity<>(response,HttpStatus.OK);
        } else {
            response.put("error","O id informado nao esta associado a nenhum usuario.");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Map<String, Object>> deletarAnuncio(@PathVariable Long id) {
        Optional<Anuncio> anuncio = anuncioService.verAnuncio(id);
        Map<String,Object> response = new HashMap<>();
        response.put("timestamp",LocalDateTime.now());
        if (anuncio.isPresent()) {
            anuncioService.deletarAnuncio(id);
            response.put("message","anuncio deletado com sucesso.");
            return new ResponseEntity<>(response,HttpStatus.OK);
        } else {
            response.put("error","Anuncio nao encontrado. Nao foi efetuado o delete.");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }
}
