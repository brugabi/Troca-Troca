package com.uneb.spring_api.repositories;

import com.uneb.spring_api.models.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta,Long> {
}
