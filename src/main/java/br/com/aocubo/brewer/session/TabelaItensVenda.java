package br.com.aocubo.brewer.session;

import br.com.aocubo.brewer.model.Cerveja;
import br.com.aocubo.brewer.model.ItemVenda;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by alessandro on 28/05/17.
 */

@SessionScope
@Component
public class TabelaItensVenda {

    private List<ItemVenda> itens = new ArrayList<>();

    public BigDecimal getValorTotal(){
        return itens.stream().map(ItemVenda::getValorTotal).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        /* OUTRA FORMA DE FAZER O CALCULO
        BigDecimal total = new BigDecimal(0);
        for(ItemVenda itemVendas : itens){
            total = total.add(itemVendas.getValorTotal());
        }
        return total;
        */
    }

    public void adicionarItem(Cerveja cerveja, Integer quantidade){
        Optional<ItemVenda> itemVendaOptional = itens.stream().filter(i -> i.getCerveja().equals(cerveja)).findAny();

        ItemVenda itemVenda = null;
        if(itemVendaOptional.isPresent()){
            itemVenda = itemVendaOptional.get();
            itemVenda.setQuantidade(itemVenda.getQuantidade() + quantidade);
        } else {
            itemVenda = new ItemVenda();
            itemVenda.setCerveja(cerveja);
            itemVenda.setQuantidade(quantidade);
            itemVenda.setValorUnitario(cerveja.getValor());
            itens.add(0, itemVenda);
        }
    }

    public int getTotal(){
        return itens.size();
    }

    public List<ItemVenda> getItens() {
        return itens;
    }
}
