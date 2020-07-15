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
public class TablaSolicitudesVisita {

    int idVisita;
    String rutVisita;
    String nombreApPaternoVisita;
    String rutResidente;
    String nombreApPaternoResidente;
    String direccionResidente;
    String fechaVisita;
    
    public TablaSolicitudesVisita() {
    }

    public TablaSolicitudesVisita(int idVisita, String rutVisita, String nombreApPaternoVisita, String rutResidente, String nombreApPaternoResidente, String direccionResidente, String fechaVisita) {
        this.idVisita = idVisita;
        this.rutVisita = rutVisita;
        this.nombreApPaternoVisita = nombreApPaternoVisita;
        this.rutResidente = rutResidente;
        this.nombreApPaternoResidente = nombreApPaternoResidente;
        this.direccionResidente = direccionResidente;
        this.fechaVisita = fechaVisita;
    }



    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
    }
    
    public String getRutVisita() {
        return rutVisita;
    }

    public void setRutVisita(String rutVisita) {
        this.rutVisita = rutVisita;
    }

    public String getNombreApPaternoVisita() {
        return nombreApPaternoVisita;
    }

    public void setNombreApPaternoVisita(String nombreApPaternoVisita) {
        this.nombreApPaternoVisita = nombreApPaternoVisita;
    }

    public String getRutResidente() {
        return rutResidente;
    }

    public void setRutResidente(String rutResidente) {
        this.rutResidente = rutResidente;
    }

    public String getNombreApPaternoResidente() {
        return nombreApPaternoResidente;
    }

    public void setNombreApPaternoResidente(String nombreApPaternoResidente) {
        this.nombreApPaternoResidente = nombreApPaternoResidente;
    }

    public String getDireccionResidente() {
        return direccionResidente;
    }

    public void setDireccionResidente(String direccionResidente) {
        this.direccionResidente = direccionResidente;
    }

    public String getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(String fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    @Override
    public String toString() {
        return "TablaSolicitudesVisita{" + "rutVisita=" + rutVisita + ", nombreApPaternoVisita=" + nombreApPaternoVisita + ", rutResidente=" + rutResidente + ", nombreApPaternoResidente=" + nombreApPaternoResidente + ", direccionResidente=" + direccionResidente + ", fechaVisita=" + fechaVisita + '}';
    }
    
}
