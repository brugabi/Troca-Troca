package com.uneb.spring_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @Getter
    @Setter
    @OneToMany(mappedBy = "departamento")
    private List<Anuncio> anuncios;
}
