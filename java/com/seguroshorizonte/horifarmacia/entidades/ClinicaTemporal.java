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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GEDICA TEAM
 */
@Entity
@Table(name = "CLINICA_TEMPORAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClinicaTemporal.findAll", query = "SELECT c FROM ClinicaTemporal c"),
    @NamedQuery(name = "ClinicaTemporal.findByIdclinicatemporal", query = "SELECT c FROM ClinicaTemporal c WHERE c.idclinicatemporal = :idclinicatemporal"),
    @NamedQuery(name = "ClinicaTemporal.findByNombreLike", query = "SELECT c FROM ClinicaTemporal c WHERE UPPER(c.nombre) LIKE :nombre"),
    @NamedQuery(name = "ClinicaTemporal.findByNombre", query = "SELECT c FROM ClinicaTemporal c WHERE c.nombre = :nombre")})
public class ClinicaTemporal implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CLINICA_TEMPORAL")
    @SequenceGenerator(name = "SQ_CLINICA_TEMPORAL", sequenceName = "SQ_CLINICA_TEMPORAL", catalog = "", allocationSize = 1)
    @Column(name = "IDCLINICATEMPORAL")
    private BigDecimal idclinicatemporal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NOMBRE")
    private String nombre;

    public ClinicaTemporal() {
    }

    public ClinicaTemporal(BigDecimal idclinicatemporal) {
        this.idclinicatemporal = idclinicatemporal;
    }

    public ClinicaTemporal(BigDecimal idclinicatemporal, String nombre) {
        this.idclinicatemporal = idclinicatemporal;
        this.nombre = nombre;
    }

    public BigDecimal getIdclinicatemporal() {
        return idclinicatemporal;
    }

    public void setIdclinicatemporal(BigDecimal idclinicatemporal) {
        this.idclinicatemporal = idclinicatemporal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idclinicatemporal != null ? idclinicatemporal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClinicaTemporal)) {
            return false;
        }
        ClinicaTemporal other = (ClinicaTemporal) object;
        if ((this.idclinicatemporal == null && other.idclinicatemporal != null) || (this.idclinicatemporal != null && !this.idclinicatemporal.equals(other.idclinicatemporal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.ClinicaTemporal[ idclinicatemporal=" + idclinicatemporal + " ]";
    }
    
}
