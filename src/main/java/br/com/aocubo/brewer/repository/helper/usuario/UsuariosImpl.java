package br.com.aocubo.brewer.repository.helper.usuario;

import br.com.aocubo.brewer.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 * Created by alessandro on 19/05/17.
 */
public class UsuariosImpl implements UsuariosQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Usuario> getEmailEAtivo(String email) {
        return entityManager
                .createQuery("select u from Usuario u where lower(email) = lower(:email) and ativo = true", Usuario.class)
                .setParameter("email", email).getResultList().stream().findFirst();
    }

}
