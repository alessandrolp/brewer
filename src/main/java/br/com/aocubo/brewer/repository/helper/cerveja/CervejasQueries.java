package br.com.aocubo.brewer.repository.helper.cerveja;

import br.com.aocubo.brewer.model.Cerveja;
import br.com.aocubo.brewer.repository.filter.CervejaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by alessandro on 20/04/17.
 */
public interface CervejasQueries {

    public Page<Cerveja> filtrar(CervejaFilter cervejaFilter, Pageable pageable);
}
