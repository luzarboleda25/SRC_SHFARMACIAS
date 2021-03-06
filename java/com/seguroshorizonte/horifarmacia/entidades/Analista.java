/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
 * @author Pangea
 */
@Entity
@Table(name = "ANALISTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Analista.findAll", query = "SELECT a FROM Analista a"),
    @NamedQuery(name = "Analista.findByIdanalista", query = "SELECT a FROM Analista a WHERE a.idanalista = :idanalista"),
    @NamedQuery(name = "Analista.findByNombre", query = "SELECT a FROM Analista a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Analista.findByApellido", query = "SELECT a FROM Analista a WHERE a.apellido = :apellido"),
    @NamedQuery(name = "Analista.findByCorreo", query = "SELECT a FROM Analista a WHERE a.correo = :correo"),
    @NamedQuery(name = "Analista.findByUsuario", query = "SELECT a FROM Analista a WHERE a.usuario = :usuario"),
    @NamedQuery(name = "Analista.findByContrasena", query = "SELECT a FROM Analista a WHERE a.contrasena = :contrasena"),
    @NamedQuery(name = "Analista.findByUsuarioYContrasena", query = "SELECT a FROM Analista a WHERE a.usuario = :usuario AND a.contrasena = :contrasena"),
    @NamedQuery(name = "Analista.findByStatus", query = "SELECT a FROM Analista a WHERE a.status = :status"),
    @NamedQuery(name = "Analista.findByContadorStatusConectado", query = "SELECT COUNT(a.idanalista) FROM Analista a WHERE a.status = :status")})
public class Analista implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDANALISTA")
    private BigDecimal idanalista;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 50)
    @Column(name = "APELLIDO")
    private String apellido;
    @Size(max = 50)
    @Column(name = "CORREO")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USUARIO")
    private String usuario;
    @Size(max = 50)
    @Column(name = "CONTRASENA")
    private String contrasena;
    @Size(max = 1)
    @Column(name = "STATUS")
    private String status;
    @JoinColumn(name = "IDROL", referencedColumnName = "IDROL")
    @ManyToOne(optional = false)
    private Rol idrol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idanalista")
    private Collection<RegistroIngreso> registroIngresoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idanalista")
    private Collection<PreordenAnalista> preordenAnalistaCollection;
    @OneToMany(mappedBy = "idauditor")
    private Collection<PreordenAnalista> preordenAnalistaCollection1;

    public Analista() {
    }

    public Analista(BigDecimal idanalista) {
        this.idanalista = idanalista;
    }

    public Analista(BigDecimal idanalista, String nombre, String usuario) {
        this.idanalista = idanalista;
        this.nombre = nombre;
        this.usuario = usuario;
    }

    public BigDecimal getIdanalista() {
        return idanalista;
    }

    public void setIdanalista(BigDecimal idanalista) {
        this.idanalista = idanalista;
    }

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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Rol getIdrol() {
        return idrol;
    }

    public void setIdrol(Rol idrol) {
        this.idrol = idrol;
    }

    @XmlTransient
    public Collection<RegistroIngreso> getRegistroIngresoCollection() {
        return registroIngresoCollection;
    }

    public void setRegistroIngresoCollection(Collection<RegistroIngreso> registroIngresoCollection) {
        this.registroIngresoCollection = registroIngresoCollection;
    }

    @XmlTransient
    public Collection<PreordenAnalista> getPreordenAnalistaCollection() {
        return preordenAnalistaCollection;
    }

    public void setPreordenAnalistaCollection(Collection<PreordenAnalista> preordenAnalistaCollection) {
        this.preordenAnalistaCollection = preordenAnalistaCollection;
    }

    @XmlTransient
    public Collection<PreordenAnalista> getPreordenAnalistaCollection1() {
        return preordenAnalistaCollection1;
    }

    public void setPreordenAnalistaCollection1(Collection<PreordenAnalista> preordenAnalistaCollection1) {
        this.preordenAnalistaCollection1 = preordenAnalistaCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idanalista != null ? idanalista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Analista)) {
            return false;
        }
        Analista other = (Analista) object;
        if ((this.idanalista == null && other.idanalista != null) || (this.idanalista != null && !this.idanalista.equals(other.idanalista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.Analista[ idanalista=" + idanalista + " ]";
    }
    
}
