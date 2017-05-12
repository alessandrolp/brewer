package br.com.aocubo.brewer.repository;

import br.com.aocubo.brewer.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by alessandro on 28/04/17.
 */

@Repository
public interface Estados extends JpaRepository<Estado, Long> {
}
