package br.com.aocubo.brewer.repository.helper.cerveja;

import br.com.aocubo.brewer.dto.CervejaDTO;
import br.com.aocubo.brewer.model.Cerveja;
import br.com.aocubo.brewer.repository.filter.CervejaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by alessandro on 20/04/17.
 */
public interface CervejasQueries {

    public Page<Cerveja> filtrar(CervejaFilter cervejaFilter, Pageable pageable);

    List<CervejaDTO> buscaPorSkuOuNome(String skuOuNome);
}
