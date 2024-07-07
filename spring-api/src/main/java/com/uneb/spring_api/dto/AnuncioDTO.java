package com.uneb.spring_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AnuncioDTONew(

        @NotBlank(message = "O título deve ser informado.")
        @Size(min = 2, max = 255, message = "O título deve ter entre 2 e 255 caracteres.")
        String titulo,

        @NotBlank(message = "A descrição deve ser informada.")
        @Size(min = 2, message = "A descrição deve ter no mínimo 2 caracteres.")
        String descricao,

        @NotNull(message = "O ID do criador do anúncio deve ser informado.")
        Long idCriador

) {}
