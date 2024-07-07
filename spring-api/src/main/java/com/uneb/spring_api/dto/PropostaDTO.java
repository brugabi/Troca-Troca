package com.uneb.spring_api.dto;

import jakarta.validation.constraints.NotNull;

public record PropostaDTO(

        @NotNull Long idRequisitante,
        @NotNull Long idAnuncio,
        @NotNull String textoProposta

) {
}
