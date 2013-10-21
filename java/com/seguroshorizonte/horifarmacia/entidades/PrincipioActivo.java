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
 * @author Gedica
 */
@Entity
@Table(name = "PRINCIPIO_ACTIVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrincipioActivo.findAll", query = "SELECT p FROM PrincipioActivo p"),
    @NamedQuery(name = "PrincipioActivo.findByIdprincipioactivo", query = "SELECT p FROM PrincipioActivo p WHERE p.idprincipioactivo = :idprincipioactivo"),
    @NamedQuery(name = "PrincipioActivo.findByNombre", query = "SELECT p FROM PrincipioActivo p WHERE p.nombre = :nombre")})
public class PrincipioActivo implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPRINCIPIOACTIVO")
    private BigDecimal idprincipioactivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprincactivo")
    private List<Medicamento> medicamentoList;

    public PrincipioActivo() {
    }

    public PrincipioActivo(BigDecimal idprincipioactivo) {
        this.idprincipioactivo = idprincipioactivo;
    }

    public PrincipioActivo(BigDecimal idprincipioactivo, String nombre) {
        this.idprincipioactivo = idprincipioactivo;
        this.nombre = nombre;
    }

    public BigDecimal getIdprincipioactivo() {
        return idprincipioactivo;
    }

    public void setIdprincipioactivo(BigDecimal idprincipioactivo) {
        this.idprincipioactivo = idprincipioactivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Medicamento> getMedicamentoList() {
        return medicamentoList;
    }

    public void setMedicamentoList(List<Medicamento> medicamentoList) {
        this.medicamentoList = medicamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprincipioactivo != null ? idprincipioactivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrincipioActivo)) {
            return false;
        }
        PrincipioActivo other = (PrincipioActivo) object;
        if ((this.idprincipioactivo == null && other.idprincipioactivo != null) || (this.idprincipioactivo != null && !this.idprincipioactivo.equals(other.idprincipioactivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.PrincipioActivo[ idprincipioactivo=" + idprincipioactivo + " ]";
    }
    
}
