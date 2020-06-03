/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dal;

import cl.pnk.dao.Cuenta;
import cl.pnk.dao.Persona;
import cl.pnk.dao.TarjetaNfc;
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
 *
 * @author TitoS
 */
public class CuentaDal {

    private DBUtils dbutils = new DBUtils();
    private UtilidadesPrograma utilsPrograma = new UtilidadesPrograma();
    private FileInputStream fis;

    //INSERT INTO `cuenta` (`ID_CUENTA`, `CLAVE`, `ESTADO`, `ID_PERSONA`, `UID`)
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
        } finally {

            dbutils.desconectar();
        }
    }

    /**
     *
     * @return @throws FileNotFoundException
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
//UPDATE `cuenta` SET `CLAVE` = 'QQqq', `FOTO` =

    public  void modificarCuentaConFoto(Cuenta cuenta, File archivo, int idPersona) throws FileNotFoundException {
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
