/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "COLA_PREORDEN_MEDICAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ColaPreordenMedicamento.findAll", query = "SELECT c FROM ColaPreordenMedicamento c"),
    @NamedQuery(name = "ColaPreordenMedicamento.findByColaPreMedId", query = "SELECT c FROM ColaPreordenMedicamento c WHERE c.colaPreMedId = :colaPreMedId")})
public class ColaPreordenMedicamento implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COLA_PRE_MED_ID")
    private BigDecimal colaPreMedId;
    @JoinColumn(name = "PREORDEN_MEDICAMENTO_ID", referencedColumnName = "IDPREORDENMEDICAMENTO")
    @ManyToOne(optional = false)
    private PreordenMedicamento preordenMedicamentoId;

    public ColaPreordenMedicamento() {
    }

    public ColaPreordenMedicamento(BigDecimal colaPreMedId) {
        this.colaPreMedId = colaPreMedId;
    }

    public BigDecimal getColaPreMedId() {
        return colaPreMedId;
    }

    public void setColaPreMedId(BigDecimal colaPreMedId) {
        this.colaPreMedId = colaPreMedId;
    }

    public PreordenMedicamento getPreordenMedicamentoId() {
        return preordenMedicamentoId;
    }

    public void setPreordenMedicamentoId(PreordenMedicamento preordenMedicamentoId) {
        this.preordenMedicamentoId = preordenMedicamentoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (colaPreMedId != null ? colaPreMedId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ColaPreordenMedicamento)) {
            return false;
        }
        ColaPreordenMedicamento other = (ColaPreordenMedicamento) object;
        if ((this.colaPreMedId == null && other.colaPreMedId != null) || (this.colaPreMedId != null && !this.colaPreMedId.equals(other.colaPreMedId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.ColaPreordenMedicamento[ colaPreMedId=" + colaPreMedId + " ]";
    }
    
}
