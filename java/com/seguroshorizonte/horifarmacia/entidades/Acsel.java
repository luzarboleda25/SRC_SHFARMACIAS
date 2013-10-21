/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.entidades;

import java.io.Serializable;
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
@Table(name = "ACSEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acsel.findAll", query = "SELECT a FROM Acsel a"),
    @NamedQuery(name = "Acsel.findByCodcli", query = "SELECT a FROM Acsel a WHERE a.codcli = :codcli"),
    @NamedQuery(name = "Acsel.findByIdepol", query = "SELECT a FROM Acsel a WHERE a.idepol = :idepol"),
    @NamedQuery(name = "Acsel.findByNumcert", query = "SELECT a FROM Acsel a WHERE a.numcert = :numcert")})
public class Acsel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "CODCLI")
    private String codcli;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEPOL")
    private BigInteger idepol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMCERT")
    private BigInteger numcert;

    public Acsel() {
    }

    public Acsel(String codcli) {
        this.codcli = codcli;
    }

    public Acsel(String codcli, BigInteger idepol, BigInteger numcert) {
        this.codcli = codcli;
        this.idepol = idepol;
        this.numcert = numcert;
    }

    public String getCodcli() {
        return codcli;
    }

    public void setCodcli(String codcli) {
        this.codcli = codcli;
    }

    public BigInteger getIdepol() {
        return idepol;
    }

    public void setIdepol(BigInteger idepol) {
        this.idepol = idepol;
    }

    public BigInteger getNumcert() {
        return numcert;
    }

    public void setNumcert(BigInteger numcert) {
        this.numcert = numcert;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codcli != null ? codcli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acsel)) {
            return false;
        }
        Acsel other = (Acsel) object;
        if ((this.codcli == null && other.codcli != null) || (this.codcli != null && !this.codcli.equals(other.codcli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.Acsel[ codcli=" + codcli + " ]";
    }
    
}
