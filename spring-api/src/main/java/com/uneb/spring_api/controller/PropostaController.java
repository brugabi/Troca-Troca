package com.uneb.spring_api.controller;

import com.uneb.spring_api.dto.PropostaDTO;
import com.uneb.spring_api.models.Anuncio;
import com.uneb.spring_api.models.Proposta;
import com.uneb.spring_api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/proposta")
public class PropostaController {

    @Autowired
    private PropostaController propostaController;

    @Autowired
    private UserController userController;

    @Autowired
    private AnuncioController anuncioController;

    @PostMapping("/criar")
    public ResponseEntity<Map<String,Object>> createProposta(@RequestBody PropostaDTO propostaDTO) {
        Proposta proposta = new Proposta();
        Map<String,Object> response = new HashMap<>();
        Optional<User> requisitante = userController.obterUsuario(propostaDTO.idRequisitante());
        if (!requisitante.isPresent()){
            response.put("message","Requisitante não existe no banco de usuários");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Optional<Anuncio> anuncio = anuncioController.verAnuncio(propostaDTO.idAnuncio());
        if ()
    }


}
