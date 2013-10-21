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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "PREORDEN_MEDICAMENTO_ANALISTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreordenMedicamentoAnalista.findAll", query = "SELECT p FROM PreordenMedicamentoAnalista p"),
    @NamedQuery(name = "PreordenMedicamentoAnalista.findByIdpreMedAna", query = "SELECT p FROM PreordenMedicamentoAnalista p WHERE p.idpreMedAna = :idpreMedAna"),
    @NamedQuery(name = "PreordenMedicamentoAnalista.findByFecha", query = "SELECT p FROM PreordenMedicamentoAnalista p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "PreordenMedicamentoAnalista.findByEstado", query = "SELECT p FROM PreordenMedicamentoAnalista p WHERE p.estado = :estado"),
    @NamedQuery(name = "PreordenMedicamentoAnalista.findByObservacion", query = "SELECT p FROM PreordenMedicamentoAnalista p WHERE p.observacion = :observacion")})
public class PreordenMedicamentoAnalista implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPRE_MED_ANA")
    private BigDecimal idpreMedAna;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 50)
    @Column(name = "ESTADO")
    private String estado;
    @Size(max = 255)
    @Column(name = "OBSERVACION")
    private String observacion;
    @JoinColumn(name = "PREORD_MED_ID", referencedColumnName = "IDPREORDENMEDICAMENTO")
    @ManyToOne(optional = false)
    private PreordenMedicamento preordMedId;
    @JoinColumn(name = "ANALISTA_IDANALISTA", referencedColumnName = "IDANALISTA")
    @ManyToOne(optional = false)
    private Analista analistaIdanalista;

    public PreordenMedicamentoAnalista() {
    }

    public PreordenMedicamentoAnalista(BigDecimal idpreMedAna) {
        this.idpreMedAna = idpreMedAna;
    }

    public BigDecimal getIdpreMedAna() {
        return idpreMedAna;
    }

    public void setIdpreMedAna(BigDecimal idpreMedAna) {
        this.idpreMedAna = idpreMedAna;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public PreordenMedicamento getPreordMedId() {
        return preordMedId;
    }

    public void setPreordMedId(PreordenMedicamento preordMedId) {
        this.preordMedId = preordMedId;
    }

    public Analista getAnalistaIdanalista() {
        return analistaIdanalista;
    }

    public void setAnalistaIdanalista(Analista analistaIdanalista) {
        this.analistaIdanalista = analistaIdanalista;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpreMedAna != null ? idpreMedAna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreordenMedicamentoAnalista)) {
            return false;
        }
        PreordenMedicamentoAnalista other = (PreordenMedicamentoAnalista) object;
        if ((this.idpreMedAna == null && other.idpreMedAna != null) || (this.idpreMedAna != null && !this.idpreMedAna.equals(other.idpreMedAna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.PreordenMedicamentoAnalista[ idpreMedAna=" + idpreMedAna + " ]";
    }
    
}
