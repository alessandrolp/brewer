package br.com.aocubo.brewer.repository.helper.estilo;

import br.com.aocubo.brewer.model.Estilo;
import br.com.aocubo.brewer.repository.filter.EstiloFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by alessandro on 21/04/17.
 */
public interface EstilosQueries {

    public Page<Estilo> filtrar(EstiloFilter estiloFilter, Pageable pageable);
}
