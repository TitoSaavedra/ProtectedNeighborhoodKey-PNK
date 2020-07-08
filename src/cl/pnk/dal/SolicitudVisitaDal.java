package cl.pnk.dal;


import cl.pnk.dto.SolicitudVisita;
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
}
