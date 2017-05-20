package br.com.aocubo.brewer.security;

import br.com.aocubo.brewer.model.Usuario;
import br.com.aocubo.brewer.repository.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

/**
 * Created by alessandro on 19/05/17.
 */

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private Usuarios usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.getEmailEAtivo(email);
        Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("usuario e/ou senha incorreto"));
        return new User(usuario.getEmail(), usuario.getSenha(), new HashSet<>());
    }

}
