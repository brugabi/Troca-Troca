package com.uneb.spring_api.service;

import com.uneb.spring_api.models.Status;
import com.uneb.spring_api.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;
    //procura o status pelo id
    public Optional<Status> obterStatus(Long id) {
        return statusRepository.findById(id);
    }
}
