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

public class Cuenta  {

  
    private Integer idCuenta;
    private String clave;
    private int estado;
    private String foto;
    private Persona persona;
    private TarjetaNfc tarjetaNfc;

    public Cuenta() {
    }

    public Cuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Cuenta(Integer idCuenta, String clave, int estado, String foto) {
        this.idCuenta = idCuenta;
        this.clave = clave;
        this.estado = estado;
        this.foto = foto;
    }

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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
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
        return "cl.pnk.dto.Cuenta[ idCuenta=" + idCuenta + " ]";
    }
    
}
