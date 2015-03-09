package GIISIC.Eventos;
import GIISIC.Entidades.MgiGrupo;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Admin
 */
@ManagedBean
@ViewScoped
public class FGrupo {
    private List<MgiGrupo> lista;
    private MgiGrupo nuevocurso;
    private MgiGrupo selectCurso;

    public FGrupo() {
        nuevocurso = new MgiGrupo();
        cargardatos(); //creamos un constructor vacio y le creamos la función de cargar datos
    }
    
    public void cargardatos(){
        lista = EGrupo.getGrupos();
    }
    
    public void insertar(){
        if(EGrupo.insertar(nuevocurso)){
            cargardatos();
        };
    }
    
    public void modificar(){
        if(EGrupo.modificar(nuevocurso))
        {
            cargardatos();
        }
    }
    
    public void eliminar(){
        if(EGrupo.eliminar(selectCurso.getGrId())){
            cargardatos();
        }
    }

    public List<MgiGrupo> getLista() {
        return lista;
    }

    public void setLista(List<MgiGrupo> lista) {
        this.lista = lista;
    }

    public MgiGrupo getNuevocurso() {
        return nuevocurso;
    }

    public void setNuevocurso(MgiGrupo nuevocurso) {
        this.nuevocurso = nuevocurso;
    }

    public MgiGrupo getSelectCurso() {
        return selectCurso;
    }

    public void setSelectCurso(MgiGrupo selectCurso) {
        this.selectCurso = selectCurso;
    }
    
    
}
