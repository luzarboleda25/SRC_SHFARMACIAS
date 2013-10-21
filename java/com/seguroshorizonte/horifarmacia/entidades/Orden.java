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
 * @author Gedica
 */
@Entity
@Table(name = "ORDEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orden.findAll", query = "SELECT o FROM Orden o"),
    @NamedQuery(name = "Orden.findByIdorden", query = "SELECT o FROM Orden o WHERE o.idorden = :idorden"),
    @NamedQuery(name = "Orden.findByFecha", query = "SELECT o FROM Orden o WHERE o.fecha = :fecha")})
public class Orden implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDORDEN")
    private BigDecimal idorden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "IDPOMEDICAMENTO", referencedColumnName = "IDPREORDENMEDICAMENTO")
    @ManyToOne(optional = false)
    private PreordenMedicamento idpomedicamento;
    @JoinColumn(name = "IDFARMACIA", referencedColumnName = "IDFARMACIA")
    @ManyToOne(optional = false)
    private Farmacia idfarmacia;

    public Orden() {
    }

    public Orden(BigDecimal idorden) {
        this.idorden = idorden;
    }

    public Orden(BigDecimal idorden, Date fecha) {
        this.idorden = idorden;
        this.fecha = fecha;
    }

    public BigDecimal getIdorden() {
        return idorden;
    }

    public void setIdorden(BigDecimal idorden) {
        this.idorden = idorden;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public PreordenMedicamento getIdpomedicamento() {
        return idpomedicamento;
    }

    public void setIdpomedicamento(PreordenMedicamento idpomedicamento) {
        this.idpomedicamento = idpomedicamento;
    }

    public Farmacia getIdfarmacia() {
        return idfarmacia;
    }

    public void setIdfarmacia(Farmacia idfarmacia) {
        this.idfarmacia = idfarmacia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idorden != null ? idorden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orden)) {
            return false;
        }
        Orden other = (Orden) object;
        if ((this.idorden == null && other.idorden != null) || (this.idorden != null && !this.idorden.equals(other.idorden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.Orden[ idorden=" + idorden + " ]";
    }
    
}
