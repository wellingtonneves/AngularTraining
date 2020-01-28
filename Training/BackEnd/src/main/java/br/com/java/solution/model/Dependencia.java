package br.com.java.solution.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "formulario_dependencia")
public class Dependencia extends EntidadeGenerica {

    @Column(name = "campo")
    private Long campo;

    @Column(name = "depende_do_campo")
    private Long dependeDoCampo;

    @Column(name = "depende_do_campo_opcao")
    private Long dependeDoCampoOpcao;

    @Column(name = "depende_do_campo_valor", nullable = true)
    private String dependeDoCampoValor;

    @OneToMany(mappedBy = "dependencia")
    private List<CampoInvisivel> camposParaDeixarInvisivel;

    // Getters and Setters

    public Long getCampo() {
        return campo;
    }

    public void setCampo(Long campo) {
        this.campo = campo;
    }

    public Long getDependeDoCampo() {
        return dependeDoCampo;
    }

    public void setDependeDoCampo(Long dependeDoCampo) {
        this.dependeDoCampo = dependeDoCampo;
    }

    public Long getDependeDoCampoOpcao() {
        return dependeDoCampoOpcao;
    }

    public void setDependeDoCampoOpcao(Long dependeDoCampoOpcao) {
        this.dependeDoCampoOpcao = dependeDoCampoOpcao;
    }

    public String getDependeDoCampoValor() {
        return dependeDoCampoValor;
    }

    public void setDependeDoCampoValor(String dependeDoCampoValor) {
        this.dependeDoCampoValor = dependeDoCampoValor;
    }

    public List<CampoInvisivel> getCamposParaDeixarInvisivel() {
        return camposParaDeixarInvisivel;
    }

    public void setCamposParaDeixarInvisivel(List<CampoInvisivel> camposParaDeixarInvisivel) {
        this.camposParaDeixarInvisivel = camposParaDeixarInvisivel;
    }
}