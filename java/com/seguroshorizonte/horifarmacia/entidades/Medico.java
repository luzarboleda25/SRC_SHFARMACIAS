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
@Table(name = "MEDICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medico.findAll", query = "SELECT m FROM Medico m"),
    @NamedQuery(name = "Medico.findByTipoid", query = "SELECT m FROM Medico m WHERE m.tipoid = :tipoid"),
    @NamedQuery(name = "Medico.findByNumid", query = "SELECT m FROM Medico m WHERE m.numid = :numid"),
    @NamedQuery(name = "Medico.findByNumidYTipoid", query = "SELECT m FROM Medico m WHERE m.numid LIKE :numid AND UPPER(m.tipoid) = :tipoid"),
    @NamedQuery(name = "Medico.findByDvid", query = "SELECT m FROM Medico m WHERE m.dvid = :dvid"),
    @NamedQuery(name = "Medico.findByCodmedico", query = "SELECT m FROM Medico m WHERE m.codmedico = :codmedico")})
public class Medico implements Serializable {
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
    @Column(name = "CODMEDICO")
    private String codmedico;

    public Medico() {
    }

    public Medico(BigDecimal numid) {
        this.numid = numid;
    }

    public Medico(BigDecimal numid, String tipoid, BigInteger dvid, String codmedico) {
        this.numid = numid;
        this.tipoid = tipoid;
        this.dvid = dvid;
        this.codmedico = codmedico;
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

    public String getCodmedico() {
        return codmedico;
    }

    public void setCodmedico(String codmedico) {
        this.codmedico = codmedico;
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
        if (!(object instanceof Medico)) {
            return false;
        }
        Medico other = (Medico) object;
        if ((this.numid == null && other.numid != null) || (this.numid != null && !this.numid.equals(other.numid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.Medico[ numid=" + numid + " ]";
    }
    
}
