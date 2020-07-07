/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dto;

import javafx.scene.image.Image;

/**
 * Clase de tipo cuenta, de la tabla Cuenta
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

    // Constructores clase cuenta
    public Cuenta() {
    }

    public Cuenta(String clave) {
        this.clave = clave;
    }

    public Cuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Cuenta(String clave, Image foto) {
        this.clave = clave;
        this.foto = foto;
    }

    public Cuenta(String clave, Image foto, TarjetaNfc tarjetaNfc) {
        this.clave = clave;
        this.foto = foto;
        this.tarjetaNfc = tarjetaNfc;
    }

    public Cuenta(Integer idCuenta, String clave, int estado, Persona persona, TarjetaNfc tarjetaNfc) {
        this.idCuenta = idCuenta;
        this.clave = clave;
        this.estado = estado;
        this.persona = persona;
        this.tarjetaNfc = tarjetaNfc;
    }

    public Cuenta(Integer idCuenta, String clave, int estado, Image foto, Persona persona, TarjetaNfc tarjetaNfc) {
        this.idCuenta = idCuenta;
        this.clave = clave;
        this.estado = estado;
        this.foto = foto;
        this.persona = persona;
        this.tarjetaNfc = tarjetaNfc;
    }
    // get y set clase cuenta

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public TarjetaNfc getTarjetaNfc() {
        return tarjetaNfc;
    }

    public void setTarjetaNfc(TarjetaNfc tarjetaNfc) {
        this.tarjetaNfc = tarjetaNfc;
    }

    @Override
    public String toString() {
        return "Id cuenta" + this.idCuenta + " Rut:" + this.persona.getRut();
    }

}
