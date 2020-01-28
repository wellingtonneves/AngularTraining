package br.com.java.solution.model;


import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity(name = "formularios")
@Where(clause = "deletado = 'false'")
public class Formulario extends EntidadeGenerica {
    @Column(nullable = false, name = "cliente_id")
    private Long cliente;

    @Column(nullable = false, name = "modulo_id")
    private Long modulo;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, name = "entidade_destino")
    private String entidadeDestino;

    @Column(nullable = false, name = "banco_dados_destino")
    private String bancoDadosDestino;

    @Column(nullable = false, name = "schema_destino")
    private String schemaDestino;

    @Column(nullable = false, name = "ordem_id")
    private Long ordem;

    @Column(name = "formulario_pai_id")
    private Long formularioPaiId;

    @Transient
    private Optional<List<Secao>> secoes;

    @Transient
    private Optional<List<Dependencia>> dependencias;

    // Constructors

    /**
     * Construtor com parametros
     *
     * @param id
     * @param cliente
     * @param modulo
     * @param nome
     * @param entidadeDestino
     * @param bancoDadosDestino
     * @param schemaDestino
     * @param dataModificado
     * @param dataCadastro
     * @param ativo
     * @param secoes
     * @param ordem
     */
    public Formulario(Long id, Long cliente, Long modulo, String nome, String entidadeDestino, String bancoDadosDestino,
                      String schemaDestino, Date dataModificado, Date dataCadastro, Boolean ativo, Long ordem,
                      Optional<List<Secao>> secoes, Optional<List<Dependencia>> dependencias) {
        super(id, dataModificado, dataCadastro, ativo);
        this.cliente = cliente;
        this.modulo = modulo;
        this.nome = nome;
        this.entidadeDestino = entidadeDestino;
        this.bancoDadosDestino = bancoDadosDestino;
        this.schemaDestino = schemaDestino;
        this.ordem = ordem;
        this.secoes = secoes;
        this.dependencias = dependencias;
    }

    /**
     * Construtor vazio
     */
    public Formulario() {
        super();
    }

    // Getters and Setters

    public Optional<List<Secao>> getSecoes() {
        return secoes;
    }

    public void setSecoes(Optional<List<Secao>> secoes) {
        this.secoes = secoes;
    }

    public Optional<List<Dependencia>> getDependencias() {
        return dependencias;
    }

    public void setDependencias(Optional<List<Dependencia>> dependencias) {
        this.dependencias = dependencias;
    }

    public Long getOrdem() {
        return ordem;
    }

    public void setOrdem(Long ordem) {
        this.ordem = ordem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public Long getModulo() {
        return modulo;
    }

    public void setModulo(Long modulo) {
        this.modulo = modulo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEntidadeDestino() {
        return entidadeDestino;
    }

    public void setEntidadeDestino(String entidadeDestino) {
        this.entidadeDestino = entidadeDestino;
    }

    public String getBancoDadosDestino() {
        return this.bancoDadosDestino;
    }

    public void setBancoDadosDestino(String bancoDadosDestino) {
        this.bancoDadosDestino = bancoDadosDestino;
    }

    public String getSchemaDestino() {
        return this.schemaDestino;
    }

    public void setSchemaDestino(String schemaDestino) {
        this.schemaDestino = schemaDestino;
    }

    public Long getFormularioPaiId() {
        return formularioPaiId;
    }

    public void setFormularioPaiId(Long formularioPaiId) {
        this.formularioPaiId = formularioPaiId;
    }

    // ToString

    /**
     * Metodo ToString
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "Formulario{" + "cliente=" + cliente + ", modulo=" + modulo + ", nome='" + nome + '\''
                + ", entidadeDestino='" + entidadeDestino + '\'' + ", ordem=" + ordem + ", secoes=" + secoes + '}';
    }
}