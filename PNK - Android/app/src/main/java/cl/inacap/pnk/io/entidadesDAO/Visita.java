package cl.inacap.pnk.io.entidadesDAO;
/**
 * Clase DAO de la tabla Visita
 * {@link Persona} sub clase
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class Visita extends Persona{
    /**
     * Constructor vacio
     */
    public Visita() {
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
     */
    public Visita(int idPersona, int estado, String rut, String nombre, String segundoNom, String apellidoPa, String apellidoMa, String correo, int telefono, TipoPersona tipoPersona) {
        super(idPersona, estado, rut, nombre, segundoNom, apellidoPa, apellidoMa, correo, telefono, tipoPersona);
    }

    public Visita(int idPersona, int estado, String rut, String nombre, String apellidoPa, String apellidoMa,TipoPersona tipoPersona) {
        super(idPersona, estado, rut, nombre, apellidoPa, apellidoMa, tipoPersona);
    }
}
