package com.uneb.spring_api.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserRecordDto(
        @NotBlank String primeiroNome,
        @NotBlank String ultimoNome,
        @NotBlank String login,
        @NotBlank String senha,
        @NotBlank @Email @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Email deve seguir o padrão nome@dominio.com") String email,
        @NotNull LocalDate dataDeNascimento) {

}
