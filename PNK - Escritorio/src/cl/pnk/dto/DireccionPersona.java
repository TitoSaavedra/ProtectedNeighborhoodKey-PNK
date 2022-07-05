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
public class DireccionPersona {

    private int idDireccion;
    private int idPersona;

    public DireccionPersona() {
    }

    public DireccionPersona(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public DireccionPersona(int idDireccion, int idPersona) {
        this.idDireccion = idDireccion;
        this.idPersona = idPersona;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public String toString() {
        return "DireccionPersona{" + "idDireccion=" + idDireccion + ", idPersona=" + idPersona + '}';
    }

}
