package br.com.aocubo.brewer.model;

import br.com.aocubo.brewer.validation.SKU;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "cerveja")
public class Cerveja implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto incremento do mysql
    private Long id;

    @SKU
    @NotBlank(message = "SKU é obrigatório")
    private String sku;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Size(min = 1, max = 50, message = "O tamanho da descrição deve estar entre 1 e 50")
    private String descricao;

    @NotNull(message = "Valor é obrigatório")
    @DecimalMin(value = "0.50", message = "valor não pode ser menor que 0,50")
    private BigDecimal valor;

    @NotNull(message = "Teor Alcoolico é obrigatório")
    @DecimalMax(value = "100.0", message = "Teor alcoolico não pode ser maior que 100%")
    @Column(name = "teor_alcoolico")
    private BigDecimal teorAlcoolico;

    @NotNull(message = "Comissão é obrigatória")
    @DecimalMax(value = "100.0", message = "Comissão não pode ser maior que 100%")
    private BigDecimal comissao;

    @NotNull(message = "Quantidade em estoque é obrigatório")
    @Column(name = "quantidade_estoque")
    private Integer quantidadeEstoque;

    @NotNull(message = "Origem é obrigatório")
    @Enumerated(EnumType.STRING)
    private Origem origem;

    @NotNull(message = "Sabor é obrigatório")
    @Enumerated(EnumType.STRING)
    private Sabor sabor;

    @NotNull(message = "Estilo é obrigatório")
    @ManyToOne
    @JoinColumn(name = "id_estilo")
    private Estilo estilo;

    private String foto;

    @Column(name = "content_type")
    private String contentType;

    @PrePersist
    @PreUpdate
    private void prePersistUpdate() {
        sku = sku.toUpperCase();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getTeorAlcoolico() {
        return teorAlcoolico;
    }

    public void setTeorAlcoolico(BigDecimal teorAlcoolico) {
        this.teorAlcoolico = teorAlcoolico;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void setOrigem(Origem origem) {
        this.origem = origem;
    }

    public void setSabor(Sabor sabor) {
        this.sabor = sabor;
    }

    public Origem getOrigem() {
        return origem;
    }

    public Sabor getSabor() {
        return sabor;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public String getFoto() {
        return foto;
    }

    public String getFotoOuMock(){
        return !StringUtils.isEmpty(foto) ? foto : "cerveja-mock.png";
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cerveja cerveja = (Cerveja) o;

        if (id != null ? !id.equals(cerveja.id) : cerveja.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
