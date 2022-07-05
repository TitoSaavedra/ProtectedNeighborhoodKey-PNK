package cl.inacap.pnk.io;

/**
 * Clase con el nombre y campos de las tablas de la base de datos
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class Model {

    /**
     * Interface con el nombre de cada tabla
     */
    public interface Tablas{
        String ACCESO="ACCESO";
        String CUENTA="CUENTA";
        String DIRECCION="DIRECCION";
        String ENCOMIENDA="ENCOMIENDA";
        String NOTIFICACION="NOTIFICACION";
        String PERSONA="PERSONA";
        String TIPO_PERSONA="TIPO_PERSONA";
        String RESIDENTE="RESIDENTE";
        String SOLICITUD_ENCOMIENDA="SOLICITUD_ENCOMIENDA";
        String SOLICITUD_VISITA="SOLICITD_VISITA";
        String TARJETA_NFC="TARJETA_NFC";
    }

    /**
     *Interface con los campos de la tabla Acceso
     */
    public interface ColAcceso{
         String ID_ACCESO="ID_ACCESO";
         String FECHA_ACCESO="FECHA_ACCESO";
         String HORA_ACCESO="HORA_ACCESO";
         String ESTADO="ESTADO";
         String TIPO_ACCESO="TIPO_ACCESO";
         String ID_CUENTA="ID_CUENTA";
    }

    /**
     *Interface con los campos de la tabla Cuenta
     */
    public interface ColCuenta{
        String ID_CUENTA="ID_CUENTA";
        String CLAVE="CLAVE";
        String ESTADO="ESTADO";
        String FOTO="FOTO";
        String ID_PERSONA="ID_PERSONA";
        String UID="UID";
    }

    /**
     *Interface con los campos de la tabla Direccion
     */
    public interface ColDireccion{
        String ID_DIRECCION="ID_DIRECCION";
        String PISO="PISO";
        String BLOCK="BLOCK";
        String NUMERO="NUMERO";
        String ID_PERSONA="ID_PERSONA";
    }

    /**
     *Interface con los campos de la tabla Encomienda
     */
    public interface ColEncomienda{
       String ID_ENCOMIENDA="ID_ENCOMIENDA";
       String NOMBRE="NOMBRE";
       String DESCRIPCION="DESCRIPCION";
       String ESTADO="ESTADO";
    }

    public interface ColNotifacion{

    }

    /**
     *Interface con los campos de la tabla Persona
     */
    public interface ColPersona{
        String ID_PERSONA="ID_PERSONA";
        String RUT="RUT";
        String NOMBRE="NOMBRE";
        String SEG_NOMBRE="SEG_NOMBRE";
        String APE_PATERNO="APE_PATERNO";
        String APE_MATERNO="APE_MATERNO";
        String TELEFONO="TELEFONO";
        String EMAIL="EMAIL";
        String ESTADO="ESTADO";
        String ID_TIPO_PERSONA="ID_TIPO_PERSONA";
    }

    /**
     *Interface con los campos de la tabla Solicitud_encomienda
     */
    public interface ColSolicitud_encomienda{
        String ID_SOLICITUD_ENCOMIENDA="ID_SOLICITUD_ENCOMIENDA";
        String ID_CUENTA="ID_CUENTA";
        String ESTADO="ESTADO";
        String ID_ENCOMIENDA="ID_ENCOMIENDA";
        String FECHA_ENTREGA="FECHA_ENTREGA";
        String HORA_ENTREGA="HORA_ENTREGA";
    }

    /**
     *Interface con los campos de la tabla Solicitud_visita
     */
    public interface ColSolicitud_Visita{
        String ID_SOLICITUD_VISITA="ID_SOLICITUD_VISITA";
        String ID_CUENTA="ID_CUENTA";
        String FECHA_VISITA="FECHA_VISITA";
        String ID_PERSONA="ID_PERSONA";
        String HORA_VISITA="HORA_VISITA";
    }

    /**
     *Interface con los campos de la tabla Tarjeta_NFC
     */
    public interface ColTarjeta_NFC{
        String UID="UID";
        String ESTADO="ESTADO";
        String FECHA_REGISTRO="FECHA_REGISTRO";
    }

    /**
     *Interface con los campos de la tabla Tipo_Persona
     */
    public interface ColTipo_Persona{
        String ID_TIPO_PERSONA="ID_TIPO_PERSONA";
        String NOMBRE="NOMBRE";
        String DESCRIPCION="DESCRIPCION";
    }
}
