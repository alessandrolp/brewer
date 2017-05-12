package br.com.aocubo.brewer.model;

/**
 * Created by alessandro on 25/03/17.
 */

public enum Origem {

    NACIONAL ("Nacional"),
    INTERNACIONAL ("Internacional");

    private String descricao;

    Origem(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
