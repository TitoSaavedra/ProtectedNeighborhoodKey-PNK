/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dal;

import cl.pnk.dto.TablaSolicitudEncomienda;
import cl.pnk.utils.DBUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author TitoS
 */
public class TablaSolicitudEncomiendaDal {
    
    private DBUtils dbutils = new DBUtils();
 //SELECT ID_SOLICITUD_ENCOMIENDA,CONCAT(persona.NOMBRE," ",persona.APE_PATERNO),FECHA_ENTREGA,HORA_ENTREGA FROM solicitud_encomienda,cuenta,persona WHERE cuenta.ID_PERSONA = persona.ID_PERSONA AND solicitud_encomienda.ID_CUENTA=cuenta.ID_CUENTA 
    
       public ObservableList<TablaSolicitudEncomienda> obtenerTablaResidentes(int estadoEncomienda) {
        ObservableList<TablaSolicitudEncomienda> listaSolicitudEncomienda = FXCollections.observableArrayList();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_SOLICITUD_ENCOMIENDA,CONCAT(persona.NOMBRE,' ',persona.APE_PATERNO),FECHA_ENTREGA,HORA_ENTREGA FROM solicitud_encomienda,cuenta,persona WHERE cuenta.ID_PERSONA = persona.ID_PERSONA AND solicitud_encomienda.ID_CUENTA=cuenta.ID_CUENTA AND ESTADO_SOLICITUD_ENCOMIENDA = ?;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            sq.setInt(1, estadoEncomienda);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                TablaSolicitudEncomienda tablaSolicitudEncomienda = new TablaSolicitudEncomienda();
                tablaSolicitudEncomienda.setIdSolicitudEncomienda(rs.getInt(1));
                tablaSolicitudEncomienda.setNombreApPaternoResidente(rs.getString(2));
                tablaSolicitudEncomienda.setFechaEncomienda(rs.getString(3));
                tablaSolicitudEncomienda.setHoraEncomienda(rs.getString(4));
                listaSolicitudEncomienda.add(tablaSolicitudEncomienda);
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return listaSolicitudEncomienda;
    }
       
             public ObservableList<TablaSolicitudEncomienda> obtenerTablaResidentesFiltrada(int estadoEncomienda,String filtro) {
        ObservableList<TablaSolicitudEncomienda> listaSolicitudEncomienda = FXCollections.observableArrayList();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_SOLICITUD_ENCOMIENDA,CONCAT(persona.NOMBRE,' ',persona.APE_PATERNO),FECHA_ENTREGA,HORA_ENTREGA FROM solicitud_encomienda,cuenta,persona "
                    + "WHERE cuenta.ID_PERSONA = persona.ID_PERSONA AND solicitud_encomienda.ID_CUENTA=cuenta.ID_CUENTA AND ESTADO_SOLICITUD_ENCOMIENDA = "+estadoEncomienda+" AND persona.RUT LIKE '%"+filtro+"%' "
                    + "OR  cuenta.ID_PERSONA = persona.ID_PERSONA AND solicitud_encomienda.ID_CUENTA=cuenta.ID_CUENTA AND ESTADO_SOLICITUD_ENCOMIENDA = "+estadoEncomienda+" AND persona.NOMBRE LIKE '%"+filtro+"%' "
                    + "OR  cuenta.ID_PERSONA = persona.ID_PERSONA AND solicitud_encomienda.ID_CUENTA=cuenta.ID_CUENTA AND ESTADO_SOLICITUD_ENCOMIENDA = "+estadoEncomienda+" AND persona.NOMBRE LIKE '%"+filtro+"%' ;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                TablaSolicitudEncomienda tablaSolicitudEncomienda = new TablaSolicitudEncomienda();
                tablaSolicitudEncomienda.setIdSolicitudEncomienda(rs.getInt(1));
                tablaSolicitudEncomienda.setNombreApPaternoResidente(rs.getString(2));
                tablaSolicitudEncomienda.setFechaEncomienda(rs.getString(3));
                tablaSolicitudEncomienda.setHoraEncomienda(rs.getString(4));
                listaSolicitudEncomienda.add(tablaSolicitudEncomienda);
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return listaSolicitudEncomienda;
    }

}
