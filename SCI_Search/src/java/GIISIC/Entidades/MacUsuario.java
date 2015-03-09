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
@Table(name = "mac_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MacUsuario.findAll", query = "SELECT m FROM MacUsuario m"),
    @NamedQuery(name = "MacUsuario.findByUsId", query = "SELECT m FROM MacUsuario m WHERE m.usId = :usId"),
    @NamedQuery(name = "MacUsuario.findByUsNombre", query = "SELECT m FROM MacUsuario m WHERE m.usNombre = :usNombre"),
    @NamedQuery(name = "MacUsuario.findByUsApellido", query = "SELECT m FROM MacUsuario m WHERE m.usApellido = :usApellido"),
    @NamedQuery(name = "MacUsuario.findByUsDireccion", query = "SELECT m FROM MacUsuario m WHERE m.usDireccion = :usDireccion"),
    @NamedQuery(name = "MacUsuario.findByUsCasillaPostal", query = "SELECT m FROM MacUsuario m WHERE m.usCasillaPostal = :usCasillaPostal"),
    @NamedQuery(name = "MacUsuario.findByUsFechaNacimiento", query = "SELECT m FROM MacUsuario m WHERE m.usFechaNacimiento = :usFechaNacimiento"),
    @NamedQuery(name = "MacUsuario.findByUsTelefonoTrabajo", query = "SELECT m FROM MacUsuario m WHERE m.usTelefonoTrabajo = :usTelefonoTrabajo"),
    @NamedQuery(name = "MacUsuario.findByUsTelefonoCasa", query = "SELECT m FROM MacUsuario m WHERE m.usTelefonoCasa = :usTelefonoCasa"),
    @NamedQuery(name = "MacUsuario.findByUsCelular", query = "SELECT m FROM MacUsuario m WHERE m.usCelular = :usCelular"),
    @NamedQuery(name = "MacUsuario.findByUsEmail", query = "SELECT m FROM MacUsuario m WHERE m.usEmail = :usEmail"),
    @NamedQuery(name = "MacUsuario.findByUsFax", query = "SELECT m FROM MacUsuario m WHERE m.usFax = :usFax"),
    @NamedQuery(name = "MacUsuario.findByUsCedula", query = "SELECT m FROM MacUsuario m WHERE m.usCedula = :usCedula"),
    @NamedQuery(name = "MacUsuario.findByUsUsuario", query = "SELECT m FROM MacUsuario m WHERE m.usUsuario = :usUsuario"),
    @NamedQuery(name = "MacUsuario.findByUsContrasenia", query = "SELECT m FROM MacUsuario m WHERE m.usContrasenia = :usContrasenia"),
    @NamedQuery(name = "MacUsuario.findByUsSexo", query = "SELECT m FROM MacUsuario m WHERE m.usSexo = :usSexo")})
public class MacUsuario implements Serializable {
    @OneToMany(mappedBy = "refUsuario")
    private Collection<MgiAsignarEvidencias> mgiAsignarEvidenciasCollection;
    @OneToMany(mappedBy = "refUsuario")
    private Collection<MgiAsignarTitulo> mgiAsignarTituloCollection;
    @OneToMany(mappedBy = "refUsuario")
    private Collection<InfEvidencia> infEvidenciaCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "us_id")
    private Integer usId;
    @Size(max = 50)
    @Column(name = "us_nombre")
    private String usNombre;
    @Size(max = 50)
    @Column(name = "us_apellido")
    private String usApellido;
    @Size(max = 150)
    @Column(name = "us_direccion")
    private String usDireccion;
    @Size(max = 8)
    @Column(name = "us_casilla_postal")
    private String usCasillaPostal;
    @Column(name = "us_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date usFechaNacimiento;
    @Size(max = 10)
    @Column(name = "us_telefono_trabajo")
    private String usTelefonoTrabajo;
    @Size(max = 10)
    @Column(name = "us_telefono_casa")
    private String usTelefonoCasa;
    @Size(max = 10)
    @Column(name = "us_celular")
    private String usCelular;
    @Size(max = 50)
    @Column(name = "us_email")
    private String usEmail;
    @Size(max = 10)
    @Column(name = "us_fax")
    private String usFax;
    @Size(max = 10)
    @Column(name = "us_cedula")
    private String usCedula;
    @Size(max = 20)
    @Column(name = "us_usuario")
    private String usUsuario;
    @Size(max = 30)
    @Column(name = "us_contrasenia")
    private String usContrasenia;
    @Column(name = "us_sexo")
    private Character usSexo;
    @JoinColumn(name = "ref_perfil", referencedColumnName = "cat_id")
    @ManyToOne
    private InfCatalogo refPerfil;
    @JoinColumn(name = "ref_nacionalidad", referencedColumnName = "cat_id")
    @ManyToOne
    private InfCatalogo refNacionalidad;
    @JoinColumn(name = "ref_cargo", referencedColumnName = "cat_id")
    @ManyToOne
    private InfCatalogo refCargo;
    @JoinColumn(name = "ref_tipo_contrato", referencedColumnName = "cat_id")
    @ManyToOne
    private InfCatalogo refTipoContrato;
    @JoinColumn(name = "ref_grupo", referencedColumnName = "gr_id")
    @ManyToOne
    private MgiGrupo refGrupo;

    public MacUsuario() {
    }

    public MacUsuario(Integer usId) {
        this.usId = usId;
    }

    public Integer getUsId() {
        return usId;
    }

    public void setUsId(Integer usId) {
        this.usId = usId;
    }

    public String getUsNombre() {
        return usNombre;
    }

    public void setUsNombre(String usNombre) {
        this.usNombre = usNombre;
    }

    public String getUsApellido() {
        return usApellido;
    }

    public void setUsApellido(String usApellido) {
        this.usApellido = usApellido;
    }

    public String getUsDireccion() {
        return usDireccion;
    }

    public void setUsDireccion(String usDireccion) {
        this.usDireccion = usDireccion;
    }

    public String getUsCasillaPostal() {
        return usCasillaPostal;
    }

    public void setUsCasillaPostal(String usCasillaPostal) {
        this.usCasillaPostal = usCasillaPostal;
    }

    public Date getUsFechaNacimiento() {
        return usFechaNacimiento;
    }

    public void setUsFechaNacimiento(Date usFechaNacimiento) {
        this.usFechaNacimiento = usFechaNacimiento;
    }

    public String getUsTelefonoTrabajo() {
        return usTelefonoTrabajo;
    }

    public void setUsTelefonoTrabajo(String usTelefonoTrabajo) {
        this.usTelefonoTrabajo = usTelefonoTrabajo;
    }

    public String getUsTelefonoCasa() {
        return usTelefonoCasa;
    }

    public void setUsTelefonoCasa(String usTelefonoCasa) {
        this.usTelefonoCasa = usTelefonoCasa;
    }

    public String getUsCelular() {
        return usCelular;
    }

    public void setUsCelular(String usCelular) {
        this.usCelular = usCelular;
    }

    public String getUsEmail() {
        return usEmail;
    }

    public void setUsEmail(String usEmail) {
        this.usEmail = usEmail;
    }

    public String getUsFax() {
        return usFax;
    }

    public void setUsFax(String usFax) {
        this.usFax = usFax;
    }

    public String getUsCedula() {
        return usCedula;
    }

    public void setUsCedula(String usCedula) {
        this.usCedula = usCedula;
    }

    public String getUsUsuario() {
        return usUsuario;
    }

    public void setUsUsuario(String usUsuario) {
        this.usUsuario = usUsuario;
    }

    public String getUsContrasenia() {
        return usContrasenia;
    }

    public void setUsContrasenia(String usContrasenia) {
        this.usContrasenia = usContrasenia;
    }

    public Character getUsSexo() {
        return usSexo;
    }

    public void setUsSexo(Character usSexo) {
        this.usSexo = usSexo;
    }

    public InfCatalogo getRefPerfil() {
        return refPerfil;
    }

    public void setRefPerfil(InfCatalogo refPerfil) {
        this.refPerfil = refPerfil;
    }

    public InfCatalogo getRefNacionalidad() {
        return refNacionalidad;
    }

    public void setRefNacionalidad(InfCatalogo refNacionalidad) {
        this.refNacionalidad = refNacionalidad;
    }

    public InfCatalogo getRefCargo() {
        return refCargo;
    }

    public void setRefCargo(InfCatalogo refCargo) {
        this.refCargo = refCargo;
    }

    public InfCatalogo getRefTipoContrato() {
        return refTipoContrato;
    }

    public void setRefTipoContrato(InfCatalogo refTipoContrato) {
        this.refTipoContrato = refTipoContrato;
    }

    public MgiGrupo getRefGrupo() {
        return refGrupo;
    }

    public void setRefGrupo(MgiGrupo refGrupo) {
        this.refGrupo = refGrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usId != null ? usId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MacUsuario)) {
            return false;
        }
        MacUsuario other = (MacUsuario) object;
        if ((this.usId == null && other.usId != null) || (this.usId != null && !this.usId.equals(other.usId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GIISIC.Entidades.MacUsuario[ usId=" + usId + " ]";
    }

    @XmlTransient
    public Collection<MgiAsignarEvidencias> getMgiAsignarEvidenciasCollection() {
        return mgiAsignarEvidenciasCollection;
    }

    public void setMgiAsignarEvidenciasCollection(Collection<MgiAsignarEvidencias> mgiAsignarEvidenciasCollection) {
        this.mgiAsignarEvidenciasCollection = mgiAsignarEvidenciasCollection;
    }

    @XmlTransient
    public Collection<MgiAsignarTitulo> getMgiAsignarTituloCollection() {
        return mgiAsignarTituloCollection;
    }

    public void setMgiAsignarTituloCollection(Collection<MgiAsignarTitulo> mgiAsignarTituloCollection) {
        this.mgiAsignarTituloCollection = mgiAsignarTituloCollection;
    }

    @XmlTransient
    public Collection<InfEvidencia> getInfEvidenciaCollection() {
        return infEvidenciaCollection;
    }

    public void setInfEvidenciaCollection(Collection<InfEvidencia> infEvidenciaCollection) {
        this.infEvidenciaCollection = infEvidenciaCollection;
    }
    
}
