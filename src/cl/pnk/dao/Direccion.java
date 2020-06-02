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

public class Direccion  {


    private Integer idDireccion;
    private String piso;
    private String block;
    private String numero;
    private Persona persona;

    /**
     *
     */
    public Direccion() {
    }

    public Direccion(String piso, String block, String numero) {
        this.piso = piso;
        this.block = block;
        this.numero = numero;
    }

    public Direccion(String piso, String block, String numero, Persona persona) {
        this.piso = piso;
        this.block = block;
        this.numero = numero;
        this.persona = persona;
    }

    /**
     *
     * @param idDireccion
     * @param piso
     * @param block
     * @param numero
     * @param persona
     */
    public Direccion(Integer idDireccion, String piso, String block, String numero, Persona persona) {
        this.idDireccion = idDireccion;
        this.piso = piso;
        this.block = block;
        this.numero = numero;
        this.persona = persona;
    }

    /**
     *
     * @return
     */
    public Integer getIdDireccion() {
        return idDireccion;
    }

    /**
     *
     * @param idDireccion
     */
    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    /**
     *
     * @return
     */
    public String getPiso() {
        return piso;
    }

    /**
     *
     * @param piso
     */
    public void setPiso(String piso) {
        this.piso = piso;
    }

    /**
     *
     * @return
     */
    public String getBlock() {
        return block;
    }

    /**
     *
     * @param block
     */
    public void setBlock(String block) {
        this.block = block;
    }

    /**
     *
     * @return
     */
    public String getNumero() {
        return numero;
    }

    /**
     *
     * @param numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
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

    @Override
    public String toString() {
        return "cl.pnk.dto.Direccion[ idDireccion=" + idDireccion + " ]";
    }
    
}
