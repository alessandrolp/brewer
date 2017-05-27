package br.com.aocubo.brewer.repository.helper.usuario;

import br.com.aocubo.brewer.model.Cerveja;
import br.com.aocubo.brewer.model.Grupo;
import br.com.aocubo.brewer.model.Usuario;
import br.com.aocubo.brewer.model.UsuarioGrupo;
import br.com.aocubo.brewer.repository.filter.CervejaFilter;
import br.com.aocubo.brewer.repository.filter.UsuarioFilter;
import br.com.aocubo.brewer.repository.paginacao.PaginacaoUtil;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private PaginacaoUtil paginacaoUtil;

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
    public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable) {
        Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Usuario.class);

        paginacaoUtil.preparar(criteria, pageable);
        adicionarFiltro(usuarioFilter, criteria);

        List<Usuario> filtrados = criteria.list();
        filtrados.forEach(u -> Hibernate.initialize(u.getGrupos()));
        return new PageImpl<>(filtrados, pageable, total(usuarioFilter));
    }

    private Long total(UsuarioFilter usuarioFilter) {
        Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Usuario.class);
        adicionarFiltro(usuarioFilter, criteria);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    private void adicionarFiltro(UsuarioFilter usuarioFilter, Criteria criteria) {
        if(usuarioFilter != null){
            if(!StringUtils.isEmpty(usuarioFilter.getNome())){
                criteria.add(Restrictions.ilike("nome", usuarioFilter.getNome(), MatchMode.ANYWHERE));
            }
            if(!StringUtils.isEmpty(usuarioFilter.getEmail())){
                criteria.add(Restrictions.ilike("email", usuarioFilter.getEmail(), MatchMode.START));
            }
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
