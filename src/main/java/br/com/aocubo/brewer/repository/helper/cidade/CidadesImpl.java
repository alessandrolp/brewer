package br.com.aocubo.brewer.repository.helper.cidade;

import br.com.aocubo.brewer.model.Cidade;
import br.com.aocubo.brewer.repository.filter.CidadeFilter;
import br.com.aocubo.brewer.repository.paginacao.PaginacaoUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by alessandro on 06/05/17.
 */
public class CidadesImpl implements CidadesQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private PaginacaoUtil paginacaoUtil;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public Page<Cidade> filtrar(CidadeFilter cidadeFilter, Pageable pageable) {
        Criteria criteria = manager.unwrap(Session.class).createCriteria(Cidade.class);

        paginacaoUtil.preparar(criteria, pageable);
        adicionarFiltro(cidadeFilter, criteria);
        criteria.createAlias("estado", "e", JoinType.LEFT_OUTER_JOIN);
        return new PageImpl<Cidade>(criteria.list(), pageable, total(cidadeFilter));
    }

    private Long total(CidadeFilter cidadeFilter) {
        Criteria criteria = manager.unwrap(Session.class).createCriteria(Cidade.class);
        adicionarFiltro(cidadeFilter, criteria);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    private void adicionarFiltro(CidadeFilter cidadeFilter, Criteria criteria) {
        if(cidadeFilter != null){
            if(cidadeFilter.getEstado() != null){
                criteria.add(Restrictions.eq("estado", cidadeFilter.getEstado()));
            }
            if(!StringUtils.isEmpty(cidadeFilter.getNome())){
                criteria.add(Restrictions.ilike("nome", cidadeFilter.getNome(), MatchMode.ANYWHERE));
            }
        }
    }


}
