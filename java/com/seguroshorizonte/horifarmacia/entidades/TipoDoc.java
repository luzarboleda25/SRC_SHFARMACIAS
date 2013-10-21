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
@Table(name = "TIPO_DOC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDoc.findAll", query = "SELECT t FROM TipoDoc t"),
    @NamedQuery(name = "TipoDoc.findByIdtipodoc", query = "SELECT t FROM TipoDoc t WHERE t.idtipodoc = :idtipodoc"),
    @NamedQuery(name = "TipoDoc.findByNombre", query = "SELECT t FROM TipoDoc t WHERE t.nombre = :nombre")})
public class TipoDoc implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDTIPODOC")
    private BigDecimal idtipodoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipodoc")
    private List<PreordenArchivo> preordenArchivoList;

    public TipoDoc() {
    }

    public TipoDoc(BigDecimal idtipodoc) {
        this.idtipodoc = idtipodoc;
    }

    public TipoDoc(BigDecimal idtipodoc, String nombre) {
        this.idtipodoc = idtipodoc;
        this.nombre = nombre;
    }

    public BigDecimal getIdtipodoc() {
        return idtipodoc;
    }

    public void setIdtipodoc(BigDecimal idtipodoc) {
        this.idtipodoc = idtipodoc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<PreordenArchivo> getPreordenArchivoList() {
        return preordenArchivoList;
    }

    public void setPreordenArchivoList(List<PreordenArchivo> preordenArchivoList) {
        this.preordenArchivoList = preordenArchivoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipodoc != null ? idtipodoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDoc)) {
            return false;
        }
        TipoDoc other = (TipoDoc) object;
        if ((this.idtipodoc == null && other.idtipodoc != null) || (this.idtipodoc != null && !this.idtipodoc.equals(other.idtipodoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.TipoDoc[ idtipodoc=" + idtipodoc + " ]";
    }
    
}
