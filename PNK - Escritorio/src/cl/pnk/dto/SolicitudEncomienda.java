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
public class SolicitudEncomienda {
   private int idSolicitudEncomienda;
   private int idCuenta;
   private int estadoSolicitudEncomienda;
   private int idEncomienda;
   private String fechaEntrega;
   private String horaEntrega;

    public SolicitudEncomienda() {
    }

    public SolicitudEncomienda(int idSolicitudEncomienda, int idCuenta, int estadoSolicitudEncomienda, int idEncomienda, String fechaEntrega, String horaEntrega) {
        this.idSolicitudEncomienda = idSolicitudEncomienda;
        this.idCuenta = idCuenta;
        this.estadoSolicitudEncomienda = estadoSolicitudEncomienda;
        this.idEncomienda = idEncomienda;
        this.fechaEntrega = fechaEntrega;
        this.horaEntrega = horaEntrega;
    }

    public int getIdSolicitudEncomienda() {
        return idSolicitudEncomienda;
    }

    public void setIdSolicitudEncomienda(int idSolicitudEncomienda) {
        this.idSolicitudEncomienda = idSolicitudEncomienda;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getEstadoSolicitudEncomienda() {
        return estadoSolicitudEncomienda;
    }

    public void setEstadoSolicitudEncomienda(int estadoSolicitudEncomienda) {
        this.estadoSolicitudEncomienda = estadoSolicitudEncomienda;
    }

    public int getIdEncomienda() {
        return idEncomienda;
    }

    public void setIdEncomienda(int idEncomienda) {
        this.idEncomienda = idEncomienda;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    @Override
    public String toString() {
        return "SolicitudEncomienda{" + "idSolicitudEncomienda=" + idSolicitudEncomienda + ", idCuenta=" + idCuenta + ", estadoSolicitudEncomienda=" + estadoSolicitudEncomienda + ", idEncomienda=" + idEncomienda + ", fechaEntrega=" + fechaEntrega + ", horaEntrega=" + horaEntrega + '}';
    }
   
}
