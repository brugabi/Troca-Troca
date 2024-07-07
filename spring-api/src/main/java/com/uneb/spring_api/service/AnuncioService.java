package com.uneb.spring_api.service;

import com.uneb.spring_api.models.Anuncio;
import com.uneb.spring_api.repositories.AnuncioRepository;
import com.uneb.spring_api.repositories.UserRepository;
import com.uneb.spring_api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private UserRepository userRepository;

    public Anuncio criarAnuncio(Anuncio anuncio) {
        return anuncioRepository.save(anuncio);
    }

    public List<Anuncio> listarAnuncios() {
        return anuncioRepository.findAll();
    }

    public List<Anuncio> getAnunciosByUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return anuncioRepository.findByCriador(user);
        }
        return null; // Ou lançar uma exceção se o usuário não for encontrado
    }
}
