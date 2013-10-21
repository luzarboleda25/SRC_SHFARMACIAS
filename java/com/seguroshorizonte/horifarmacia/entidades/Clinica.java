/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GEDICA TEAM
 */
@Entity
@Table(name = "CLINICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clinica.findAll", query = "SELECT c FROM Clinica c"),
    @NamedQuery(name = "Clinica.findByTipoid", query = "SELECT c FROM Clinica c WHERE c.tipoid = :tipoid"),
    @NamedQuery(name = "Clinica.findByNumid", query = "SELECT c FROM Clinica c WHERE c.numid = :numid"),
    @NamedQuery(name = "Clinica.findByDvid", query = "SELECT c FROM Clinica c WHERE c.dvid = :dvid"),
    @NamedQuery(name = "Clinica.findByCodclinica", query = "SELECT c FROM Clinica c WHERE c.codclinica = :codclinica")})
public class Clinica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPOID")
    private String tipoid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMID")
    private BigDecimal numid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DVID")
    private BigInteger dvid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CODCLINICA")
    private String codclinica;

    public Clinica() {
    }

    public Clinica(BigDecimal numid) {
        this.numid = numid;
    }

    public Clinica(BigDecimal numid, String tipoid, BigInteger dvid, String codclinica) {
        this.numid = numid;
        this.tipoid = tipoid;
        this.dvid = dvid;
        this.codclinica = codclinica;
    }

    public String getTipoid() {
        return tipoid;
    }

    public void setTipoid(String tipoid) {
        this.tipoid = tipoid;
    }

    public BigDecimal getNumid() {
        return numid;
    }

    public void setNumid(BigDecimal numid) {
        this.numid = numid;
    }

    public BigInteger getDvid() {
        return dvid;
    }

    public void setDvid(BigInteger dvid) {
        this.dvid = dvid;
    }

    public String getCodclinica() {
        return codclinica;
    }

    public void setCodclinica(String codclinica) {
        this.codclinica = codclinica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numid != null ? numid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clinica)) {
            return false;
        }
        Clinica other = (Clinica) object;
        if ((this.numid == null && other.numid != null) || (this.numid != null && !this.numid.equals(other.numid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.Clinica[ numid=" + numid + " ]";
    }
    
}
