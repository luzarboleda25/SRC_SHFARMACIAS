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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "PREORDEN_ARCHIVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreordenArchivo.findAll", query = "SELECT p FROM PreordenArchivo p"),
    @NamedQuery(name = "PreordenArchivo.findByIdpreordenarchivo", query = "SELECT p FROM PreordenArchivo p WHERE p.idpreordenarchivo = :idpreordenarchivo"),
    @NamedQuery(name = "PreordenArchivo.findByNombre", query = "SELECT p FROM PreordenArchivo p WHERE p.nombre = :nombre")})
public class PreordenArchivo implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQPOARCHIVO")
    @SequenceGenerator(name = "SQPOARCHIVO", sequenceName = "SQ_PO_ARCHIVO",initialValue=1, allocationSize = 1)
    @Column(name = "IDPREORDENARCHIVO")
    private BigDecimal idpreordenarchivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumn(name = "IDTIPODOC", referencedColumnName = "IDTIPODOC")
    @ManyToOne(optional = false)
    private TipoDoc idtipodoc;
    @JoinColumn(name = "IDPREORDEN", referencedColumnName = "IDPREORDEN")
    @ManyToOne(optional = false)
    private Preorden idpreorden;

    public PreordenArchivo() {
    }

    public PreordenArchivo(BigDecimal idpreordenarchivo) {
        this.idpreordenarchivo = idpreordenarchivo;
    }

    public PreordenArchivo(BigDecimal idpreordenarchivo, String nombre) {
        this.idpreordenarchivo = idpreordenarchivo;
        this.nombre = nombre;
    }

    public BigDecimal getIdpreordenarchivo() {
        return idpreordenarchivo;
    }

    public void setIdpreordenarchivo(BigDecimal idpreordenarchivo) {
        this.idpreordenarchivo = idpreordenarchivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoDoc getIdtipodoc() {
        return idtipodoc;
    }

    public void setIdtipodoc(TipoDoc idtipodoc) {
        this.idtipodoc = idtipodoc;
    }

    public Preorden getIdpreorden() {
        return idpreorden;
    }

    public void setIdpreorden(Preorden idpreorden) {
        this.idpreorden = idpreorden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpreordenarchivo != null ? idpreordenarchivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreordenArchivo)) {
            return false;
        }
        PreordenArchivo other = (PreordenArchivo) object;
        if ((this.idpreordenarchivo == null && other.idpreordenarchivo != null) || (this.idpreordenarchivo != null && !this.idpreordenarchivo.equals(other.idpreordenarchivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.PreordenArchivo[ idpreordenarchivo=" + idpreordenarchivo + " ]";
    }
    
}
