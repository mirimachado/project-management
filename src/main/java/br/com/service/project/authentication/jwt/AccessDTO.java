package br.com.service.project.authentication.jwt;

public class AccessDTO {
    private String token;

    public AccessDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
