package br.com.aocubo.brewer.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by alessandro on 28/04/17.
 */

@Entity
@Table(name = "estado")
public class Estado implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sigla;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estado estado = (Estado) o;

        if (id != null ? !id.equals(estado.id) : estado.id != null) return false;
        if (nome != null ? !nome.equals(estado.nome) : estado.nome != null) return false;
        if (sigla != null ? !sigla.equals(estado.sigla) : estado.sigla != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (sigla != null ? sigla.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

}
