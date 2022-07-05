package cl.inacap.pnk.io.entidadesDAO;

import java.util.Date;

/**
 * Clase DAO de la tabla Solicitud visita
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class SolicitudVisita {
    //Atributos de la clase
    private int idSolicitudVisita;
    private int estado;
    private Date fechaVisita;
    private String horaVisita;
    private Cuenta cuenta;
    private Visita visita;

    /**
     * Constructor vacio
     */
    public SolicitudVisita() {
    }

    /**
     * Constructor completo
     * @param idSolicitudVisita Codigo unico
     * @param estado Estado
     * @param fechaVisita Fecha de visita
     * @param horaVisita Hora de vista
     * @param cuenta Cuenta
     * @param visita Vista
     */
    public SolicitudVisita(int idSolicitudVisita, int estado, Date fechaVisita, String horaVisita, Cuenta cuenta, Visita visita) {
        this.idSolicitudVisita = idSolicitudVisita;
        this.estado = estado;
        this.fechaVisita = fechaVisita;
        this.horaVisita = horaVisita;
        this.cuenta = cuenta;
        this.visita = visita;
    }

    //Metodos getter and setter
    public int getIdSolicitudVisita() {
        return idSolicitudVisita;
    }

    public void setIdSolicitudVisita(int idSolicitudVisita) {
        this.idSolicitudVisita = idSolicitudVisita;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(Date fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public String getHoraVisita() {
        return horaVisita;
    }

    public void setHoraVisita(String horaVisita) {
        this.horaVisita = horaVisita;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }
    //Fin metodos getter and setter
}
