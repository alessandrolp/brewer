package br.com.aocubo.brewer.repository.helper.cidade;

import br.com.aocubo.brewer.model.Cidade;
import br.com.aocubo.brewer.repository.filter.CidadeFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by alessandro on 06/05/17.
 */
public interface CidadesQueries {

    public Page<Cidade> filtrar(CidadeFilter cidadeFilter, Pageable pageable);
}
