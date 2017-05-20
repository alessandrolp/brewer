package br.com.aocubo.brewer.repository.helper.usuario;

import br.com.aocubo.brewer.model.Grupo;
import br.com.aocubo.brewer.model.Usuario;
import br.com.aocubo.brewer.model.UsuarioGrupo;
import br.com.aocubo.brewer.repository.filter.UsuarioFilter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
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

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Usuario> filtrar(UsuarioFilter usuarioFilter) {
        Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Usuario.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        adicionarFiltro(usuarioFilter, criteria);
        return criteria.list();
    }

    private void adicionarFiltro(UsuarioFilter usuarioFilter, Criteria criteria) {
        if(usuarioFilter != null){
            if(!StringUtils.isEmpty(usuarioFilter.getNome())){
                criteria.add(Restrictions.ilike("nome", usuarioFilter.getNome(), MatchMode.ANYWHERE));
            }
            if(!StringUtils.isEmpty(usuarioFilter.getEmail())){
                criteria.add(Restrictions.ilike("email", usuarioFilter.getEmail(), MatchMode.START));
            }
            criteria.createAlias("grupos", "g", JoinType.LEFT_OUTER_JOIN);
            if(usuarioFilter.getGrupos() != null && !usuarioFilter.getGrupos().isEmpty()){
                List<Criterion> subqueries = new ArrayList<>();
                for(Long idGrupo : usuarioFilter.getGrupos().stream().mapToLong(Grupo::getId).toArray()){
                    DetachedCriteria dc = DetachedCriteria.forClass(UsuarioGrupo.class);
                    dc.add(Restrictions.eq("id.grupo.id",idGrupo));
                    dc.setProjection(Projections.property("id.usuario"));
                    subqueries.add(Subqueries.propertyIn("id", dc));
                }
                Criterion[] criterions = new Criterion[subqueries.size()];
                criteria.add(Restrictions.and(subqueries.toArray(criterions)));
            }
        }
    }

}
