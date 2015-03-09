/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GIISIC.Eventos;

import GIISIC.Entidades.MacCofinanciador;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Admin
 */
@ManagedBean
@ViewScoped
public class FCofinanciador {
    private List<MacCofinanciador> lista;
    private MacCofinanciador nuevocurso;
    private MacCofinanciador selectCurso;

    public FCofinanciador() {
        nuevocurso = new MacCofinanciador();
        cargardatos(); //creamos un constructor vacio y le creamos la función de cargar datos
    }
    
    public void cargardatos(){
        lista = ECofinanciador.getGrupos();
    }
    
    public void insertar(){
        if(ECofinanciador.insertar(nuevocurso)){
            cargardatos();
        };
    }
    
    public void modificar(){
        //if(ECatalogo.modificar(selectCurso))
        if(ECofinanciador.modificar(nuevocurso))//Eliminar
        {
            cargardatos();
        }
    }
    
    public void eliminar(){
        if(ECofinanciador.eliminar(selectCurso.getUsId())){
            cargardatos();
        }
    }

    public List<MacCofinanciador> getLista() {
        return lista;
    }

    public void setLista(List<MacCofinanciador> lista) {
        this.lista = lista;
    }

    public MacCofinanciador getNuevocurso() {
        return nuevocurso;
    }

    public void setNuevocurso(MacCofinanciador nuevocurso) {
        this.nuevocurso = nuevocurso;
    }

    public MacCofinanciador getSelectCurso() {
        return selectCurso;
    }

    public void setSelectCurso(MacCofinanciador selectCurso) {
        this.selectCurso = selectCurso;
    }
    
    
}
