package br.com.service.project.authentication.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthService() {
    }

    public AccessDTO login(AuthenticationDTO authDto) {
        try {
            UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password());
            Authentication authentication = this.authenticationManager.authenticate(userAuth);
            UserDetailsImpl userAuthenticate = (UserDetailsImpl)authentication.getPrincipal();
            String token = this.jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);
            AccessDTO accessDto = new AccessDTO(token);
            return accessDto;
        } catch (BadCredentialsException var7) {
            return new AccessDTO("Acesso negado");
        }
    }
}
