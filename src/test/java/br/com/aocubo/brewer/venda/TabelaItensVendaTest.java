package br.com.aocubo.brewer.venda;

import br.com.aocubo.brewer.model.Cerveja;
import br.com.aocubo.brewer.session.TabelaItensVenda;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by alessandro on 28/05/17.
 */
public class TabelaItensVendaTest {

    private TabelaItensVenda tabelaItensVenda;

    @Before
    public void setUp(){
        this.tabelaItensVenda = new TabelaItensVenda();
    }

    @Test
    public void deveCalcularValorTotalSemItens() throws Exception {
        assertEquals(BigDecimal.ZERO, tabelaItensVenda.getValorTotal());
    }

    @Test
    public void deveCalcularValorTotalComUmItem() throws Exception {
        Cerveja cerveja = new Cerveja();
        cerveja.setValor(new BigDecimal("8.90"));
        tabelaItensVenda.adicionarItem(cerveja, 1);
        assertEquals(new BigDecimal("8.90"), tabelaItensVenda.getValorTotal());
    }

    @Test
    public void deveCalcularValorTotalComVariosItens() throws Exception {
        Cerveja cerveja1 = new Cerveja();
        cerveja1.setId(1L);
        cerveja1.setValor(new BigDecimal("8.90"));
        tabelaItensVenda.adicionarItem(cerveja1, 1);

        Cerveja cerveja2 = new Cerveja();
        cerveja2.setId(2L);
        cerveja2.setValor(new BigDecimal("5.00"));
        tabelaItensVenda.adicionarItem(cerveja2, 3);

        assertEquals(new BigDecimal("23.90"), tabelaItensVenda.getValorTotal());
    }

    @Test
    public void deveManterTamanhoDaListaParaMesmasCervejas() throws Exception {
        Cerveja cerveja = new Cerveja();
        cerveja.setId(1L);
        cerveja.setValor(new BigDecimal("5.00"));
        tabelaItensVenda.adicionarItem(cerveja, 1);
        tabelaItensVenda.adicionarItem(cerveja, 5);
        tabelaItensVenda.adicionarItem(cerveja, 1);

        assertEquals(1, tabelaItensVenda.getTotal());
        assertEquals(new BigDecimal("35.00"), tabelaItensVenda.getValorTotal());
    }

}
