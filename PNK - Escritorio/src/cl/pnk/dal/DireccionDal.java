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
        try {
            dbutils.conectar();
            String sql = "INSERT INTO direccion(ID_DIRECCION,PISO,BLOCK,NUMERO)"
                    + " VALUES(?,?,?,?)";
            PreparedStatement st = dbutils.getConexion().prepareStatement(sql);
            st.setInt(1, 0);
            st.setString(2, direccion.getPiso());
            st.setString(3, direccion.getBlock());
            st.setString(4, direccion.getNumero());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            dbutils.desconectar();
        }
    }
    
        public void modificarDireccion(Direccion direccion) {
        try {
            dbutils.conectar();
            String sql = "UPDATE direccion SET PISO = ? , BLOCK = ? , NUMERO = ? WHERE direccion.ID_DIRECCION = ?";
            PreparedStatement st = dbutils.getConexion().prepareStatement(sql);
            st.setString(1, direccion.getPiso());
            st.setString(2, direccion.getBlock());
            st.setString(3, direccion.getNumero());
            st.setInt(4, direccion.getIdDireccion());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
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
            String sql = "SELECT ID_DIRECCION,PISO,BLOCK,NUMERO FROM direccion;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                Direccion direccion = new Direccion();
                direccion.setIdDireccion(rs.getInt(1));
                direccion.setPiso(rs.getString(2));
                direccion.setBlock(rs.getString(3));
                direccion.setNumero(rs.getString(4));
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

    public Direccion obtenerDireccion(int idDireccion) {
        Direccion direccion = new Direccion();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_DIRECCION,PISO,BLOCK,NUMERO FROM direccion WHERE ID_DIRECCION = ?;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            sq.setInt(1, idDireccion);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                direccion.setIdDireccion(rs.getInt(1));
                direccion.setPiso(rs.getString(2));
                direccion.setBlock(rs.getString(3));
                direccion.setNumero(rs.getString(4));
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return direccion;
    }

    public Direccion obtenerUltimaDireccion() {
        // SELECT * FROM persona ORDER BY ID_PERSONA DESC LIMIT 1 
        Direccion direccion = new Direccion();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_DIRECCION,PISO,BLOCK,NUMERO FROM direccion ORDER BY ID_DIRECCION DESC LIMIT 1;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                direccion.setIdDireccion(rs.getInt(1));
                direccion.setPiso(rs.getString(2));
                direccion.setBlock(rs.getString(3));
                direccion.setNumero(rs.getString(4));
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
