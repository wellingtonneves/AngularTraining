package br.com.java.solution.model;


import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "exportacao_midias")
@Where(clause = "deletado = 'false'")
public class ExportacaoMidia extends EntidadeGenerica {

    @Transient
    private byte[] bytes;

    @Column(name = "formulario_id")
    private long formularioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formulario_id", insertable = false, updatable = false)
    private Formulario formulario;

    @Column(name = "categoria_id")
    private long categoriaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", insertable = false, updatable = false)
    private MidiaCategoria categoria;

    @Column(name = "atributo_id_associado")
    private long atributoIdAssociado;

    @Column(nullable = false)
    private String nome;

    @Column(name = "ordem_servico_id")
    private Long ordemServicoId;

    @Column(name = "filho_id")
    private Long filhoID;

    @Column(name = "pai_id")
    private Long paiID;

    // Constructors

    /**
     * Construtor com parametros
     *
     * @param id
     * @param dataCadastro
     * @param ativo
     * @param categoria
     * @param dataModificado
     * @param formulario
     * @param atributoIdAssociado
     */
    public ExportacaoMidia(Long id, Date dataCadastro, Boolean ativo, MidiaCategoria categoria, Date dataModificado,
                           Formulario formulario, long atributoIdAssociado) {
        super(id, dataModificado, dataCadastro, ativo);
        this.categoria = categoria;
        this.formulario = formulario;
        this.atributoIdAssociado = atributoIdAssociado;
    }

    /**
     * Construtor vazio
     */
    public ExportacaoMidia() {
    }

    // Getters and Setters

    public long getCategoriaId() {
        return this.categoriaId;
    }

    public void setCategoriaId(long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public MidiaCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(MidiaCategoria categoria) {
        this.categoria = categoria;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public long getFormularioId() {
        return this.formularioId;
    }

    public void setFormularioId(long formularioId) {
        this.formularioId = formularioId;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public long getAtributoIdAssociado() {
        return this.atributoIdAssociado;
    }

    public void setAtributoIdAssociado(long atributoIdAssociado) {
        this.atributoIdAssociado = atributoIdAssociado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public Long getOrdemServicoId() {
        return ordemServicoId;
    }

    public void setOrdemServicoId(Long ordemServicoId) {
        this.ordemServicoId = ordemServicoId;
    }

    public Long getFilhoID() {
        return filhoID;
    }

    public void setFilhoID(Long filhoID) {
        this.filhoID = filhoID;
    }

    public Long getPaiID() {
        return paiID;
    }

    public void setPaiID(Long paiID) {
        this.paiID = paiID;
    }

    // To String

    /**
     * Metodo ToString
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "ExportacaoMidia{" + "formulario=" + formulario + ", categoria=" + categoria + ", nome='" + nome + '\''
                + '}';
    }
}
