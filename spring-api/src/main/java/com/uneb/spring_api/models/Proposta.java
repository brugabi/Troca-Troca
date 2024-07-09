package com.uneb.spring_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "proposta")
public class Proposta extends RepresentationModel<Proposta> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O requisitante deve ser inserido!")
    @ManyToOne
    private User requisitante;

    @NotNull(message = "O anuncio deve ser inserido!")
    @ManyToOne
    private Anuncio anuncio;

    @Column(columnDefinition = "TEXT")
    private String mensagem = null;

    @ManyToOne
    @NotNull(message = "O anuncio deve ter um status")
    private Status status;

    @NotNull
    private LocalDateTime dataDeProposta;
}
