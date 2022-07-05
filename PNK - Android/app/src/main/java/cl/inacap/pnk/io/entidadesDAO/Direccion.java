package cl.inacap.pnk.io.entidadesDAO;

/**
 * Clase DAO de la tabla direccion
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class Direccion {
    //Atributos de la clase
    private int idDireccion;
    private int estado;
    private int block;
    private int piso;
    private int numero;
    private Persona persona;

    /**
     * Constructor vacio
     */
    public Direccion() {
    }

    /**
     * Constructor con solo el id
     * @param idDireccion Codigo unico
     */
    public Direccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    /**
     * Constructor completo
     * @param idDireccion Codigo unico
     * @param estado Estado
     * @param block Block
     * @param piso Piso
     * @param numero Numero
     * @param persona Persona con sus datos
     */
    public Direccion(int idDireccion, int estado, int block, int piso, int numero, Persona persona) {
        this.idDireccion = idDireccion;
        this.estado = estado;
        this.block = block;
        this.piso = piso;
        this.numero = numero;
        this.persona = persona;
    }

    //Metodos getter and setter
    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Persona getPersonas() {
        return persona;
    }

    public void setPersonas(Persona persona) {
        this.persona = persona;
    }
    //Fin metodos getter and setter
}
