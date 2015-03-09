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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pruebas_ECV
 */
@Entity
@Table(name = "mgi_asignar_titulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MgiAsignarTitulo.findAll", query = "SELECT m FROM MgiAsignarTitulo m"),
    @NamedQuery(name = "MgiAsignarTitulo.findByATId", query = "SELECT m FROM MgiAsignarTitulo m WHERE m.aTId = :aTId")})
public class MgiAsignarTitulo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "a_t_id")
    private Integer aTId;
    @JoinColumn(name = "ref_titulo", referencedColumnName = "cat_id")
    @ManyToOne
    private InfCatalogo refTitulo;
    @JoinColumn(name = "ref_usuario", referencedColumnName = "us_id")
    @ManyToOne
    private MacUsuario refUsuario;

    public MgiAsignarTitulo() {
    }

    public MgiAsignarTitulo(Integer aTId) {
        this.aTId = aTId;
    }

    public Integer getATId() {
        return aTId;
    }

    public void setATId(Integer aTId) {
        this.aTId = aTId;
    }

    public InfCatalogo getRefTitulo() {
        return refTitulo;
    }

    public void setRefTitulo(InfCatalogo refTitulo) {
        this.refTitulo = refTitulo;
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
        hash += (aTId != null ? aTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MgiAsignarTitulo)) {
            return false;
        }
        MgiAsignarTitulo other = (MgiAsignarTitulo) object;
        if ((this.aTId == null && other.aTId != null) || (this.aTId != null && !this.aTId.equals(other.aTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GIISIC.Entidades.MgiAsignarTitulo[ aTId=" + aTId + " ]";
    }
    
}
