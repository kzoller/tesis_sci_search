/**
 * ***************************************************************************
 * Clase: EGrupo.java * Espacio de nombres: GIISIC.Eventos * Autor: Karla Zöller
 * S. * *
 * ---------------------------------------------------------------------------*
 * Descripción: * * *
 * ---------------------------------------------------------------------------*
 * Fecha Autor Razón * 22/02/2015 Karla Zöller S. Creación * *
 ***************************************************************************
 */
package GIISIC.Eventos;

import GIISIC.Global.AccesoDatos;
import GIISIC.Global.ConjuntoResultado;
import GIISIC.Global.Parametro;
import GIISIC.Entidades.MacUsuario;
import GIISIC.Entidades.MgiGrupo;
import java.util.ArrayList;
import GIISIC.Eventos.EGrupo;
import javax.swing.JOptionPane;

/**
 *
 * @author Pruebas_ECV
 */
public class EUsuario {

     public static ArrayList<MacUsuario> getUsuarioinformacion(int codigo) {
        ArrayList<MacUsuario> ListaUusuarios = new ArrayList<MacUsuario>();
        String sql = "SELECT * from mgi_grupo WHERE gr_id = ?";

        ArrayList<Parametro> lstPar = new ArrayList<Parametro>();

        lstPar.add(new Parametro(1, codigo));
        
        try {
            ConjuntoResultado cres = AccesoDatos.ejecutaQuery(sql,lstPar);

            while (cres.next()) {
                MacUsuario objeto = new MacUsuario();
                objeto.setRefGrupo(EGrupo.getGrupo(cres.getInt("gr_id")));
                ListaUusuarios.add(objeto);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return ListaUusuarios;
    }

    public static ArrayList<MacUsuario> getUsuario() {
        ArrayList<MacUsuario> ListaUusuarios = new ArrayList<MacUsuario>();
        String sql = "SELECT us_nombre,us_apellido, ref_grupo FROM mac_usuario ";

        try {
            ConjuntoResultado cres = AccesoDatos.ejecutaQuery(sql);

            while (cres.next()) {
            MacUsuario objeto = new MacUsuario();
                objeto.setUsNombre(cres.getString("us_nombre"));
                objeto.setUsApellido(cres.getString("us_apellido"));
                objeto.setRefGrupo(EGrupo.getGrupo(cres.getInt("ref_grupo")));
                ListaUusuarios.add(objeto);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return ListaUusuarios;
    }

    public static ArrayList<MacUsuario> getUsuario(int codigo) {
        ArrayList<MacUsuario> ListaUusuarios = new ArrayList<MacUsuario>();
        String sql = "SELECT us_nombre,us_apellido, ref_grupo FROM mac_usuario WHERE ref_grupo = ?";

        ArrayList<Parametro> lstPar = new ArrayList<Parametro>();

        lstPar.add(new Parametro(1, codigo));
        
        try {
            ConjuntoResultado cres = AccesoDatos.ejecutaQuery(sql,lstPar);

            while (cres.next()) {
                MacUsuario objeto = new MacUsuario();
                objeto.setUsNombre(cres.getString("us_nombre"));
                objeto.setUsApellido(cres.getString("us_apellido"));
                objeto.setRefGrupo(EGrupo.getGrupo(cres.getInt("ref_grupo")));
                ListaUusuarios.add(objeto);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return ListaUusuarios;
    }
    
    public static MacUsuario getUsuarios(int codigo) {
        MacUsuario objeto = null;
        String sql = "SELECT "
                + "us_nombre, "
                + "us_apellido, "
                + "ref_grupo "
                + "FROM mac_usuario "
                + "WHERE ref_grupo = ?";

        ArrayList<Parametro> lstPar = new ArrayList<Parametro>();

        lstPar.add(new Parametro(1, codigo));

        try {
            ConjuntoResultado cres = AccesoDatos.ejecutaQuery(sql, lstPar);

            while (cres.next()) {
                objeto = new MacUsuario();
                objeto.setUsNombre(cres.getString("us_nombre"));
                objeto.setUsApellido(cres.getString("us_apellido"));
                objeto.setRefGrupo(EGrupo.getGrupo(cres.getInt("ref_grupo")));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return objeto;
    }
    
    
    //actualizar 
     public static ArrayList<MacUsuario> actualizacion(int estado,int id) {
        ArrayList<MacUsuario> ListaUusuarios = new ArrayList<MacUsuario>();
        String sql = "UPDATE mgi_grupo SET "
                   + "gr_estado=? "
                   + "WHERE gr_id=?;";

        ArrayList<Parametro> lstPar = new ArrayList<Parametro>();
        lstPar.add(new Parametro(1, estado));
        lstPar.add(new Parametro(2, id));
        
        try {
            ConjuntoResultado cres = AccesoDatos.ejecutaQuery(sql,lstPar);
            ;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return ListaUusuarios;
    }    
    
}
