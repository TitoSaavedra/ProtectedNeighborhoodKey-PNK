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
import java.io.FileNotFoundException;
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

    public List<Cuenta> getCuentas() throws FileNotFoundException {
        List<Cuenta> listaCuentas = new ArrayList<>();
        byte[] imageBytes;
        Image image;
        try {
            boolean res = this.dbutils.conectar();
            System.out.println(res);
            String sql = "SELECT ID_CUENTA,CLAVE,ESTADO,FOTO,ID_PERSONA,UID FROM cuenta;";
            PreparedStatement sq = this.dbutils.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setIdCuenta(rs.getInt(1));
                cuenta.setClave(rs.getString(2));
                cuenta.setEstado(rs.getInt(3));
                imageBytes = rs.getBytes(4);
                BufferedImage buffImage= ImageIO.read(new ByteArrayInputStream(imageBytes));
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

}
