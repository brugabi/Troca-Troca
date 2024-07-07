package com.uneb.spring_api.repositories;

import com.uneb.spring_api.models.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento,Long> {
}
