package GIISIC.Eventos;
import GIISIC.Entidades.MacUsuario;
import java.util.List;
import GIISIC.Eventos.EGrupo;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Admin
 */
@ManagedBean
@ViewScoped
public class FUsuario {
    private List<MacUsuario> lista;
    private List<MacUsuario> lista2;
    private MacUsuario nuevocurso;
    private MacUsuario selectCurso;

    public FUsuario() {
        nuevocurso = new MacUsuario();
        //int value="#{bean.productId}";
       // int parametro1 = nuevocurso.getRefGrupo(i);
        cargardatos(); //creamos un constructor vacio y le creamos la función de cargar datos
    }
    
    public void cargardatos(int i){
        int i1=1;
        lista = EUsuario.getUsuario();
        lista2= EUsuario.getUsuarioinformacion(i1);
    }
    
    public void cargardatos(){
        int i=1;
        
        //objeto.setRefGrupo(EGrupo.getGrupo(cres.getInt("ref_grupo")));
        //int i=request.getParameter("dato1");
        
        lista = EUsuario.getUsuario(i);
        lista2= EUsuario.getUsuarioinformacion(i);
    }
   
    public List<MacUsuario> getLista() {
        return lista;
    }
    
    public List<MacUsuario> getLista2() {
        return lista2;
    }

    public void setLista(List<MacUsuario> lista) {
        this.lista = lista;
    }
    
       public MacUsuario getNuevocurso() {
        return nuevocurso;
    }

    public void setNuevocurso(MacUsuario nuevocurso) {
        this.nuevocurso = nuevocurso;
    }

    public MacUsuario getSelectCurso() {
        return selectCurso;
    }

    public void setSelectCurso(MacUsuario selectCurso) {
        this.selectCurso = selectCurso;
    }
    
    public void aprobar(){
        int i1 = 3;
        int i2 = 1;
       lista2= EUsuario.actualizacion(i1,i2);
    }
    
    public void negar(){
        int i4 = 4;
        int i5 = 1;
       lista2= EUsuario.actualizacion(i4,i5);
    }
    
}
