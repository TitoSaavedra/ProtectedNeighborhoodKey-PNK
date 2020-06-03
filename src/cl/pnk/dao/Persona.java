/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dao;

/**
 * Clase de tipo Persona, de la tabla Persona
 *
 * @author TitoS
 */
public class Persona {

    private Integer idPersona;
    private String rut;
    private String nombre;
    private String segNombre;
    private String apePaterno;
    private String apeMaterno;
    private String telefono;
    private String email;
    private int estado;
    private TipoPersona tipoPersona;

    public Persona() {
    }

    public Persona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Persona(Integer idPersona, String rut, String nombre, String segNombre, String apePaterno, String apeMaterno, String telefono, String email, int estado) {
        this.idPersona = idPersona;
        this.rut = rut;
        this.nombre = nombre;
        this.segNombre = segNombre;
        this.apePaterno = apePaterno;
        this.apeMaterno = apeMaterno;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
    }

    public Persona(Integer idPersona, String rut, String nombre, String segNombre, String apePaterno, String apeMaterno, String telefono, String email, int estado, TipoPersona tipoPersona) {
        this.idPersona = idPersona;
        this.rut = rut;
        this.nombre = nombre;
        this.segNombre = segNombre;
        this.apePaterno = apePaterno;
        this.apeMaterno = apeMaterno;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
        this.tipoPersona = tipoPersona;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSegNombre() {
        return segNombre;
    }

    public void setSegNombre(String segNombre) {
        this.segNombre = segNombre;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    @Override
    public String toString() {
        return "cl.pnk.dto.Persona[ idPersona=" + idPersona + " ]";
    }

}
