package com.uneb.spring_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Status extends RepresentationModel<Anuncio> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome do status deve ser informado!")
    @Size(min = 2, max = 254, message = "O nome deve ter tamanho minimo de 2 e tamanho máximo de 254 caracteres!")
    private String nome;

    @OneToMany(mappedBy = "status")
    @JsonIgnore
    private List<Proposta> propostas;
}
