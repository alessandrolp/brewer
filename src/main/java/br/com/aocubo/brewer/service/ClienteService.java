package br.com.aocubo.brewer.service;

import br.com.aocubo.brewer.model.Cliente;
import br.com.aocubo.brewer.repository.Clientes;
import br.com.aocubo.brewer.service.exception.CpfCnpjClienteCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by alessandro on 29/04/17.
 */

@Service
public class ClienteService {

    @Autowired
    private Clientes clienteRepository;

    @Transactional
    public void salvar(Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findByCpfCnpj(cliente.getCpfCnpjSemFormatacao());
        if(clienteExistente.isPresent()){
            throw new CpfCnpjClienteCadastradoException("CPF/CNPJ já cadastrado");
        }
        clienteRepository.save(cliente);
    }

}
