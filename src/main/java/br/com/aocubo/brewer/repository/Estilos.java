package br.com.aocubo.brewer.repository;

import br.com.aocubo.brewer.model.Estilo;
import br.com.aocubo.brewer.repository.helper.estilo.EstilosQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by alessandro on 08/04/17.
 */

@Repository
public interface Estilos extends JpaRepository<Estilo, Long>, EstilosQueries {

    public Optional<Estilo> findByNomeIgnoreCase(String nome);

}
