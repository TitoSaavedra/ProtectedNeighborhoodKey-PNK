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
    
    /**
     *
     */
    public TarjetaNfc() {
    }

    /**
     *
     * @param uid
     */
    public TarjetaNfc(String uid) {
        this.uid = uid;
    }

    /**
     *
     * @param uid
     * @param estado
     * @param fechaRegistro
     */
    public TarjetaNfc(String uid, int estado, Date fechaRegistro) {
        this.uid = uid;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    /**
     *
     * @return
     */
    public String getUid() {
        return uid;
    }

    /**
     *
     * @param uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     *
     * @return
     */
    public int getEstado() {
        return estado;
    }

    /**
     *
     * @param estado
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     *
     * @return
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     *
     * @param fechaRegistro
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "cl.pnk.dto.TarjetaNfc[ uid=" + uid + " ]";
    }
    
}
