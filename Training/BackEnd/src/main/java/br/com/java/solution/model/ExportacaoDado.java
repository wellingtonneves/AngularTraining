package br.com.java.solution.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "exportacao_dados")
@Where(clause = "deletado = 'false'")
public class ExportacaoDado extends EntidadeGenerica {

    @Column(nullable = false, name = "modulo_id")
    private Long moduloId;

    @Column(name = "formulario_id")
    private long formularioId;

    @Transient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formulario_id", insertable = false, updatable = false)
    private Formulario formulario;

    @Column(name = "campo_id")
    private long campoId;

    @Transient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campo_id", insertable = false, updatable = false)
    private Campo campo;

    @Column(name = "atributo_destino")
    private String atributoDestino;

    @Column(name = "agente_responsavel", nullable = false)
    private String agenteResponsavel;

    @Column
    private String valor;

    @Column(name = "ordem_servico_id", nullable = false)
    private Long ordemServicoId;

    @Column(name = "filho_numero")
    private Long filhoNumero;

    @Column(name = "filho_id")
    private Long filhoID;

    @Column(name = "pai_id")
    private Long paiID;

    /**
     * Construtor com todos os parametros
     *
     * @param id
     * @param dataUltimaAtualizacao
     * @param dataCadastro
     * @param deletado
     * @param moduloId
     * @param formulario
     * @param campo
     * @param agenteResponsavel
     */
    public ExportacaoDado(Long id, Date dataUltimaAtualizacao, Date dataCadastro, Boolean deletado, Long moduloId,
                          Formulario formulario, Campo campo, String agenteResponsavel) {
        super(id, dataUltimaAtualizacao, dataCadastro, deletado);
        this.moduloId = moduloId;
        this.formulario = formulario;
        this.campo = campo;
        this.agenteResponsavel = agenteResponsavel;
    }

    /**
     * Construtor com parametros desta classe
     *
     * @param moduloId
     * @param formulario
     * @param campo
     */
    public ExportacaoDado(Long moduloId, Formulario formulario, Campo campo) {
        this.moduloId = moduloId;
        this.formulario = formulario;
        this.campo = campo;
    }

    /**
     * Construtor vazio
     */
    public ExportacaoDado() {
    }


    public Long getModuloId() {
        return moduloId;
    }

    public void setModuloId(Long moduloId) {
        this.moduloId = moduloId;
    }

    public long getFormularioId() {
        return this.formularioId;
    }

    public void setFormularioId(long formularioId) {
        this.formularioId = formularioId;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public long getCampoId() {
        return this.campoId;
    }

    public void setCampoId(long campoId) {
        this.campoId = campoId;
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public String getAgenteResponsavel() {
        return this.agenteResponsavel;
    }

    public void setAgenteResponsavel(String agenteResponsavel) {
        this.agenteResponsavel = agenteResponsavel;
    }

    public String getAtributoDestino() {
        return this.atributoDestino;
    }

    public void setAtributoDestino(String atributoDestino) {
        this.atributoDestino = atributoDestino;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Long getOrdemServicoId() {
        return ordemServicoId;
    }

    public void setOrdemServicoId(Long ordemServicoId) {
        this.ordemServicoId = ordemServicoId;
    }

    public Long getFilhoNumero() {
        return filhoNumero;
    }

    public void setFilhoNumero(Long filhoNumero) {
        this.filhoNumero = filhoNumero;
    }

    public Long getFilhoID() {
        return this.filhoID;
    }

    public void setFilhoID(Long filhoID) {
        this.filhoID = filhoID;
    }

    public Long getPaiID() {
        return this.paiID;
    }

    public void setPaiID(Long paiID) {
        this.paiID = paiID;
    }

    /**
     * Metodo ToString
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "ExportacaoDado{" + "id=" + id + ", dataCadastro=" + dataCadastro + ", deletado=" + deletado + '}';
    }
}

