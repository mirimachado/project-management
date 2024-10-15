package br.com.service.project.authentication.jwt;

import br.com.service.project.authentication.user.UsuarioController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/auth"})
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UsuarioController usuarioController;

    public AuthController() {
    }

    @PostMapping({"/login"})
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO authDto) {
        return ResponseEntity.ok(this.authService.login(authDto));
    }
}
