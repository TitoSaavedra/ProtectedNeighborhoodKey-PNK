/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dal;

import cl.pnk.dto.Cuenta;
import cl.pnk.dto.Direccion;
import cl.pnk.dto.DireccionPersona;
import cl.pnk.dto.Persona;
import cl.pnk.dto.SolicitudVisita;
import cl.pnk.dto.TablaSolicitudesVisita;
import cl.pnk.utils.DBUtils;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author TitoS
 */
public class TablaSolicitudesVisitaDal {

    private DBUtils dbutils = new DBUtils();

    public ObservableList<TablaSolicitudesVisita> obtenerTablaResidentes() {
        ObservableList<TablaSolicitudesVisita> listaTablaSolicitudesVisita = FXCollections.observableArrayList();
        try {
            TablaSolicitudesVisita tablaSolicitudesVisita = new TablaSolicitudesVisita();
            List<SolicitudVisita> listaSolicitudVisita = new SolicitudVisitaDal().obtenerSolicitudesVisita("3");
            for (SolicitudVisita solicitudVisita : listaSolicitudVisita) {
                Cuenta cuentaResidente = new CuentaDal().getCuenta(solicitudVisita.getIdCuentaResidente());
                Persona personaVisita = new PersonaDal().obtenerPersonaId(solicitudVisita.getIdPersonaVisita());
                DireccionPersona direccionPersona = new DireccionPersonaDal().obtenerDireccionPersona(personaVisita.getIdPersona());
                Direccion direccion = new DireccionDal().obtenerDireccion(direccionPersona.getIdDireccion());
                String direccionResidente = "Piso: "+direccion.getPiso()+" Block: "+direccion.getBlock()+" Nro: "+direccion.getNumero();
                tablaSolicitudesVisita.setRutVisita(personaVisita.getRut());
                tablaSolicitudesVisita.setNombreApPaternoVisita(personaVisita.getNombre()+" "+personaVisita.getApePaterno());
                tablaSolicitudesVisita.setRutResidente(cuentaResidente.getPersona().getRut());
                tablaSolicitudesVisita.setNombreApPaternoResidente(cuentaResidente.getPersona().getNombre()+" "+cuentaResidente.getPersona().getApePaterno());
                tablaSolicitudesVisita.setDireccionResidente(direccionResidente);
                tablaSolicitudesVisita.setFechaVisita(solicitudVisita.getFechaVisita()+" "+solicitudVisita.getHoraVisita());
                listaTablaSolicitudesVisita.add(tablaSolicitudesVisita);
            }
        } catch (Exception e) {
            return null;
        } finally {
            this.dbutils.desconectar();
        }
        return listaTablaSolicitudesVisita;
    }

}
