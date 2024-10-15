package br.com.service.project.authentication.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNomeOrEmail(String nome, String email);

    Optional<Usuario> findByLogin(String login);
}
