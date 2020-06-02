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

public class Encomienda  {

    private Integer idEncomienda;
    private String nombre;
    private String descripcion;
    private int estado;

    /**
     *
     */
    public Encomienda() {
    }

    /**
     *
     * @param idEncomienda
     */
    public Encomienda(Integer idEncomienda) {
        this.idEncomienda = idEncomienda;
    }

    /**
     *
     * @param idEncomienda
     * @param nombre
     * @param descripcion
     * @param estado
     */
    public Encomienda(Integer idEncomienda, String nombre, String descripcion, int estado) {
        this.idEncomienda = idEncomienda;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    /**
     *
     * @return
     */
    public Integer getIdEncomienda() {
        return idEncomienda;
    }

    /**
     *
     * @param idEncomienda
     */
    public void setIdEncomienda(Integer idEncomienda) {
        this.idEncomienda = idEncomienda;
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


    @Override
    public String toString() {
        return "cl.pnk.dto.Encomienda[ idEncomienda=" + idEncomienda + " ]";
    }
    
}
