package br.com.java.solution.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "formulario_campo_invisivel")
public class CampoInvisivel extends EntidadeGenerica {

    @ManyToOne
    @JsonIgnoreProperties({"camposParaDeixarInvisivel"})
    @JsonIgnore
    @JoinColumn(name = "dependencia_id")
    private Dependencia dependencia;

    @Column(name = "campo_id")
    private Long idCampo;


    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public Long getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(Long idCampo) {
        this.idCampo = idCampo;
    }
}
