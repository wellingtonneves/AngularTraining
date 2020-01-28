package br.com.java.solution.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public abstract class EntidadeGenerica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, name = "data_ultima_atualizacao")
    private Date dataUltimaAtualizacao;

    @Column(nullable = false, name = "data_cadastro")
    protected Date dataCadastro;

    @Column(nullable = false)
    protected Boolean deletado;

    // Constructors

    /**
     * Construtor com parametros
     *
     * @param id
     * @param dataCadastro
     * @param deletado
     */
    public EntidadeGenerica(Long id, Date dataUltimaAtualizacao, Date dataCadastro, Boolean deletado) {
        this.id = id;
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
        this.dataCadastro = dataCadastro;
        this.deletado = deletado;
    }

    /**
     * Construtor vazio
     */
    public EntidadeGenerica() {
        this.deletado = false;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void dataCadastro(String dtCad) {
        this.dataCadastro = dataCadastro;
    }

    public Boolean getDeletado() {
        return deletado;
    }

    public void setDeletado(Boolean deletado) {
        this.deletado = deletado;
    }

    // Equals and HasCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntidadeGenerica)) return false;
        EntidadeGenerica that = (EntidadeGenerica) o;
        return getId().equals(that.getId()) &&
                getDataCadastro().equals(that.getDataCadastro()) &&
                getDeletado().equals(that.getDeletado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDataCadastro(), getDeletado());
    }

    // Pre Persist and Pre Update methods

    /**
     * Metodo preparado para executar antes de uma insercao de dados no banco.
     */
    @PrePersist
    private void prePersist() {
        atualizarUltimaAlteracao();
    }

    /**
     * Metodo preparado para executar antes de uma alteracao de dados no banco.
     */
    @PreUpdate
    private void preUpdate() {
        atualizarUltimaAlteracao();
    }

    private void atualizarUltimaAlteracao() {
        this.setDataUltimaAtualizacao(new Date());
    }

    // ToString

    /**
     * Metodo ToString
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "EntidadeGenerica{" +
                "id=" + id +
                ", dataModificado=" + dataUltimaAtualizacao +
                ", dataCadastro='" + dataCadastro + '\'' +
                ", deletado=" + deletado +
                '}';
    }
}
