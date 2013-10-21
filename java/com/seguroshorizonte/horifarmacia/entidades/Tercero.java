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
 * @author Gedica
 */
@Entity
@Table(name = "TERCERO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tercero.findAll", query = "SELECT t FROM Tercero t"),
    @NamedQuery(name = "Tercero.findByTipoid", query = "SELECT t FROM Tercero t WHERE t.tipoid = :tipoid"),
    @NamedQuery(name = "Tercero.findByNumid", query = "SELECT t FROM Tercero t WHERE t.numid = :numid"),
    @NamedQuery(name = "Tercero.findByDvid", query = "SELECT t FROM Tercero t WHERE t.dvid = :dvid"),
    @NamedQuery(name = "Tercero.findByNomter", query = "SELECT t FROM Tercero t WHERE t.nomter = :nomter"),
    @NamedQuery(name = "Tercero.findByNumidTipoid", query = "SELECT t FROM Tercero t WHERE t.numid = :numid AND t.tipoid = :tipoid"),
    @NamedQuery(name = "Tercero.findByApeter", query = "SELECT t FROM Tercero t WHERE t.apeter = :apeter")})
public class Tercero implements Serializable {
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
    @Size(min = 1, max = 100)
    @Column(name = "NOMTER")
    private String nomter;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "APETER")
    private String apeter;

    public Tercero() {
    }

    public Tercero(BigDecimal numid) {
        this.numid = numid;
    }

    public Tercero(BigDecimal numid, String tipoid, BigInteger dvid, String nomter, String apeter) {
        this.numid = numid;
        this.tipoid = tipoid;
        this.dvid = dvid;
        this.nomter = nomter;
        this.apeter = apeter;
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

    public String getNomter() {
        return nomter;
    }

    public void setNomter(String nomter) {
        this.nomter = nomter;
    }

    public String getApeter() {
        return apeter;
    }

    public void setApeter(String apeter) {
        this.apeter = apeter;
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
        if (!(object instanceof Tercero)) {
            return false;
        }
        Tercero other = (Tercero) object;
        if ((this.numid == null && other.numid != null) || (this.numid != null && !this.numid.equals(other.numid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.Tercero[ numid=" + numid + " ]";
    }
    
}
