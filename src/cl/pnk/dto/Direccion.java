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

public class Direccion  {


    private Integer idDireccion;
    private int piso;
    private int block;
    private int numero;

    public Direccion() {
    }

    public Direccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Direccion(Integer idDireccion, int piso, int block, int numero) {
        this.idDireccion = idDireccion;
        this.piso = piso;
        this.block = block;
        this.numero = numero;
    }

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }


    @Override
    public String toString() {
        return "cl.pnk.dto.Direccion[ idDireccion=" + idDireccion + " ]";
    }
    
}
