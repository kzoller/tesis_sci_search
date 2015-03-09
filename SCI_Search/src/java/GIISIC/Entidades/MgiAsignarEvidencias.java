/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GIISIC.Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pruebas_ECV
 */
@Entity
@Table(name = "mgi_asignar_evidencias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MgiAsignarEvidencias.findAll", query = "SELECT m FROM MgiAsignarEvidencias m"),
    @NamedQuery(name = "MgiAsignarEvidencias.findByGiaEId", query = "SELECT m FROM MgiAsignarEvidencias m WHERE m.giaEId = :giaEId"),
    @NamedQuery(name = "MgiAsignarEvidencias.findByGiaETipo", query = "SELECT m FROM MgiAsignarEvidencias m WHERE m.giaETipo = :giaETipo")})
public class MgiAsignarEvidencias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "gia_e_id")
    private Integer giaEId;
    @Size(max = 2)
    @Column(name = "gia_e_tipo")
    private String giaETipo;
    @JoinColumn(name = "ref_evidencia", referencedColumnName = "en_id")
    @ManyToOne
    private InfEvidencia refEvidencia;
    @JoinColumn(name = "ref_usuario", referencedColumnName = "us_id")
    @ManyToOne
    private MacUsuario refUsuario;

    public MgiAsignarEvidencias() {
    }

    public MgiAsignarEvidencias(Integer giaEId) {
        this.giaEId = giaEId;
    }

    public Integer getGiaEId() {
        return giaEId;
    }

    public void setGiaEId(Integer giaEId) {
        this.giaEId = giaEId;
    }

    public String getGiaETipo() {
        return giaETipo;
    }

    public void setGiaETipo(String giaETipo) {
        this.giaETipo = giaETipo;
    }

    public InfEvidencia getRefEvidencia() {
        return refEvidencia;
    }

    public void setRefEvidencia(InfEvidencia refEvidencia) {
        this.refEvidencia = refEvidencia;
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
        hash += (giaEId != null ? giaEId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MgiAsignarEvidencias)) {
            return false;
        }
        MgiAsignarEvidencias other = (MgiAsignarEvidencias) object;
        if ((this.giaEId == null && other.giaEId != null) || (this.giaEId != null && !this.giaEId.equals(other.giaEId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GIISIC.Entidades.MgiAsignarEvidencias[ giaEId=" + giaEId + " ]";
    }
    
}
