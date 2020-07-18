package cl.pnk.dal;

import cl.pnk.dto.SolicitudVisita;
import cl.pnk.utils.ConnDBAmazon;
import cl.pnk.utils.DBUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TitoS
 */
public class SolicitudVisitaDal {

    private DBUtils dbutils = new DBUtils();
    private ConnDBAmazon connDBAmazon = new ConnDBAmazon();

    /**
     * @param estadoSolicitud 1=Aceptado 2=Negado 3=En proceso
     * @return listaSolicitudesVisita
     */
    public List<SolicitudVisita> obtenerSolicitudesVisita(String estadoSolicitud) {
        List<SolicitudVisita> listaSolicitudesVisita = new ArrayList<>();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_SOLICITUD_VISITA,ID_CUENTA,ESTADO_SOLICITUD_VISITA,FECHA_VISITA,ID_PERSONA,HORA_VISITA FROM solicitud_visita WHERE solicitud_visita.ESTADO_SOLICITUD_VISITA =? ";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            sq.setString(1, estadoSolicitud);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                SolicitudVisita solicitudVisita = new SolicitudVisita();
                solicitudVisita.setIdSolicitud(rs.getInt(1));
                solicitudVisita.setIdCuentaResidente(rs.getInt(2));
                solicitudVisita.setEstadoSolicitud(rs.getInt(3));
                solicitudVisita.setFechaVisita(rs.getString(4));
                solicitudVisita.setIdPersonaVisita(rs.getInt(5));
                solicitudVisita.setHoraVisita(rs.getString(6));
                listaSolicitudesVisita.add(solicitudVisita);
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return listaSolicitudesVisita;
    }

    public void ingresarSolicitudVisita(SolicitudVisita solicitudVisita) {
        connDBAmazon.conectar();
        try {
            String sql = "INSERT INTO solicitud_visita (ID_SOLICITUD_VISITA, ID_CUENTA, ESTADO_SOLICITUD_VISITA, FECHA_VISITA, ID_PERSONA, HORA_VISITA)"
                    + " VALUES(?,?,?,?,?,?)";
            PreparedStatement st = connDBAmazon.getConexion().prepareStatement(sql);
            st.setInt(1, 0);
            st.setInt(2, solicitudVisita.getIdCuentaResidente());
            st.setInt(3, 1);
            st.setString(4, solicitudVisita.getFechaVisita());
            st.setInt(5, solicitudVisita.getIdPersonaVisita());
            st.setString(6, solicitudVisita.getHoraVisita());
            st.executeUpdate();
        } catch (Exception e) {
        } finally {
            connDBAmazon.desconectar();
        }
    }

    public SolicitudVisita obtenerSolicitudVisita(int idSolicitud) {
        SolicitudVisita solicitudVisita = new SolicitudVisita();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_SOLICITUD_VISITA,ID_CUENTA,ESTADO_SOLICITUD_VISITA,FECHA_VISITA,ID_PERSONA,HORA_VISITA FROM solicitud_visita WHERE solicitud_visita.ID_SOLICITUD_VISITA =? ";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            sq.setInt(1, idSolicitud);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                solicitudVisita.setIdSolicitud(rs.getInt(1));
                solicitudVisita.setIdCuentaResidente(rs.getInt(2));
                solicitudVisita.setEstadoSolicitud(rs.getInt(3));
                solicitudVisita.setFechaVisita(rs.getString(4));
                solicitudVisita.setIdPersonaVisita(rs.getInt(5));
                solicitudVisita.setHoraVisita(rs.getString(6));
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return solicitudVisita;
    }

    public void modificarSolicitudVisita(int idSolicitud, int estado) {
        try {
            dbutils.conectar();
            String sql = "UPDATE solicitud_visita SET ESTADO_SOLICITUD_VISITA = ? WHERE solicitud_visita.ID_SOLICITUD_VISITA = ?;";
            PreparedStatement st = dbutils.getConexion().prepareStatement(sql);
            st.setInt(1, estado);
            st.setInt(2, idSolicitud);
            System.out.println(st);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
        } finally {
            //4 desconectar DB
            dbutils.desconectar();
        }
    }
}
