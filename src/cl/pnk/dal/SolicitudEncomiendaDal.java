/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dal;

import cl.pnk.dto.Encomienda;
import cl.pnk.dto.SolicitudEncomienda;
import cl.pnk.utils.DBUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author TitoS
 */
public class SolicitudEncomiendaDal {

    private DBUtils dbutils = new DBUtils();

    public void ingresarSolicitudEncomienda(SolicitudEncomienda solicitudEncomienda) {
        try {
            dbutils.conectar();
            String sql = "INSERT INTO solicitud_encomienda (ID_SOLICITUD_ENCOMIENDA,ID_CUENTA,ESTADO_SOLICITUD_ENCOMIENDA,ID_ENCOMIENDA,FECHA_ENTREGA,HORA_ENTREGA)"
                    + " VALUES(?,?,?,?,?,?)";
            PreparedStatement st = dbutils.getConexion().prepareStatement(sql);
            st.setInt(1, 0);
            st.setInt(2, solicitudEncomienda.getIdCuenta());
            st.setInt(3, 3);
            st.setInt(4, solicitudEncomienda.getIdEncomienda());
            st.setString(5, solicitudEncomienda.getFechaEntrega());
            st.setString(6, solicitudEncomienda.getHoraEntrega());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            dbutils.desconectar();
        }
    }

    public SolicitudEncomienda obtenerSolicitudEncomienda(int idSolicitudEncomienda, int estadoSolicitudEncomienda) {
        SolicitudEncomienda solicitudEncomienda = new SolicitudEncomienda();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_SOLICITUD_ENCOMIENDA,ID_CUENTA,ESTADO_SOLICITUD_ENCOMIENDA,ID_ENCOMIENDA,FECHA_ENTREGA,HORA_ENTREGA FROM solicitud_encomienda WHERE ID_SOLICITUD_ENCOMIENDA = ? AND ESTADO_SOLICITUD_ENCOMIENDA = ?;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            sq.setInt(1, idSolicitudEncomienda);
            sq.setInt(2, estadoSolicitudEncomienda);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                solicitudEncomienda.setIdSolicitudEncomienda(rs.getInt(1));
                solicitudEncomienda.setIdCuenta(rs.getInt(2));
                solicitudEncomienda.setEstadoSolicitudEncomienda(rs.getInt(3));
                solicitudEncomienda.setIdEncomienda(rs.getInt(4));
                solicitudEncomienda.setFechaEntrega(rs.getString(5));
                solicitudEncomienda.setHoraEntrega(rs.getString(6));
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return solicitudEncomienda;
    }

    public void entregarEncomienda(int idSolicitudEncomienda) {
        try {
            dbutils.conectar();
            String sql = "UPDATE solicitud_encomienda SET ESTADO_SOLICITUD_ENCOMIENDA = '1' WHERE solicitud_encomienda.ID_SOLICITUD_ENCOMIENDA = ?";
            PreparedStatement st = dbutils.getConexion().prepareStatement(sql);
            st.setInt(1, idSolicitudEncomienda);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            dbutils.desconectar();
        }
    }
}
