package cl.inacap.pnk.io;

import android.content.Context;

/**
 * Clase para la conexion de la base de datos
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public final class ConexionBD {

    //Atributos de la clase
    public static BaseDatos baseDatos;
    private static ConexionBD instancia=new ConexionBD();

    /**
     * Constructor vacio
     */
    private ConexionBD() {

    }

    /**
     * Metodo para la conexion a la base de datos
     * @param context Contexto de la aplicación
     * @return Instancia de la base de datos
     */
    public static ConexionBD obtenerInstancia(Context context){
        if (baseDatos ==null){
            baseDatos= new BaseDatos(context);
        }
        return instancia;
    }
}
