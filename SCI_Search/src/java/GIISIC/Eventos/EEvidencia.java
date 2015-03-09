/*****************************************************************************
 *       Clase:                 EGrupo.java                                  *
 *       Espacio de nombres:    GIISIC.Eventos                               *
 *       Autor:                 Karla Zöller S.                              *
 *                                                                           *
 *---------------------------------------------------------------------------*
 *      Descripción:                                                         *
 *                                                                           *
 *                                                                           *
 *---------------------------------------------------------------------------*
 *      Fecha           Autor               Razón                            *
 *      23/02/2015      Karla Zöller S.    Creación                          *
 *                                                                           *
 ****************************************************************************/

package GIISIC.Eventos;

import GIISIC.Global.AccesoDatos;
import GIISIC.Global.ConjuntoResultado;
import GIISIC.Global.Parametro;
//import GIISIC.Entidades.MgiAsignarEvidencias;
import GIISIC.Entidades.MgiAsignarTitulo;
import java.util.ArrayList;

/**
 *
 * @author Pruebas_ECV
 */
public class EEvidencia {
    
     public static ArrayList<MgiAsignarTitulo> getGrupos(){
        ArrayList<MgiAsignarTitulo> ListaGrupos = new ArrayList<MgiAsignarTitulo>();
        String sql = "select * from mgi_asignar_titulo;";
        
        try {
            ConjuntoResultado cres = AccesoDatos.ejecutaQuery(sql);
            MgiAsignarTitulo objeto = new MgiAsignarTitulo();
            while(cres.next()){
                objeto.setATId(cres.getInt("a_t_id"));
                ListaGrupos.add(objeto);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return ListaGrupos;
    }

  
    
}
