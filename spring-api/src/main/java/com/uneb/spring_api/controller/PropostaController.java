package com.uneb.spring_api.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uneb.spring_api.dto.PropostaDTO;
import com.uneb.spring_api.models.Anuncio;
import com.uneb.spring_api.models.Proposta;
import com.uneb.spring_api.models.Status;
import com.uneb.spring_api.models.User;
import com.uneb.spring_api.service.AnuncioService;
import com.uneb.spring_api.service.PropostaService;
import com.uneb.spring_api.service.StatusService;
import com.uneb.spring_api.service.UserService;

@RestController
@RequestMapping("/api/proposta")
public class PropostaController {

    @Autowired
    private PropostaService propostaService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnuncioService anuncioService;

    @Autowired
    private StatusService statusService;

    @PostMapping("/criar")
    public ResponseEntity<Map<String,Object>> createProposta(@RequestBody PropostaDTO propostaDTO) {
        Proposta proposta = new Proposta();
        System.out.println("recena" + propostaDTO);
        Map<String,Object> response = new HashMap<>();
        Optional<User> requisitante = userService.verUsuario(propostaDTO.idRequisitante());
        Optional<Anuncio> anuncio = anuncioService.verAnuncio(propostaDTO.idAnuncio());
        Optional<Status> status = statusService.obterStatus(1L);
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
            proposta.setMensagem(propostaDTO.mensagem());

            propostaService.criarProposta(proposta);
            response.put("proposta", proposta);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @GetMapping("/lista/createdBy/{userId}")
    public List<Proposta> listarPropostaByUser(@PathVariable Long userId) {
        System.out.println("body" + userId);
        return propostaService.listarPropostasByUserId(userId);
    }

    @PostMapping("/aceitar/{id}")
    public ResponseEntity<Map<String,Object>> aceitarProposta(@PathVariable Long id){

        // Alterando a proposta na tabela
        Optional<Proposta> propostaAAceitar = propostaService.obterProposta(id);
        Optional<Status> statusDeAceito = statusService.obterStatus(2L);
        propostaAAceitar.get().setStatus(statusDeAceito.get());
        propostaService.atualizarProposta(propostaAAceitar.get());

        // Alterar status do anuncio
        Anuncio anuncio = propostaAAceitar.get().getAnuncio();
        anuncio.setStatus(false);
        anuncioService.atualizarStatusAnuncio(anuncio);

        Map<String,Object> response = new HashMap<>();
        response.put("message","Proposta aceita!");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/recusar/{id}")
    public ResponseEntity<Map<String,Object>> recusarProposta(@PathVariable Long id){

        // Alterando a proposta na tabela
        Optional<Proposta> propostaAAceitar = propostaService.obterProposta(id);
        Optional<Status> statusDeAceito = statusService.obterStatus(3L);
        propostaAAceitar.get().setStatus(statusDeAceito.get());
        propostaService.atualizarProposta(propostaAAceitar.get());


        Map<String,Object> response = new HashMap<>();
        response.put("message","Proposta recusada!");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
