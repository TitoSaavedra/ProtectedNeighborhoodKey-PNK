/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dao;


/**
 *
 * @author TitoS
 */

public class Persona  {
    
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

    /**
     *
     */
    public Persona() {
    }

    /**
     *
     * @param idPersona
     */
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

    /**
     *
     * @param idPersona
     * @param rut
     * @param nombre
     * @param segNombre
     * @param apePaterno
     * @param apeMaterno
     * @param telefono
     * @param email
     * @param estado
     * @param tipoPersona
     */
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

    /**
     *
     * @return
     */
    public Integer getIdPersona() {
        return idPersona;
    }

    /**
     *
     * @param idPersona
     */
    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    /**
     *
     * @return
     */
    public String getRut() {
        return rut;
    }

    /**
     *
     * @param rut
     */
    public void setRut(String rut) {
        this.rut = rut;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getSegNombre() {
        return segNombre;
    }

    /**
     *
     * @param segNombre
     */
    public void setSegNombre(String segNombre) {
        this.segNombre = segNombre;
    }

    /**
     *
     * @return
     */
    public String getApePaterno() {
        return apePaterno;
    }

    /**
     *
     * @param apePaterno
     */
    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    /**
     *
     * @return
     */
    public String getApeMaterno() {
        return apeMaterno;
    }

    /**
     *
     * @param apeMaterno
     */
    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    /**
     *
     * @return
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     *
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
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
    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    /**
     *
     * @param tipoPersona
     */
    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    @Override
    public String toString() {
        return "cl.pnk.dto.Persona[ idPersona=" + idPersona + " ]";
    }
    
}
