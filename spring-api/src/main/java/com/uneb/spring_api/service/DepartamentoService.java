package com.uneb.spring_api.service;

import com.uneb.spring_api.models.Departamento;
import com.uneb.spring_api.repositories.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Departamento criarDepartamento(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public List<Departamento> listarDepartamentos() {
        return departamentoRepository.findAll();
    }

    public Optional<Departamento> verDepartamento(Long id) {
        return departamentoRepository.findById(id);
    }
}
