package br.com.aocubo.brewer.repository.helper.estilo;

import br.com.aocubo.brewer.model.Estilo;
import br.com.aocubo.brewer.repository.filter.EstiloFilter;
import br.com.aocubo.brewer.repository.paginacao.PaginacaoUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by alessandro on 21/04/17.
 */
public class EstilosImpl implements EstilosQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private PaginacaoUtil paginacaoUtil;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public Page<Estilo> filtrar(EstiloFilter estiloFilter, Pageable pageable) {
        Criteria criteria = manager.unwrap(Session.class).createCriteria(Estilo.class);

        paginacaoUtil.preparar(criteria, pageable);
        adicionarFiltro(estiloFilter, criteria);

        return new PageImpl<Estilo>(criteria.list(), pageable, total(estiloFilter));
    }

    private Long total(EstiloFilter estiloFilter) {
        Criteria criteria = manager.unwrap(Session.class).createCriteria(Estilo.class);
        adicionarFiltro(estiloFilter, criteria);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    private void adicionarFiltro(EstiloFilter estiloFilter, Criteria criteria) {
        if(estiloFilter != null){
            if(!StringUtils.isEmpty(estiloFilter.getNome())){
                criteria.add(Restrictions.ilike("nome", estiloFilter.getNome(), MatchMode.ANYWHERE));
            }
        }
    }

}
