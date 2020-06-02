/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dto;

import java.util.Date;


/**
 *
 * @author TitoS
 */

public class TarjetaNfc {

    private String uid;
    private int estado;
    private Date fechaRegistro;
    
    public TarjetaNfc() {
    }

    public TarjetaNfc(String uid) {
        this.uid = uid;
    }

    public TarjetaNfc(String uid, int estado, Date fechaRegistro) {
        this.uid = uid;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "cl.pnk.dto.TarjetaNfc[ uid=" + uid + " ]";
    }
    
}
