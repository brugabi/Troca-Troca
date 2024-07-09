package com.uneb.spring_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;

@Entity
public class Anuncio extends RepresentationModel<Anuncio> implements Serializable {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O titulo deve ser informado")
    @Size(min = 2, max = 255, message = "O titulo deve ter tamanho minimo de 2 e tamanho maximo de 255 caracteres.")
    private String titulo;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Todo anuncio deve ter uma descricao")
    @Size(min = 2, message = "A descricao deve ter tamanho minimo de 2 caracteres.")
    private String descricao;

    @Setter
    @Getter
    @ManyToOne
    private User criador;

    @Setter
    @Getter
    @ManyToOne
    private Departamento departamento;

    @Getter
    @OneToMany(mappedBy = "anuncio")
    @JsonIgnore
    private List<Proposta> propostas;

    @Getter
    @Setter
    private boolean status;

    @Setter
    @Getter
    private String imagemUrl;

    public @NotBlank(message = "O titulo deve ser informado") @Size(min = 2, max = 255, message = "O titulo deve ter tamanho minimo de 2 e tamanho maximo de 255 caracteres.") String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank(message = "O titulo deve ser informado") @Size(min = 2, max = 255, message = "O titulo deve ter tamanho minimo de 2 e tamanho maximo de 255 caracteres.") String titulo) {
        this.titulo = titulo;
    }

    public @NotBlank(message = "Todo anuncio deve ter uma descricao") @Size(min = 2, message = "A descricao deve ter tamanho minimo de 2 caracteres.") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "Todo anuncio deve ter uma descricao") @Size(min = 2, message = "A descricao deve ter tamanho minimo de 2 caracteres.") String descricao) {
        this.descricao = descricao;
    }
}
