/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GIISIC.Eventos;

import GIISIC.Entidades.MacCofinanciador;
import GIISIC.Global.AccesoDatos;
import GIISIC.Global.ConjuntoResultado;
import GIISIC.Global.Parametro;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Pruebas_ECV
 */
public class ECofinanciador {
    public static boolean insertar(MacCofinanciador objeto){
        boolean resultado = false;
        String sql = "INSERT INTO mac_cofinanciador"
                   + "(co_razon_social,"
                   + " co_representante_legal,"
                   + " co_direccion,"
                   + " co_telefono,"
                   + " co_pagina_web,"
                   + " co_email) "
                   + "VALUES (?, ?, ?, ?, ?, ?);";
        ArrayList<Parametro> lstPar = new ArrayList<Parametro>();     
        lstPar.add(new Parametro(1,  objeto.getCoRazonSocial()));
        lstPar.add(new Parametro(2,  objeto.getCoRepresentanteLegal()));
        lstPar.add(new Parametro(3,  objeto.getCoDireccion()));
        lstPar.add(new Parametro(4,  objeto.getCoTelefono()));
        lstPar.add(new Parametro(5,  objeto.getCoPaginaWeb()));
        lstPar.add(new Parametro(6,  objeto.getCoEmail()));
        try {
            resultado = AccesoDatos.ejecutaComando(sql, lstPar);        
             //JOptionPane.showMessageDialog(null, "Se ha Registrado el Cofinanciador Exitosamente");
           
          // JOptionPane.showMessageDialog(null, "Ingreso Exitoso! " +" ", "Ingreso Exitoso", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return resultado;
    }
    
    public static boolean eliminar (int codigo){
        boolean resultado = false;
        String sql = "DELETE FROM inf_catalogo "
                   + "WHERE cat_id = ? ";
        ArrayList<Parametro> lstPar = new ArrayList<Parametro>();
        
        lstPar.add(new Parametro(1, codigo));
        
        try {
            resultado = AccesoDatos.ejecutaComando(sql, lstPar);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return resultado;
    }
    
    public static boolean modificar (MacCofinanciador objeto){
        boolean resultado = false;
        String sql = "UPDATE inf_catalogo SET "
                    + "cat_id=?,"
                    + "cat_catalogo=?,"
                    + "cat_codigo_miembro=?, "
                    + "cat_nombre_miembro=?"
                    + "WHERE cat_id=?;";
        ArrayList<Parametro> lstPar = new ArrayList<Parametro>();
        
       /* lstPar.add(new Parametro(1,  objeto.getCatId()));
        lstPar.add(new Parametro(2,  objeto.getCatCatalogo()));
        lstPar.add(new Parametro(3,  objeto.getCatCodigoMiembro()));
        lstPar.add(new Parametro(4,  objeto.getCatNombreMiembro()));
        lstPar.add(new Parametro(5,  objeto.getCatId()));*/
        
        try {
            resultado = AccesoDatos.ejecutaComando(sql, lstPar);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return resultado;
    }
    
    public static MacCofinanciador getGrupo(int codigo){
        MacCofinanciador objeto = null;
        String sql = "SELECT "
                   + " co_razon_social,"
                   + " co_representante_legal,"
                   + " co_direccion,"
                   + " co_telefono,"
                   + " co_pagina_web,"
                   + " co_email"
                    + "FROM mac_cofinanciador "
                    + "WHERE us_id = ?";
        
        ArrayList<Parametro> lstPar = new ArrayList<Parametro>();
        
        lstPar.add(new Parametro(1, codigo));
        
        try {
            ConjuntoResultado cres = AccesoDatos.ejecutaQuery(sql, lstPar);
            
            while(cres.next()){
                objeto = new MacCofinanciador();
                objeto.setUsId(cres.getInt("cat_id"));
                objeto.setCoRazonSocial(cres.getString("co_razon_social"));
                objeto.setCoRepresentanteLegal(cres.getString("co_representante_legal"));
                objeto.setCoDireccion(cres.getString("co_direccion"));
                objeto.setCoTelefono(cres.getString("co_telefono"));
                objeto.setCoPaginaWeb(cres.getString("co_pagina_web"));
                objeto.setCoEmail(cres.getString("co_email"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return objeto;
    }
    
    public static ArrayList<MacCofinanciador> getGrupos(){
        ArrayList<MacCofinanciador> ListaGrupos = new ArrayList<MacCofinanciador>();
        String sql = "SELECT "
                   + "us_id, "
                   + "co_razon_social,"
                   + " co_representante_legal,"
                   + " co_direccion,"
                   + " co_telefono,"
                   + " co_pagina_web,"
                   + " co_email"
                    + "FROM mac_cofinanciador;";
        
        try {
            ConjuntoResultado cres = AccesoDatos.ejecutaQuery(sql);
            MacCofinanciador objeto = new MacCofinanciador();
            while(cres.next()){
                objeto = new MacCofinanciador();
                objeto.setUsId(cres.getInt("us_id"));
                objeto.setCoRazonSocial(cres.getString("co_razon_social"));
                objeto.setCoRepresentanteLegal(cres.getString("co_representante_legal"));
                objeto.setCoDireccion(cres.getString("co_direccion"));
                objeto.setCoTelefono(cres.getString("co_telefono"));
                objeto.setCoPaginaWeb(cres.getString("co_pagina_web"));
                objeto.setCoEmail(cres.getString("co_email"));
                
                ListaGrupos.add(objeto);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return ListaGrupos;
    }
}