package com.uneb.spring_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AnuncioDTO {
    @NotBlank(message = "O título deve ser informado.")
    @Size(min = 2, max = 255, message = "O título deve ter entre 2 e 255 caracteres.")
    private String titulo;

    @NotBlank(message = "A descrição deve ser informada.")
    @Size(min = 2, message = "A descrição deve ter no mínimo 2 caracteres.")
    private String descricao;

    @NotNull(message = "O ID do criador do anúncio deve ser informado.")
    private Long idCriador;

    public @NotBlank(message = "O título deve ser informado.") @Size(min = 2, max = 255, message = "O título deve ter entre 2 e 255 caracteres.") String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank(message = "O título deve ser informado.") @Size(min = 2, max = 255, message = "O título deve ter entre 2 e 255 caracteres.") String titulo) {
        this.titulo = titulo;
    }

    public @NotBlank(message = "A descrição deve ser informada.") @Size(min = 2, message = "A descrição deve ter no mínimo 2 caracteres.") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "A descrição deve ser informada.") @Size(min = 2, message = "A descrição deve ter no mínimo 2 caracteres.") String descricao) {
        this.descricao = descricao;
    }

    public @NotNull(message = "O ID do criador do anúncio deve ser informado.") Long getIdCriador() {
        return idCriador;
    }

    public void setIdCriador(@NotNull(message = "O ID do criador do anúncio deve ser informado.") Long idCriador) {
        this.idCriador = idCriador;
    }
}
