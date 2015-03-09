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
@Table(name = "mgi_grupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MgiGrupo.findAll", query = "SELECT m FROM MgiGrupo m"),
    @NamedQuery(name = "MgiGrupo.findByGrId", query = "SELECT m FROM MgiGrupo m WHERE m.grId = :grId"),
    @NamedQuery(name = "MgiGrupo.findByGrNombreGrupo", query = "SELECT m FROM MgiGrupo m WHERE m.grNombreGrupo = :grNombreGrupo"),
    @NamedQuery(name = "MgiGrupo.findByGrAbreviaturaGrupo", query = "SELECT m FROM MgiGrupo m WHERE m.grAbreviaturaGrupo = :grAbreviaturaGrupo"),
    @NamedQuery(name = "MgiGrupo.findByGrFechaRegistro", query = "SELECT m FROM MgiGrupo m WHERE m.grFechaRegistro = :grFechaRegistro"),
    @NamedQuery(name = "MgiGrupo.findByGrFechaAprobacion", query = "SELECT m FROM MgiGrupo m WHERE m.grFechaAprobacion = :grFechaAprobacion"),
    @NamedQuery(name = "MgiGrupo.findByGrEstado", query = "SELECT m FROM MgiGrupo m WHERE m.grEstado = :grEstado")})
public class MgiGrupo implements Serializable {
    @OneToMany(mappedBy = "refGrupo")
    private Collection<MacUsuario> macUsuarioCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "gr_id")
    private Integer grId;
    @Size(max = 50)
    @Column(name = "gr_nombre_grupo")
    private String grNombreGrupo;
    @Size(max = 10)
    @Column(name = "gr_abreviatura_grupo")
    private String grAbreviaturaGrupo;
    @Column(name = "gr_fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date grFechaRegistro;
    @Column(name = "gr_fecha_aprobacion")
    @Temporal(TemporalType.DATE)
    private Date grFechaAprobacion;
    @Size(max = 2)
    @Column(name = "gr_estado")
    private String grEstado;

    public MgiGrupo() {
    }

    public MgiGrupo(Integer grId) {
        this.grId = grId;
    }

    public Integer getGrId() {
        return grId;
    }

    public void setGrId(Integer grId) {
        this.grId = grId;
    }

    public String getGrNombreGrupo() {
        return grNombreGrupo;
    }

    public void setGrNombreGrupo(String grNombreGrupo) {
        this.grNombreGrupo = grNombreGrupo;
    }

    public String getGrAbreviaturaGrupo() {
        return grAbreviaturaGrupo;
    }

    public void setGrAbreviaturaGrupo(String grAbreviaturaGrupo) {
        this.grAbreviaturaGrupo = grAbreviaturaGrupo;
    }

    public Date getGrFechaRegistro() {
        return grFechaRegistro;
    }

    public void setGrFechaRegistro(Date grFechaRegistro) {
        this.grFechaRegistro = grFechaRegistro;
    }

    public Date getGrFechaAprobacion() {
        return grFechaAprobacion;
    }

    public void setGrFechaAprobacion(Date grFechaAprobacion) {
        this.grFechaAprobacion = grFechaAprobacion;
    }

    public String getGrEstado() {
        return grEstado;
    }

    public void setGrEstado(String grEstado) {
        this.grEstado = grEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grId != null ? grId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MgiGrupo)) {
            return false;
        }
        MgiGrupo other = (MgiGrupo) object;
        if ((this.grId == null && other.grId != null) || (this.grId != null && !this.grId.equals(other.grId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GIISIC.Entidades.MgiGrupo[ grId=" + grId + " ]";
    }

    @XmlTransient
    public Collection<MacUsuario> getMacUsuarioCollection() {
        return macUsuarioCollection;
    }

    public void setMacUsuarioCollection(Collection<MacUsuario> macUsuarioCollection) {
        this.macUsuarioCollection = macUsuarioCollection;
    }
    
}
