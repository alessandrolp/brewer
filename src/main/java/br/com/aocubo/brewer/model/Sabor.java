package br.com.aocubo.brewer.model;

/**
 * Created by alessandro on 25/03/17.
 */

public enum Sabor {

    ADOCICADA ("Adocicada"),
    AMARGA ("Amarga"),
    FORTE ("Forte"),
    FRUTADA ("Frutada"),
    SUAVE ("Suave");

    private String descricao;

    Sabor(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
