package cl.inacap.pnk.io.entidadesDAO;

import java.util.Date;
/**
 * Clase DAO de la tabla cuenta
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class Notificacion {
    //Atributos de la clase
    private int idNotificacion;
    private String tipoNotificacion;
    private String detalleNotificacion;
    private Date fechaNotificacion;
    private String horaNotificacion;
    private int estado;

    /**
     * Constructor vacio
     */
    public Notificacion() {
    }

    /**
     * Constructor con solo el id
     * @param idNotificacion Codigo unico
     */
    public Notificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    /**
     * Constructor completo
     * @param idNotificacion Codigo unico
     * @param tipoNotificacion Tipo de notificacion
     * @param detalleNotificacion Detalle de notificacion
     * @param fechaNotificacion Fecha de notifiacion
     * @param horaNotificacion Hora de notificacion
     * @param estado Estado
     */
    public Notificacion(int idNotificacion, String tipoNotificacion, String detalleNotificacion, Date fechaNotificacion, String horaNotificacion, int estado) {
        this.idNotificacion = idNotificacion;
        this.tipoNotificacion = tipoNotificacion;
        this.detalleNotificacion = detalleNotificacion;
        this.fechaNotificacion = fechaNotificacion;
        this.horaNotificacion = horaNotificacion;
        this.estado = estado;
    }

    //Metodos getter and setter
    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public String getDetalleNotificacion() {
        return detalleNotificacion;
    }

    public void setDetalleNotificacion(String detalleNotificacion) {
        this.detalleNotificacion = detalleNotificacion;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public String getHoraNotificacion() {
        return horaNotificacion;
    }

    public void setHoraNotificacion(String horaNotificacion) {
        this.horaNotificacion = horaNotificacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    //Fin de metodos getter and setter
}
