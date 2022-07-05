package cl.inacap.pnk.io.entidadesDAO;

import java.util.Date;

/**
 * Clase DAO de la tabla Acceso
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class Acceso {
    //Atributos de la clase
    private int idAcceso;
    private int estado;
    private int tipoAcceso;
    private Date fechaAcceso;
    private String horaAcceso;

    /**
     * Constructor para solo el id
     * @param idAcceso codigo unico del acceso
     */
    public Acceso(int idAcceso) {
        this.idAcceso = idAcceso;
    }

    /**
     * Constructor completo
     * @param idAcceso Codigo unico del acceso
     * @param estado Estado del acceso
     * @param tipoAcceso Tipo de acceso
     * @param fechaAcceso Fecha de acceso
     * @param horaAcceso Hora de acceso
     */
    public Acceso(int idAcceso, int estado, int tipoAcceso, Date fechaAcceso, String horaAcceso) {
        this.idAcceso = idAcceso;
        this.estado = estado;
        this.tipoAcceso = tipoAcceso;
        this.fechaAcceso = fechaAcceso;
        this.horaAcceso = horaAcceso;
    }

    //Metodos getter and setter
    public int getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(int idAcceso) {
        this.idAcceso = idAcceso;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getTipoAcceso() {
        return tipoAcceso;
    }

    public void setTipoAcceso(int tipoAcceso) {
        this.tipoAcceso = tipoAcceso;
    }

    public Date getFechaAcceso() {
        return fechaAcceso;
    }

    public void setFechaAcceso(Date fechaAcceso) {
        this.fechaAcceso = fechaAcceso;
    }

    public String getHoraAcceso() {
        return horaAcceso;
    }

    public void setHoraAcceso(String horaAcceso) {
        this.horaAcceso = horaAcceso;
    }
    //Fin metodos getter and setter
}
