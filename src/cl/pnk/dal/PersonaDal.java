/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dal;

import cl.pnk.dto.Persona;
import cl.pnk.dto.TipoPersona;
import cl.pnk.utils.DBUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TitoS
 */
public class PersonaDal {

    private DBUtils dbutils = new DBUtils();

//INSERT INTO `persona` (`ID_PERSONA`, `RUT`, `NOMBRE`, `SEG_NOMBRE`, `APE_PATERNO`, `APE_MATERNO`, `TELEFONO`, `EMAIL`, `ESTADO`, `ID_TIPO_PERSONA`) VALUES (NULL, '1', '1', '1', '1', '1', '1', '1', '1', '2'); 
    /**
     *
     * @param persona
     */
    public void ingresarPersona(Persona persona) {

        dbutils.conectar();

        try {
            String sql = "INSERT INTO persona(ID_PERSONA,RUT,NOMBRE,SEG_NOMBRE,APE_PATERNO,APE_MATERNO,TELEFONO,EMAIL,ESTADO,ID_TIPO_PERSONA)"
                    + " VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = dbutils.getConexion().prepareStatement(sql);

            st.setInt(1, 0);
            st.setString(2, persona.getRut());
            st.setString(3, persona.getNombre());
            st.setString(4, persona.getSegNombre());
            st.setString(5, persona.getApePaterno());
            st.setString(6, persona.getApeMaterno());
            st.setInt(7, persona.getTelefono());
            st.setString(8, persona.getEmail());
            st.setInt(9, persona.getEstado());
            st.setInt(10, 2);
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
    public List<Persona> obtenerPersonas() {
        List<Persona> listaPersonas = new ArrayList<>();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_PERSONA,RUT,NOMBRE,SEG_NOMBRE,APE_PATERNO,APE_MATERNO,TELEFONO,EMAIL,ESTADO,ID_TIPO_PERSONA FROM persona;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setIdPersona(rs.getInt(1));
                persona.setRut(rs.getString(2));
                persona.setNombre(rs.getString(3));
                persona.setSegNombre(rs.getString(4));
                persona.setApePaterno(rs.getString(5));
                persona.setApeMaterno(rs.getString(6));
                persona.setTelefono(rs.getInt(7));
                persona.setEmail(rs.getString(8));
                persona.setEstado(rs.getInt(9));
                int idTipoPersona = rs.getInt(10);
                List<TipoPersona> tipoPersonas = new TipoPersonaDal().obtenerTipoPersona();
                for (TipoPersona tipoPersona : tipoPersonas) {
                    if (idTipoPersona == tipoPersona.getIdTipoPersona()) {
                        persona.setTipoPersona(tipoPersona);
                    }
                }
                listaPersonas.add(persona);
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return listaPersonas;
    }

    /**
     *
     * @param rut
     * @return
     */
    public Persona obtenerPersonaRut(String rut) {
        Persona persona = new Persona();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_PERSONA,RUT,NOMBRE,SEG_NOMBRE,APE_PATERNO,APE_MATERNO,TELEFONO,EMAIL,ESTADO,ID_TIPO_PERSONA FROM persona WHERE persona.RUT=" + rut + ";";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                persona.setIdPersona(rs.getInt(1));
                persona.setRut(rs.getString(2));
                persona.setNombre(rs.getString(3));
                persona.setSegNombre(rs.getString(4));
                persona.setApePaterno(rs.getString(5));
                persona.setApeMaterno(rs.getString(6));
                persona.setTelefono(rs.getInt(7));
                persona.setEmail(rs.getString(8));
                persona.setEstado(rs.getInt(9));
                int idTipoPersona = rs.getInt(10);
                List<TipoPersona> tipoPersonas = new TipoPersonaDal().obtenerTipoPersona();
                for (TipoPersona tipoPersona : tipoPersonas) {
                    if (idTipoPersona == tipoPersona.getIdTipoPersona()) {
                        persona.setTipoPersona(tipoPersona);
                    }
                }
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return persona;
    }

    /**
     *
     * @param ID
     * @return
     */
    public Persona obtenerPersonaId(int ID) {
        Persona persona = new Persona();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_PERSONA,RUT,NOMBRE,SEG_NOMBRE,APE_PATERNO,APE_MATERNO,TELEFONO,EMAIL,ESTADO,ID_TIPO_PERSONA FROM persona WHERE ID_PERSONA = " + ID + ";";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                persona.setIdPersona(rs.getInt(1));
                persona.setRut(rs.getString(2));
                persona.setNombre(rs.getString(3));
                persona.setSegNombre(rs.getString(4));
                persona.setApePaterno(rs.getString(5));
                persona.setApeMaterno(rs.getString(6));
                persona.setTelefono(rs.getInt(7));
                persona.setEmail(rs.getString(8));
                persona.setEstado(rs.getInt(9));
                int idTipoPersona = rs.getInt(10);
                List<TipoPersona> tipoPersonas = new TipoPersonaDal().obtenerTipoPersona();
                for (TipoPersona tipoPersona : tipoPersonas) {
                    if (idTipoPersona == tipoPersona.getIdTipoPersona()) {
                        persona.setTipoPersona(tipoPersona);
                    }
                }
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return persona;
    }

    public int obtenerUltimoIdPersona() {
        int id;
        List<Persona> listaPersonas = this.obtenerPersonas();
        if (!listaPersonas.isEmpty()) {
            Persona op = listaPersonas.get(listaPersonas.size() - 1);
            id = op.getIdPersona();
        } else {
            id = 0;
        }
        return id;
    }
    

}
