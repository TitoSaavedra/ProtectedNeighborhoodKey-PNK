/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dal;

import cl.pnk.dao.TablaResidente;
import cl.pnk.utils.DBUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author TitoS
 */
public class TablaResidenteDal {

    private DBUtils dbutils = new DBUtils();

    public ObservableList<TablaResidente> obtenerTablaResidentes() {
        ObservableList<TablaResidente> listaTablaResidentes = FXCollections.observableArrayList();
        try {
            this.dbutils.conectar();
            String sql = "SELECT persona.RUT, persona.NOMBRE,persona.APE_PATERNO,persona.APE_MATERNO, CONCAT(direccion.PISO,' ', direccion.BLOCK,' ', direccion.NUMERO),persona.TELEFONO,persona.EMAIL FROM persona,direccion WHERE direccion.ID_PERSONA = persona.ID_PERSONA AND persona.ID_TIPO_PERSONA='2' AND persona.ESTADO=1;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                TablaResidente tablaResidente = new TablaResidente();
                tablaResidente.setRut(rs.getString(1));
                tablaResidente.setNombre(rs.getString(2));
                tablaResidente.setApPaterno(rs.getString(3));
                tablaResidente.setApMaterno(rs.getString(4));
                tablaResidente.setDir(rs.getString(5));
                tablaResidente.setTelefono(rs.getString(6));
                tablaResidente.setCorreo(rs.getString(7));
                listaTablaResidentes.add(tablaResidente);
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return listaTablaResidentes;
    }
    
    
      public ObservableList<TablaResidente> obtenerTablaResidentesFiltrada(String text) {
        ObservableList<TablaResidente> listaTablaResidentes = FXCollections.observableArrayList();
        try {
            this.dbutils.conectar();
            String sql = "SELECT persona.RUT, persona.NOMBRE,persona.APE_PATERNO,persona.APE_MATERNO, CONCAT(direccion.PISO,' ', direccion.BLOCK,' ', direccion.NUMERO),persona.TELEFONO,persona.EMAIL FROM persona,direccion WHERE direccion.ID_PERSONA = persona.ID_PERSONA AND persona.ID_TIPO_PERSONA='2' AND persona.ESTADO=1 AND persona.RUT LIKE '%"+text+"%' OR direccion.ID_PERSONA = persona.ID_PERSONA AND persona.ID_TIPO_PERSONA='2' AND persona.ESTADO=1 AND persona.NOMBRE LIKE '%"+text+"%' OR direccion.ID_PERSONA = persona.ID_PERSONA AND persona.ID_TIPO_PERSONA='2' AND persona.ESTADO=1 AND persona.APE_PATERNO LIKE '%"+text+"%' OR direccion.ID_PERSONA = persona.ID_PERSONA AND persona.ID_TIPO_PERSONA='2' AND persona.ESTADO=1 AND persona.APE_MATERNO LIKE '%"+text+"%';";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                TablaResidente tablaResidente = new TablaResidente();
                tablaResidente.setRut(rs.getString(1));
                tablaResidente.setNombre(rs.getString(2));
                tablaResidente.setApPaterno(rs.getString(3));
                tablaResidente.setApMaterno(rs.getString(4));
                tablaResidente.setDir(rs.getString(5));
                tablaResidente.setTelefono(rs.getString(6));
                tablaResidente.setCorreo(rs.getString(7));
                listaTablaResidentes.add(tablaResidente);
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return listaTablaResidentes;
    }
}
