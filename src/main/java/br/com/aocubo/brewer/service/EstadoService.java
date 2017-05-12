package br.com.aocubo.brewer.service;

import br.com.aocubo.brewer.model.Estado;
import br.com.aocubo.brewer.repository.Estados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alessandro on 28/04/17.
 */

@Service
public class EstadoService {

    @Autowired
    private Estados estadoRepository;

    public List<Estado> buscarTodos(){
        return estadoRepository.findAll();
    }
}
