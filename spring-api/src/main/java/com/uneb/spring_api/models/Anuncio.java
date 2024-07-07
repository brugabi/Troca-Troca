package com.uneb.spring_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Anuncio {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O titulo deve ser informado")
    @Size(min = 2, max = 255,message = "O titilo deve ter tamanho minimo de 2 e tamanho maximo de 255 caracteres.")
    private String titulo;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Todo anuncio deve ter uma descricao")
    @Size(min = 2,message = "A descricao deve ter tamanho minimo de 2 caracteres.")
    private String descricao;

    @Setter
    @Getter
    @ManyToOne
    private User criador;

    public @NotBlank(message = "O titulo deve ser informado") @Size(min = 2, max = 255, message = "O titilo deve ter tamanho minimo de 2 e tamanho maximo de 255 caracteres.") String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank(message = "O titulo deve ser informado") @Size(min = 2, max = 255, message = "O titilo deve ter tamanho minimo de 2 e tamanho maximo de 255 caracteres.") String titulo) {
        this.titulo = titulo;
    }

    public @NotBlank(message = "Todo anuncio deve ter uma descricao") @Size(min = 2, message = "A descricao deve ter tamanho minimo de 2 caracteres.") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "Todo anuncio deve ter uma descricao") @Size(min = 2, message = "A descricao deve ter tamanho minimo de 2 caracteres.") String descricao) {
        this.descricao = descricao;
    }

}
