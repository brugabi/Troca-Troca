package com.uneb.spring_api.repositories;

import com.uneb.spring_api.models.Anuncio;
import com.uneb.spring_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
    List<Anuncio> findByCriador(User criador);
    List<Anuncio> findByTituloContainingIgnoreCase(String titulo);
}
