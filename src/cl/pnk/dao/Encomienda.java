/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dao;

/**
 * Clase de tipo Encomienda, de la tabla Encomienda
 *
 * @author TitoS
 */

public class Encomienda {

    private Integer idEncomienda;
    private String nombre;
    private String descripcion;
    private int estado;

    public Encomienda() {
    }

    public Encomienda(Integer idEncomienda) {
        this.idEncomienda = idEncomienda;
    }

    public Encomienda(Integer idEncomienda, String nombre, String descripcion, int estado) {
        this.idEncomienda = idEncomienda;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Integer getIdEncomienda() {
        return idEncomienda;
    }

    public void setIdEncomienda(Integer idEncomienda) {
        this.idEncomienda = idEncomienda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "cl.pnk.dto.Encomienda[ idEncomienda=" + idEncomienda + " ]";
    }

}
