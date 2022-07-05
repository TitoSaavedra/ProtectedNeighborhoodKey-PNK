package cl.inacap.pnk.io.entidadesDAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import cl.inacap.pnk.io.BaseDatos;
import cl.inacap.pnk.io.ConexionBD;
import cl.inacap.pnk.io.Model;
import cl.inacap.pnk.io.entidadesDAO.TarjetaNFC;

/**
 * Clase DAL de la tabla TarjetaNFC
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class TarjetaNFC_DAL {
    //Atributo con la base de datos
    private  static BaseDatos baseDatos;

    /**
     * Constructor de la clase
     * @param context Contexto de la aplicacion
     */
    public TarjetaNFC_DAL(Context context){
        ConexionBD conexionBD=ConexionBD.obtenerInstancia(context);
        baseDatos=ConexionBD.baseDatos;
    }

    /**
     * Metodo para insertar tarjetas nfc en la base local
     * @param tarjetaNFC Datos a insertar
     */
    public void insertTarjetaNFC(TarjetaNFC tarjetaNFC){
        SQLiteDatabase bd=baseDatos.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(Model.ColTarjeta_NFC.UID,tarjetaNFC.getUID());
        values.put(Model.ColTarjeta_NFC.ESTADO,tarjetaNFC.getEstado());
        values.put(Model.ColTarjeta_NFC.FECHA_REGISTRO,String.valueOf(tarjetaNFC.getFechaRegistro()));
        bd.insert(Model.Tablas.TARJETA_NFC,null,values);
        bd.close();
    }

    /**
     * Metodo para buscar tarjetas nfc en la base local segun el UID
     * @param tarjetaNFC Tarjeta nfc con el UID a buscar
     * @return Tarjeta NFC con todos los datos
     */
    public TarjetaNFC buscarTarjetaNFC(TarjetaNFC tarjetaNFC){
        SQLiteDatabase bd=baseDatos.getReadableDatabase();
        Cursor c=bd.rawQuery(String.format("SELECT %s,%s,%s FROM %s WHERE %s='%s'",
                Model.ColTarjeta_NFC.UID, Model.ColTarjeta_NFC.ESTADO, Model.ColTarjeta_NFC.FECHA_REGISTRO,
                Model.Tablas.TARJETA_NFC, Model.ColTarjeta_NFC.UID,tarjetaNFC.getUID()),null);
        if (c.moveToNext()){
            SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            tarjetaNFC.setEstado(c.getInt(1));
            try {
                tarjetaNFC.setFechaRegistro(dateFormat.parse(c.getString(2)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            tarjetaNFC=null;
        }
        return tarjetaNFC;
    }
}
