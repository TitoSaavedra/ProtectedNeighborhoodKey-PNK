/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase de conexion de la bd
 *
 * @author TitoS
 */
public class DBUtils {

    //Atributos
    private final String usuario = "root";
    // private final String password = "PNK_server1";
    // private final String servidor = "34.232.87.118";
    private final String password = "";
    private final String servidor = "localhost";
    private final String baseDatos = "pnk_rial";
    private Connection conexion;

    /**
     * Conecta con la base de datos
     *
     * @return el resultado de la conexion, falso o verdadero
     */
    public boolean conectar() {
        boolean resultado = false;
        try {
            //1 agregar class Driver del motor
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2 Utilizar DriverManager
            //api del conexion:motor://servidor:puerto/basedatos
            String url = "jdbc:mysql://" + servidor + ":3306/" + baseDatos;
            this.conexion = DriverManager.getConnection(url, usuario, password);
            return resultado = true;
        } catch (Exception e) {
            return resultado = false;
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    /**
     * Metodo para cerrar la conexion
     */
    public void desconectar() {
        try {
            this.conexion.close();
        } catch (Exception e) {
        }

    }
}
