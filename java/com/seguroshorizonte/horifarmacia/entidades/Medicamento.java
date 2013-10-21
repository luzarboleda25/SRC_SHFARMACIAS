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
 * @author Gedica
 */
@Entity
@Table(name = "MEDICAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medicamento.findAll", query = "SELECT m FROM Medicamento m"),
    @NamedQuery(name = "Medicamento.findByIdmedicamento", query = "SELECT m FROM Medicamento m WHERE m.idmedicamento = :idmedicamento"),
    @NamedQuery(name = "Medicamento.findMedicamentoJoinPAXNombre", query = "SELECT m FROM Medicamento m JOIN m.idprincactivo pa WHERE UPPER( pa.nombre ) LIKE :nombre OR UPPER( m.descripcion ) LIKE :nombre"),
    @NamedQuery(name = "Medicamento.findByCodigo", query = "SELECT m FROM Medicamento m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "Medicamento.findByDescripcion", query = "SELECT m FROM Medicamento m WHERE m.descripcion = :descripcion")})
public class Medicamento implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDMEDICAMENTO")
    private BigDecimal idmedicamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmedicamento")
    private List<PreordenMedicamento> preordenMedicamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmedicamento")
    private List<FarmaciaMedicamento> farmaciaMedicamentoList;
    @JoinColumn(name = "IDPRINCACTIVO", referencedColumnName = "IDPRINCIPIOACTIVO")
    @ManyToOne(optional = false)
    private PrincipioActivo idprincactivo;

    public Medicamento() {
    }

    public Medicamento(BigDecimal idmedicamento) {
        this.idmedicamento = idmedicamento;
    }

    public Medicamento(BigDecimal idmedicamento, String codigo, String descripcion) {
        this.idmedicamento = idmedicamento;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public BigDecimal getIdmedicamento() {
        return idmedicamento;
    }

    public void setIdmedicamento(BigDecimal idmedicamento) {
        this.idmedicamento = idmedicamento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<PreordenMedicamento> getPreordenMedicamentoList() {
        return preordenMedicamentoList;
    }

    public void setPreordenMedicamentoList(List<PreordenMedicamento> preordenMedicamentoList) {
        this.preordenMedicamentoList = preordenMedicamentoList;
    }

    @XmlTransient
    public List<FarmaciaMedicamento> getFarmaciaMedicamentoList() {
        return farmaciaMedicamentoList;
    }

    public void setFarmaciaMedicamentoList(List<FarmaciaMedicamento> farmaciaMedicamentoList) {
        this.farmaciaMedicamentoList = farmaciaMedicamentoList;
    }

    public PrincipioActivo getIdprincactivo() {
        return idprincactivo;
    }

    public void setIdprincactivo(PrincipioActivo idprincactivo) {
        this.idprincactivo = idprincactivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmedicamento != null ? idmedicamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicamento)) {
            return false;
        }
        Medicamento other = (Medicamento) object;
        if ((this.idmedicamento == null && other.idmedicamento != null) || (this.idmedicamento != null && !this.idmedicamento.equals(other.idmedicamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.horifarmacia.entidades.Medicamento[ idmedicamento=" + idmedicamento + " ]";
    }
    
}
