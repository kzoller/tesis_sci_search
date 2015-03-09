/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GIISIC.Entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pruebas_ECV
 */
@Entity
@Table(name = "inf_evidencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfEvidencia.findAll", query = "SELECT i FROM InfEvidencia i"),
    @NamedQuery(name = "InfEvidencia.findByEnId", query = "SELECT i FROM InfEvidencia i WHERE i.enId = :enId"),
    @NamedQuery(name = "InfEvidencia.findByEnNombreArchivo", query = "SELECT i FROM InfEvidencia i WHERE i.enNombreArchivo = :enNombreArchivo"),
    @NamedQuery(name = "InfEvidencia.findByEnFechaArchivo", query = "SELECT i FROM InfEvidencia i WHERE i.enFechaArchivo = :enFechaArchivo"),
    @NamedQuery(name = "InfEvidencia.findByEnRutaArchivo", query = "SELECT i FROM InfEvidencia i WHERE i.enRutaArchivo = :enRutaArchivo")})
public class InfEvidencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "en_id")
    private Integer enId;
    @Size(max = 50)
    @Column(name = "en_nombre_archivo")
    private String enNombreArchivo;
    @Column(name = "en_fecha_archivo")
    @Temporal(TemporalType.DATE)
    private Date enFechaArchivo;
    @Size(max = 300)
    @Column(name = "en_ruta_archivo")
    private String enRutaArchivo;
    @OneToMany(mappedBy = "refEvidencia")
    private Collection<MgiAsignarEvidencias> mgiAsignarEvidenciasCollection;
    @JoinColumn(name = "ref_usuario", referencedColumnName = "us_id")
    @ManyToOne
    private MacUsuario refUsuario;

    public InfEvidencia() {
    }

    public InfEvidencia(Integer enId) {
        this.enId = enId;
    }

    public Integer getEnId() {
        return enId;
    }

    public void setEnId(Integer enId) {
        this.enId = enId;
    }

    public String getEnNombreArchivo() {
        return enNombreArchivo;
    }

    public void setEnNombreArchivo(String enNombreArchivo) {
        this.enNombreArchivo = enNombreArchivo;
    }

    public Date getEnFechaArchivo() {
        return enFechaArchivo;
    }

    public void setEnFechaArchivo(Date enFechaArchivo) {
        this.enFechaArchivo = enFechaArchivo;
    }

    public String getEnRutaArchivo() {
        return enRutaArchivo;
    }

    public void setEnRutaArchivo(String enRutaArchivo) {
        this.enRutaArchivo = enRutaArchivo;
    }

    @XmlTransient
    public Collection<MgiAsignarEvidencias> getMgiAsignarEvidenciasCollection() {
        return mgiAsignarEvidenciasCollection;
    }

    public void setMgiAsignarEvidenciasCollection(Collection<MgiAsignarEvidencias> mgiAsignarEvidenciasCollection) {
        this.mgiAsignarEvidenciasCollection = mgiAsignarEvidenciasCollection;
    }

    public MacUsuario getRefUsuario() {
        return refUsuario;
    }

    public void setRefUsuario(MacUsuario refUsuario) {
        this.refUsuario = refUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enId != null ? enId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfEvidencia)) {
            return false;
        }
        InfEvidencia other = (InfEvidencia) object;
        if ((this.enId == null && other.enId != null) || (this.enId != null && !this.enId.equals(other.enId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GIISIC.Entidades.InfEvidencia[ enId=" + enId + " ]";
    }
    
}
