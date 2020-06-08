/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dal;

import cl.pnk.dto.Cuenta;
import cl.pnk.dto.Persona;
import cl.pnk.dto.TarjetaNfc;
import cl.pnk.utils.DBUtils;
import cl.pnk.utils.UtilidadesPrograma;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 * Clase que conecta la clase Cuenta con la base de datos
 *
 * @author TitoS
 */
public class CuentaDal {

    private DBUtils dbutils = new DBUtils();
    private UtilidadesPrograma utilsPrograma = new UtilidadesPrograma();
    private FileInputStream fis;

    /**
     *
     * @param cuenta cuenta de la persona
     * @param archivo es el archivo que contiene la imagen de la cuenta
     * @param idPersona es el id de la persona
     * @throws FileNotFoundException Si el archivo esta nulo
     */
    public void ingresarCuenta(Cuenta cuenta, File archivo, int idPersona) throws FileNotFoundException {
        dbutils.conectar();
        fis = new FileInputStream(archivo);
        try {
            String sql = "INSERT INTO cuenta(CLAVE,ESTADO,FOTO,ID_PERSONA)"
                    + " VALUES(?,?,?,?)";
            PreparedStatement st = dbutils.getConexion().prepareStatement(sql);
            st.setString(1, cuenta.getClave());
            st.setInt(2, 1);
            st.setBinaryStream(3, (InputStream) fis, (int) archivo.length());
            st.setInt(4, idPersona);
            st.executeUpdate();

        } catch (Exception e) {
            e.toString();
        } finally {
            dbutils.desconectar();
        }
    }

    /**
     * Metodo que envia todas las cuentas de la base de datos una lista
     *
     * @throws FileNotFoundException si alguna cuenta no tiene una foto lista
     * @return Cuentas esta es la lista de las cuentas de la bd
     */
    public List<Cuenta> getCuentas() throws FileNotFoundException {
        List<Cuenta> listaCuentas = new ArrayList<>();
        byte[] imageBytes;
        Image image;
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_CUENTA,CLAVE,ESTADO,FOTO,ID_PERSONA,UID FROM cuenta WHERE Estado = 1;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setIdCuenta(rs.getInt(1));
                cuenta.setClave(rs.getString(2));
                cuenta.setEstado(rs.getInt(3));
                imageBytes = rs.getBytes(4);
                BufferedImage buffImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
                image = this.utilsPrograma.convertirImagen(buffImage);
                cuenta.setFoto(image);
                //Falta añadir los datos de la persona
                List<Persona> listaPersonas = new PersonaDal().obtenerPersonas();
                for (Persona persona : listaPersonas) {
                    if (persona.getIdPersona() == rs.getInt(5)) {
                        cuenta.setPersona(persona);
                    }
                }
                List<TarjetaNfc> tarjetasNfc = new TarjetaNfcDal().obtenerTarjetaNfc();
                for (TarjetaNfc tarjetaNfc : tarjetasNfc) {
                    if (tarjetaNfc.getUid().equals(rs.getString(6))) {
                        cuenta.setTarjetaNfc(tarjetaNfc);
                    }
                }
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

    /**
     * Metodo que modifica una cuenta con archivo de imagen
     *
     * @param cuenta la cuenta a modificar
     * @param archivo la imagen a modificar
     * @param idPersona id de persona a modificar
     * @throws FileNotFoundException si la imagen esta nula
     */
    public void modificarCuentaConFoto(Cuenta cuenta, File archivo, int idPersona) throws FileNotFoundException {
        dbutils.conectar();
        FileInputStream in = new FileInputStream(archivo);
        try {
            String sql = "UPDATE `cuenta` SET `CLAVE` = ?, `FOTO` = ? WHERE `cuenta`.`ID_CUENTA` = " + idPersona + ";";
            PreparedStatement st = dbutils.getConexion().prepareStatement(sql);
            st.setString(1, cuenta.getClave());
            st.setBinaryStream(2, in, (int) archivo.length());
            st.executeUpdate();
            dbutils.getConexion().commit();
        } catch (Exception e) {
        } finally {

            dbutils.desconectar();
        }
    }

    /**
     * Metodo que modifica una cuenta sin foto
     *
     * @param cuenta objeto cuenta a modificar
     * @param idPersona id de la persona de la cuenta a modificar
     */
    public void modificarCuentaSinFoto(Cuenta cuenta, int idPersona) {
        dbutils.conectar();
        try {
            String sql = "UPDATE `cuenta` SET `CLAVE` = ? WHERE `cuenta`.`ID_CUENTA` = " + idPersona + ";";
            PreparedStatement st = dbutils.getConexion().prepareStatement(sql);
            st.setString(1, cuenta.getClave());
            st.executeUpdate();
        } catch (Exception e) {
        } finally {

            dbutils.desconectar();
        }
    }

    /**
     * * Metodo que busca una cuenta por id
     *
     * @param id id de la cuenta a buscar
     * @return cuenta encontrada
     * @throws FileNotFoundException si la cuenta el la bd tiene la imagen nula
     */
    public Cuenta getCuenta(int id) throws FileNotFoundException {
        Cuenta cuenta = new Cuenta();
        byte[] imageBytes;
        Image image;
        try {
            this.dbutils.conectar();
            String sql = "SELECT ID_CUENTA,CLAVE,ESTADO,FOTO,ID_PERSONA,UID FROM cuenta WHERE ID_PERSONA = '" + id + "';";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                cuenta.setIdCuenta(rs.getInt(1));
                cuenta.setClave(rs.getString(2));
                cuenta.setEstado(rs.getInt(3));
                imageBytes = rs.getBytes(4);
                BufferedImage buffImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
                image = this.utilsPrograma.convertirImagen(buffImage);
                cuenta.setFoto(image);
                //Falta añadir los datos de la persona
                List<Persona> listaPersonas = new PersonaDal().obtenerPersonas();
                for (Persona persona : listaPersonas) {
                    if (persona.getIdPersona() == rs.getInt(5)) {
                        cuenta.setPersona(persona);
                    }
                }
                List<TarjetaNfc> tarjetasNfc = new TarjetaNfcDal().obtenerTarjetaNfc();
                for (TarjetaNfc tarjetaNfc : tarjetasNfc) {
                    if (tarjetaNfc.getUid().equals(rs.getString(6))) {
                        cuenta.setTarjetaNfc(tarjetaNfc);
                    }
                }
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return cuenta;
    }

}
