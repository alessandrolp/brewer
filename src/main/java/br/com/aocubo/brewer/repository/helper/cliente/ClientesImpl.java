package br.com.aocubo.brewer.repository.helper.cliente;

import br.com.aocubo.brewer.model.Cliente;
import br.com.aocubo.brewer.repository.filter.ClienteFilter;
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
 * Created by alessandro on 01/05/17.
 */
public class ClientesImpl implements ClientesQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private PaginacaoUtil paginacaoUtil;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable) {
        Criteria criteria = manager.unwrap(Session.class).createCriteria(Cliente.class);

        paginacaoUtil.preparar(criteria, pageable);
        adicionarFiltro(clienteFilter, criteria);
        criteria.createAlias("endereco.cidade", "c", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("c.estado", "e", JoinType.LEFT_OUTER_JOIN);
        return new PageImpl<Cliente>(criteria.list(), pageable, total(clienteFilter));
    }

    private Long total(ClienteFilter clienteFilter) {
        Criteria criteria = manager.unwrap(Session.class).createCriteria(Cliente.class);
        adicionarFiltro(clienteFilter, criteria);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    private void adicionarFiltro(ClienteFilter clienteFilter, Criteria criteria) {
        if(clienteFilter != null){
            if(!StringUtils.isEmpty(clienteFilter.getNome())){
                criteria.add(Restrictions.ilike("nome", clienteFilter.getNome(), MatchMode.ANYWHERE));
            }
            if(!StringUtils.isEmpty(clienteFilter.getCpfCnpj())){
                criteria.add(Restrictions.eq("cpfCnpj", clienteFilter.getCpfCnpj()));
            }
        }
    }

}
