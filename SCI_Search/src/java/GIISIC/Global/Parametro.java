/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GIISIC.Global;
import java.sql.*;
import javax.sql.*;
/**
 *
 * @author Dada
 */
public class Parametro {   
    private int posicion;
    private Object valor; 
    public Parametro()
    {
    posicion=0;
    valor= null;
    }

    public Parametro(int posicion, Object valor)
    {
    this.posicion=posicion;
    this.valor= valor;
    }

    /**
     * @return the posicion
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    /**
     * @return the valor
     */
    public Object getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Object valor) {
        this.valor = valor;
    }
    


}
