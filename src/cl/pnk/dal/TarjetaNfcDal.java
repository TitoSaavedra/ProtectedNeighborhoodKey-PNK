/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dal;

import cl.pnk.dao.TarjetaNfc;
import cl.pnk.utils.DBUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TitoS
 */
public class TarjetaNfcDal {

    private DBUtils dbutils = new DBUtils();

    /**
     *
     * @return
     */
    public List<TarjetaNfc> obtenerTarjetaNfc() {
        List<TarjetaNfc> listaTarjetasNfc = new ArrayList<>();
        try {
            this.dbutils.conectar();
            String sql = "SELECT UID,ESTADO,FECHA_REGISTRO FROM tarjeta_nfc;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                TarjetaNfc tarjetaNfc = new TarjetaNfc();
                tarjetaNfc.setUid(rs.getString(1));
                tarjetaNfc.setEstado(rs.getInt(2));
                tarjetaNfc.setFechaRegistro(rs.getDate(3));
                listaTarjetasNfc.add(tarjetaNfc);
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return listaTarjetasNfc;
    }

}
