package cl.inacap.pnk.io.entidadesDAO;
/**
 * Clase DAO de la tabla Residente
 * {@link Persona} sub clase
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class Residente extends Persona {
    //Atributos de la clase
    private String fotografia;

    /**
     * Constructor con solo la fotografia
     * @param fotografia Fotografia
     */
    public Residente(String fotografia) {
        this.fotografia = fotografia;
    }

    /**
     * Constructor completo
     * @param idPersona Codigo unico
     * @param estado Estado
     * @param rut Rut
     * @param nombre Nombre
     * @param segundoNom Segundo nombre
     * @param apellidoPa Apellido paterno
     * @param apellidoMa Apellido materno
     * @param correo Correo electronico
     * @param telefono Telefono
     * @param tipoPersona Tipo de persona
     * @param fotografia Fotografia
     */
    public Residente(int idPersona, int estado, String rut, String nombre, String segundoNom, String apellidoPa, String apellidoMa, String correo, int telefono, TipoPersona tipoPersona, String fotografia) {
        super(idPersona, estado, rut, nombre, segundoNom, apellidoPa, apellidoMa, correo, telefono, tipoPersona);
        this.fotografia = fotografia;
    }

    //Metodos getter and setter
    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }
    //Fin metodos getter and setter
}
