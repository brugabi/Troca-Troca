package com.uneb.spring_api.service;

import com.uneb.spring_api.models.Departamento;
import com.uneb.spring_api.repositories.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Departamento criarDepartamento(Departamento departamento) {
        return departamentoRepository.save(departamento); //salva o departamento no banco
    }

    public List<Map<String, Object>> listarDepartamentos() {
        List<Object[]> results = departamentoRepository.findIdAndNome();
        List<Map<String, Object>> finalResult = new ArrayList<>();
        for (Object[] lista:results){
            Map<String,Object> dicionario = new HashMap<>();
            dicionario.put("id",lista[0]);
            dicionario.put("nome",lista[1]);
            finalResult.add(dicionario);
        }
        return finalResult;
    }

    public Optional<Departamento> verDepartamento(Long id) {
        return departamentoRepository.findById(id);
    }
}
