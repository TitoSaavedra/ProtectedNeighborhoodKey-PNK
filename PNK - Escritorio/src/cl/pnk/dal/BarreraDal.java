/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dal;

import cl.pnk.dto.Barrera;
import cl.pnk.utils.ConnDBAmazon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author TitoS
 */
public class BarreraDal {

    private final ConnDBAmazon connDBAmazon = new ConnDBAmazon();

    public Barrera estadoBarrera() {
        Barrera barrera = new Barrera();
        try {
            this.connDBAmazon.conectar();
            String sql = "SELECT BARRERA_ESTADO FROM barrera;";
            PreparedStatement sq = this.connDBAmazon.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                barrera.setEstadoBarrera(rs.getInt(1));
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.connDBAmazon.desconectar();
        }
        return barrera;
    }

    public void accionBarrera(int idEstado) {
        try {
            this.connDBAmazon.conectar();
            String sql = "UPDATE `barrera` SET `BARRERA_ESTADO` = '"+idEstado+"' WHERE `barrera`.`ID_BARRERA` = 1";
            PreparedStatement sq = this.connDBAmazon.getConexion().prepareStatement(sql);
            sq.executeUpdate();
        } catch (Exception e) {
        } finally {
            this.connDBAmazon.desconectar();
        }
    }
}
