/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dal;

import cl.pnk.dto.Cuenta;
import cl.pnk.utils.DBUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TitoS
 */
public class CuentaDal {

    private DBUtils dbutils = new DBUtils();

    public List<Cuenta> getCuentas() {
        List<Cuenta> listaCuentas = new ArrayList<>();
        try {
            boolean res = this.dbutils.conectar();
            System.out.println(res);
            String sql = "SELECT * FROM cuenta ";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setIdCuenta(rs.getInt(1));
                cuenta.setClave(rs.getString(2));
                cuenta.setEstado(rs.getInt(3));
                cuenta.setFoto(rs.getString(4));
                //Falta añadir los datos de la persona
              listaCuentas.add(cuenta);
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return listaCuentas;
    }
}
