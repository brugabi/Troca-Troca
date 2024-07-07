package com.uneb.spring_api.repositories;

import com.uneb.spring_api.models.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartamentoRepository extends JpaRepository<Departamento,Long> {
    @Query("SELECT d.id, d.nome FROM Departamento d")
    List<Object[]> findIdAndNome();
}
