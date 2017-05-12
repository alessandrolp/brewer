package br.com.aocubo.brewer.repository;

import br.com.aocubo.brewer.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by alessandro on 07/05/17.
 */

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);

}
