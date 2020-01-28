package br.com.java.solution.model;


import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity(name = "formulario_secoes")
@Where(clause = "deletado = 'false'")
public class Secao extends EntidadeGenerica {
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Boolean visivelMobile;

    @Column(nullable = false, name = "ordem_exibicao")
    private Integer ordemExibicao;

    @ManyToOne
    @JoinColumn(name = "formulario_id")
    private Formulario formulario;

    @Transient
    private Optional<List<Campo>> campos;

    // Constructor

    /**
     * Construtor com parametros
     *
     * @param id
     * @param nome
     * @param visivelMobile
     * @param ordemExibicao
     * @param formulario
     * @param ativo
     * @param dataModificado
     * @param dataCadastro
     * @param campos
     */
    public Secao(Long id, String nome, Boolean visivelMobile, Integer ordemExibicao, Formulario formulario,
                 Boolean ativo, Date dataModificado, Date dataCadastro, Optional<List<Campo>> campos) {
        super(id, dataModificado, dataCadastro, ativo);
        this.nome = nome;
        this.visivelMobile = visivelMobile;
        this.ordemExibicao = ordemExibicao;
        this.formulario = formulario;
        this.campos = campos;
    }

    /**
     * Construtor vazio
     */
    public Secao() {
        super();
        setVisivelMobile(true);
    }

    // Getters and Setters

    public Optional<List<Campo>> getCampos() {
        return campos;
    }

    public void setCampos(Optional<List<Campo>> campos) {
        this.campos = campos;
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

    public Boolean getVisivelMobile() {
        return visivelMobile;
    }

    public void setVisivelMobile(Boolean visivelMobile) {
        this.visivelMobile = visivelMobile;
    }

    public Integer getOrdemExibicao() {
        return ordemExibicao;
    }

    public void setOrdemExibicao(Integer ordemExibicao) {
        this.ordemExibicao = ordemExibicao;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    // To String

    /**
     * Metodo ToString
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "Secao{" + "nome='" + nome + '\'' + ", visivelMobile=" + visivelMobile + ", ordemExibicao='"
                + ordemExibicao + '\'' + ", formulario=" + formulario + ", campos=" + campos + '}';
    }
}