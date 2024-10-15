package br.com.service.project.authentication.jwt;

import br.com.service.project.authentication.user.Usuario;
import br.com.service.project.authentication.user.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public UserDetailServiceImpl() {
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByLogin(username).get();
        return UserDetailsImpl.build(usuario);
    }
}
