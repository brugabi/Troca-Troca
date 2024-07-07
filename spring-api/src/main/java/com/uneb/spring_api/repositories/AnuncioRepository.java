package com.uneb.spring_api.repositories;

import com.uneb.spring_api.models.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnuncioRepository extends JpaRepository<Anuncio,Long> {
}
