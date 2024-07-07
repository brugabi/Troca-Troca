package com.uneb.spring_api.repositories;

import com.uneb.spring_api.models.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta,Long> {
}
