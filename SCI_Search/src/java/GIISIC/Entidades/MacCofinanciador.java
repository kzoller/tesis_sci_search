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
@Table(name = "mac_cofinanciador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MacCofinanciador.findAll", query = "SELECT m FROM MacCofinanciador m"),
    @NamedQuery(name = "MacCofinanciador.findByUsId", query = "SELECT m FROM MacCofinanciador m WHERE m.usId = :usId"),
    @NamedQuery(name = "MacCofinanciador.findByCoRazonSocial", query = "SELECT m FROM MacCofinanciador m WHERE m.coRazonSocial = :coRazonSocial"),
    @NamedQuery(name = "MacCofinanciador.findByCoRepresentanteLegal", query = "SELECT m FROM MacCofinanciador m WHERE m.coRepresentanteLegal = :coRepresentanteLegal"),
    @NamedQuery(name = "MacCofinanciador.findByCoDireccion", query = "SELECT m FROM MacCofinanciador m WHERE m.coDireccion = :coDireccion"),
    @NamedQuery(name = "MacCofinanciador.findByCoTelefono", query = "SELECT m FROM MacCofinanciador m WHERE m.coTelefono = :coTelefono"),
    @NamedQuery(name = "MacCofinanciador.findByCoPaginaWeb", query = "SELECT m FROM MacCofinanciador m WHERE m.coPaginaWeb = :coPaginaWeb"),
    @NamedQuery(name = "MacCofinanciador.findByCoEmail", query = "SELECT m FROM MacCofinanciador m WHERE m.coEmail = :coEmail")})
public class MacCofinanciador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "us_id")
    private Integer usId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "co_razon_social")
    private String coRazonSocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "co_representante_legal")
    private String coRepresentanteLegal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "co_direccion")
    private String coDireccion;
    @Size(max = 10)
    @Column(name = "co_telefono")
    private String coTelefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "co_pagina_web")
    private String coPaginaWeb;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "co_email")
    private String coEmail;

    public MacCofinanciador() {
    }

    public MacCofinanciador(Integer usId) {
        this.usId = usId;
    }

    public MacCofinanciador(Integer usId, String coRazonSocial, String coRepresentanteLegal, String coDireccion, String coPaginaWeb, String coEmail) {
        this.usId = usId;
        this.coRazonSocial = coRazonSocial;
        this.coRepresentanteLegal = coRepresentanteLegal;
        this.coDireccion = coDireccion;
        this.coPaginaWeb = coPaginaWeb;
        this.coEmail = coEmail;
    }

    public Integer getUsId() {
        return usId;
    }

    public void setUsId(Integer usId) {
        this.usId = usId;
    }

    public String getCoRazonSocial() {
        return coRazonSocial;
    }

    public void setCoRazonSocial(String coRazonSocial) {
        this.coRazonSocial = coRazonSocial;
    }

    public String getCoRepresentanteLegal() {
        return coRepresentanteLegal;
    }

    public void setCoRepresentanteLegal(String coRepresentanteLegal) {
        this.coRepresentanteLegal = coRepresentanteLegal;
    }

    public String getCoDireccion() {
        return coDireccion;
    }

    public void setCoDireccion(String coDireccion) {
        this.coDireccion = coDireccion;
    }

    public String getCoTelefono() {
        return coTelefono;
    }

    public void setCoTelefono(String coTelefono) {
        this.coTelefono = coTelefono;
    }

    public String getCoPaginaWeb() {
        return coPaginaWeb;
    }

    public void setCoPaginaWeb(String coPaginaWeb) {
        this.coPaginaWeb = coPaginaWeb;
    }

    public String getCoEmail() {
        return coEmail;
    }

    public void setCoEmail(String coEmail) {
        this.coEmail = coEmail;
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
        if (!(object instanceof MacCofinanciador)) {
            return false;
        }
        MacCofinanciador other = (MacCofinanciador) object;
        if ((this.usId == null && other.usId != null) || (this.usId != null && !this.usId.equals(other.usId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GIISIC.Entidades.MacCofinanciador[ usId=" + usId + " ]";
    }
    
}
