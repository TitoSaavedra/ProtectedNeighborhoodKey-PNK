/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dal;

import cl.pnk.dto.Persona;
import cl.pnk.utils.DBUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que conecta la clase Persona con la base de datos
 *
 * @author TitoS
 */
public class PersonaDal {

    private DBUtils dbutils = new DBUtils();

    /**
     * Metodo que ingresa una persona en la bd
     *
     * @param persona Objeto clase persona que se desea ingresar
     */
    public void ingresarPersona(Persona persona) {
        dbutils.conectar();
        try {
            String sql = "INSERT INTO persona(ID_PERSONA,RUT,NOMBRE,SEG_NOMBRE,APE_PATERNO,APE_MATERNO,TELEFONO,EMAIL,ESTADO_PERSONA,ID_TIPO_PERSONA)"
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

    public void ingresarVisita(Persona persona) {
        dbutils.conectar();
        try {
            String sql = "INSERT INTO persona(ID_PERSONA,RUT,NOMBRE,SEG_NOMBRE,APE_PATERNO,APE_MATERNO,ESTADO_PERSONA,ID_TIPO_PERSONA)"
                    + " VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement st = dbutils.getConexion().prepareStatement(sql);
            st.setInt(1, 0);
            st.setString(2, persona.getRut());
            st.setString(3, persona.getNombre());
            st.setString(4, persona.getSegNombre());
            st.setString(5, persona.getApePaterno());
            st.setString(6, persona.getApeMaterno());
            st.setInt(7, 1);
            st.setInt(8, 3);
            st.executeUpdate();
        } catch (Exception e) {
        } finally {
            dbutils.desconectar();
        }
    }

    /**
     * Metodo que retorna todas las personas de la bd
     *
     * @return listaPersonas Coleccion de personas
     */
    public List<Persona> obtenerPersonas() {
        List<Persona> listaPersonas = new ArrayList<>();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_PERSONA,RUT,NOMBRE,SEG_NOMBRE,APE_PATERNO,APE_MATERNO,TELEFONO,EMAIL,ESTADO_PERSONA,ID_TIPO_PERSONA FROM persona Where persona.ESTADO_PERSONA = 1;";
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
                persona.setTipoPersona(rs.getInt(10));
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
     * Metodo que obtiene una persona de la base de datos filtrada por rut
     *
     * @param rut texto que contiene el rut a filtrar
     * @return persona persona encontrada en la bd
     */
    public Persona obtenerPersonaRut(String rut, String tipoPersona) {
        Persona persona = new Persona();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_PERSONA,RUT,NOMBRE,SEG_NOMBRE,APE_PATERNO,APE_MATERNO,TELEFONO,EMAIL,ESTADO_PERSONA,ID_TIPO_PERSONA FROM persona WHERE persona.RUT='" + rut + "' And persona.ESTADO_PERSONA = 1 AND persona.ID_TIPO_PERSONA='" + tipoPersona + "';";
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
                persona.setTipoPersona(rs.getInt(10));
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
     * Metodo que obtiene una persona de la base de datos filtrada por id
     *
     * @param ID int que contiene el id a filtrar
     * @return persona persona encontrada en la bd
     */
    public Persona obtenerPersonaId(int ID) {
        Persona persona = new Persona();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_PERSONA,RUT,NOMBRE,SEG_NOMBRE,APE_PATERNO,APE_MATERNO,TELEFONO,EMAIL,ESTADO_PERSONA,ID_TIPO_PERSONA FROM persona WHERE ID_PERSONA = '" + ID + "' AND persona.ESTADO_PERSONA = 1;";
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
                persona.setTipoPersona(rs.getInt(10));
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
     * Metodo que elimina una persona en la bd
     *
     * @param persona objeto tipo persona que se desa eliminar
     */
    public void eliminarPersona(Persona persona) {
        try {
            dbutils.conectar();
            String sql = "UPDATE persona SET ESTADO_PERSONA = '2' WHERE persona.RUT = '" + persona.getRut() + "';";
            PreparedStatement st = dbutils.getConexion().prepareStatement(sql);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
        } finally {
            //4 desconectar DB
            dbutils.desconectar();
        }
    }

    /**
     * Metodo que modifica una persona
     *
     * @param persona objeto clase persona que se desea modificar
     */
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

    public void modificarVisita(Persona persona) {
        //UPDATE `persona` SET `ESTADO` = '1' WHERE persona.RUT = '19145130-9' 
        try {
            dbutils.conectar();
            String sql = "UPDATE persona  SET NOMBRE = ?, SEG_NOMBRE = ?, APE_PATERNO = ?, APE_MATERNO = ?  WHERE persona.ID_PERSONA = ?;";
            PreparedStatement st = dbutils.getConexion().prepareStatement(sql);
            st.setString(1, persona.getNombre());
            st.setString(2, persona.getSegNombre());
            st.setString(3, persona.getApePaterno());
            st.setString(4, persona.getApeMaterno());
            st.setInt(5, persona.getIdPersona());
            System.out.println(st);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
        } finally {
            //4 desconectar DB
            dbutils.desconectar();
        }
    }

    /**
     * Metodo que obtiene la ultima persona registrada en la bd
     *
     * @return persona objeto tipo persona que se obtiene despues de hacer la
     * consulta
     */
    public Persona obtenerUltimaPersona(String tipoPersona) {
        // SELECT * FROM persona ORDER BY ID_PERSONA DESC LIMIT 1 
        Persona persona = new Persona();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_PERSONA,RUT,NOMBRE,SEG_NOMBRE,APE_PATERNO,APE_MATERNO,TELEFONO,EMAIL,ESTADO_PERSONA,ID_TIPO_PERSONA FROM persona WHERE  ID_TIPO_PERSONA = ? ORDER BY ID_PERSONA DESC LIMIT 1;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            sq.setString(1, tipoPersona);
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
                persona.setTipoPersona(rs.getInt(10));
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return persona;
    }

    public Persona obtenerPersonaCorreo(String correo) {
        Persona persona = new Persona();
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_PERSONA,RUT,NOMBRE,SEG_NOMBRE,APE_PATERNO,APE_MATERNO,TELEFONO,EMAIL,ESTADO_PERSONA,ID_TIPO_PERSONA FROM persona WHERE EMAIL = ? AND persona.ESTADO_PERSONA = 1;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            sq.setString(1, correo);
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
                persona.setTipoPersona(rs.getInt(10));
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
