/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pangea
 */
@Entity
@Table(name = "COLA_PREORDEN_MEDICAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ColaPreordenMedicamento.findAll", query = "SELECT c FROM ColaPreordenMedicamento c"),
    @NamedQuery(name = "ColaPreordenMedicamento.findByColaPreMedId", query = "SELECT c FROM ColaPreordenMedicamento c WHERE c.colaPreMedId = :colaPreMedId"),
    @NamedQuery(name = "ColaPreordenMedicamento.findPrimeroCola", query = "SELECT MIN(c.colaPreMedId) FROM ColaPreordenMedicamento c"),
    @NamedQuery(name = "ColaPreordenMedicamento.findByCodCli", query = "SELECT c FROM ColaPreordenMedicamento c WHERE c.preordenMedicamentoId.idpreorden.codcli = :codcli"),
    @NamedQuery(name = "ColaPreordenMedicamento.findByColaIdPreOrden", query = "SELECT c FROM ColaPreordenMedicamento c WHERE c.preordenMedicamentoId.idpreorden = :idpreorden"),
    @NamedQuery(name = "ColaPreordenMedicamento.findByFechaHoy", query = "SELECT COUNT(c.preordenMedicamentoId) FROM ColaPreordenMedicamento c WHERE c.fecha BETWEEN :fecha1 AND :fecha2")})
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
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
