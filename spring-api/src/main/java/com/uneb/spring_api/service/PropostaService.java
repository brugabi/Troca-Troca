package com.uneb.spring_api.service;

import com.uneb.spring_api.models.Proposta;
import com.uneb.spring_api.repositories.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PropostaService {

    @Autowired
    private PropostaRepository propostaRepository;

    //salva a proposta no banco de dados
    public Proposta criarProposta(Proposta proposta) {
        //coloca a data que a proposta foi gerada
        proposta.setDataDeProposta(LocalDateTime.now());
        return propostaRepository.save(proposta);
    }

    //retorna a lista completa de proposta
    public List<Proposta> listarPropostas() {
        return propostaRepository.findAll();
    }
    //lista a proposta pelo requisitante
    public List<Proposta> listarPropostasByRequisitante(Long userId) {
        List<Proposta> todas = listarPropostas(); //lista com todas as propostas
        List<Proposta> resultado = new ArrayList<>(); //lista para o resultado de proposta do requisitante
        for (Proposta proposta : todas) {
            //verifica se o idRequisitante da proposta equivale ao id de usuario informado
            if (Objects.equals(proposta.getRequisitante().getId(), userId)) {
                resultado.add(proposta);
            }
        }
        //retorna a lista com o resultado gerado
        return resultado;
    }
    
    //lista a proposta pelo usuario dono do anuncio
    public List<Proposta> listarPropostasByUserId(Long userId) {
        List<Proposta> todas = listarPropostas();
        List<Proposta> resultado = new ArrayList<>();
        //verifica se o id do Criador equivale  ai id de usuario informado
        for (Proposta proposta: todas) if (Objects.equals(proposta.getAnuncio().getCriador().getId(), userId)) resultado.add(proposta);
        return resultado;
    }
    //procura a proposta pelo id
    public Optional<Proposta> obterProposta(Long id) {
        return propostaRepository.findById(id);
    }

    //atualiza a proposta
    public Proposta atualizarProposta(Proposta propostaAtualizada) {
        return propostaRepository.save(propostaAtualizada);
    }
    

}
