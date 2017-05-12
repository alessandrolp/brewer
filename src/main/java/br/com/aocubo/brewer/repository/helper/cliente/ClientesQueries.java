package br.com.aocubo.brewer.repository.helper.cliente;

import br.com.aocubo.brewer.model.Cliente;
import br.com.aocubo.brewer.repository.filter.ClienteFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by alessandro on 01/05/17.
 */
public interface ClientesQueries {

    public Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable);
}
