package br.com.aocubo.brewer.repository.filter;

import br.com.aocubo.brewer.model.Estado;

/**
 * Created by alessandro on 06/05/17.
 */
public class CidadeFilter {

    private String nome;

    private Estado estado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
