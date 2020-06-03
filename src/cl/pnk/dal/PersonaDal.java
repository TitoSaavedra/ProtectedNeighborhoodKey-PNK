/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dal;

import cl.pnk.dao.Persona;
import cl.pnk.dao.TipoPersona;
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
            st.setString(7, persona.getTelefono());
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
            String sql = "SELECT ID_PERSONA,RUT,NOMBRE,SEG_NOMBRE,APE_PATERNO,APE_MATERNO,TELEFONO,EMAIL,ESTADO,ID_TIPO_PERSONA FROM persona Where persona.estado = 1;";
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
                persona.setTelefono(rs.getString(7));
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
            String sql = "SELECT ID_PERSONA,RUT,NOMBRE,SEG_NOMBRE,APE_PATERNO,APE_MATERNO,TELEFONO,EMAIL,ESTADO,ID_TIPO_PERSONA FROM persona WHERE persona.RUT='" + rut + "' And persona.estado = 1 AND persona.ID_TIPO_PERSONA=2;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                persona.setIdPersona(rs.getInt(1));
                persona.setRut(rs.getString(2));
                persona.setNombre(rs.getString(3));
                persona.setSegNombre(rs.getString(4));
                persona.setApePaterno(rs.getString(5));
                persona.setApeMaterno(rs.getString(6));
                persona.setTelefono(rs.getString(7));
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
            String sql = "SELECT ID_PERSONA,RUT,NOMBRE,SEG_NOMBRE,APE_PATERNO,APE_MATERNO,TELEFONO,EMAIL,ESTADO,ID_TIPO_PERSONA FROM persona WHERE ID_PERSONA = '" + ID + "' AND persona.estado = 1;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                persona.setIdPersona(rs.getInt(1));
                persona.setRut(rs.getString(2));
                persona.setNombre(rs.getString(3));
                persona.setSegNombre(rs.getString(4));
                persona.setApePaterno(rs.getString(5));
                persona.setApeMaterno(rs.getString(6));
                persona.setTelefono(rs.getString(7));
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

    public void eliminarPersona(Persona persona) {
        //UPDATE `persona` SET `ESTADO` = '1' WHERE persona.RUT = '19145130-9' 
        try {
            dbutils.conectar();
            String sql = "UPDATE persona SET ESTADO = '2' WHERE persona.RUT = '" + persona.getRut() + "';";
            PreparedStatement st = dbutils.getConexion().prepareStatement(sql);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
        } finally {
            //4 desconectar DB
            dbutils.desconectar();
        }
    }
//UPDATE `persona` SET `RUT` = '1-92', `NOMBRE` = 'Alejandroa', `SEG_NOMBRE` = 'Alejandroa', `APE_PATERNO` = 'De las chacanasa', `APE_MATERNO` = 'Chacanoidesa', `TELEFONO` = '+51680510213a', `EMAIL` = 'hoola@hoka.coma', `ESTADO` = '1', `ID_TIPO_PERSONA` = '1' WHERE `persona`.`ID_PERSONA` = 3;

    public void modificarPersona(Persona persona) {
        //UPDATE `persona` SET `ESTADO` = '1' WHERE persona.RUT = '19145130-9' 
        try {
            dbutils.conectar();
            String sql = "UPDATE persona  SET `RUT` = '" + persona.getRut() + "', `NOMBRE` = '" + persona.getNombre() + "', `SEG_NOMBRE` = '" + persona.getSegNombre() + "', `APE_PATERNO` = '" + persona.getApePaterno() + "', `APE_MATERNO` = '" + persona.getApeMaterno() + "', `TELEFONO` = '" + persona.getTelefono() + "', `EMAIL` = '" + persona.getEmail() + "' WHERE `persona`.`ID_PERSONA` = '" + persona.getIdPersona() + "';";
            PreparedStatement st = dbutils.getConexion().prepareStatement(sql);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
        } finally {
            //4 desconectar DB
            dbutils.desconectar();
        }
    }

    public Persona obtenerUltimaPersona() {
        // SELECT * FROM persona ORDER BY ID_PERSONA DESC LIMIT 1 
        Persona persona = new Persona();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_PERSONA,RUT,NOMBRE,SEG_NOMBRE,APE_PATERNO,APE_MATERNO,TELEFONO,EMAIL,ESTADO,ID_PERSONA FROM persona ORDER BY ID_PERSONA DESC LIMIT 1;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                persona.setIdPersona(rs.getInt(1));
                persona.setRut(rs.getString(2));
                persona.setNombre(rs.getString(3));
                persona.setSegNombre(rs.getString(4));
                persona.setApePaterno(rs.getString(5));
                persona.setApeMaterno(rs.getString(6));
                persona.setTelefono(rs.getString(7));
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

}
