package com.uneb.spring_api.dto;

import jakarta.validation.constraints.NotNull;

public record DepartamentoDTO(
        @NotNull (message = "O nome do departamento deve ser informado.")
        String nome
) {
}
