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
public class TablaSolicitudEncomienda {
    
    private int idSolicitudEncomienda;
    private String nombreApPaternoResidente;
    private String fechaEncomienda;
    private String horaEncomienda;

    public TablaSolicitudEncomienda() {
    }

    public TablaSolicitudEncomienda(int idSolicitudEncomienda, String nombreApPaternoResidente, String fechaEncomienda, String horaEncomienda) {
        this.idSolicitudEncomienda = idSolicitudEncomienda;
        this.nombreApPaternoResidente = nombreApPaternoResidente;
        this.fechaEncomienda = fechaEncomienda;
        this.horaEncomienda = horaEncomienda;
    }

    public int getIdSolicitudEncomienda() {
        return idSolicitudEncomienda;
    }

    public void setIdSolicitudEncomienda(int idSolicitudEncomienda) {
        this.idSolicitudEncomienda = idSolicitudEncomienda;
    }

    public String getNombreApPaternoResidente() {
        return nombreApPaternoResidente;
    }

    public void setNombreApPaternoResidente(String nombreApPaternoResidente) {
        this.nombreApPaternoResidente = nombreApPaternoResidente;
    }

    public String getFechaEncomienda() {
        return fechaEncomienda;
    }

    public void setFechaEncomienda(String fechaEncomienda) {
        this.fechaEncomienda = fechaEncomienda;
    }

    public String getHoraEncomienda() {
        return horaEncomienda;
    }

    public void setHoraEncomienda(String horaEncomienda) {
        this.horaEncomienda = horaEncomienda;
    }
    
}
