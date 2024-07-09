package com.uneb.spring_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.util.List;

@Entity
@Data
public class Departamento {

    @Id //identificacao para id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincremento
    private Long id; //id do departamento

    @NotNull
    private String nome; //nome do departamento

    @OneToMany(mappedBy = "departamento") //relacionamento muitos para um
    @JsonIgnore //evitar serializacao, estava crashando o codigo
    private List<Anuncio> anuncios; //lista de anuncio que possui esse departamento
}
