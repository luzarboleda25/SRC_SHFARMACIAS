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
 * @author GEDICA TEAM
 */
@Entity
@Table(name = "DOSIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dosis.findAll", query = "SELECT d FROM Dosis d"),
    @NamedQuery(name = "Dosis.findByIddosis", query = "SELECT d FROM Dosis d WHERE d.iddosis = :iddosis"),
    @NamedQuery(name = "Dosis.findByCodigo", query = "SELECT d FROM Dosis d WHERE d.codigo = :codigo"),
    @NamedQuery(name = "Dosis.findByNombre", query = "SELECT d FROM Dosis d WHERE d.nombre = :nombre")})
public class Dosis implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDOSIS")
    private BigDecimal iddosis;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddosis")
    private List<PreordenMedicamento> preordenMedicamentoList;

    public Dosis() {
    }

    public Dosis(BigDecimal iddosis) {
        this.iddosis = iddosis;
    }

    public Dosis(BigDecimal iddosis, String codigo, String nombre) {
        this.iddosis = iddosis;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public BigDecimal getIddosis() {
        return iddosis;
    }

    public void setIddosis(BigDecimal iddosis) {
        this.iddosis = iddosis;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<PreordenMedicamento> getPreordenMedicamentoList() {
        return preordenMedicamentoList;
    }

    public void setPreordenMedicamentoList(List<PreordenMedicamento> preordenMedicamentoList) {
        this.preordenMedicamentoList = preordenMedicamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddosis != null ? iddosis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dosis)) {
            return false;
        }
        Dosis other = (Dosis) object;
        if ((this.iddosis == null && other.iddosis != null) || (this.iddosis != null && !this.iddosis.equals(other.iddosis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.Dosis[ iddosis=" + iddosis + " ]";
    }
    
}
