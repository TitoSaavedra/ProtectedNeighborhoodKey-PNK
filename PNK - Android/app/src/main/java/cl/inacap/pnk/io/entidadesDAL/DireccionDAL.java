package cl.inacap.pnk.io.entidadesDAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import cl.inacap.pnk.io.BaseDatos;
import cl.inacap.pnk.io.ConexionBD;
import cl.inacap.pnk.io.Model;
import cl.inacap.pnk.io.entidadesDAO.Direccion;

/**
 * Clase DAL de la tabla Direccion
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class DireccionDAL {
    //Atributo con la base de datos
    private  static BaseDatos baseDatos;

    /**
     * Constructor de la clase
     * @param context Contexto de la aplicacion
     */
    public DireccionDAL(Context context){
        ConexionBD conexionBD=ConexionBD.obtenerInstancia(context);
        baseDatos=ConexionBD.baseDatos;
    }

    /**
     * Metodo para ingresar una direccion a la base de datos local
     * @param direccion Direccion con los datos a ingresar
     */
    public void insertDireccion(Direccion direccion){
        SQLiteDatabase bd=baseDatos.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(Model.ColDireccion.PISO,direccion.getPiso());
        values.put(Model.ColDireccion.BLOCK,direccion.getBlock());
        values.put(Model.ColDireccion.NUMERO,direccion.getNumero());
        values.put(Model.ColDireccion.ID_PERSONA,direccion.getPersonas().getIdPersona());
        bd.insert(Model.Tablas.DIRECCION,null,values);
        bd.close();
    }

}
