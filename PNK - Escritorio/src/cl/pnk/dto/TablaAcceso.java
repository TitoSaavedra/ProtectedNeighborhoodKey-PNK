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
public class TablaAcceso {
    private int idCuenta;
    private String nombreResidente;
    private String ApellidoPaternoMaternoResidente;
    private String direccionResidente;
    private String fechaAcceso;
    private String tipoAcceso;

    public TablaAcceso() {
    }

    public TablaAcceso(int idCuenta, String nombreResidente, String ApellidoPaternoMaternoResidente, String direccionResidente, String fechaAcceso, String tipoAcceso) {
        this.idCuenta = idCuenta;
        this.nombreResidente = nombreResidente;
        this.ApellidoPaternoMaternoResidente = ApellidoPaternoMaternoResidente;
        this.direccionResidente = direccionResidente;
        this.fechaAcceso = fechaAcceso;
        this.tipoAcceso = tipoAcceso;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNombreResidente() {
        return nombreResidente;
    }

    public void setNombreResidente(String nombreResidente) {
        this.nombreResidente = nombreResidente;
    }

    public String getApellidoPaternoMaternoResidente() {
        return ApellidoPaternoMaternoResidente;
    }

    public void setApellidoPaternoMaternoResidente(String ApellidoPaternoMaternoResidente) {
        this.ApellidoPaternoMaternoResidente = ApellidoPaternoMaternoResidente;
    }

    public String getDireccionResidente() {
        return direccionResidente;
    }

    public void setDireccionResidente(String direccionResidente) {
        this.direccionResidente = direccionResidente;
    }

    public String getFechaAcceso() {
        return fechaAcceso;
    }

    public void setFechaAcceso(String fechaAcceso) {
        this.fechaAcceso = fechaAcceso;
    }

    public String getTipoAcceso() {
        return tipoAcceso;
    }

    public void setTipoAcceso(String tipoAcceso) {
        this.tipoAcceso = tipoAcceso;
    }


    
}
