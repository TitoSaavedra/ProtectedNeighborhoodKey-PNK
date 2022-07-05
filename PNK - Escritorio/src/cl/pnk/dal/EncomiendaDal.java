/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dal;

import cl.pnk.dto.Encomienda;
import cl.pnk.utils.DBUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author TitoS
 */
public class EncomiendaDal {

    private DBUtils dbutils = new DBUtils();

    public void ingresarEncomienda(Encomienda encomienda) {
        try {
            dbutils.conectar();
            String sql = "INSERT INTO encomienda (ID_ENCOMIENDA,NOMBRE,DESCRIPCION,ESTADO_ENCOMIENDA)"
                    + " VALUES(?,?,?,?)";
            PreparedStatement st = dbutils.getConexion().prepareStatement(sql);
            st.setInt(1, 0);
            st.setString(2, encomienda.getNombre());
            st.setString(3, encomienda.getDescripcion());
            st.setInt(4, encomienda.getEstado());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            dbutils.desconectar();
        }
    }

    public Encomienda obtenerUltimaEncomienda() {
        // SELECT * FROM persona ORDER BY ID_PERSONA DESC LIMIT 1 
        Encomienda encomienda = new Encomienda();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_ENCOMIENDA,NOMBRE,DESCRIPCION,ESTADO_ENCOMIENDA FROM encomienda ORDER BY ID_ENCOMIENDA DESC LIMIT 1;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                encomienda.setIdEncomienda(rs.getInt(1));
                encomienda.setNombre(rs.getString(2));
                encomienda.setDescripcion(rs.getString(3));
                encomienda.setEstado(rs.getInt(4));
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return encomienda;
    }
    
        public Encomienda obtenerEncomienda(int idEncomienda) {
        // SELECT * FROM persona ORDER BY ID_PERSONA DESC LIMIT 1 
        Encomienda encomienda = new Encomienda();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_ENCOMIENDA,NOMBRE,DESCRIPCION,ESTADO_ENCOMIENDA FROM encomienda WHERE ID_ENCOMIENDA =?;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            sq.setInt(1, idEncomienda);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                encomienda.setIdEncomienda(rs.getInt(1));
                encomienda.setNombre(rs.getString(2));
                encomienda.setDescripcion(rs.getString(3));
                encomienda.setEstado(rs.getInt(4));
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return encomienda;
    }
}
