package cl.inacap.pnk.io.entidadesDAO;

import android.graphics.Bitmap;

/**
 * Clase DAO de la tabla cuenta
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class Cuenta {
    //Atributos de la clase
    private int idCuenta;
    private int estado;
    private String clave;
    private Bitmap foto;
    private Persona persona;
    private TarjetaNFC tarjetaNFC;

    /**
     * Constructor vacio
     */
    public Cuenta() {
    }

    /**
     * Constructor con solo el id
     * @param idCuenta Codigo unico
     */
    public Cuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    /**
     * Constructor completo
     * @param idCuenta Codigo unico
     * @param estado Estado
     * @param clave Clave
     * @param foto Foto
     * @param persona Persona con sus datos
     * @param tarjetaNFC Tarjeta NFC con sus datos
     */
    public Cuenta(int idCuenta, int estado, String clave, Bitmap foto, Persona persona, TarjetaNFC tarjetaNFC) {
        this.idCuenta = idCuenta;
        this.estado = estado;
        this.clave = clave;
        this.foto = foto;
        this.persona = persona;
        this.tarjetaNFC = tarjetaNFC;
    }
    //Metodos getter and setter

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public TarjetaNFC getTarjetaNFC() {
        return tarjetaNFC;
    }

    public void setTarjetaNFC(TarjetaNFC tarjetaNFC) {
        this.tarjetaNFC = tarjetaNFC;
    }

    //Fin metodos getter and setter
}
