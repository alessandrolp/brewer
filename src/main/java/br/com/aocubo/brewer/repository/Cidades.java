package br.com.aocubo.brewer.repository;

import br.com.aocubo.brewer.model.Cidade;
import br.com.aocubo.brewer.model.Estado;
import br.com.aocubo.brewer.repository.helper.cidade.CidadesQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by alessandro on 28/04/17.
 */

@Repository
public interface Cidades extends JpaRepository<Cidade, Long>, CidadesQueries {

    public List<Cidade> findByEstadoId(Long idEstado);

    public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);

}
