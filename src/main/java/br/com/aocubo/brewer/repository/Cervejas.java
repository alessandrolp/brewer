package br.com.aocubo.brewer.repository;

import br.com.aocubo.brewer.model.Cerveja;
import br.com.aocubo.brewer.repository.helper.cerveja.CervejasQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by alessandro on 26/03/17.
 */

@Repository
public interface Cervejas extends JpaRepository<Cerveja, Long>, CervejasQueries{

}
