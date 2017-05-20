package br.com.aocubo.brewer.repository.helper.usuario;

import br.com.aocubo.brewer.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
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

    @Override
    public List<String> getPermissoes(Usuario usuario) {
        return entityManager
                .createQuery("select distinct p.nome from Usuario u inner join u.grupos g inner join g.permissoes p where u = :usuario", String.class)
                .setParameter("usuario", usuario)
                .getResultList();
    }

}
