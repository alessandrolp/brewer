package br.com.aocubo.brewer.model.validation;

import br.com.aocubo.brewer.model.Cliente;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alessandro on 29/04/17.
 */
public class ClienteGroupSequenceProvider implements DefaultGroupSequenceProvider<Cliente> {

    @Override
    public List<Class<?>> getValidationGroups(Cliente cliente) {
        List<Class<?>> grupos = new ArrayList<>();
        grupos.add(Cliente.class);

        if(isPessoaSelecionada(cliente)){
            grupos.add(cliente.getTipoPessoa().getGrupo());
        }
        return grupos;
    }

    private boolean isPessoaSelecionada(Cliente cliente){
        return cliente != null && cliente.getTipoPessoa() != null;
    }

}
