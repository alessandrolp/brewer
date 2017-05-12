package br.com.aocubo.brewer.repository;

import br.com.aocubo.brewer.model.Cliente;
import br.com.aocubo.brewer.repository.helper.cliente.ClientesQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by alessandro on 29/04/17.
 */

@Repository
public interface Clientes extends JpaRepository<Cliente, Long>, ClientesQueries{

    public Optional<Cliente> findByCpfCnpj(String cpfCnpj);

}
