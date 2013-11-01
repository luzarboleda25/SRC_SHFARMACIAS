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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pangea
 */
@Entity
@Table(name = "PREORDEN_ANALISTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreordenAnalista.findAll", query = "SELECT p FROM PreordenAnalista p"),
    @NamedQuery(name = "PreordenAnalista.findByIdpreordenanalista", query = "SELECT p FROM PreordenAnalista p WHERE p.idpreordenanalista = :idpreordenanalista"),
    @NamedQuery(name = "PreordenAnalista.findByFecha", query = "SELECT p FROM PreordenAnalista p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "PreordenAnalista.findByStatus", query = "SELECT p FROM PreordenAnalista p WHERE p.status = :status"),
    @NamedQuery(name = "PreordenAnalista.findByObservacion", query = "SELECT p FROM PreordenAnalista p WHERE p.observacion = :observacion"),
    @NamedQuery(name = "PreordenAnalista.findByObservacionauditor", query = "SELECT p FROM PreordenAnalista p WHERE p.observacionauditor = :observacionauditor"),
    @NamedQuery(name = "PreordenAnalista.ContarSHXidAnalista", query = "SELECT COUNT(p.idpreordenanalista)  FROM PreordenAnalista p WHERE p.idanalista = :idanalista AND p.fecha BETWEEN :fecha1 and :fecha2"),
    @NamedQuery(name = "PreordenAnalista.findByIdAnalista", query = "SELECT  p FROM PreordenAnalista p  WHERE p.idanalista = :idanalista AND p.fecha BETWEEN :fecha1 and :fecha2"),
    @NamedQuery(name = "PreordenAnalista.findByProcesadaFechaHoy", query = "SELECT DISTINCT p.idanalista FROM PreordenAnalista p WHERE p.status = :status AND p.fecha BETWEEN :fecha1 and :fecha2"),
    @NamedQuery(name = "PreordenAnalista.findByStatusPendiente", query = "SELECT DISTINCT p.idanalista FROM PreordenAnalista p WHERE p.status = :status"),
    @NamedQuery(name = "PreordenAnalista.ContarAnalistaStatusYFecha", query = "SELECT COUNT(p.idpreordenanalista) FROM PreordenAnalista p WHERE p.idanalista = :idanalista AND p.fecha BETWEEN :fecha1 and :fecha2 AND p.status = :status"),
    @NamedQuery(name = "PreordenAnalista.findByContadorProcesadas", query = "SELECT COUNT(p) FROM PreordenAnalista p WHERE p.status = :status AND p.fecha BETWEEN :fecha1 and :fecha2")})

public class PreordenAnalista implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_POM_ANALISTA")
    @SequenceGenerator(name = "SQ_POM_ANALISTA", sequenceName = "SQ_POM_ANALISTA", allocationSize = 1)
    @Id
    @Basic(optional = false)
    @Column(name = "IDPREORDENANALISTA")
    private BigDecimal idpreordenanalista;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 1)
    @Column(name = "STATUS")
    private String status;
    @Size(max = 255)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Size(max = 255)
    @Column(name = "OBSERVACIONAUDITOR")
    private String observacionauditor;
    @JoinColumn(name = "IDPREORDENANALISTA", referencedColumnName = "IDPREORDEN", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Preorden preorden;
    @JoinColumn(name = "IDANALISTA", referencedColumnName = "IDANALISTA")
    @ManyToOne(optional = false)
    private Analista idanalista;
    @JoinColumn(name = "IDAUDITOR", referencedColumnName = "IDANALISTA")
    @ManyToOne
    private Analista idauditor;

    public PreordenAnalista() {
    }

    public PreordenAnalista(BigDecimal idpreordenanalista) {
        this.idpreordenanalista = idpreordenanalista;
    }

    public BigDecimal getIdpreordenanalista() {
        return idpreordenanalista;
    }

    public void setIdpreordenanalista(BigDecimal idpreordenanalista) {
        this.idpreordenanalista = idpreordenanalista;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getObservacionauditor() {
        return observacionauditor;
    }

    public void setObservacionauditor(String observacionauditor) {
        this.observacionauditor = observacionauditor;
    }

    public Preorden getPreorden() {
        return preorden;
    }

    public void setPreorden(Preorden preorden) {
        this.preorden = preorden;
    }

    public Analista getIdanalista() {
        return idanalista;
    }

    public void setIdanalista(Analista idanalista) {
        this.idanalista = idanalista;
    }

    public Analista getIdauditor() {
        return idauditor;
    }

    public void setIdauditor(Analista idauditor) {
        this.idauditor = idauditor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpreordenanalista != null ? idpreordenanalista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreordenAnalista)) {
            return false;
        }
        PreordenAnalista other = (PreordenAnalista) object;
        if ((this.idpreordenanalista == null && other.idpreordenanalista != null) || (this.idpreordenanalista != null && !this.idpreordenanalista.equals(other.idpreordenanalista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.PreordenAnalista[ idpreordenanalista=" + idpreordenanalista + " ]";
    }
    
}
