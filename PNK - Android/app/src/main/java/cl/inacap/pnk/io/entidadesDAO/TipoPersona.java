package cl.inacap.pnk.io.entidadesDAO;

/**
 * Clase DAO de la tabla TipoPersona
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class TipoPersona {
    //Atributos de la clase
    private int id_tipo_persona;
    private String nombre;
    private String Descripcion;

    /**
     * Constructor vacio
     */
    public TipoPersona() {
    }

    /**
     * Constructor con solo el id
     * @param id_tipo_persona
     */
    public TipoPersona(int id_tipo_persona) {
        this.id_tipo_persona = id_tipo_persona;
    }

    /**
     * Constructor completo
     * @param id_tipo_persona Codigo unico
     * @param nombre Nombre
     * @param descripcion Descripcion
     */
    public TipoPersona(int id_tipo_persona, String nombre, String descripcion) {
        this.id_tipo_persona = id_tipo_persona;
        this.nombre = nombre;
        Descripcion = descripcion;
    }

    //Metodos getter and setter
    public int getId_tipo_persona() {
        return id_tipo_persona;
    }

    public void setId_tipo_persona(int id_tipo_persona) {
        this.id_tipo_persona = id_tipo_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
    //Fin metodos getter and setter
}
