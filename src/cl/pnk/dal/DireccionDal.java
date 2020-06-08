/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dal;

import cl.pnk.dto.Direccion;
import cl.pnk.utils.DBUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que conecta la clase Direccion con la base de datos
 *
 * @author TitoS
 */
public class DireccionDal {

    private DBUtils dbutils = new DBUtils();

    /**
     * Metodo que ingresa una dirección
     *
     * @param direccion Objeto clase direccion que se desea ingresar
     */
    public void ingresarDireccion(Direccion direccion) {
        dbutils.conectar();
        try {
            String sql = "INSERT INTO direccion(ID_DIRECCION,PISO,BLOCK,NUMERO,ID_PERSONA)"
                    + " VALUES(?,?,?,?,?)";
            PreparedStatement st = dbutils.getConexion().prepareStatement(sql);
            st.setInt(1, 0);
            st.setString(2, direccion.getPiso());
            st.setString(3, direccion.getBlock());
            st.setString(4, direccion.getNumero());
            st.setInt(5, direccion.getPersona().getIdPersona());
            st.executeUpdate();
        } catch (Exception e) {
        } finally {

            dbutils.desconectar();
        }
    }

    /**
     * Metodo que devuelve todas las direcciones de la bd
     *
     * @return listaDirecciones coleccion de direcciones
     */
    public List<Direccion> obtenerDirecciones() {
        List<Direccion> listaDirecciones = new ArrayList<>();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_DIRECCION,PISO,BLOCK,NUMERO,ID_PERSONA FROM direccion;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                Direccion direccion = new Direccion();
                direccion.setIdDireccion(rs.getInt(1));
                direccion.setPiso(rs.getString(2));
                direccion.setBlock(rs.getString(3));
                direccion.setNumero(rs.getString(4));
                direccion.setPersona(new PersonaDal().obtenerPersonaId(rs.getInt(5)));
                listaDirecciones.add(direccion);
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return listaDirecciones;
    }

    /**
     * Metodo que devuelve una direccion filtrada por rut
     *
     * @param rut cadena de texto que es un rut
     * @return direccion de la bd filtrada
     */
    public Direccion obtenerDireccionRut(String rut) {
        Direccion direccion = new Direccion();
        try {
            this.dbutils.conectar();
            String sql = "SELECT * FROM direccion, persona WHERE persona.ESTADO=1 AND persona.ID_TIPO_PERSONA=2 AND direccion.ID_PERSONA = persona.ID_PERSONA AND persona.RUT='" + rut + "' LIMIT 1;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                direccion.setIdDireccion(rs.getInt(1));
                direccion.setPiso(rs.getString(2));
                direccion.setBlock(rs.getString(3));
                direccion.setNumero(rs.getString(4));
                direccion.setPersona(new PersonaDal().obtenerPersonaId(rs.getInt(5)));
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return direccion;
    }
}
