package br.com.service.project.authentication.user;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(Long id, @NotBlank String nome, @NotBlank String senha, @NotBlank String email, String login) {

    public UsuarioDTO(Long id, @NotBlank String nome, @NotBlank String senha, @NotBlank String email, String login) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.login = login;
    }

    public Long id() {
        return this.id;
    }

    public @NotBlank String nome() {
        return this.nome;
    }

    public @NotBlank String senha() {
        return this.senha;
    }

    public @NotBlank String email() {
        return this.email;
    }

    public String login() {
        return this.login;
    }
}
