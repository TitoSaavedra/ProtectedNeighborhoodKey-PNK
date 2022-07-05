/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dal;

import cl.pnk.dto.Cuenta;
import cl.pnk.dto.Direccion;
import cl.pnk.dto.DireccionPersona;
import cl.pnk.dto.TablaAcceso;
import cl.pnk.utils.ConnDBAmazon;
import cl.pnk.utils.DBUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author TitoS
 */
public class TablaAccesoDal {

    private DBUtils dbutils = new DBUtils();
    private ConnDBAmazon connDBAmazon = new ConnDBAmazon();

    public ObservableList<TablaAcceso> obtenerAccesos() {
        ObservableList<TablaAcceso> listaTablaAccesos = FXCollections.observableArrayList();
        this.connDBAmazon.conectar();
        try {
            String sql = "SELECT acceso.ID_CUENTA,persona.NOMBRE,CONCAT(persona.APE_PATERNO,' ',persona.APE_MATERNO),CONCAT(acceso.FECHA_ACCESO,' ',acceso.HORA_ACCESO),acceso.TIPO_ACCESO FROM acceso,persona,cuenta WHERE acceso.ID_CUENTA = cuenta.ID_CUENTA AND persona.ID_PERSONA=cuenta.ID_PERSONA "
                    + "ORDER BY acceso.ID_ACCESO DESC";
            PreparedStatement sq = this.connDBAmazon.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                TablaAcceso tablaAcceso = new TablaAcceso();
                tablaAcceso.setIdCuenta(rs.getInt(1));
                tablaAcceso.setNombreResidente(rs.getString(2));
                tablaAcceso.setApellidoPaternoMaternoResidente(rs.getString(3));
                tablaAcceso.setFechaAcceso(rs.getString(4));
                switch (rs.getInt(5)) {
                    case 1:
                        tablaAcceso.setTipoAcceso("Entrada");
                        break;
                    case 2:
                        tablaAcceso.setTipoAcceso("Salida");
                        break;
                }
                Cuenta cuentaResidente = new CuentaDal().getCuentaSinFoto(tablaAcceso.getIdCuenta());
                DireccionPersona direccionPersona = new DireccionPersonaDal().obtenerDireccionPersona(cuentaResidente.getPersona().getIdPersona());
                Direccion direccion = new DireccionDal().obtenerDireccion(direccionPersona.getIdDireccion());
                String direccionResidente = direccion.getPiso() + " " + direccion.getBlock() + " " + direccion.getNumero();
                tablaAcceso.setDireccionResidente(direccionResidente);
                listaTablaAccesos.add(tablaAcceso);
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.connDBAmazon.desconectar();
        }
        return listaTablaAccesos;
    }

    public ObservableList<TablaAcceso> obtenerAccesosFiltrado(String txt) {
        ObservableList<TablaAcceso> listaTablaAccesos = FXCollections.observableArrayList();
        try {
            this.connDBAmazon.conectar();
            String sql = "SELECT acceso.ID_CUENTA,persona.NOMBRE,CONCAT(persona.APE_PATERNO,' ',persona.APE_MATERNO),CONCAT(acceso.FECHA_ACCESO,' ',acceso.HORA_ACCESO),acceso.TIPO_ACCESO FROM acceso,persona,cuenta "
                    + "WHERE acceso.ID_CUENTA = cuenta.ID_CUENTA AND persona.ID_PERSONA=cuenta.ID_PERSONA AND persona.RUT LIKE '%" + txt + "%' "
                    + "OR acceso.ID_CUENTA = cuenta.ID_CUENTA AND persona.ID_PERSONA=cuenta.ID_PERSONA AND persona.NOMBRE LIKE '%" + txt + "%' "
                    + "OR acceso.ID_CUENTA = cuenta.ID_CUENTA AND persona.ID_PERSONA=cuenta.ID_PERSONA AND persona.APE_PATERNO LIKE '%" + txt + "%' "
                    + " OR acceso.ID_CUENTA = cuenta.ID_CUENTA AND persona.ID_PERSONA=cuenta.ID_PERSONA AND persona.APE_MATERNO LIKE '%" + txt + "%';";
            PreparedStatement sq = this.connDBAmazon.getConexion().prepareStatement(sql);
            ResultSet rs = sq.executeQuery();
            while (rs.next()) {
                TablaAcceso tablaAcceso = new TablaAcceso();
                tablaAcceso.setIdCuenta(rs.getInt(1));
                tablaAcceso.setNombreResidente(rs.getString(2));
                tablaAcceso.setApellidoPaternoMaternoResidente(rs.getString(3));
                tablaAcceso.setFechaAcceso(rs.getString(4));
                switch (rs.getInt(5)) {
                    case 1:
                        tablaAcceso.setTipoAcceso("Entrada");
                        break;
                    case 2:
                        tablaAcceso.setTipoAcceso("Salida");
                        break;
                }
                Cuenta cuentaResidente = new CuentaDal().getCuentaSinFoto(tablaAcceso.getIdCuenta());
                DireccionPersona direccionPersona = new DireccionPersonaDal().obtenerDireccionPersona(cuentaResidente.getPersona().getIdPersona());
                Direccion direccion = new DireccionDal().obtenerDireccion(direccionPersona.getIdDireccion());
                String direccionResidente = direccion.getPiso() + " " + direccion.getBlock() + " " + direccion.getNumero();
                tablaAcceso.setDireccionResidente(direccionResidente);
                listaTablaAccesos.add(tablaAcceso);
            }
            rs.close();
        } catch (Exception e) {
            return null;
        } finally {
            this.connDBAmazon.desconectar();
        }
        return listaTablaAccesos;
    }
}
