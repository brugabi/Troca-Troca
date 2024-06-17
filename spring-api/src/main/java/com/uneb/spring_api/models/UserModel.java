package com.uneb.spring_api.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios")
public class UserModel extends RepresentationModel<UserModel> implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @NotNull
    @NotBlank
    @NotEmpty
    private String primeiroNome;
    
    @NotNull
    @NotBlank
    @NotEmpty
    private String ultimoNome;
    
    @NotNull
    @NotBlank
    @NotEmpty
    @Column(unique = true)
    private String login;
    
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 8, max = 16)
    private String senha;
    
    @NotNull
    @NotBlank
    @NotEmpty
    @Column(unique = true)
    private String email;
    
    @NotNull
    private LocalDate dataDeNascimento;
    
    @NotNull
    private LocalDateTime dataDeCadastro;


}
