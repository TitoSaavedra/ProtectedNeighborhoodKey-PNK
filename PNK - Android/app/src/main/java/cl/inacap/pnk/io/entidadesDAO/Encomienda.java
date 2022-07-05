package cl.inacap.pnk.io.entidadesDAO;

import java.util.Date;

/**
 * Clase DAO de la tabla encomienda
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class Encomienda {
    //Atributos de la clase
    private int idEncomienda;
    private int estado;
    private String nombre;
    private String descripcion;
    private Date fechaRegistroEncomienda;

    /**
     * Constructor vacio
     * @param idEncomienda Codigo unico
     */
    public Encomienda(int idEncomienda) {
        this.idEncomienda = idEncomienda;
    }

    /**
     * Constructor completo
     * @param idEncomienda Codigo unico
     * @param estado Estado
     * @param nombre Nombre
     * @param descripcion Descripcion
     * @param fechaRegistroEncomienda Fecha de registro de encomienda
     */
    public Encomienda(int idEncomienda, int estado, String nombre, String descripcion, Date fechaRegistroEncomienda) {
        this.idEncomienda = idEncomienda;
        this.estado = estado;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaRegistroEncomienda = fechaRegistroEncomienda;
    }

    //Metodos getter and setter
    public int getIdEncomienda() {
        return idEncomienda;
    }

    public void setIdEncomienda(int idEncomienda) {
        this.idEncomienda = idEncomienda;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public Date getFechaRegistroEncomienda() {
        return fechaRegistroEncomienda;
    }

    public void setFechaRegistroEncomienda(Date fechaRegistroEncomienda) {
        this.fechaRegistroEncomienda = fechaRegistroEncomienda;
    }
    //Fin metodos getter and setter
}
