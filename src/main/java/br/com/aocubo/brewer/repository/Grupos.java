package br.com.aocubo.brewer.repository;

import br.com.aocubo.brewer.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by alessandro on 08/05/17.
 */

@Repository
public interface Grupos extends JpaRepository<Grupo, Long> {

}
