/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dao;

import javafx.scene.image.Image;

/**
 *
 * @author TitoS
 */
public class Cuenta {

    private Integer idCuenta;
    private String clave;
    private int estado;
    private Image foto;
    private Persona persona;
    private TarjetaNfc tarjetaNfc;

    /**
     *
     */
    public Cuenta() {
    }

    /**
     *
     * @param idCuenta
     */
    public Cuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Cuenta(String clave, Image foto) {
        this.clave = clave;
        this.foto = foto;
    }



    /**
     *
     * @param idCuenta
     * @param clave
     * @param estado
     * @param persona
     * @param tarjetaNfc
     */
    public Cuenta(Integer idCuenta, String clave, int estado, Persona persona, TarjetaNfc tarjetaNfc) {
        this.idCuenta = idCuenta;
        this.clave = clave;
        this.estado = estado;
        this.persona = persona;
        this.tarjetaNfc = tarjetaNfc;
    }

    /**
     *
     * @param idCuenta
     * @param clave
     * @param estado
     * @param foto
     * @param persona
     * @param tarjetaNfc
     */
    public Cuenta(Integer idCuenta, String clave, int estado, Image foto, Persona persona, TarjetaNfc tarjetaNfc) {
        this.idCuenta = idCuenta;
        this.clave = clave;
        this.estado = estado;
        this.foto = foto;
        this.persona = persona;
        this.tarjetaNfc = tarjetaNfc;
    }

    /**
     *
     * @return
     */
    public Integer getIdCuenta() {
        return idCuenta;
    }

    /**
     *
     * @param idCuenta
     */
    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    /**
     *
     * @return
     */
    public String getClave() {
        return clave;
    }

    /**
     *
     * @param clave
     */
    public void setClave(String clave) {
        this.clave = clave;
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
    public Image getFoto() {
        return foto;
    }

    /**
     *
     * @param foto
     */
    public void setFoto(Image foto) {
        this.foto = foto;
    }

    /**
     *
     * @return
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     *
     * @param persona
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     *
     * @return
     */
    public TarjetaNfc getTarjetaNfc() {
        return tarjetaNfc;
    }

    /**
     *
     * @param tarjetaNfc
     */
    public void setTarjetaNfc(TarjetaNfc tarjetaNfc) {
        this.tarjetaNfc = tarjetaNfc;
    }




    @Override
    public String toString() {
        return "Id cuenta"+this.idCuenta+" Rut:"+this.persona.getRut();
    }

}
