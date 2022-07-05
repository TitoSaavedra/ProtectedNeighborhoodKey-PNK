/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dal;

import cl.pnk.dto.DireccionPersona;
import cl.pnk.utils.DBUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TitoS
 */
public class DireccionPersonaDal {

    private DBUtils dbutils = new DBUtils();

    public void ingresarDireccionPersona(DireccionPersona direccionPersona) {
        dbutils.conectar();
        try {
            String sql = "INSERT INTO direccion_persona(direccion_ID_DIRECCION,persona_ID_PERSONA)"
                    + " VALUES(?,?)";
            PreparedStatement st = dbutils.getConexion().prepareStatement(sql);
            st.setInt(1, direccionPersona.getIdDireccion());
            st.setInt(2, direccionPersona.getIdPersona());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            dbutils.desconectar();
        }
    }

    public List<DireccionPersona> obtenerDireccionPersonas() {
        List<DireccionPersona> listaDireccionPersona = new ArrayList<>();
        try {
            dbutils.conectar();
            String sql = "SELECT direccion_ID_DIRECCION,persona_ID_PERSONA FROM direccion_persona;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                DireccionPersona direccionPersona = new DireccionPersona();
                direccionPersona.setIdDireccion(rs.getInt(1));
                direccionPersona.setIdPersona(rs.getInt(2));
                listaDireccionPersona.add(direccionPersona);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            dbutils.desconectar();
        }
        return listaDireccionPersona;
    }

    public DireccionPersona obtenerDireccionPersona(int idPersona) {
        DireccionPersona direccionPersona = new DireccionPersona();
        try {
            dbutils.conectar();
            String sql = "SELECT direccion_ID_DIRECCION,persona_ID_PERSONA FROM direccion_persona WHERE persona_ID_PERSONA = ?;";
            PreparedStatement st = dbutils.getConexion().prepareStatement(sql);
            st.setInt(1, idPersona);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                direccionPersona.setIdDireccion(rs.getInt(1));
                direccionPersona.setIdPersona(rs.getInt(2));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            dbutils.desconectar();
        }
        return direccionPersona;
    }
}
