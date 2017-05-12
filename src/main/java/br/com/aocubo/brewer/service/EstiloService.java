package br.com.aocubo.brewer.service;

import br.com.aocubo.brewer.model.Estilo;
import br.com.aocubo.brewer.repository.Estilos;
import br.com.aocubo.brewer.service.exception.NomeEstiloJaCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by alessandro on 09/04/17.
 */

@Service
public class EstiloService {

    @Autowired
    private Estilos estilos;

    @Transactional
    public Estilo salvar(Estilo estilo){
        Optional<Estilo> estiloOptional = estilos.findByNomeIgnoreCase(estilo.getNome());
        if(estiloOptional.isPresent()) {
            throw new NomeEstiloJaCadastradoException("Nome do estilo já cadastrado!");
        }
        return estilos.saveAndFlush(estilo);
    }

}
