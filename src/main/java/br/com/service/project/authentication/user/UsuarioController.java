package br.com.service.project.authentication.user;

import br.com.service.project.authentication.services.EmailService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/usuario"})
public class UsuarioController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioController() {
    }

    @GetMapping({"/buscar"})
    public ResponseEntity buscaUsuario() {
        List<Usuario> todosOsUsuarios = this.usuarioRepository.findAll();
        return ResponseEntity.ok(todosOsUsuarios);
    }

    @PostMapping({"/registrar"})
    public ResponseEntity registrarUsuario(@RequestBody UsuarioDTO dado) {
        Optional<Usuario> verificaUsuario = this.usuarioRepository.findByNomeOrEmail(dado.nome(), dado.email());
        if (dado != null && !verificaUsuario.isPresent()) {
            Usuario novoUsuario = new Usuario();
            novoUsuario.setEmail(dado.email());
            novoUsuario.setNome(dado.nome());
            novoUsuario.setSenha(this.passwordEncoder.encode(dado.senha()));
            novoUsuario.setLogin(dado.login());
            this.usuarioRepository.save(novoUsuario);
            this.emailService.enviaEmail("Você está recebendo um e-mail de teste", dado.email(), "Novo usuário cadastrado");
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping({"/atualizar"})
    @Transactional
    public ResponseEntity atualizaUsuario(@RequestBody UsuarioDTO dado) {
        if (!this.usuarioRepository.existsById(dado.id())) {
            return ResponseEntity.badRequest().build();
        } else {
            Usuario usuarioAtualizado = new Usuario();
            usuarioAtualizado.setId(dado.id());
            usuarioAtualizado.setNome(dado.nome());
            usuarioAtualizado.setEmail(dado.email());
            usuarioAtualizado.setSenha(this.passwordEncoder.encode(usuarioAtualizado.getSenha()));
            this.usuarioRepository.save(usuarioAtualizado);
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping({"/deletar"})
    @Transactional
    public ResponseEntity deletaUsuario(@RequestBody Long id) {
        if (this.usuarioRepository.existsById(id)) {
            this.usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
