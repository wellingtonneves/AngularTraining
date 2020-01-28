package br.com.java.solution.model;


import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "formulario_midia_categorias")
@Where(clause = "deletado = 'false'")
public class MidiaCategoria extends EntidadeGenerica {
    @Column(nullable = false)
    private String nome;

    // Constructor

    /**
     * Construtor com parametros
     *
     * @param id
     * @param dataCadastro
     * @param ativo
     * @param nome
     * @param dataModificado
     */
    public MidiaCategoria(Long id, Date dataCadastro, Boolean ativo, String nome, Date dataModificado) {
        super(id, dataModificado, dataCadastro, ativo);
        this.nome = nome;
    }

    /**
     * Construtor vazio
     */
    public MidiaCategoria() {
    }

    // Getters and Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // To String

    /**
     * Metodo ToString
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "MidiaCategoria{" + "nome='" + nome + '\'' + '}';
    }
}
