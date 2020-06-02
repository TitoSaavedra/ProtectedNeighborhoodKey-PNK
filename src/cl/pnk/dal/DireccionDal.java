/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dal;

import cl.pnk.dao.Direccion;
import cl.pnk.utils.DBUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TitoS
 */
public class DireccionDal {

    private DBUtils dbutils = new DBUtils();

    public void ingresarDireccion(Direccion direccion) {
        dbutils.conectar();
        try {
            String sql = "INSERT INTO direccion(ID_DIRECCION,PISO,BLOCK,NUMERO,ID_PERSONA)"
                    + " VALUES(?,?,?,?,?)";
            PreparedStatement st = dbutils.getConexion().prepareStatement(sql);
            st.setInt(1, 0);
            st.setString(2, direccion.getPiso());
            st.setString(3, direccion.getBlock());
            st.setString(4, direccion.getNumero());
            st.setInt(5, direccion.getPersona().getIdPersona());
            st.executeUpdate();
        } catch (Exception e) {
        } finally {

            dbutils.desconectar();
        }
    }

    /**
     *
     * @return
     */
    public List<Direccion> obtenerDirecciones() {
        List<Direccion> listaDirecciones = new ArrayList<>();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_DIRECCION,PISO,BLOCK,NUMERO,ID_PERSONA FROM direccion;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                Direccion direccion = new Direccion();
                direccion.setIdDireccion(rs.getInt(1));
                direccion.setPiso(rs.getString(2));
                direccion.setBlock(rs.getString(3));
                direccion.setNumero(rs.getString(4));
                direccion.setPersona(new PersonaDal().obtenerPersonaId(rs.getInt(5)));
                listaDirecciones.add(direccion);
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return listaDirecciones;
    }
}
