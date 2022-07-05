package cl.inacap.pnk.io.entidadesDAO;

import java.io.Serializable;

/**
 * Clase DAO de la tabla Persona
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class Persona{
    //Atributos de la clase
    private int idPersona;
    private int estado;
    private String rut;
    private String nombre;
    private String segundoNom;
    private String apellidoPa;
    private String apellidoMa;
    private String correo;
    private int telefono;
    private TipoPersona tipoPersona;

    /**
     * Constructor vacio
     */
    public Persona() {
    }

    /**
     * Constructor con solo el id
     * @param idPersona Codigo unico
     */
    public Persona(int idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * Constructor completo
     * @param idPersona Codigo unico
     * @param estado Estado
     * @param rut Rut
     * @param nombre Nombre
     * @param segundoNom Segundo nombre
     * @param apellidoPa Apellido paterno
     * @param apellidoMa Apellido materno
     * @param correo Correo electronico
     * @param telefono Telefono
     * @param tipoPersona Tipo de persona
     */
    public Persona(int idPersona, int estado, String rut, String nombre, String segundoNom, String apellidoPa, String apellidoMa, String correo, int telefono, TipoPersona tipoPersona) {
        this.idPersona = idPersona;
        this.estado = estado;
        this.rut = rut;
        this.nombre = nombre;
        this.segundoNom = segundoNom;
        this.apellidoPa = apellidoPa;
        this.apellidoMa = apellidoMa;
        this.correo = correo;
        this.telefono = telefono;
        this.tipoPersona = tipoPersona;
    }

    public Persona(int idPersona, int estado, String rut, String nombre, String apellidoPa, String apellidoMa, TipoPersona tipoPersona) {
        this.idPersona = idPersona;
        this.estado = estado;
        this.rut = rut;
        this.nombre = nombre;
        this.apellidoPa = apellidoPa;
        this.apellidoMa = apellidoMa;
        this.tipoPersona = tipoPersona;
    }

    //Metodos getter and setter
    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public String getSegundoNom() {
        return segundoNom;
    }

    public void setSegundoNom(String segundoNom) {
        this.segundoNom = segundoNom;
    }

    public String getApellidoPa() {
        return apellidoPa;
    }

    public void setApellidoPa(String apellidoPa) {
        this.apellidoPa = apellidoPa;
    }

    public String getApellidoMa() {
        return apellidoMa;
    }

    public void setApellidoMa(String apellidoMa) {
        this.apellidoMa = apellidoMa;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
    //Fin metodos getter and setter
}
