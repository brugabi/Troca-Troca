package com.uneb.spring_api.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class User{

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(min = 2,message = "O primeiro nome deve ter no minimo 2 caracteres.")
    @NotBlank(message = "O primeiro do nome deve ser informado.")
    private String primeiroNome;

    @Size(min = 2, message = "O sobrenome deve ter no minimo 2 caracteres.")
    @NotBlank(message = "O sobrenome deve ser informado.")
    private String sobrenome;
    
    @NotNull(message = "O login nao pode se nulo.")
    @NotBlank(message = "O login deve ser informado")
    @Column(unique = true)
    private String login;
    
    @NotNull(message = "A senha nao pode ser vazia")
    @NotBlank(message = "Um senha deve ser informada.")
    @Size(min = 8, max = 16)
    private String senha;
    
    @NotNull(message = "O email nao pode ser vazio.")
    @NotBlank(message = "Deve ser informado um email.")
    @Column(unique = true)
    private String email;
    
    @NotNull
    private LocalDate dataDeNascimento;
    
    @NotNull
    private LocalDateTime dataDeCadastro;

    @Setter
    @Getter
    @OneToMany(mappedBy = "criador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Anuncio> anuncios;

    @Getter
    @OneToMany(mappedBy = "requisitante")
    @JsonIgnore
    private List<Proposta> propostas;

    public @Size(min = 2, message = "O primeiro nome deve ter no minimo 2 caracteres.") @NotBlank(message = "O primeiro do nome deve ser informado.") String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(@Size(min = 2, message = "O primeiro nome deve ter no minimo 2 caracteres.") @NotBlank(message = "O primeiro do nome deve ser informado.") String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public @Size(min = 2, message = "O sobrenome deve ter no minimo 2 caracteres.") @NotBlank(message = "O sobrenome deve ser informado.") String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(@Size(min = 2, message = "O sobrenome deve ter no minimo 2 caracteres.") @NotBlank(message = "O sobrenome deve ser informado.") String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public @NotNull(message = "O login nao pode se nulo.") @NotBlank(message = "O login deve ser informado") String getLogin() {
        return login;
    }

    public void setLogin(@NotNull(message = "O login nao pode se nulo.") @NotBlank(message = "O login deve ser informado") String login) {
        this.login = login;
    }

    public @NotNull(message = "A senha nao pode ser vazia") @NotBlank(message = "Um senha deve ser informada.") @Size(min = 8, max = 16) String getSenha() {
        return senha;
    }

    public void setSenha(@NotNull(message = "A senha nao pode ser vazia") @NotBlank(message = "Um senha deve ser informada.") @Size(min = 8, max = 16) String senha) {
        this.senha = senha;
    }

    public @NotNull(message = "O email nao pode ser vazio.") @NotBlank(message = "Deve ser informado um email.") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "O email nao pode ser vazio.") @NotBlank(message = "Deve ser informado um email.") String email) {
        this.email = email;
    }

    public @NotNull LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(@NotNull LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public @NotNull LocalDateTime getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(@NotNull LocalDateTime dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

}
