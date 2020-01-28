package br.com.java.solution.model;


import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name = "formulario_campos_opcao")
@Where(clause = "deletado = 'false'")
public class CampoOpcao extends EntidadeGenerica {
    @ManyToOne
    @JoinColumn(name = "campo_id")
    private Campo campo;

    @Column(nullable = false)
    private String valor;

    // Constructor

    /**
     * Construtor com parametros
     *
     * @param id
     * @param campo
     * @param valor
     * @param dataModificado
     * @param dataCadastro
     * @param ativo
     */
    public CampoOpcao(Long id, Campo campo, String valor, Date dataModificado, Date dataCadastro, Boolean ativo) {
        super(id, dataModificado, dataCadastro, ativo);
        this.campo = campo;
        this.valor = valor;
    }

    /**
     * Construtor vazio
     */
    public CampoOpcao() {
        super();
    }

    // Getters and Setters

    public Campo getCampos() {
        return campo;
    }

    public void setCampos(Campo campo) {
        this.campo = campo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // To String

    /**
     * Metodo ToString
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "CampoOpcao{" +
                "campo=" + campo +
                ", valor='" + valor + '\'' +
                '}';
    }
}
