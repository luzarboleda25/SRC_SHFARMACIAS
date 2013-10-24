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
    @NamedQuery(name = "RegistroIngreso.findById", query = "SELECT r FROM RegistroIngreso r WHERE r.id = :id"),
    @NamedQuery(name = "RegistroIngreso.findByFecha", query = "SELECT r FROM RegistroIngreso r WHERE r.fecha = :fecha")})
public class RegistroIngreso implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_REGISTRO_INGRESO")
    @SequenceGenerator(name = "SQ_REGISTRO_INGRESO", sequenceName = "SQ_REGISTRO_INGRESO", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "ANALISTA_IDANALISTA", referencedColumnName = "IDANALISTA")
    @ManyToOne(optional = false)
    private Analista analistaIdanalista;

    public RegistroIngreso() {
    }

    public RegistroIngreso(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroIngreso)) {
            return false;
        }
        RegistroIngreso other = (RegistroIngreso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.RegistroIngreso[ id=" + id + " ]";
    }
}
