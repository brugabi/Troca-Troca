package com.uneb.spring_api.controller;

import com.uneb.spring_api.dto.PropostaDTO;
import com.uneb.spring_api.models.Anuncio;
import com.uneb.spring_api.models.Proposta;
import com.uneb.spring_api.models.Status;
import com.uneb.spring_api.models.User;
import com.uneb.spring_api.repositories.AnuncioRepository;
import com.uneb.spring_api.repositories.PropostaRepository;
import com.uneb.spring_api.repositories.StatusRepository;
import com.uneb.spring_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/proposta")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private StatusRepository statusRepository;

    @PostMapping("/criar")
    public ResponseEntity<Map<String,Object>> createProposta(@RequestBody PropostaDTO propostaDTO) {
        Proposta proposta = new Proposta();
        Map<String,Object> response = new HashMap<>();
        Optional<User> requisitante = userRepository.findById(propostaDTO.idRequisitante());
        Optional<Anuncio> anuncio = anuncioRepository.findById(propostaDTO.idAnuncio());
        Optional<Status> status = statusRepository.findById(1L);
        if (!requisitante.isPresent()){
            response.put("message","Requisitante não existe no banco de usuários");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else if (!anuncio.isPresent()) {
            response.put("message","O anuncio informado não foi achado.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else if (!status.isPresent()) {
            response.put("message","O status infomrado não foi achado no banco de dados.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            proposta.setRequisitante(requisitante.get());
            proposta.setAnuncio(anuncio.get());
            proposta.setStatus(status.get());
            proposta.setDataDeProposta(LocalDateTime.now());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }


}
