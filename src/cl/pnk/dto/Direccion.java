/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.dto;


/**
 * Clase de tipo Direccion, de la tabla Direccion
 *
 * @author TitoS
 */
public class Direccion  {


    private Integer idDireccion;
    private String piso;
    private String block;
    private String numero;
    private Persona persona;

   
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


    public Direccion(Integer idDireccion, String piso, String block, String numero, Persona persona) {
        this.idDireccion = idDireccion;
        this.piso = piso;
        this.block = block;
        this.numero = numero;
        this.persona = persona;
    }

  
    public Integer getIdDireccion() {
        return idDireccion;
    }

  
    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

 
    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

  
    public String getBlock() {
        return block;
    }


    public void setBlock(String block) {
        this.block = block;
    }

 
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "cl.pnk.dto.Direccion[ idDireccion=" + idDireccion + " ]";
    }
    
}
