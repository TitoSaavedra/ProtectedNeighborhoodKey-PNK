package cl.inacap.pnk.io.entidadesDAO;

import java.util.Date;
/**
 * Clase DAO de la tabla Tarjeta NFC
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class TarjetaNFC {
    //Atributos de la clase
    private String UID;
    private int estado;
    private Date fechaRegistro;

    /**
     * Constructo vacio
     */
    public TarjetaNFC() {
    }

    /**
     * Constructor con solo el UID
     * @param UID Codigo de verficacion
     */
    public TarjetaNFC(String UID) {
        this.UID = UID;
    }

    /**
     * Constructor completo
     * @param UID Codigo de verificación
     * @param estado Estado
     * @param fechaRegistro Fecha de registro
     */
    public TarjetaNFC(String UID, int estado, Date fechaRegistro) {
        this.UID = UID;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    //Metodos getter and setter
    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    //Fin metodos getter and setter
}
