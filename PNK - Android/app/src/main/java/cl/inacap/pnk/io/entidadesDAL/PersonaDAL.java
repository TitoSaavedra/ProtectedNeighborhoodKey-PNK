package cl.inacap.pnk.io.entidadesDAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

import cl.inacap.pnk.io.BaseDatos;
import cl.inacap.pnk.io.ConexionBD;
import cl.inacap.pnk.io.Model;
import cl.inacap.pnk.io.entidadesDAO.Persona;
import cl.inacap.pnk.io.entidadesDAO.TipoPersona;


/**
 * Clase DAL de la tabla Persona
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class PersonaDAL {

    //Atributo con la base de datos
    private static BaseDatos baseDatos;
    private static Context con;

    /**
     * Constructor de la clase
     * @param context Contexto de la aplicacion
     */
    public PersonaDAL(Context context){
        con=context;
        ConexionBD conexionBD=ConexionBD.obtenerInstancia(context);
        baseDatos=ConexionBD.baseDatos;
    }

    /**
     * Metodo para insertar la persona a la base de datos local
     * @param persona Persona con los datos a ingresar
     */
    public void insertarPersona(Persona persona){
        SQLiteDatabase bd=baseDatos.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(Model.ColPersona.ID_PERSONA,persona.getIdPersona());
        values.put(Model.ColPersona.NOMBRE,persona.getNombre());
        values.put(Model.ColPersona.SEG_NOMBRE,persona.getSegundoNom());
        values.put(Model.ColPersona.APE_PATERNO,persona.getApellidoPa());
        values.put(Model.ColPersona.APE_MATERNO,persona.getApellidoMa());
        values.put(Model.ColPersona.TELEFONO,persona.getTelefono());
        values.put(Model.ColPersona.EMAIL,persona.getCorreo());
        values.put(Model.ColPersona.ESTADO,persona.getEstado());
        values.put(Model.ColPersona.ID_TIPO_PERSONA,persona.getTipoPersona().getId_tipo_persona());
        bd.insert(Model.Tablas.PERSONA,null,values);
        bd.close();
    }

    /**
     * Metodo para buscar personas por medio del id en la base de datos local
     * @param persona Persona con los datos a buscar
     * @return Persona con los datos completos
     */
    public Persona buscarPersona(Persona persona){
        SQLiteDatabase bd=baseDatos.getReadableDatabase();
        Cursor c=bd.rawQuery(String.format("SELECT %s,%s,%s,%s,%s,%s,%s,%s,%s,%s FROM %s WHERE %s='%s'",
                Model.ColPersona.ID_PERSONA, Model.ColPersona.RUT,Model.ColPersona.NOMBRE, Model.ColPersona.SEG_NOMBRE,
                Model.ColPersona.APE_PATERNO, Model.ColPersona.APE_MATERNO, Model.ColPersona.TELEFONO,
                Model.ColPersona.EMAIL, Model.ColPersona.ESTADO, Model.ColPersona.ID_TIPO_PERSONA,
                Model.Tablas.PERSONA,Model.ColPersona.ID_TIPO_PERSONA,persona.getIdPersona()),null);
        if (c.moveToNext()){
            TipoPersonaDAL tipoPersonaDAL=new TipoPersonaDAL(con);
            persona.setRut(c.getString(1));
            persona.setNombre(c.getString(2));
            persona.setSegundoNom(c.getString(3));
            persona.setApellidoPa(c.getString(4));
            persona.setApellidoMa(c.getString(5));
            persona.setTelefono(Integer.parseInt(c.getString(6)));
            persona.setCorreo(c.getString(7));
            persona.setEstado(Integer.parseInt(c.getString(8)));
            persona.setTipoPersona(tipoPersonaDAL.buscarTipoPersona(new TipoPersona(Integer.parseInt(c.getString(9)))));
        }else{
            persona=null;
        }
        bd.close();
        return persona;
    }

    /**
     * Metodo para buscar persona por medio del email en la base de datos local
     * @param persona Persona con el email a buscar
     * @return Persona con los datos encontrados
     */
    public Persona buscarPersonaPorEmail(Persona persona){
        SQLiteDatabase bd=baseDatos.getReadableDatabase();
        Cursor c=bd.rawQuery(String.format("SELECT %s,%s,%s,%s,%s,%s,%s,%s,%s,%s FROM %s WHERE %s='%s'",
                Model.ColPersona.ID_PERSONA, Model.ColPersona.RUT,Model.ColPersona.NOMBRE, Model.ColPersona.SEG_NOMBRE,
                Model.ColPersona.APE_PATERNO, Model.ColPersona.APE_MATERNO, Model.ColPersona.TELEFONO,
                Model.ColPersona.EMAIL, Model.ColPersona.ESTADO, Model.ColPersona.ID_TIPO_PERSONA ,Model.Tablas.PERSONA,Model.ColPersona.EMAIL,persona.getCorreo()),null);
        if (c.moveToNext()){
            TipoPersonaDAL tipoPersonaDAL=new TipoPersonaDAL(con);
            persona.setIdPersona(c.getInt(0));
            persona.setRut(c.getString(1));
            persona.setNombre(c.getString(2));
            persona.setSegundoNom(c.getString(3));
            persona.setApellidoPa(c.getString(4));
            persona.setApellidoMa(c.getString(5));
            persona.setTelefono(Integer.parseInt(c.getString(6)));
            persona.setCorreo(c.getString(7));
            persona.setEstado(Integer.parseInt(c.getString(8)));
            persona.setTipoPersona(tipoPersonaDAL.buscarTipoPersona(new TipoPersona(Integer.parseInt(c.getString(9)))));
        }else{
            persona=null;
        }
        bd.close();
        return persona;
    }



}
