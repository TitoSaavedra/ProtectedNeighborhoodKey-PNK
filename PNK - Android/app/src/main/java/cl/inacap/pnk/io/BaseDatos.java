package cl.inacap.pnk.io;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import androidx.annotation.Nullable;

/**
 * Clase para la creación de la base de datos
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class BaseDatos extends SQLiteOpenHelper {
    //Atributos de la clase
    public static final String NOMBRE_BASE_DATOS="pnk";
    public static final int VERSION_ACTUAL=1;
    private final Context context;

    /**
     * Constructor de la clase
     * @param context contexto de la aplicación
     */
    public BaseDatos(@Nullable Context context) {
        super(context, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
        this.context=context;
    }

    /**
     * Metodo para la creacion de la base de datos
     * @param db instacia de la base de datos
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    /**
     * Interface con las referencias a las tablas que se usan para las FK
     */
    interface Referencias{
        String ID_CUENTA=String.format("REFERENCES %s (%s)",Model.Tablas.CUENTA,Model.ColCuenta.ID_CUENTA);
        String ID_PERSONA=String.format("REFERENCES %s (%s)",Model.Tablas.PERSONA,Model.ColPersona.ID_PERSONA);
        String UID=String.format("REFERENCES %s (%s)",Model.Tablas.TARJETA_NFC,Model.ColTarjeta_NFC.UID);
        String ID_ENCOMIENDA=String.format("REFERENCES %s (%s)",Model.Tablas.ENCOMIENDA,Model.ColEncomienda.ID_ENCOMIENDA);
        String ID_TIPO_PERSONA=String.format("REFERENCES %s (%s)",Model.Tablas.TIPO_PERSONA,Model.ColTipo_Persona.ID_TIPO_PERSONA);
    }

    /**
     * Metodo que crea la base de datos con sus tablas y referencias
     * @param db instancia de la base de datos
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabla Tarjeta NFC
        db.execSQL(String.format("CREATE TABLE %s (%s TEXT PRIMARY KEY ,%s INTEGER,%s DATE)",
                Model.Tablas.TARJETA_NFC, Model.ColTarjeta_NFC.UID, Model.ColTarjeta_NFC.ESTADO, Model.ColTarjeta_NFC.FECHA_REGISTRO));

        //Tabla Tipo persona
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY,%s TEXT,%s TEXT)",
                Model.Tablas.TIPO_PERSONA, Model.ColTipo_Persona.ID_TIPO_PERSONA, Model.ColTipo_Persona.NOMBRE, Model.ColTipo_Persona.DESCRIPCION));

        //Tabla Encomienda
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,%s TEXT,%s INTEGER)",
                Model.Tablas.ENCOMIENDA, Model.ColEncomienda.ID_ENCOMIENDA, Model.ColEncomienda.NOMBRE, Model.ColEncomienda.DESCRIPCION, Model.ColEncomienda.ESTADO));

        //Tabla Persona
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s INTEGER,%s TEXT,%s INTEGER, %s INTEGER," +
                        "FOREIGN KEY (%s) %s)",
                Model.Tablas.PERSONA, Model.ColPersona.ID_PERSONA, Model.ColPersona.RUT, Model.ColPersona.NOMBRE, Model.ColPersona.SEG_NOMBRE, Model.ColPersona.APE_PATERNO,
                Model.ColPersona.APE_MATERNO, Model.ColPersona.TELEFONO, Model.ColPersona.EMAIL, Model.ColPersona.ESTADO, Model.ColPersona.ID_TIPO_PERSONA,
                Model.ColPersona.ID_TIPO_PERSONA,Referencias.ID_TIPO_PERSONA));

        //Tabla Cuenta
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,%s INTEGER,%s TEXT,%s INTEGER,%s TEXT" +
                        ",FOREIGN KEY (%s) %s" +
                        ",FOREIGN KEY (%s) %s)"
                ,Model.Tablas.CUENTA, Model.ColCuenta.ID_CUENTA, Model.ColCuenta.CLAVE, Model.ColCuenta.ESTADO, Model.ColCuenta.FOTO,
                Model.ColCuenta.ID_PERSONA, Model.ColCuenta.UID,
                Model.ColCuenta.ID_PERSONA,Referencias.ID_PERSONA,
                Model.ColCuenta.UID,Referencias.UID));

        //Tabla Direccion
        db.execSQL((String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT,%s INTEGER,%s INTEGER, %s INTEGER, %s INTEGER," +
                        "FOREIGN KEY (%s) %s)",
                Model.Tablas.DIRECCION, Model.ColDireccion.ID_DIRECCION, Model.ColDireccion.PISO, Model.ColDireccion.BLOCK, Model.ColDireccion.NUMERO, Model.ColDireccion.ID_PERSONA,
                Model.ColDireccion.ID_PERSONA,Referencias.ID_PERSONA)));


        //Tabla Acceso
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT,%s DATE,%s TIME,%s INTEGER,%s INTEGER,%s INTEGER," +
                        "FOREIGN KEY (%s) %s)"
                ,Model.Tablas.ACCESO, Model.ColAcceso.ID_ACCESO,Model.ColAcceso.FECHA_ACCESO, Model.ColAcceso.HORA_ACCESO,
                Model.ColAcceso.TIPO_ACCESO, Model.ColAcceso.ESTADO, Model.ColAcceso.ID_CUENTA, Model.ColAcceso.ID_CUENTA,Referencias.ID_CUENTA));

        //Tabla Solicitud_encomienda
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER ,%s INTEGER,%s INTEGER,%s INTEGER,%s DATE,%s TIME," +
                        "PRIMARY KEY(%s,%s,%s)," +
                        "FOREIGN KEY (%s) %s," +
                        "FOREIGN KEY (%s) %s)",
                Model.Tablas.SOLICITUD_ENCOMIENDA, Model.ColSolicitud_encomienda.ID_SOLICITUD_ENCOMIENDA,Model.ColSolicitud_encomienda.ID_CUENTA,
                Model.ColSolicitud_encomienda.ESTADO, Model.ColSolicitud_encomienda.ID_ENCOMIENDA, Model.ColSolicitud_encomienda.FECHA_ENTREGA, Model.ColSolicitud_encomienda.HORA_ENTREGA,
                Model.ColSolicitud_encomienda.ID_SOLICITUD_ENCOMIENDA,Model.ColSolicitud_encomienda.ID_ENCOMIENDA, Model.ColSolicitud_encomienda.ID_CUENTA,
                Model.ColSolicitud_encomienda.ID_ENCOMIENDA,Referencias.ID_ENCOMIENDA,
                Model.ColSolicitud_encomienda.ID_CUENTA,Referencias.ID_CUENTA));

        //Tabla Solicitud visita
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER,%s INTEGER,%s DATE,%s INTEGER,%s TIME," +
                "PRIMARY KEY(%s,%s,%s)," +
                "FOREIGN KEY (%s) %s," +
                "FOREIGN KEY (%s) %s)",
                Model.Tablas.SOLICITUD_VISITA, Model.ColSolicitud_Visita.ID_SOLICITUD_VISITA, Model.ColSolicitud_Visita.ID_CUENTA,
                Model.ColSolicitud_Visita.FECHA_VISITA, Model.ColSolicitud_Visita.ID_PERSONA,Model.ColSolicitud_Visita.HORA_VISITA,
                Model.ColSolicitud_Visita.ID_SOLICITUD_VISITA,Model.ColSolicitud_Visita.ID_PERSONA, Model.ColSolicitud_Visita.ID_CUENTA,
                Model.ColSolicitud_Visita.ID_PERSONA,Referencias.ID_PERSONA,
                Model.ColSolicitud_Visita.ID_CUENTA, Referencias.ID_CUENTA));

    }

    /**
     * Metodo para actualizar la base de datos si es que cambia la version de esta
     * @param db Instancia de la base de datos
     * @param oldVersion Version anterior
     * @param newVersion Version nueva
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Eliminar las tablas
        db.execSQL("DROP TABLE IF EXISTS "+ Model.Tablas.TARJETA_NFC);
        db.execSQL("DROP TABLE IF EXISTS "+ Model.Tablas.TIPO_PERSONA);
        db.execSQL("DROP TABLE IF EXISTS "+ Model.Tablas.ENCOMIENDA);
        db.execSQL("DROP TABLE IF EXISTS "+ Model.Tablas.PERSONA);
        db.execSQL("DROP TABLE IF EXISTS "+ Model.Tablas.CUENTA);
        db.execSQL("DROP TABLE IF EXISTS "+ Model.Tablas.DIRECCION);
        db.execSQL("DROP TABLE IF EXISTS "+ Model.Tablas.ACCESO);
        db.execSQL("DROP TABLE IF EXISTS "+ Model.Tablas.SOLICITUD_ENCOMIENDA);
        db.execSQL("DROP TABLE IF EXISTS "+ Model.Tablas.SOLICITUD_VISITA);
        onCreate(db);
    }
}
