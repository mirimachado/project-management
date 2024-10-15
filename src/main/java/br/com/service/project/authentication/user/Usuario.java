package br.com.service.project.authentication.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Generated;

@Entity(name = "usuario")
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private @NotEmpty String senha;
    private @NotEmpty String nome;
    private @NotEmpty String email;
    private String login;

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getSenha() {
        return this.senha;
    }

    @Generated
    public String getNome() {
        return this.nome;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public String getLogin() {
        return this.login;
    }

    @Generated
    public void setId(final Long id) {
        this.id = id;
    }

    @Generated
    public void setSenha(final String senha) {
        this.senha = senha;
    }

    @Generated
    public void setNome(final String nome) {
        this.nome = nome;
    }

    @Generated
    public void setEmail(final String email) {
        this.email = email;
    }

    @Generated
    public void setLogin(final String login) {
        this.login = login;
    }

    @Generated
    public String toString() {
        Long var10000 = this.getId();
        return "Usuario(id=" + var10000 + ", senha=" + this.getSenha() + ", nome=" + this.getNome() + ", email=" + this.getEmail() + ", login=" + this.getLogin() + ")";
    }

    @Generated
    public Usuario() {
    }

    @Generated
    public Usuario(final Long id, final String senha, final String nome, final String email, final String login) {
        this.id = id;
        this.senha = senha;
        this.nome = nome;
        this.email = email;
        this.login = login;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Usuario)) {
            return false;
        } else {
            Usuario other = (Usuario)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof Usuario;
    }

    @Generated
    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        return result;
    }
}
