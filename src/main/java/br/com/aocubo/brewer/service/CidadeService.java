package br.com.aocubo.brewer.service;

import br.com.aocubo.brewer.model.Cidade;
import br.com.aocubo.brewer.repository.Cidades;
import br.com.aocubo.brewer.service.exception.CidadeCadastradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by alessandro on 28/04/17.
 */

@Service
public class CidadeService {

    @Autowired
    private Cidades cidadeRepository;

    public List<Cidade> buscarTodos(){
        return cidadeRepository.findAll();
    }

    public List<Cidade> buscaPorIdEstado(Long idEstado){
        return cidadeRepository.findByEstadoId(idEstado);
    }

    @Transactional
    public void salvar(Cidade cidade){
        Optional<Cidade> cidadeExistente = cidadeRepository.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());
        if(cidadeExistente.isPresent()){
            throw new CidadeCadastradaException("Cidade ja cadastrada!");
        }
        cidadeRepository.save(cidade);
    }

}
