/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.horifarmacia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "PREORDEN_MEDICAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreordenMedicamento.findAll", query = "SELECT p FROM PreordenMedicamento p"),
    @NamedQuery(name = "PreordenMedicamento.findByIdpreordenmedicamento", query = "SELECT p FROM PreordenMedicamento p WHERE p.idpreordenmedicamento = :idpreordenmedicamento"),
    @NamedQuery(name = "PreordenMedicamento.findByDuracion", query = "SELECT p FROM PreordenMedicamento p WHERE p.duracion = :duracion"),
    @NamedQuery(name = "PreordenMedicamento.findByIdpreorden", query = "SELECT p FROM PreordenMedicamento p WHERE p.idpreorden = :idpreorden"),
    @NamedQuery(name = "PreordenMedicamento.findByCantidad", query = "SELECT p FROM PreordenMedicamento p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "PreordenMedicamento.findByEntregado", query = "SELECT p FROM PreordenMedicamento p WHERE p.entregado = :entregado")})
public class PreordenMedicamento implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preordMedId")
    private Collection<PreordenMedicamentoAnalista> preordenMedicamentoAnalistaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preordenMedicamentoId")
    private Collection<ColaPreordenMedicamento> colaPreordenMedicamentoCollection;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQPREORDENMEDICAMENTO")
    @SequenceGenerator(name = "SQPREORDENMEDICAMENTO", sequenceName = "SQ_PO_MEDICAMENTO",initialValue=1, allocationSize = 1)
    @Column(name = "IDPREORDENMEDICAMENTO")
    private BigDecimal idpreordenmedicamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DURACION")
    private BigInteger duracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private BigInteger cantidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ENTREGADO")
    private String entregado;
    @JoinColumn(name = "IDPREORDEN", referencedColumnName = "IDPREORDEN")
    @ManyToOne(optional = false)
    private Preorden idpreorden;
    @JoinColumn(name = "IDMEDICAMENTO", referencedColumnName = "IDMEDICAMENTO")
    @ManyToOne(optional = false)
    private Medicamento idmedicamento;
    @JoinColumn(name = "IDDOSIS", referencedColumnName = "IDDOSIS")
    @ManyToOne(optional = false)
    private Dosis iddosis;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpomedicamento")
    private List<Orden> ordenList;

    public PreordenMedicamento() {
    }

    public PreordenMedicamento(BigDecimal idpreordenmedicamento) {
        this.idpreordenmedicamento = idpreordenmedicamento;
    }

    public PreordenMedicamento(BigDecimal idpreordenmedicamento, BigInteger duracion, BigInteger cantidad, String entregado) {
        this.idpreordenmedicamento = idpreordenmedicamento;
        this.duracion = duracion;
        this.cantidad = cantidad;
        this.entregado = entregado;
    }

    public BigDecimal getIdpreordenmedicamento() {
        return idpreordenmedicamento;
    }

    public void setIdpreordenmedicamento(BigDecimal idpreordenmedicamento) {
        this.idpreordenmedicamento = idpreordenmedicamento;
    }

    public BigInteger getDuracion() {
        return duracion;
    }

    public void setDuracion(BigInteger duracion) {
        this.duracion = duracion;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public String getEntregado() {
        return entregado;
    }

    public void setEntregado(String entregado) {
        this.entregado = entregado;
    }

    public Preorden getIdpreorden() {
        return idpreorden;
    }

    public void setIdpreorden(Preorden idpreorden) {
        this.idpreorden = idpreorden;
    }

    public Medicamento getIdmedicamento() {
        return idmedicamento;
    }

    public void setIdmedicamento(Medicamento idmedicamento) {
        this.idmedicamento = idmedicamento;
    }

    public Dosis getIddosis() {
        return iddosis;
    }

    public void setIddosis(Dosis iddosis) {
        this.iddosis = iddosis;
    }

    @XmlTransient
    public List<Orden> getOrdenList() {
        return ordenList;
    }

    public void setOrdenList(List<Orden> ordenList) {
        this.ordenList = ordenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpreordenmedicamento != null ? idpreordenmedicamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreordenMedicamento)) {
            return false;
        }
        PreordenMedicamento other = (PreordenMedicamento) object;
        if ((this.idpreordenmedicamento == null && other.idpreordenmedicamento != null) || (this.idpreordenmedicamento != null && !this.idpreordenmedicamento.equals(other.idpreordenmedicamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.PreordenMedicamento[ idpreordenmedicamento=" + idpreordenmedicamento + " ]";
    }

    @XmlTransient
    public Collection<PreordenMedicamentoAnalista> getPreordenMedicamentoAnalistaCollection() {
        return preordenMedicamentoAnalistaCollection;
    }

    public void setPreordenMedicamentoAnalistaCollection(Collection<PreordenMedicamentoAnalista> preordenMedicamentoAnalistaCollection) {
        this.preordenMedicamentoAnalistaCollection = preordenMedicamentoAnalistaCollection;
    }

    @XmlTransient
    public Collection<ColaPreordenMedicamento> getColaPreordenMedicamentoCollection() {
        return colaPreordenMedicamentoCollection;
    }

    public void setColaPreordenMedicamentoCollection(Collection<ColaPreordenMedicamento> colaPreordenMedicamentoCollection) {
        this.colaPreordenMedicamentoCollection = colaPreordenMedicamentoCollection;
    }
    
}
