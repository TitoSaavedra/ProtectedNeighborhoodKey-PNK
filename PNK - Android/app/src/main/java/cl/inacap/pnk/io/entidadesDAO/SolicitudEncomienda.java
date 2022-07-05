package cl.inacap.pnk.io.entidadesDAO;

/**
 * Clase DAO de la tabla SolicitudEncomienda
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class SolicitudEncomienda {
    //Atributos de la clase
    private int idSolicitudEncomienda;
    private int estado;
    private String fechaEntrega;
    private String horaEntrega;
    private Cuenta cuenta;
    private Encomienda encomienda;

    /**
     * Constructor vacio
     */
    public SolicitudEncomienda() {
    }

    /**
     * Constructor completo
     * @param idSolicitudEncomienda Codigo unico
     * @param estado Estado
     * @param fechaEntrega Fecha de entrega
     * @param horaEntrega Hora de entrega
     * @param cuenta Cuenta
     * @param encomienda Encomienda
     */
    public SolicitudEncomienda(int idSolicitudEncomienda, int estado, String fechaEntrega, String horaEntrega, Cuenta cuenta, Encomienda encomienda) {
        this.idSolicitudEncomienda = idSolicitudEncomienda;
        this.estado = estado;
        this.fechaEntrega = fechaEntrega;
        this.horaEntrega = horaEntrega;
        this.cuenta = cuenta;
        this.encomienda = encomienda;
    }

    //Metodos getter and setter
    public int getIdSolicitudEncomienda() {
        return idSolicitudEncomienda;
    }

    public void setIdSolicitudEncomienda(int idSolicitudEncomienda) {
        this.idSolicitudEncomienda = idSolicitudEncomienda;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Encomienda getEncomienda() {
        return encomienda;
    }

    public void setEncomienda(Encomienda encomienda) {
        this.encomienda = encomienda;
    }
    //Fin metodos getter and setter
}
