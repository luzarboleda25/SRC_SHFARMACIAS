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
 * @author Gedica
 */
@Entity
@Table(name = "MEDICO_TEMPORAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicoTemporal.findAll", query = "SELECT m FROM MedicoTemporal m"),
    @NamedQuery(name = "MedicoTemporal.findByIdmedicotemporal", query = "SELECT m FROM MedicoTemporal m WHERE m.idmedicotemporal = :idmedicotemporal"),
    @NamedQuery(name = "MedicoTemporal.findByTipoid", query = "SELECT m FROM MedicoTemporal m WHERE m.tipoid = :tipoid"),
    @NamedQuery(name = "MedicoTemporal.findByNumid", query = "SELECT m FROM MedicoTemporal m WHERE m.numid = :numid"),
    @NamedQuery(name = "MedicoTemporal.findByNumidYTipoid", query = "SELECT m FROM MedicoTemporal m WHERE m.numid LIKE :numid AND UPPER(m.tipoid) = :tipoid"),
    @NamedQuery(name = "MedicoTemporal.findByNombre", query = "SELECT m FROM MedicoTemporal m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MedicoTemporal.findByApellido", query = "SELECT m FROM MedicoTemporal m WHERE m.apellido = :apellido")})
public class MedicoTemporal implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MEDICO_TEMPORAL")
    @SequenceGenerator(name = "SQ_MEDICO_TEMPORAL", sequenceName = "SQ_MEDICO_TEMPORAL", catalog = "", allocationSize = 1)
    @Column(name = "IDMEDICOTEMPORAL")
    private BigDecimal idmedicotemporal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPOID")
    private String tipoid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "NUMID")
    private String numid;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 10)
//    @Column(name = "NUMERO")
//    private String numero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "APELLIDO")
    private String apellido;

    public MedicoTemporal() {
    }

    public MedicoTemporal(BigDecimal idmedicotemporal) {
        this.idmedicotemporal = idmedicotemporal;
    }

    public MedicoTemporal(BigDecimal idmedicotemporal, String tipoid, String numid, String nombre, String apellido) {
        this.idmedicotemporal = idmedicotemporal;
        this.tipoid = tipoid;
        this.numid = numid;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public BigDecimal getIdmedicotemporal() {
        return idmedicotemporal;
    }

    public void setIdmedicotemporal(BigDecimal idmedicotemporal) {
        this.idmedicotemporal = idmedicotemporal;
    }

    public String getTipoid() {
        return tipoid;
    }

    public void setTipoid(String tipoid) {
        this.tipoid = tipoid;
    }

    public String getNumid() {
        return numid;
    }

    public void setNumid(String numid) {
        this.numid = numid;
    }
//
//    public String getNumero() {
//        return numero;
//    }
//
//    public void setNumero(String numero) {
//        this.numero = numero;
//    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmedicotemporal != null ? idmedicotemporal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicoTemporal)) {
            return false;
        }
        MedicoTemporal other = (MedicoTemporal) object;
        if ((this.idmedicotemporal == null && other.idmedicotemporal != null) || (this.idmedicotemporal != null && !this.idmedicotemporal.equals(other.idmedicotemporal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.MedicoTemporal[ idmedicotemporal=" + idmedicotemporal + " ]";
    }
    
}
