package com.uneb.spring_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uneb.spring_api.models.Imagem;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {

}
