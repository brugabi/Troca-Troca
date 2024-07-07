package com.uneb.spring_api.service;

import com.uneb.spring_api.models.Proposta;
import com.uneb.spring_api.repositories.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PropostaService {

    @Autowired
    private PropostaRepository propostaRepository;

    public Proposta criarProposta(Proposta proposta) {
        proposta.setDataDeProposta(LocalDateTime.now());
        return propostaRepository.save(proposta);
    }

    public List<Proposta> listarPropostas() {
        return propostaRepository.findAll();
    }

    public List<Proposta> listarPropostasByUserId(Long userId) {
        List<Proposta> todas = listarPropostas();
        List<Proposta> resultado = new ArrayList<>();
        for (Proposta proposta: todas) if (Objects.equals(proposta.getAnuncio().getCriador().getId(), userId)) resultado.add(proposta);
        return resultado;
    }

}
