package br.com.service.project.authentication.jwt;

public record AuthenticationDTO(String username, String password) {
    public AuthenticationDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String username() {
        return this.username;
    }

    public String password() {
        return this.password;
    }
}
