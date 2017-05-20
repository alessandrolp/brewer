package br.com.aocubo.brewer.repository.filter;

import br.com.aocubo.brewer.model.Grupo;

import java.util.List;

/**
 * Created by alessandro on 20/05/17.
 */
public class UsuarioFilter {

    private String nome;

    private String email;

    private List<Grupo> grupos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
}
