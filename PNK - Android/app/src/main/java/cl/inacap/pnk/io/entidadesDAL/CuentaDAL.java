package cl.inacap.pnk.io.entidadesDAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.Toast;

import cl.inacap.pnk.io.BaseDatos;
import cl.inacap.pnk.io.ConexionBD;
import cl.inacap.pnk.io.Model;
import cl.inacap.pnk.io.entidadesDAO.Cuenta;
import cl.inacap.pnk.io.entidadesDAO.Persona;
import cl.inacap.pnk.io.entidadesDAO.TarjetaNFC;

/**
 * Clase DAL de la tabla cuenta
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class CuentaDAL {
    //Atributo con la base de datos
    private  static BaseDatos baseDatos;
    private Context con;

    /**
     * Constructor de la clase
     * @param context Contexto de la aplicacion
     */
    public CuentaDAL(Context context){
        ConexionBD conexionBD=ConexionBD.obtenerInstancia(context);
        baseDatos=ConexionBD.baseDatos;
    }

    /**
     * Metodo para insertar una cuenta en la base de datos local
     * @param cuenta cuenta con los datos para ingresarlos
     */
    public void insertarCuenta(Cuenta cuenta){
        SQLiteDatabase bd=baseDatos.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(Model.ColCuenta.CLAVE,cuenta.getClave());
        values.put(Model.ColCuenta.ESTADO,cuenta.getEstado());
        values.put(Model.ColCuenta.FOTO,cuenta.getFoto().toString());
        values.put(Model.ColCuenta.ID_PERSONA,cuenta.getPersona().getIdPersona());
        values.put(Model.ColCuenta.UID,cuenta.getTarjetaNFC().getUID());
        bd.insert(Model.Tablas.CUENTA,null,values);
        bd.close();
    }

    /**
     * Metodo para buscar la cuenta en la base de datos local
     * @param cuenta Cuenta con datos para realizar la busqueda
     * @return Cuenta con los datos encontrados
     */
    public Cuenta buscarCuenta(Cuenta cuenta){
        SQLiteDatabase bd=baseDatos.getReadableDatabase();
        Cursor c=bd.rawQuery(String.format("SELECT %s,%s,%s,%s,%s,%s FROM %s WHERE %s='$%'",
                Model.ColCuenta.ID_CUENTA, Model.ColCuenta.CLAVE, Model.ColCuenta.ESTADO, Model.ColCuenta.FOTO,
                Model.ColCuenta.ID_PERSONA, Model.ColCuenta.UID,
                Model.Tablas.CUENTA, Model.ColCuenta.ID_CUENTA,cuenta.getIdCuenta()),null);
        if(c.moveToNext()){
            PersonaDAL personaDAL=new PersonaDAL(con);
            cuenta.setClave(c.getString(1));
            cuenta.setEstado(Integer.parseInt(c.getString(2)));
            byte[] bytes= Base64.decode(c.getString(3),Base64.DEFAULT);
            Bitmap imagen= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            cuenta.setFoto(imagen);
            cuenta.setPersona(personaDAL.buscarPersona(new Persona(Integer.parseInt(c.getString(4)))));
            cuenta.setClave(c.getString(1));
        }else{
            cuenta=null;
        }
        return cuenta;
    }

    /**
     * Metodo para verificar si la cuenta coincide con los datos enviados
     * @param cuenta Cuenta con los datos enviados
     * @return Cuenta con los datos completos
     */
    public Cuenta verificarCuenta(Cuenta cuenta){
        SQLiteDatabase bd=baseDatos.getReadableDatabase();
        Cursor c=bd.rawQuery(String.format("SELECT %s,%s,%s,%s,%s,%s FROM %s WHERE %s='%s' AND %s='%s'",
                Model.ColCuenta.ID_CUENTA, Model.ColCuenta.CLAVE, Model.ColCuenta.ESTADO, Model.ColCuenta.FOTO,
                Model.ColCuenta.ID_PERSONA, Model.ColCuenta.UID,
                Model.Tablas.CUENTA, Model.ColCuenta.ID_PERSONA,cuenta.getPersona().getIdPersona(),
                Model.ColCuenta.CLAVE, cuenta.getClave()),null);
        if(c.moveToNext()){
            PersonaDAL personaDAL=new PersonaDAL(con);
            TarjetaNFC_DAL tarjetaNFC_dal=new TarjetaNFC_DAL(con);
            cuenta.setIdCuenta(c.getInt(0));
            cuenta.setClave(c.getString(1));
            cuenta.setEstado(Integer.parseInt(c.getString(2)));
            byte[] bytes= Base64.decode(c.getString(3),Base64.DEFAULT);
            Bitmap imagen= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            cuenta.setFoto(imagen);
            cuenta.setPersona(personaDAL.buscarPersona(new Persona(Integer.parseInt(c.getString(4)))));
            cuenta.setTarjetaNFC(tarjetaNFC_dal.buscarTarjetaNFC(new TarjetaNFC(c.getString(5))));
        }else{
            cuenta=null;
        }
        return cuenta;
    }
}
