/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GEDICA TEAM
 */
@Entity
@Table(name = "FARMACIA_MEDICAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FarmaciaMedicamento.findAll", query = "SELECT f FROM FarmaciaMedicamento f"),
    @NamedQuery(name = "FarmaciaMedicamento.findByIdfarmedi", query = "SELECT f FROM FarmaciaMedicamento f WHERE f.idfarmedi = :idfarmedi"),
    @NamedQuery(name = "FarmaciaMedicamento.findByPrecio", query = "SELECT f FROM FarmaciaMedicamento f WHERE f.precio = :precio"),
    @NamedQuery(name = "FarmaciaMedicamento.findByIva", query = "SELECT f FROM FarmaciaMedicamento f WHERE f.iva = :iva")})
public class FarmaciaMedicamento implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO")
    private Serializable precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IVA")
    private Serializable iva;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDFARMEDI")
    private BigDecimal idfarmedi;
    @JoinColumn(name = "IDMEDICAMENTO", referencedColumnName = "IDMEDICAMENTO")
    @ManyToOne(optional = false)
    private Medicamento idmedicamento;
    @JoinColumn(name = "IDFARMACIA", referencedColumnName = "IDFARMACIA")
    @ManyToOne(optional = false)
    private Farmacia idfarmacia;

    public FarmaciaMedicamento() {
    }

    public FarmaciaMedicamento(BigDecimal idfarmedi) {
        this.idfarmedi = idfarmedi;
    }

    public FarmaciaMedicamento(BigDecimal idfarmedi, Serializable precio, Serializable iva) {
        this.idfarmedi = idfarmedi;
        this.precio = precio;
        this.iva = iva;
    }

    public BigDecimal getIdfarmedi() {
        return idfarmedi;
    }

    public void setIdfarmedi(BigDecimal idfarmedi) {
        this.idfarmedi = idfarmedi;
    }

    public Medicamento getIdmedicamento() {
        return idmedicamento;
    }

    public void setIdmedicamento(Medicamento idmedicamento) {
        this.idmedicamento = idmedicamento;
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
        hash += (idfarmedi != null ? idfarmedi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FarmaciaMedicamento)) {
            return false;
        }
        FarmaciaMedicamento other = (FarmaciaMedicamento) object;
        if ((this.idfarmedi == null && other.idfarmedi != null) || (this.idfarmedi != null && !this.idfarmedi.equals(other.idfarmedi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.FarmaciaMedicamento[ idfarmedi=" + idfarmedi + " ]";
    }

    public Serializable getPrecio() {
        return precio;
    }

    public void setPrecio(Serializable precio) {
        this.precio = precio;
    }

    public Serializable getIva() {
        return iva;
    }

    public void setIva(Serializable iva) {
        this.iva = iva;
    }
    
}
