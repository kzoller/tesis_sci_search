/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GIISIC.Global;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Paul
 */
public class ConjuntoResultado {
   private String[] cabecera;
   private ArrayList<Object[]> Datos;
   private int indCol;
   private int indFil;
   private int tamCol;
   private int tamFil;
   public ConjuntoResultado() throws SQLException,Exception
   {
       tamFil=0;
       tamCol=0;
   indCol=-1;
   indFil=-1;
   }
   public boolean Fill(ResultSet rs) throws SQLException,Exception
   {
       boolean res=false;
    try {
        ResultSetMetaData metaData = rs.getMetaData();
        tamCol= metaData.getColumnCount();
        cabecera= new String[getTamCol()];
        for(int i=1;i<=getTamCol();i++)
        {
            cabecera[i-1]=metaData.getColumnName(i);
        }
        Datos = new ArrayList<Object[]>();
        try{
				//Ingreso de los Datos en el ConjuntoResultado
				while(rs.next()){
                    Object[] fila=null;
                    fila= new Object[getTamCol()];
                    for(int i=1; i<=getTamCol(); i++){
                    fila[i-1]=rs.getObject(i);                    
					}
                    Datos.add(fila);
                    tamFil+=1;
				}
			}catch(Exception exx){
				throw exx;
			}

       } catch (SQLException e) {
           throw e;
       }
       catch(Exception ex)
       {
           throw ex;
       }
       return res;
   }


   public boolean next()
   {
    boolean res=false;
    indFil+=1;
    if(indFil<getTamFil())
    {  res=true;
    }
       return res;
    }

   public String getString(int nCol) throws Exception
		{
            try {
            return this.Datos.get(indFil)[nCol].toString();
            } catch (Exception e) {
                throw e;
            }

		}
    public String getString(String nomCol) throws Exception
		{try{
             int col=Find(nomCol);
			return this.Datos.get(indFil)[col].toString();
             } catch (Exception e) {
                throw e;
            }
		}

    public short getShort(int nCol) throws Exception
		{try{
			return new Short(this.Datos.get(indFil)[nCol].toString());
             } catch (Exception e) {
                throw e;
            }
		}
    public short getShort(String nomCol) throws Exception
		{try{
			return new Short(this.Datos.get(indFil)[Find(nomCol)].toString());
             } catch (Exception e) {
                throw e;
            }
		}

     public byte[] getBytes(int nCol) throws Exception
		{try{
			return  toBytes(this.Datos.get(indFil)[nCol]);
             } catch (Exception e) {
                throw e;
            }
		}
    public byte[] getBytes(String nomCol) throws Exception
		{try{
			return toBytes(this.Datos.get(indFil)[Find(nomCol)]);
             } catch (Exception e) {
                throw e;
            }
		}

     public int getInt(int nCol) throws Exception
		{try{
             int val= Integer.parseInt( this.Datos.get(indFil)[nCol].toString());
			return val;
             } catch (Exception e) {
                throw e;
            }
		}
    public int getInt(String nomCol) throws Exception
		{try{
			return Integer.parseInt(this.Datos.get(indFil)[Find(nomCol)].toString());
             } catch (Exception e) {
                throw e;
            }
		}
  public long getLong(int nCol) throws Exception
		{try{
			return (Long)(this.Datos.get(indFil)[nCol]);
             } catch (Exception e) {
                throw e;
            }
		}
    public long getLong(String nomCol) throws Exception
		{try{
			return (Long)(this.Datos.get(indFil)[Find(nomCol)]);
             } catch (Exception e) {
                throw e;
            }
		}
     public Date getDate(int nCol) throws Exception
		{try{
			return (Date)(this.Datos.get(indFil)[nCol]);
             } catch (Exception e) {
                throw e;
            }
		}
    public Date getDate(String nomCol) throws Exception
		{try{
			return (Date)(this.Datos.get(indFil)[Find(nomCol)]);
             } catch (Exception e) {
                throw e;
            }
		}

     public boolean getBoolean(int nCol) throws Exception
		{try{
			return (Boolean)(this.Datos.get(indFil)[nCol]);
             } catch (Exception e) {
                throw e;
            }
		}
    public boolean getBoolean(String nomCol) throws Exception
		{try{
			return (Boolean)(this.Datos.get(indFil)[Find(nomCol)]);
             } catch (Exception e) {
                throw e;
            }
		}

      public Object getObject(int nCol) throws Exception
		{
            try {
            return this.Datos.get(indFil)[nCol];
            } catch (Exception e) {
                throw e;
            }

		}
    public Object getObject(String nomCol) throws Exception
		{try{
             int col=Find(nomCol);
			return this.Datos.get(indFil)[col];
             } catch (Exception e) {
                throw e;
            }
		}



   //Metodo para encontrar la columna segun el nombre
   private int Find(String nomCol) throws Exception
   {
       nomCol=nomCol.toLowerCase();
     int index=-1;
     indCol=0;
     while((index<0)&&(indCol<getTamCol()))
     {   if(cabecera[indCol].toLowerCase().equals(nomCol))
             index=indCol;
         indCol+=1;
     }
     if(index<0)
         throw new Exception("No existe la columna "+nomCol);
     return index;
   }

   private static byte[] toBytes(Object obj) throws java.io.IOException
   {
        ObjectOutputStream os = null;

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(5000);
        os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
        os.flush();
        os.writeObject(obj);
        os.flush();
        byte[] sendBuf = byteStream.toByteArray();
        os.close();
        return sendBuf;

}

    /**
     * @return the tamCol
     */
    public int getTamCol() {
        return tamCol;
    }

    /**
     * @return the tamFil
     */
    public int getTamFil() {
        return tamFil;
    }



}
