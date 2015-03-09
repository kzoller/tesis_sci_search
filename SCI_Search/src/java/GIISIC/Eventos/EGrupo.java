/*****************************************************************************
 *       Clase:                 EGrupo.java                                  *
 *       Espacio de nombres:    GIISIC.Eventos                               *
 *       Autor:                 Tomas Coronel                                *
 *                                                                           *
 *---------------------------------------------------------------------------*
 *      Descripción:                                                         *
 *                                                                           *
 *                                                                           *
 *---------------------------------------------------------------------------*
 *      Fecha           Autor               Razón                            *
 *      10/02/2015      Tomas Coronel C.    Creación                         *
 *                                                                           *
 ****************************************************************************/

package GIISIC.Eventos;

import GIISIC.Global.AccesoDatos;
import GIISIC.Global.ConjuntoResultado;
import GIISIC.Global.Parametro;
import GIISIC.Entidades.MgiGrupo;
import GIISIC.Entidades.InfCatalogo;
import java.util.ArrayList;

/**
 *
 * @author Pruebas_ECV
 */
public class EGrupo {
    
    public static boolean insertar(MgiGrupo objeto){
        boolean resultado = false;
        String sql = "INSERT INTO mgi_grupo( gr_id, gr_nombre_grupo, "
                   + " gr_abreviatura_grupo, gr_fecha_registro, "
                   + "gr_fecha_aprobacion, gr_estado) "
                   + "VALUES (?, ?, ?, ?, ?, ?);";
        ArrayList<Parametro> lstPar = new ArrayList<Parametro>();
        
        lstPar.add(new Parametro(1, objeto.getGrNombreGrupo()));
        lstPar.add(new Parametro(2, objeto.getGrAbreviaturaGrupo()));
        lstPar.add(new Parametro(3, objeto.getGrFechaRegistro()));
        lstPar.add(new Parametro(4, objeto.getGrFechaAprobacion()));
        lstPar.add(new Parametro(5, objeto.getGrEstado()));
        
        try {
            resultado = AccesoDatos.ejecutaComando(sql, lstPar);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return resultado;
    }
    
    public static boolean eliminar (int codigo){
        boolean resultado = false;
        String sql = "DELETE FROM mgi_grupo "
                   + "WHERE gr_id = ? ";
        ArrayList<Parametro> lstPar = new ArrayList<Parametro>();
        
        lstPar.add(new Parametro(1, codigo));
        
        try {
            resultado = AccesoDatos.ejecutaComando(sql, lstPar);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return resultado;
    }

    
    public static boolean modificar (MgiGrupo objeto){
        boolean resultado = false;
        String sql = "UPDATE mgi_grupo SET "
                   + "gr_estado=? "
                   + "WHERE gr_id=?;";
        ArrayList<Parametro> lstPar = new ArrayList<Parametro>();
        
        lstPar.add(new Parametro(1, objeto.getGrEstado()));
        lstPar.add(new Parametro(2, objeto.getGrId()));
        try {
            resultado = AccesoDatos.ejecutaComando(sql, lstPar);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return resultado;
    }
    
    public static MgiGrupo getGrupo(int codigo){
        MgiGrupo objeto = new MgiGrupo();
        String sql = "SELECT "
                   + "gr_id, "
                   + "gr_nombre_grupo, "
                   + "gr_abreviatura_grupo, "
                   + "gr_fecha_registro, "
                   + "gr_fecha_aprobacion, "
                   + "gr_estado "
                   + "FROM mgi_grupo "
                   + "WHERE gr_id = ?";
        
        ArrayList<Parametro> lstPar = new ArrayList<Parametro>();
        
        lstPar.add(new Parametro(1, codigo));
        
        try {
            ConjuntoResultado cres = AccesoDatos.ejecutaQuery(sql, lstPar);
            
            while(cres.next()){
                objeto = new MgiGrupo();
                objeto.setGrId(cres.getInt("gr_id"));
                objeto.setGrNombreGrupo(cres.getString("gr_nombre_grupo"));
                objeto.setGrAbreviaturaGrupo(cres.getString("gr_abreviatura_grupo"));
                objeto.setGrFechaRegistro(cres.getDate("gr_fecha_registro"));
                objeto.setGrFechaAprobacion(cres.getDate("gr_fecha_aprobacion"));
                objeto.setGrEstado(cres.getString("gr_estado"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return objeto;
    }
    
    public static ArrayList<MgiGrupo> getGrupos(){
        ArrayList<MgiGrupo> ListaGrupos = new ArrayList<MgiGrupo>();
        String sql = "SELECT "
                   + "gr_id, "
                   + "gr_nombre_grupo, "
                   + "gr_abreviatura_grupo, "
                   + "gr_fecha_registro, "
                   + "gr_fecha_aprobacion, "
                   + "gr_estado "
                   + "FROM mgi_grupo;";
        
        try {
            ConjuntoResultado cres = AccesoDatos.ejecutaQuery(sql);
            while(cres.next()){
            MgiGrupo objeto = new MgiGrupo();
                objeto.setGrId(cres.getInt("gr_id"));
                objeto.setGrNombreGrupo(cres.getString("gr_nombre_grupo"));
                objeto.setGrAbreviaturaGrupo(cres.getString("gr_abreviatura_grupo"));
                objeto.setGrFechaRegistro(cres.getDate("gr_fecha_registro"));
                objeto.setGrFechaAprobacion(cres.getDate("gr_fecha_aprobacion"));
                objeto.setGrEstado(cres.getString("gr_estado"));
                
                ListaGrupos.add(objeto);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return ListaGrupos;
    }
    
}
