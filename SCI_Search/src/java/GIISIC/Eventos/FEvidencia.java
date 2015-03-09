package GIISIC.Eventos;
import GIISIC.Entidades.MgiAsignarTitulo;
import GIISIC.Entidades.MgiAsignarTitulo;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import static org.apache.jasper.Constants.DEFAULT_BUFFER_SIZE;

/**
 *
 * @author Admin
 */
@ManagedBean
@ViewScoped
public class FEvidencia {
    private List<MgiAsignarTitulo> lista;
    private MgiAsignarTitulo nuevocurso;
    private MgiAsignarTitulo selectCurso;

    public FEvidencia() {
        nuevocurso = new MgiAsignarTitulo();
        cargardatos(); //creamos un constructor vacio y le creamos la función de cargar datos
    }
    
    public void cargardatos(){
        lista = EEvidencia.getGrupos();
    }
    
    public List<MgiAsignarTitulo> getLista() {
        return lista;
    }

    public void setLista(List<MgiAsignarTitulo> lista) {
        this.lista = lista;
    }

    public MgiAsignarTitulo getNuevocurso() {
        return nuevocurso;
    }

    public void setNuevocurso(MgiAsignarTitulo nuevocurso) {
        this.nuevocurso = nuevocurso;
    }

    public MgiAsignarTitulo getSelectCurso() {
        return selectCurso;
    }

    public void setSelectCurso(MgiAsignarTitulo selectCurso) {
        this.selectCurso = selectCurso;
    }
    
    
    
    public void downloadFile() throws IOException{
    String filePath="C:\\new_features.pdf";
    FacesContext context = FacesContext.getCurrentInstance();  
    HttpServletResponse response = (HttpServletResponse) context  
                         .getExternalContext().getResponse();  
    File file = new File(filePath);  
    if (!file.exists()) {  
      response.sendError(HttpServletResponse.SC_NOT_FOUND);  
      return;  
     }  
    response.reset();  
    response.setBufferSize(DEFAULT_BUFFER_SIZE);  
    response.setContentType("application/octet-stream");  
    response.setHeader("Content-Length", String.valueOf(file.length()));  
    response.setHeader("Content-Disposition", "attachment;filename=\""  
           + file.getName() + "\"");  
    BufferedInputStream input = null;  
    BufferedOutputStream output = null;  

    try 
    {  
        input = new BufferedInputStream(new FileInputStream(file),  
                    DEFAULT_BUFFER_SIZE);  
        output = new BufferedOutputStream(response.getOutputStream(),  
                        DEFAULT_BUFFER_SIZE);  
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];  
        int length;  
        while ((length = input.read(buffer)) > 0) {  
            output.write(buffer, 0, length);  
        }  
    } finally 
    {  
        input.close();  
        output.close();  
    }  
    context.responseComplete();
}
    
    
}
