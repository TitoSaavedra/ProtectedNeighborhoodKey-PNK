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

public class TipoPersona  {

    private Integer idTipoPersona;
    private String nombre;
    private String descripcion;

    /**
     *
     */
    public TipoPersona() {
    }

    /**
     *
     * @param idTipoPersona
     */
    public TipoPersona(Integer idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    /**
     *
     * @param idTipoPersona
     * @param nombre
     * @param descripcion
     */
    public TipoPersona(Integer idTipoPersona, String nombre, String descripcion) {
        this.idTipoPersona = idTipoPersona;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     *
     * @return
     */
    public Integer getIdTipoPersona() {
        return idTipoPersona;
    }

    /**
     *
     * @param idTipoPersona
     */
    public void setIdTipoPersona(Integer idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
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
    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "cl.pnk.dto.TipoPersona[ idTipoPersona=" + idTipoPersona + " ]";
    }
    
}
