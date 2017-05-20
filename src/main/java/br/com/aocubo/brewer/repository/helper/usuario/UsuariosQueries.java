package br.com.aocubo.brewer.repository.helper.usuario;

import br.com.aocubo.brewer.model.Usuario;
import br.com.aocubo.brewer.repository.filter.UsuarioFilter;

import java.util.List;
import java.util.Optional;

/**
 * Created by alessandro on 19/05/17.
 */
public interface UsuariosQueries {

    Optional<Usuario> getEmailEAtivo(String email);

    List<String> getPermissoes(Usuario usuario);

    List<Usuario> filtrar(UsuarioFilter usuarioFilter);

}
