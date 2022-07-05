/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dal;

import cl.pnk.dto.TipoPersona;
import cl.pnk.utils.DBUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que conecta el TipoPersona con la bd
 *
 * @author TitoS
 */
public class TipoPersonaDal {

    private DBUtils dbutils = new DBUtils();

    /**
     * Metodo que retorna una coleccion de tipoPersona
     *
     * @return listaTipoPersona Coleccion de tipos de persona
     */
    public List<TipoPersona> obtenerTipoPersona() {
        List<TipoPersona> listaTipoPersona = new ArrayList<>();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_TIPO_PERSONA,NOMBRE,DESCRIPCION FROM tipo_persona;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                TipoPersona tipoPersona = new TipoPersona();
                tipoPersona.setIdTipoPersona(rs.getInt(1));
                tipoPersona.setNombre(rs.getString(2));
                tipoPersona.setDescripcion(rs.getString(3));
                //Falta añadir los datos de la persona
                listaTipoPersona.add(tipoPersona);
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return listaTipoPersona;
    }
}
