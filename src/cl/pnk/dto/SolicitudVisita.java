/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dto;

/**
 *
 * @author TitoS
 */
public class SolicitudVisita {
    int idSolicitud;
    int idCuentaResidente;
    int estadoSolicitud;
    String fechaVisita;
    int idPersonaVisita;
    String horaVisita;

    public SolicitudVisita() {
    }

    public SolicitudVisita(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public SolicitudVisita(int idSolicitud, int idCuentaResidente) {
        this.idSolicitud = idSolicitud;
        this.idCuentaResidente = idCuentaResidente;
    }

    public SolicitudVisita(int idSolicitud, int idCuentaResidente, int estadoSolicitud) {
        this.idSolicitud = idSolicitud;
        this.idCuentaResidente = idCuentaResidente;
        this.estadoSolicitud = estadoSolicitud;
    }

    public SolicitudVisita(int idSolicitud, int idCuentaResidente, int estadoSolicitud, String fechaVisita) {
        this.idSolicitud = idSolicitud;
        this.idCuentaResidente = idCuentaResidente;
        this.estadoSolicitud = estadoSolicitud;
        this.fechaVisita = fechaVisita;
    }

    public SolicitudVisita(int idSolicitud, int idCuentaResidente, int estadoSolicitud, String fechaVisita, int idPersonaVisita) {
        this.idSolicitud = idSolicitud;
        this.idCuentaResidente = idCuentaResidente;
        this.estadoSolicitud = estadoSolicitud;
        this.fechaVisita = fechaVisita;
        this.idPersonaVisita = idPersonaVisita;
    }

    public SolicitudVisita(int idSolicitud, int idCuentaResidente, int estadoSolicitud, String fechaVisita, int idPersonaVisita, String horaVisita) {
        this.idSolicitud = idSolicitud;
        this.idCuentaResidente = idCuentaResidente;
        this.estadoSolicitud = estadoSolicitud;
        this.fechaVisita = fechaVisita;
        this.idPersonaVisita = idPersonaVisita;
        this.horaVisita = horaVisita;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getIdCuentaResidente() {
        return idCuentaResidente;
    }

    public void setIdCuentaResidente(int idCuentaResidente) {
        this.idCuentaResidente = idCuentaResidente;
    }

    public int getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(int estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public String getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(String fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public int getIdPersonaVisita() {
        return idPersonaVisita;
    }

    public void setIdPersonaVisita(int idPersonaVisita) {
        this.idPersonaVisita = idPersonaVisita;
    }

    public String getHoraVisita() {
        return horaVisita;
    }

    public void setHoraVisita(String horaVisita) {
        this.horaVisita = horaVisita;
    }

    @Override
    public String toString() {
        return "SolicitudVisita{" + "idSolicitud=" + idSolicitud + ", idCuentaResidente=" + idCuentaResidente + ", estadoSolicitud=" + estadoSolicitud + ", fechaVisita=" + fechaVisita + ", idPersonaVisita=" + idPersonaVisita + ", horaVisita=" + horaVisita + '}';
    }
    
}
