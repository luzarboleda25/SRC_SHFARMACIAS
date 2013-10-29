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
import javax.persistence.SequenceGenerator;
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
@Table(name = "COLA_PREORDEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ColaPreorden.findAll", query = "SELECT c FROM ColaPreorden c"),
    @NamedQuery(name = "ColaPreorden.findByColaPreMedId", query = "SELECT c FROM ColaPreorden c WHERE c.idcolapreorden = :idcolapreorden"),
    @NamedQuery(name = "ColaPreorden.findPrimeroCola", query = "SELECT MIN(c.idcolapreorden) FROM ColaPreorden c"),
    @NamedQuery(name = "ColaPreorden.findByCodCli", query = "SELECT c FROM ColaPreorden c WHERE c.idpreorden.codcli = :codcli"),
    @NamedQuery(name = "ColaPreorden.findByColaIdPreOrden", query = "SELECT c FROM ColaPreorden c WHERE c.idpreorden = :idpreorden"),
    @NamedQuery(name = "ColaPreorden.findByColaFechaHoy", query = "SELECT COUNT(c) FROM ColaPreorden c WHERE c.fecha BETWEEN :fecha1 and :fecha2")})
public class ColaPreorden implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COLA_PREORDEN")
    @SequenceGenerator(name = "SQ_COLA_PREORDEN", sequenceName = "SQ_COLA_PREORDEN", allocationSize = 1)
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCOLAPREORDEN")
    private BigDecimal idcolapreorden;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "IDPREORDEN", referencedColumnName = "IDPREORDEN")
    @ManyToOne(optional = false)
    private Preorden idpreorden;

    public ColaPreorden() {
    }

    public ColaPreorden(BigDecimal idcolapreorden) {
        this.idcolapreorden = idcolapreorden;
    }

    public BigDecimal getIdcolapreorden() {
        return idcolapreorden;
    }

    public void setIdcolapreorden(BigDecimal idcolapreorden) {
        this.idcolapreorden = idcolapreorden;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Preorden getIdpreorden() {
        return idpreorden;
    }

    public void setIdpreorden(Preorden idpreorden) {
        this.idpreorden = idpreorden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcolapreorden != null ? idcolapreorden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ColaPreorden)) {
            return false;
        }
        ColaPreorden other = (ColaPreorden) object;
        if ((this.idcolapreorden == null && other.idcolapreorden != null) || (this.idcolapreorden != null && !this.idcolapreorden.equals(other.idcolapreorden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.ColaPreorden[ idcolapreorden=" + idcolapreorden + " ]";
    }
    
}
