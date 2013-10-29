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
@Table(name = "REGISTRO_INGRESO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroIngreso.findAll", query = "SELECT r FROM RegistroIngreso r"),
    @NamedQuery(name = "RegistroIngreso.findById", query = "SELECT r FROM RegistroIngreso r WHERE r.idregistroingreso = :idregistroingreso"),
    @NamedQuery(name = "RegistroIngreso.diasTrabajadosXS", query = "SELECT count(r) FROM RegistroIngreso r WHERE r.idanalista = :idanalista AND r.fecha between :fecha1 AND :fecha2"),
    @NamedQuery(name = "RegistroIngreso.findByFecha", query = "SELECT r FROM RegistroIngreso r WHERE r.fecha = :fecha")})

public class RegistroIngreso implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_REGISTRO_INGRESO")
   @SequenceGenerator(name = "SQ_REGISTRO_INGRESO", sequenceName = "SQ_REGISTRO_INGRESO", allocationSize = 1)
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDREGISTROINGRESO")
    private BigDecimal idregistroingreso;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "IDANALISTA", referencedColumnName = "IDANALISTA")
    @ManyToOne(optional = false)
    private Analista idanalista;

    public RegistroIngreso() {
    }

    public RegistroIngreso(BigDecimal idregistroingreso) {
        this.idregistroingreso = idregistroingreso;
    }

    public BigDecimal getIdregistroingreso() {
        return idregistroingreso;
    }

    public void setIdregistroingreso(BigDecimal idregistroingreso) {
        this.idregistroingreso = idregistroingreso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Analista getIdanalista() {
        return idanalista;
    }

    public void setIdanalista(Analista idanalista) {
        this.idanalista = idanalista;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idregistroingreso != null ? idregistroingreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroIngreso)) {
            return false;
        }
        RegistroIngreso other = (RegistroIngreso) object;
        if ((this.idregistroingreso == null && other.idregistroingreso != null) || (this.idregistroingreso != null && !this.idregistroingreso.equals(other.idregistroingreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.RegistroIngreso[ idregistroingreso=" + idregistroingreso + " ]";
    }
    
}
