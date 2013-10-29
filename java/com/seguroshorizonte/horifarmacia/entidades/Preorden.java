/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gedica
 */
@Entity
@Table(name = "PREORDEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preorden.findAll", query = "SELECT p FROM Preorden p"),
    @NamedQuery(name = "Preorden.findByIdpreorden", query = "SELECT p FROM Preorden p WHERE p.idpreorden = :idpreorden"),
    @NamedQuery(name = "Preorden.findByCodigo", query = "SELECT p FROM Preorden p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Preorden.findByFecha", query = "SELECT p FROM Preorden p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Preorden.findByFechainformemedico", query = "SELECT p FROM Preorden p WHERE p.fechainformemedico = :fechainformemedico"),
    @NamedQuery(name = "Preorden.findByDiagnosticomedico", query = "SELECT p FROM Preorden p WHERE p.diagnosticomedico = :diagnosticomedico"),
    @NamedQuery(name = "Preorden.findByTipotratamiento", query = "SELECT p FROM Preorden p WHERE p.tipotratamiento = :tipotratamiento"),
    @NamedQuery(name = "Preorden.findByCodcli", query = "SELECT p FROM Preorden p WHERE p.codcli = :codcli"),
    @NamedQuery(name = "Preorden.findByIdepol", query = "SELECT p FROM Preorden p WHERE p.idepol = :idepol"),
    @NamedQuery(name = "Preorden.findByNumcert", query = "SELECT p FROM Preorden p WHERE p.numcert = :numcert"),
    @NamedQuery(name = "Preorden.findByRetiratipoid", query = "SELECT p FROM Preorden p WHERE p.retiratipoid = :retiratipoid"),
    @NamedQuery(name = "Preorden.findByRetiranumid", query = "SELECT p FROM Preorden p WHERE p.retiranumid = :retiranumid"),
    @NamedQuery(name = "Preorden.findByRetiranombre", query = "SELECT p FROM Preorden p WHERE p.retiranombre = :retiranombre"),
    @NamedQuery(name = "Preorden.findByRetiraapellido", query = "SELECT p FROM Preorden p WHERE p.retiraapellido = :retiraapellido"),
    @NamedQuery(name = "Preorden.findByRetiratelefono", query = "SELECT p FROM Preorden p WHERE p.retiratelefono = :retiratelefono"),
    @NamedQuery(name = "Preorden.findByNombreclinica", query = "SELECT p FROM Preorden p WHERE p.nombreclinica = :nombreclinica"),
    @NamedQuery(name = "Preorden.findByTipoidmedico", query = "SELECT p FROM Preorden p WHERE p.tipoidmedico = :tipoidmedico"),
    @NamedQuery(name = "Preorden.findByNumidmedico", query = "SELECT p FROM Preorden p WHERE p.numidmedico = :numidmedico"),
    @NamedQuery(name = "Preorden.findByNombremedico", query = "SELECT p FROM Preorden p WHERE p.nombremedico = :nombremedico"),
    @NamedQuery(name = "Preorden.findByApellidomedico", query = "SELECT p FROM Preorden p WHERE p.apellidomedico = :apellidomedico"),
    @NamedQuery(name = "Preorden.findByAprobado", query = "SELECT p FROM Preorden p WHERE p.aprobado = :aprobado"),
    @NamedQuery(name = "Preorden.findByStatus", query = "SELECT p FROM Preorden p WHERE p.status = :status"),
    @NamedQuery(name = "Preorden.findByReconsiderar", query = "SELECT p FROM Preorden p WHERE p.reconsiderar = :reconsiderar"),
    @NamedQuery(name = "Preorden.findByReconsiderarObservaciones", query = "SELECT p FROM Preorden p WHERE p.reconsiderarObservaciones = :reconsiderarObservaciones")})
public class Preorden implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpreorden")
    private Collection<ColaPreorden> colaPreordenCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "preorden")
    private PreordenAnalista preordenAnalista;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "preorden")
   
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQPREORDEN")
    @SequenceGenerator(name = "SQPREORDEN", sequenceName = "SQ_PREORDEN",initialValue=1, allocationSize = 1)
    @Column(name = "IDPREORDEN")
    private BigDecimal idpreorden;
    @Basic(optional = false)
    @Size(min = 1, max = 20)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "FECHAINFORMEMEDICO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainformemedico;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "DIAGNOSTICOMEDICO")
    private String diagnosticomedico;
    @Basic(optional = false)
    @Size(min = 1, max = 1)
    @Column(name = "TIPOTRATAMIENTO")
    private String tipotratamiento;
    @Basic(optional = false)
    @Size(min = 1, max = 15)
    @Column(name = "CODCLI")
    private String codcli;
    @Basic(optional = false)
    @Column(name = "IDEPOL")
    private BigInteger idepol;
    @Basic(optional = false)
    @Column(name = "NUMCERT")
    private BigInteger numcert;
    @Basic(optional = false)
    @Size(min = 1, max = 1)
    @Column(name = "RETIRATIPOID")
    private String retiratipoid;
    @Basic(optional = false)
    @Size(min = 1, max = 10)
    @Column(name = "RETIRANUMID")
    private String retiranumid;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "RETIRANOMBRE")
    private String retiranombre;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "RETIRAAPELLIDO")
    private String retiraapellido;
    @Basic(optional = false)
    @Size(min = 1, max = 15)
    @Column(name = "RETIRATELEFONO")
    private String retiratelefono;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "NOMBRECLINICA")
    private String nombreclinica;
    @Basic(optional = false)
    @Size(min = 1, max = 1)
    @Column(name = "TIPOIDMEDICO")
    private String tipoidmedico;
    @Basic(optional = false)
    @Column(name = "NUMIDMEDICO")
    private BigInteger numidmedico;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "NOMBREMEDICO")
    private String nombremedico;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "APELLIDOMEDICO")
    private String apellidomedico;
    @Basic(optional = false)
    @Size(min = 1, max = 1)
    @Column(name = "APROBADO")
    private String aprobado;
    @Basic(optional = false)
    @Size(min = 1, max = 1)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @Size(min = 1, max = 1)
    @Column(name = "RECONSIDERAR")
    private String reconsiderar;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "RECONSIDERAR_OBSERVACIONES")
    private String reconsiderarObservaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpreorden",fetch = FetchType.EAGER)
    private List<PreordenMedicamento> preordenMedicamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpreorden")
    private List<PreordenArchivo> preordenArchivoList;

    public Preorden() {
    }

    public Preorden(BigDecimal idpreorden) {
        this.idpreorden = idpreorden;
    }

    public Preorden(BigDecimal idpreorden, String codigo, Date fecha, Date fechainformemedico, String diagnosticomedico, String tipotratamiento, String codcli, BigInteger idepol, BigInteger numcert, String retiratipoid, String retiranumid, String retiranombre, String retiraapellido, String retiratelefono, String nombreclinica, String tipoidmedico, BigInteger numidmedico, String nombremedico, String apellidomedico, String aprobado, String status, String reconsiderar, String reconsiderarObservaciones) {
        this.idpreorden = idpreorden;
        this.codigo = codigo;
        this.fecha = fecha;
        this.fechainformemedico = fechainformemedico;
        this.diagnosticomedico = diagnosticomedico;
        this.tipotratamiento = tipotratamiento;
        this.codcli = codcli;
        this.idepol = idepol;
        this.numcert = numcert;
        this.retiratipoid = retiratipoid;
        this.retiranumid = retiranumid;
        this.retiranombre = retiranombre;
        this.retiraapellido = retiraapellido;
        this.retiratelefono = retiratelefono;
        this.nombreclinica = nombreclinica;
        this.tipoidmedico = tipoidmedico;
        this.numidmedico = numidmedico;
        this.nombremedico = nombremedico;
        this.apellidomedico = apellidomedico;
        this.aprobado = aprobado;
        this.status = status;
        this.reconsiderar = reconsiderar;
        this.reconsiderarObservaciones = reconsiderarObservaciones;
    }

    public BigDecimal getIdpreorden() {
        return idpreorden;
    }

    public void setIdpreorden(BigDecimal idpreorden) {
        this.idpreorden = idpreorden;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechainformemedico() {
        return fechainformemedico;
    }

    public void setFechainformemedico(Date fechainformemedico) {
        this.fechainformemedico = fechainformemedico;
    }

    public String getDiagnosticomedico() {
        return diagnosticomedico;
    }

    public void setDiagnosticomedico(String diagnosticomedico) {
        this.diagnosticomedico = diagnosticomedico;
    }

    public String getTipotratamiento() {
        return tipotratamiento;
    }

    public void setTipotratamiento(String tipotratamiento) {
        this.tipotratamiento = tipotratamiento;
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

    public String getRetiratipoid() {
        return retiratipoid;
    }

    public void setRetiratipoid(String retiratipoid) {
        this.retiratipoid = retiratipoid;
    }

    public String getRetiranumid() {
        return retiranumid;
    }

    public void setRetiranumid(String retiranumid) {
        this.retiranumid = retiranumid;
    }

    public String getRetiranombre() {
        return retiranombre;
    }

    public void setRetiranombre(String retiranombre) {
        this.retiranombre = retiranombre;
    }

    public String getRetiraapellido() {
        return retiraapellido;
    }

    public void setRetiraapellido(String retiraapellido) {
        this.retiraapellido = retiraapellido;
    }

    public String getRetiratelefono() {
        return retiratelefono;
    }

    public void setRetiratelefono(String retiratelefono) {
        this.retiratelefono = retiratelefono;
    }

    public String getNombreclinica() {
        return nombreclinica;
    }

    public void setNombreclinica(String nombreclinica) {
        this.nombreclinica = nombreclinica;
    }

    public String getTipoidmedico() {
        return tipoidmedico;
    }

    public void setTipoidmedico(String tipoidmedico) {
        this.tipoidmedico = tipoidmedico;
    }

    public BigInteger getNumidmedico() {
        return numidmedico;
    }

    public void setNumidmedico(BigInteger numidmedico) {
        this.numidmedico = numidmedico;
    }

    public String getNombremedico() {
        return nombremedico;
    }

    public void setNombremedico(String nombremedico) {
        this.nombremedico = nombremedico;
    }

    public String getApellidomedico() {
        return apellidomedico;
    }

    public void setApellidomedico(String apellidomedico) {
        this.apellidomedico = apellidomedico;
    }

    public String getAprobado() {
        return aprobado;
    }

    public void setAprobado(String aprobado) {
        this.aprobado = aprobado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReconsiderar() {
        return reconsiderar;
    }

    public void setReconsiderar(String reconsiderar) {
        this.reconsiderar = reconsiderar;
    }

    public String getReconsiderarObservaciones() {
        return reconsiderarObservaciones;
    }

    public void setReconsiderarObservaciones(String reconsiderarObservaciones) {
        this.reconsiderarObservaciones = reconsiderarObservaciones;
    }

    @XmlTransient
    public List<PreordenMedicamento> getPreordenMedicamentoList() {
        return preordenMedicamentoList;
    }

    public void setPreordenMedicamentoList(List<PreordenMedicamento> preordenMedicamentoList) {
        this.preordenMedicamentoList = preordenMedicamentoList;
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
        hash += (idpreorden != null ? idpreorden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preorden)) {
            return false;
        }
        Preorden other = (Preorden) object;
        if ((this.idpreorden == null && other.idpreorden != null) || (this.idpreorden != null && !this.idpreorden.equals(other.idpreorden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.Preorden[ idpreorden=" + idpreorden + " ]";
    }

   

    @XmlTransient
    public Collection<ColaPreorden> getColaPreordenCollection() {
        return colaPreordenCollection;
    }

    public void setColaPreordenCollection(Collection<ColaPreorden> colaPreordenCollection) {
        this.colaPreordenCollection = colaPreordenCollection;
    }

    public PreordenAnalista getPreordenAnalista() {
        return preordenAnalista;
    }

    public void setPreordenAnalista(PreordenAnalista preordenAnalista) {
        this.preordenAnalista = preordenAnalista;
    }
    
}
