/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GIISIC.Entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
 * @author Pruebas_ECV
 */
@Entity
@Table(name = "inf_catalogo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfCatalogo.findAll", query = "SELECT i FROM InfCatalogo i"),
    @NamedQuery(name = "InfCatalogo.findByCatId", query = "SELECT i FROM InfCatalogo i WHERE i.catId = :catId"),
    @NamedQuery(name = "InfCatalogo.findByCatCatalogo", query = "SELECT i FROM InfCatalogo i WHERE i.catCatalogo = :catCatalogo"),
    @NamedQuery(name = "InfCatalogo.findByCatCodigoMiembro", query = "SELECT i FROM InfCatalogo i WHERE i.catCodigoMiembro = :catCodigoMiembro"),
    @NamedQuery(name = "InfCatalogo.findByCatNombreMiembro", query = "SELECT i FROM InfCatalogo i WHERE i.catNombreMiembro = :catNombreMiembro")})
public class InfCatalogo implements Serializable {
    @OneToMany(mappedBy = "refTitulo")
    private Collection<MgiAsignarTitulo> mgiAsignarTituloCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cat_id")
    private Integer catId;
    @Size(max = 30)
    @Column(name = "cat_catalogo")
    private String catCatalogo;
    @Size(max = 20)
    @Column(name = "cat_codigo_miembro")
    private String catCodigoMiembro;
    @Size(max = 100)
    @Column(name = "cat_nombre_miembro")
    private String catNombreMiembro;
    @OneToMany(mappedBy = "refPerfil")
    private Collection<MacUsuario> macUsuarioCollection;
    @OneToMany(mappedBy = "refNacionalidad")
    private Collection<MacUsuario> macUsuarioCollection1;
    @OneToMany(mappedBy = "refCargo")
    private Collection<MacUsuario> macUsuarioCollection2;
    @OneToMany(mappedBy = "refTipoContrato")
    private Collection<MacUsuario> macUsuarioCollection3;

    public InfCatalogo() {
    }

    public InfCatalogo(Integer catId) {
        this.catId = catId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatCatalogo() {
        return catCatalogo;
    }

    public void setCatCatalogo(String catCatalogo) {
        this.catCatalogo = catCatalogo;
    }

    public String getCatCodigoMiembro() {
        return catCodigoMiembro;
    }

    public void setCatCodigoMiembro(String catCodigoMiembro) {
        this.catCodigoMiembro = catCodigoMiembro;
    }

    public String getCatNombreMiembro() {
        return catNombreMiembro;
    }

    public void setCatNombreMiembro(String catNombreMiembro) {
        this.catNombreMiembro = catNombreMiembro;
    }

    @XmlTransient
    public Collection<MacUsuario> getMacUsuarioCollection() {
        return macUsuarioCollection;
    }

    public void setMacUsuarioCollection(Collection<MacUsuario> macUsuarioCollection) {
        this.macUsuarioCollection = macUsuarioCollection;
    }

    @XmlTransient
    public Collection<MacUsuario> getMacUsuarioCollection1() {
        return macUsuarioCollection1;
    }

    public void setMacUsuarioCollection1(Collection<MacUsuario> macUsuarioCollection1) {
        this.macUsuarioCollection1 = macUsuarioCollection1;
    }

    @XmlTransient
    public Collection<MacUsuario> getMacUsuarioCollection2() {
        return macUsuarioCollection2;
    }

    public void setMacUsuarioCollection2(Collection<MacUsuario> macUsuarioCollection2) {
        this.macUsuarioCollection2 = macUsuarioCollection2;
    }

    @XmlTransient
    public Collection<MacUsuario> getMacUsuarioCollection3() {
        return macUsuarioCollection3;
    }

    public void setMacUsuarioCollection3(Collection<MacUsuario> macUsuarioCollection3) {
        this.macUsuarioCollection3 = macUsuarioCollection3;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catId != null ? catId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfCatalogo)) {
            return false;
        }
        InfCatalogo other = (InfCatalogo) object;
        if ((this.catId == null && other.catId != null) || (this.catId != null && !this.catId.equals(other.catId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GIISIC.Entidades.InfCatalogo[ catId=" + catId + " ]";
    }

    @XmlTransient
    public Collection<MgiAsignarTitulo> getMgiAsignarTituloCollection() {
        return mgiAsignarTituloCollection;
    }

    public void setMgiAsignarTituloCollection(Collection<MgiAsignarTitulo> mgiAsignarTituloCollection) {
        this.mgiAsignarTituloCollection = mgiAsignarTituloCollection;
    }
    
}
