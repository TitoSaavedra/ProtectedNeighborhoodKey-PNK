package cl.inacap.pnk.io.entidadesDAO;

import java.util.Date;

public class Sesion {

    //Atributos de la clase
    int idSesion;
    String dispositivo;
    String direccionIp;
    Date fechaSesion;
    String horaSesion;
    int idCuenta;

    public Sesion(int idSesion) {
        this.idSesion = idSesion;
    }

    public Sesion(int idSesion, String dispositivo, String direccionIp, Date fechaSesion, String horaSesion, int idCuenta) {
        this.idSesion = idSesion;
        this.dispositivo = dispositivo;
        this.direccionIp = direccionIp;
        this.fechaSesion = fechaSesion;
        this.horaSesion = horaSesion;
        this.idCuenta = idCuenta;
    }

    public Sesion() {

    }

    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public Date getFechaSesion() {
        return fechaSesion;
    }

    public void setFechaSesion(Date fechaSesion) {
        this.fechaSesion = fechaSesion;
    }

    public String getHoraSesion() {
        return horaSesion;
    }

    public void setHoraSesion(String horaSesion) {
        this.horaSesion = horaSesion;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }
}
