package br.com.java.solution.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity(name = "formulario_campos")
@Where(clause = "deletado = 'false'")
public class Campo extends EntidadeGenerica {
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false, name = "ordem_exibicao")
    private Integer ordemExibicao;

    @Column(nullable = false)
    private Boolean visivelMobile;

    @Column(nullable = true)
    private Boolean abrirFormularioFilho;

    @Column(nullable = true)
    private Boolean inativo;

    @Column(nullable = true)
    private Boolean obrigatorio;

    @ManyToOne
    @JoinColumn(name = "secao_id")
    private Secao secao;

    @Column(nullable = true, name = "atributo_destino")
    private String atributoDestino;

    @OneToOne
    @JoinColumn(name = "dependencia_id")
    private Dependencia dependencia;

    @Transient
    private Optional<List<CampoOpcao>> CampoOpcaos;

    // Constructors

    /**
     * Construtor com parametros
     *
     * @param id
     * @param nome
     * @param tipo
     * @param ordemExibicao
     * @param visivelMobile
     * @param abrirFormularioFilho
     * @param secao
     * @param dataModificado
     * @param dataCadastro
     * @param ativo
     * @param CampoOpcaos
     */
    public Campo(Long id, String nome, String tipo, Integer ordemExibicao, Boolean visivelMobile, Secao secao,
                 Date dataModificado, Date dataCadastro, Boolean ativo, Optional<List<CampoOpcao>> CampoOpcaos, Boolean abrirFormularioFilho) {
        super(id, dataModificado, dataCadastro, ativo);
        this.nome = nome;
        this.tipo = tipo;
        this.ordemExibicao = ordemExibicao;
        this.visivelMobile = visivelMobile;
        this.abrirFormularioFilho = abrirFormularioFilho;
        this.secao = secao;
        this.CampoOpcaos = CampoOpcaos;
    }

    /**
     * Construtor vazio
     */
    public Campo() {
        super();
    }

    // Getters and Setters

    public Optional<List<CampoOpcao>> getCampoOpcaos() {
        return CampoOpcaos;
    }

    public void setCampoOpcaos(Optional<List<CampoOpcao>> CampoOpcaos) {
        this.CampoOpcaos = CampoOpcaos;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getOrdemExibicao() {
        return ordemExibicao;
    }

    public void setOrdemExibicao(Integer ordemExibicao) {
        this.ordemExibicao = ordemExibicao;
    }

    public Boolean getVisivelMobile() {
        return visivelMobile;
    }

    public void setVisivelMobile(Boolean visivelMobile) {
        this.visivelMobile = visivelMobile;
    }

    public Boolean getAbrirFormularioFilho() {
        return abrirFormularioFilho;
    }

    public void setAbrirFormularioFilho(Boolean abrirFormularioFilho) {
        this.abrirFormularioFilho = abrirFormularioFilho;
    }

    public Boolean getInativo() {
        return this.inativo;
    }

    public Boolean getObrigatorio() {
        return this.obrigatorio;
    }

    public void setObrigatorio(Boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    public Secao getSecao() {
        return secao;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public void setInativo(Boolean inativo) {
        this.inativo = inativo;
    }

    public String getAtributoDestino() {
        return atributoDestino;
    }

    public void setAtributoDestino(String atributoDestino) {
        this.atributoDestino = atributoDestino;
    }

    // To String

    /**
     * Metodo ToString
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "Campo{" + "nome='" + nome + '\'' + ", tipo='" + tipo + '\'' + ", ordemExibicao='" + ordemExibicao + '\''
                + ", visivelMobile=" + visivelMobile + ", secao=" + secao + ", CampoOpcaos=" + CampoOpcaos + '}';
    }
}
