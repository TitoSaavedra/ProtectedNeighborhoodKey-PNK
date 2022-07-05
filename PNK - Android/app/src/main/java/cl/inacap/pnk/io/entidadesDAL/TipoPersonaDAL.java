package cl.inacap.pnk.io.entidadesDAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cl.inacap.pnk.io.BaseDatos;
import cl.inacap.pnk.io.ConexionBD;
import cl.inacap.pnk.io.Model;
import cl.inacap.pnk.io.entidadesDAO.TipoPersona;

/**
 * Clase DAL de la tabla TipoPersona
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class TipoPersonaDAL {

    //Atributo con la base de datos
    private static BaseDatos baseDatos;

    /**
     * Constructor de la clase
     * @param context Contexto de la aplicacion
     */
    public TipoPersonaDAL(Context context){
        ConexionBD conexionBD=ConexionBD.obtenerInstancia(context);
        baseDatos= ConexionBD.baseDatos;
    }

    /**
     * Metodo para insertar personas en la base de datos local
     * @param tipoPersona Tipo de persona con los datos a ingresar
     */
    public void insertarTipoPersona(TipoPersona tipoPersona){
        SQLiteDatabase bd=baseDatos.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(Model.ColTipo_Persona.ID_TIPO_PERSONA,tipoPersona.getId_tipo_persona());
        values.put(Model.ColTipo_Persona.NOMBRE,tipoPersona.getNombre());
        values.put(Model.ColTipo_Persona.DESCRIPCION,tipoPersona.getDescripcion());
        bd.insert(Model.Tablas.TIPO_PERSONA,null,values);
        bd.close();
    }

    /**
     * Metodo para buscar personas en la base de datos local segun id
     * @param tipoPersona Tipo de persona con el id a buscar
     * @return Tipo de persona con los datos encontrados
     */
    public TipoPersona buscarTipoPersona(TipoPersona tipoPersona){
        SQLiteDatabase bd=baseDatos.getReadableDatabase();
        Cursor c=bd.rawQuery(String.format("SELECT %s,%s,%s FROM %s WHERE %s='%s'",
                Model.ColTipo_Persona.ID_TIPO_PERSONA, Model.ColTipo_Persona.NOMBRE, Model.ColTipo_Persona.DESCRIPCION,
                Model.Tablas.TIPO_PERSONA, Model.ColTipo_Persona.ID_TIPO_PERSONA,tipoPersona.getId_tipo_persona()),null);
        if(c.moveToNext()){
            tipoPersona.setNombre(c.getString(1));
            tipoPersona.setDescripcion(c.getString(2));
        }else {
            tipoPersona=null;
        }
        return tipoPersona;
    }

}
