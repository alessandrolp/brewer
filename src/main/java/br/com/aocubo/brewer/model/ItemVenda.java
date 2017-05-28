package br.com.aocubo.brewer.model;

import java.math.BigDecimal;

/**
 * Created by alessandro on 28/05/17.
 */
public class ItemVenda {

    private Long id;

    private Integer quantidade;

    private BigDecimal valorUnitario;

    private Cerveja cerveja;

    public BigDecimal getValorTotal(){
        return valorUnitario.multiply(new BigDecimal(quantidade));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemVenda itemVenda = (ItemVenda) o;

        if (id != null ? !id.equals(itemVenda.id) : itemVenda.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Cerveja getCerveja() {
        return cerveja;
    }

    public void setCerveja(Cerveja cerveja) {
        this.cerveja = cerveja;
    }
}
