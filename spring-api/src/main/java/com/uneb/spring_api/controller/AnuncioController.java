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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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

    @GetMapping("/listarAnuncios")
    public ResponseEntity<Map<String, Object>> listarAnuncio() {
        Map<String,Object> response = new HashMap<>();
        response.put("Datetime", LocalDateTime.now());
        response.put("Lista",anuncioService.listarAnuncios());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/usuarios/{userId}/anuncios")
    public List<Anuncio> getAnunciosByUserId(@PathVariable Long userId) {
        return anuncioService.getAnunciosByUserId(userId);
    }
}
