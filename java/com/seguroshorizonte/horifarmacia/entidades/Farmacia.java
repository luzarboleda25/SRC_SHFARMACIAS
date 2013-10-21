/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author GEDICA TEAM
 */
@Entity
@Table(name = "FARMACIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Farmacia.findAll", query = "SELECT f FROM Farmacia f"),
    @NamedQuery(name = "Farmacia.findByIdfarmacia", query = "SELECT f FROM Farmacia f WHERE f.idfarmacia = :idfarmacia"),
    @NamedQuery(name = "Farmacia.findByRif", query = "SELECT f FROM Farmacia f WHERE f.rif = :rif"),
    @NamedQuery(name = "Farmacia.findByRazonSocial", query = "SELECT f FROM Farmacia f WHERE f.razonSocial = :razonSocial"),
    @NamedQuery(name = "Farmacia.findByTelefono", query = "SELECT f FROM Farmacia f WHERE f.telefono = :telefono"),
    @NamedQuery(name = "Farmacia.findByActivo", query = "SELECT f FROM Farmacia f WHERE f.activo = :activo")})
public class Farmacia implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDFARMACIA")
    private BigDecimal idfarmacia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "RIF")
    private String rif;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "TELEFONO")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACTIVO")
    private String activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idfarmacia")
    private List<FarmaciaMedicamento> farmaciaMedicamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idfarmacia")
    private List<Orden> ordenList;
    @JoinColumn(name = "IDPROVEEDOR", referencedColumnName = "IDPROVEEDOR")
    @ManyToOne(optional = false)
    private Proveedor idproveedor;

    public Farmacia() {
    }

    public Farmacia(BigDecimal idfarmacia) {
        this.idfarmacia = idfarmacia;
    }

    public Farmacia(BigDecimal idfarmacia, String rif, String razonSocial, String telefono, String activo) {
        this.idfarmacia = idfarmacia;
        this.rif = rif;
        this.razonSocial = razonSocial;
        this.telefono = telefono;
        this.activo = activo;
    }

    public BigDecimal getIdfarmacia() {
        return idfarmacia;
    }

    public void setIdfarmacia(BigDecimal idfarmacia) {
        this.idfarmacia = idfarmacia;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<FarmaciaMedicamento> getFarmaciaMedicamentoList() {
        return farmaciaMedicamentoList;
    }

    public void setFarmaciaMedicamentoList(List<FarmaciaMedicamento> farmaciaMedicamentoList) {
        this.farmaciaMedicamentoList = farmaciaMedicamentoList;
    }

    @XmlTransient
    public List<Orden> getOrdenList() {
        return ordenList;
    }

    public void setOrdenList(List<Orden> ordenList) {
        this.ordenList = ordenList;
    }

    public Proveedor getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(Proveedor idproveedor) {
        this.idproveedor = idproveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfarmacia != null ? idfarmacia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Farmacia)) {
            return false;
        }
        Farmacia other = (Farmacia) object;
        if ((this.idfarmacia == null && other.idfarmacia != null) || (this.idfarmacia != null && !this.idfarmacia.equals(other.idfarmacia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.Farmacia[ idfarmacia=" + idfarmacia + " ]";
    }
    
}
