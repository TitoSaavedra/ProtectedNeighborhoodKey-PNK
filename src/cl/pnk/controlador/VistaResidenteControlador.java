/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.controlador;

import cl.pnk.dal.CuentaDal;
import cl.pnk.dal.DireccionDal;
import cl.pnk.dal.DireccionPersonaDal;
import cl.pnk.dal.PersonaDal;
import cl.pnk.dal.TablaResidenteDal;
import cl.pnk.dto.Cuenta;
import cl.pnk.dto.Direccion;
import cl.pnk.dto.DireccionPersona;
import cl.pnk.dto.Persona;
import cl.pnk.dto.TablaResidente;
import cl.pnk.utils.UtilidadesPrograma;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class Esta clase controla las acciones del archivo FXML
 * VistaResidente.fxml
 *
 * @author TitoS
 */
public class VistaResidenteControlador implements Initializable {

    @FXML
    private AnchorPane apPanelPrincipal;
    @FXML
    private Text txtNombreMenu;
    @FXML
    private Text txtNombreRuta;
    @FXML
    private JFXButton btnImagen;
    @FXML
    private Text txtUID;
    @FXML
    private Text txtFechaRegistro;
    @FXML
    private JFXButton btnBloquearTarjeta;
    @FXML
    private JFXButton btnDesbloquearTarjea;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private Tab submenuResidente;
    @FXML
    private Tab submenuSeguridad;
    @FXML
    private Tab submenuLista;
    @FXML
    private TextField jtfBusquedaP1;
    @FXML
    private TextField jtfBusquedaP2;
    @FXML
    private TableView<TablaResidente> tvTablaResidentes;
    @FXML
    private Text txtResultadoBusquedaRutP1;
    @FXML
    private JFXTextField jtxtRut;
    @FXML
    private JFXTextField jtxtNombre;
    @FXML
    private JFXTextField jtxtSegNombre;
    @FXML
    private JFXTextField jtxtApellidoPaterno;
    @FXML
    private JFXTextField jtxtApellidoMaterno;
    @FXML
    private JFXTextField jtxtTelefono;
    @FXML
    private JFXTextField jtxtCorreo;
    @FXML
    private JFXPasswordField jtxtClave;
    @FXML
    private JFXPasswordField jtxtRepitaClave;
    @FXML
    private JFXTextField jtxtPiso;
    @FXML
    private JFXTextField jtxtBlock;
    @FXML
    private JFXTextField jtxtNumeroCasa;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private Text txtResultadoBusquedaRutP2;
    @FXML
    private JFXTabPane jfxTabPane;
    @FXML
    private JFXButton btnModificar;
    @FXML
    private Circle clImagenVista;

    @FXML
    private TableColumn<TablaResidente, String> rowRut;
    @FXML
    private TableColumn<TablaResidente, String> rowNombre;
    @FXML
    private TableColumn<TablaResidente, String> rowApPaterno;
    @FXML
    private TableColumn<TablaResidente, String> rowApMaterno;
    @FXML
    private TableColumn<TablaResidente, String> rowDireccion;
    @FXML
    private TableColumn<TablaResidente, String> rowTelefono;
    @FXML
    private TableColumn<TablaResidente, String> rowCorreo;

    @FXML
    private Text txtErrorRut;
    @FXML
    private Text txtErrorNombre;
    @FXML
    private Text txtErrorSegNombre;
    @FXML
    private Text txtErrorApPaterno;
    @FXML
    private Text txtErrorApMaterno;
    @FXML
    private Text txtErrorTelefono;
    @FXML
    private Text txtErrorCorreo;
    @FXML
    private Text txtErrorClave;
    @FXML
    private Text txtErrorReingreso;
    @FXML
    private Text txtErrorPiso;
    @FXML
    private Text txtErrorBlock;
    @FXML
    private Text txtErrorNumeroCasa;
    @FXML
    private Text txtConfirmacionAccion;
    @FXML
    private TextField jtxBusquedaFiltrada;
    //Utilidades varias
    private final UtilidadesPrograma utilidadesPrograma = new UtilidadesPrograma();
    //Archivos de imagen y validador
    private File imagenPerfilArchivo = new File("src/cl/pnk/imagenes/ImagenPerfilPredeterminada.png");
    private Image imagenPerfil = null;
    private final File imagenReset = new File("src/cl/pnk/imagenes/ImagenPerfilPredeterminada.png");
    String btnImagenPresionado = "no";

    /**
     * Initializes the controller class.
     *
     * @param url es propio de java
     * @param rb es un archivo propio de java, que contiene los datos de
     * localización especificos
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.txtResultadoBusquedaRutP1.setVisible(false);
        this.jfxTabPane.getTabs().remove(submenuSeguridad);
        this.resetImagenUsuario();
        this.mostrarDatosTabla();
        this.desabilitarTodosTextoError();
        this.desabhilitarModElim();
    }

    /**
     * Este metodo se inicia al apretar el boton Buscar imagen
     *
     * @param event Evento de accion que se genera al apretar el boton buscar
     * imagen. Aqui se carga y se muestra la ventana para buscar archivos dentro
     * de windows, y esta filtrada para que solo se muestren archivos .png y
     * .jpg
     *
     * Al seleccionar el archivo se guarda la imagen en imagenPerfil y la ruta
     * del archivo en imagenPerfilArchivo
     */
    @FXML
    public void accionBuscarImagen(ActionEvent event) {
        Stage stage = (Stage) this.apPanelPrincipal.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("png y jpg", "*.png", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        this.imagenPerfilArchivo = fileChooser.showOpenDialog(stage);
        if (this.imagenPerfilArchivo != null) {
            this.imagenPerfil = new Image(this.imagenPerfilArchivo.toURI().toString());
            this.clImagenVista.setFill(new ImagePattern(imagenPerfil));
        }
        this.btnImagenPresionado = "si";
    }

    @FXML
    private void accionBloquearTarjeta(ActionEvent event) {
    }

    @FXML
    private void accionDesbloquearTarjeta(ActionEvent event) {
    }

    /**
     * Este metodo se inicia al apretar el boton MenuBusqueda
     *
     * @param event Evento de accion que se genera al apretar el Menu Busqueda.
     * Aqui se cambian los titulos de los textos que indican en menu que esta en
     * pantalla mediante el metodo cambioNombreMenuRuta
     *
     */
    @FXML
    public void accionMenuBusqueda(Event event) {
        cambioNombreMenuRuta(submenuResidente.getText());
    }

    @FXML
    private void accionMenuSeguridad(Event event) {
        cambioNombreMenuRuta(submenuSeguridad.getText());
    }

    /**
     * Este metodo se inicia al apretar el boton Menu lista residentes
     *
     * @param event Evento de accion que se genera al apretar el Menu lista
     * residentes. Aqui se cambian los titulos de los textos que indican en menu
     * que esta en pantalla mediante el metodo cambioNombreMenuRuta
     *
     */
    @FXML
    public void accionMenuListaResidentes(Event event) {
        cambioNombreMenuRuta(submenuLista.getText());
    }

    /**
     * Este metodo es para cambiar el nombre de la ruta y el nombre del menu de
     * los titulos presentes en la pantalla
     *
     * @param menu Es el texto que se le asignara a la ruta y nombre del menu
     *
     */
    private void cambioNombreMenuRuta(String menu) {
        txtNombreMenu.setText(menu);
        txtNombreRuta.setText(menu);
    }

    /**
     * Este metodo se inicia al presionar una tecla en KeyApretadaP1
     *
     * @param event Evento de accion que se genera al precionar una tecla en el
     * jtxfield rut. Ingresa el mismo rut en el jtxfiel rut de la otra ventana
     *
     */
    @FXML
    public void accionKeyApretadaP1(KeyEvent event) {
        this.jtfBusquedaP2.setText(this.jtfBusquedaP1.getText());
    }

    /**
     * Este metodo se inicia al presionar al precionar el boton busqueda
     *
     * @param event Evento de accion que se genera al precionar el boton de
     * busqueda de rut. inciia el metodo filtrarPorRut();
     * @throws FileNotFoundException si el File de la imagen esta vacio.
     */
    @FXML
    public void accionFiltrarP1(ActionEvent event) throws FileNotFoundException {
        this.filtrarPorRut();
        this.btnImagenPresionado = "no";
    }

    @FXML
    private void accionKeyApretadaP2(KeyEvent event) {
        this.jtfBusquedaP1.setText(this.jtfBusquedaP2.getText());
    }

    @FXML
    private void accionFiltrarP2(ActionEvent event) {
    }

    /**
     * Este metodo se inicia al presionar al precionar el boton eliminar
     * residente
     *
     * @param event Evento de accion que se genera al precionar el boton de
     * eliminar residente. inciia el metodo validarCampos(), Luego busca en la
     * bd al residente, para luego por medio de PersonaDal eliminar el
     * residente.
     * @throws java.io.FileNotFoundException
     *
     * @see PersonaDal
     */
    @FXML
    public void accionEliminarResidente(ActionEvent event) throws FileNotFoundException {
        if (validarCampos()) {
            String rut = this.jtxtRut.getText();
            Persona persona = new PersonaDal().obtenerPersonaRut(rut,"2");
            Cuenta cuenta = new CuentaDal().getCuentaPersona(persona.getIdPersona());
            new PersonaDal().eliminarPersona(persona);
            new CuentaDal().eliminarCuenta(cuenta.getIdCuenta());
            this.textoConfirmacion("Residente Eliminado", true, 1);
            mostrarDatosTabla();
        }
    }

    /**
     * Este metodo se inicia al presionar al precionar el boton modificar
     * residente
     *
     * @param event Evento de accion que se genera al precionar el boton de
     * modificar residente. incia el metodo validarCampos(), Luego busca en la
     * bd al residente, para luego por medio de PersonaDal modificar el
     * residente.
     * @throws FileNotFoundException si el File de la imagen esta vacio.
     * @see PersonaDal
     */
    @FXML
    public void accionModificarrResidente(ActionEvent event) throws FileNotFoundException {
        if (validarCampos()) {
            String rut = this.jtxtRut.getText();
            Persona persona = new PersonaDal().obtenerPersonaRut(rut,"2");
            if (validarModificiarCorreo(this.jtxtCorreo.getText().trim().toLowerCase(), persona)) {
                this.modificarResidente(persona);
                mostrarDatosTabla();
            }
        }
    }

    /**
     * Este metodo se inicia al presionar al precionar el boton agregar
     * residente
     *
     * @param event Evento de accion que se genera al precionar el boton de
     * agregar residente. incia el metodo validarCampos(), luego inicia el
     * metodo ingresarResidente enviado el rut rescatado del textField rut
     * @throws FileNotFoundException si el File de la imagen esta vacio.
     */
    @FXML
    public void accionAgregarResidente(ActionEvent event) throws IOException {
        if (validarCampos()) {
            if (validarIngresoCorreo(this.jtxtCorreo.getText().trim().toLowerCase())) {
                String rut = this.jtxtRut.getText();
                this.ingresarResidente(rut);
                mostrarDatosTabla();
            }
        }
    }

    /**
     * Este metodo desabilita los botonoes eliminar residente y modificar
     * residente tambien habilita el ingresar residente
     */
    public void desabhilitarModElim() {
        this.btnAgregar.setDisable(false);
        this.btnModificar.setDisable(true);
        this.btnEliminar.setDisable(true);
    }

    /**
     * Este metodo habilita los botonoes eliminar residente y modificar
     * residente tambien desahabilita el ingresar residente
     */
    public void habilitarModElim() {
        this.btnAgregar.setDisable(true);
        this.btnModificar.setDisable(false);
        this.btnEliminar.setDisable(false);
    }

    /**
     * Este metodo modifica el residente.
     *
     * Este metodo mofifica al residente, crea un residente y lo modifica
     * mendiante PersonaDal Luego modifica la cuenta del residente mediante
     * Cuenta Dal Finalizando modifica la direccion del residente mediante
     * DireccionDal
     *
     * @throws FileNotFoundException si el File de la imagen esta vacio.
     * @param residente este es un objeto de la clase Persona, contiene todos
     * los datos de la persona
     * @see Persona
     * @see PersonaDal
     * @see CuentaDal
     * @see DireccionDal
     */
    public void modificarResidente(Persona residente) throws FileNotFoundException {
        String nombre = this.jtxtNombre.getText();
        String segNombre = this.jtxtSegNombre.getText();
        String apePaterno = this.jtxtApellidoPaterno.getText();
        String apeMaterno = this.jtxtApellidoMaterno.getText();
        String telefono = this.jtxtTelefono.getText();
        String email = this.jtxtCorreo.getText();
        String piso = this.jtxtPiso.getText();
        String block = this.jtxtBlock.getText();
        String numero = this.jtxtNumeroCasa.getText();
        residente.setNombre(nombre);
        residente.setSegNombre(segNombre);
        residente.setApePaterno(apePaterno);
        residente.setApeMaterno(apeMaterno);
        residente.setTelefono(telefono);
        residente.setEmail(email);
        new PersonaDal().modificarPersona(residente);
        Cuenta cuenta = new CuentaDal().getCuentaPersona(residente.getIdPersona());
        cuenta.setClave(this.jtxtRepitaClave.getText());
        if (this.btnImagenPresionado.equals("si")) {
            new CuentaDal().modificarCuentaConFoto(cuenta, imagenPerfilArchivo);
        } else {
            new CuentaDal().modificarCuentaSinFoto(cuenta);
        }
        DireccionPersona direccionPersona = new DireccionPersonaDal().obtenerDireccionPersona(residente.getIdPersona());
        Direccion direccion = new Direccion(direccionPersona.getIdDireccion(), piso, block, numero);
        new DireccionDal().modificarDireccion(direccion);
        this.textoConfirmacion("Residente Modificado", true, 2);
        resetCampos("");
        resetImagenUsuario();
    }

    /**
     * Este metodo ingresar Residente.
     *
     * Este metodo ingresar Residente, crea un residente mendiante PersonaDal
     * Luego modifica la cuenta del residente mediante Cuenta Dal Finalizando
     * modifica la direccion del residente mediante DireccionDal
     *
     * @param rut es el rut de la ersona que se desea ingresar
     * @throws FileNotFoundException si el File de la imagen esta vacio.
     *
     * @see Persona
     * @see PersonaDal
     * @see CuentaDal
     * @see DireccionDal
     */
    public void ingresarResidente(String rut) throws FileNotFoundException {
        int idPersona = 0;
        String nombre = this.jtxtNombre.getText();
        String segNombre = this.jtxtSegNombre.getText();
        String apePaterno = this.jtxtApellidoPaterno.getText();
        String apeMaterno = this.jtxtApellidoMaterno.getText();
        String telefono = this.jtxtTelefono.getText();
        String email = this.jtxtCorreo.getText();
        int estado = 1;
        Persona persona = new Persona(idPersona, rut, nombre, segNombre, apePaterno, apeMaterno, telefono, email, estado);
        new PersonaDal().ingresarPersona(persona);
        Persona ultimaPersona = new PersonaDal().obtenerUltimaPersona("2");
        String piso = this.jtxtPiso.getText();
        String block = this.jtxtBlock.getText();
        String numero = this.jtxtNumeroCasa.getText();
        Direccion direccion = new Direccion(0, piso, block, numero);
        new DireccionDal().ingresarDireccion(direccion);
        Direccion ultimaDireccion = new DireccionDal().obtenerUltimaDireccion();
        DireccionPersona direccionPersona = new DireccionPersona(ultimaDireccion.getIdDireccion(), ultimaPersona.getIdPersona());
        new DireccionPersonaDal().ingresarDireccionPersona(direccionPersona);
        String clave = this.jtxtClave.getText();
        Cuenta cuenta = new Cuenta(clave, this.imagenPerfil);
        if (imagenPerfilArchivo == null) {
            new CuentaDal().ingresarCuenta(cuenta, imagenReset, ultimaPersona.getIdPersona());
        } else {
            new CuentaDal().ingresarCuenta(cuenta, imagenPerfilArchivo, ultimaPersona.getIdPersona());
        }
        this.btnEliminar.setDisable(false);
        this.btnModificar.setDisable(false);
        this.btnAgregar.setDisable(true);
        this.textoConfirmacion("Residente agregado", true, 2);
        mostrarDatosTabla();
    }

    /**
     * Este metodo Muestra los datos en la tabla .
     *
     * @see TablaResidenteDal
     */
    public void mostrarDatosTabla() {
        ObservableList<TablaResidente> listaTablaResidentes = new TablaResidenteDal().obtenerTablaResidentes();
        this.rowRut.setCellValueFactory(new PropertyValueFactory<>("rut"));
        this.rowNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.rowApPaterno.setCellValueFactory(new PropertyValueFactory<>("apPaterno"));
        this.rowApMaterno.setCellValueFactory(new PropertyValueFactory<>("apMaterno"));
        this.rowDireccion.setCellValueFactory(new PropertyValueFactory<>("dir"));
        this.rowTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        this.rowCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        this.tvTablaResidentes.setItems(listaTablaResidentes);
    }

    /**
     * Este metodo asigna un texto a un input txt
     *
     * @param jfXTextField es el JFXTextField que se le quiere cambiar el texto
     * @param texto es la cadena de texto que se le quiere asignar
     */
    public void asignarValorJTextField(JFXTextField jfXTextField, String texto) {
        jfXTextField.setText(texto);
    }

    /**
     * Este metodo a un texto a un input password
     *
     * @param jFXPasswordField es el JFXPasswordField que se le asignara el
     * password
     * @param texto es la cadena de texto que se le quiere asignar
     */
    public void asignarValorJPasswordField(JFXPasswordField jFXPasswordField, String texto) {
        jFXPasswordField.setText(texto);
    }

    /**
     * Este metodo le asigna a un texto el mensaje
     *
     * @param mensaje es la cadena de texto que se le quiere asignar
     * @param texto es el jTextField que se le quere asignar el mensaje
     */
    public void mostrarAdvertencia(Text texto, String mensaje) {
        texto.setText(mensaje);
        texto.setVisible(true);
    }

    /**
     * Este metodo oculta el textto
     *
     * @param texto es el jTextField que se quiere ocultar
     */
    public void ocultarAdvertencia(Text texto) {
        texto.setVisible(false);
    }

    /**
     * Este metodo oculta todos los textos de validación
     */
    public void desabilitarTodosTextoError() {
        this.txtErrorRut.setVisible(false);
        this.txtErrorNombre.setVisible(false);
        this.txtErrorSegNombre.setVisible(false);
        this.txtErrorApPaterno.setVisible(false);
        this.txtErrorApMaterno.setVisible(false);
        this.txtErrorTelefono.setVisible(false);
        this.txtErrorCorreo.setVisible(false);
        this.txtErrorClave.setVisible(false);
        this.txtErrorReingreso.setVisible(false);
        this.txtErrorPiso.setVisible(false);
        this.txtErrorBlock.setVisible(false);
        this.txtErrorNumeroCasa.setVisible(false);
        this.txtConfirmacionAccion.setVisible(false);
    }

    /**
     * Este metodo habilita o desabilita un texto de error
     *
     * @param texto es el jtextField que se queire habilitar o deshabilitar
     * @param op booleano que indica si habilitar o deshabilitar
     */
    public void deshabilitarHabilitarTextoError(Text texto, boolean op) {
        texto.setVisible(op);
    }

    /**
     * Este metodo valida la información del los input text y muestra los
     * mensaes de error para cada input text
     *
     * @return validacion true = Validacion buena todos los campos llenos ||
     * false = validacion mala faltan campos por llenar o la repetircion de
     * contraseñas esta mala
     */
    public boolean validarCampos() {
        boolean validacion = false;
        int val = 1;
        String rut = this.jtxtRut.getText().trim();
        if (rut.equals("") || rut == null) {
            this.txtErrorRut.setText("Falta ingresar el rut");
            this.deshabilitarHabilitarTextoError(this.txtErrorRut, true);
            validacion = false;
            val--;
        } else if (utilidadesPrograma.validarRut(rut) == false) {
            this.txtErrorRut.setText("Rut no valido");
            this.deshabilitarHabilitarTextoError(this.txtErrorRut, true);
            validacion = false;
            val--;
        } else {
            this.txtErrorRut.setText("Falta ingresar el rut");
            this.deshabilitarHabilitarTextoError(this.txtErrorRut, false);
            validacion = true;
        }
        String nombre = this.jtxtNombre.getText().trim();
        if (nombre.equals("") || nombre == null) {
            this.deshabilitarHabilitarTextoError(this.txtErrorNombre, true);
            validacion = false;
            val--;
        } else {
            this.deshabilitarHabilitarTextoError(this.txtErrorNombre, false);
            validacion = true;
        }
        String segNombre = this.jtxtSegNombre.getText().trim();
        if (segNombre.equals("") || segNombre == null) {
            this.deshabilitarHabilitarTextoError(this.txtErrorSegNombre, true);
            validacion = false;
            val--;
        } else {
            this.deshabilitarHabilitarTextoError(this.txtErrorSegNombre, false);
            validacion = true;
        }
        String apePaterno = this.jtxtApellidoPaterno.getText().trim();
        if (apePaterno.equals("") || apePaterno == null) {
            this.deshabilitarHabilitarTextoError(this.txtErrorApPaterno, true);
            validacion = false;
            val--;
        } else {
            this.deshabilitarHabilitarTextoError(this.txtErrorApPaterno, false);
            validacion = true;
        }
        String apeMaterno = this.jtxtApellidoMaterno.getText().trim();
        if (apeMaterno.equals("") || apeMaterno == null) {
            this.deshabilitarHabilitarTextoError(this.txtErrorApMaterno, true);
            validacion = false;
            val--;
        } else {
            this.deshabilitarHabilitarTextoError(this.txtErrorApMaterno, false);
            validacion = true;
        }
        String telefono = this.jtxtTelefono.getText().trim();
        if (telefono.equals("") || telefono == null) {
            this.deshabilitarHabilitarTextoError(this.txtErrorTelefono, true);
            validacion = false;
            val--;
        } else {
            this.deshabilitarHabilitarTextoError(this.txtErrorTelefono, false);
            validacion = true;
        }
        String email = this.jtxtCorreo.getText().trim();
        if (email.equals("") || email == null) {
            this.deshabilitarHabilitarTextoError(this.txtErrorCorreo, true);
            validacion = false;
            val--;
        } else {
            this.deshabilitarHabilitarTextoError(this.txtErrorCorreo, false);
            validacion = true;
        }
        String clave = this.jtxtClave.getText().trim();
        if (clave.equals("") || clave == null) {
            this.deshabilitarHabilitarTextoError(this.txtErrorClave, true);
            validacion = false;
            val--;
        } else {
            this.deshabilitarHabilitarTextoError(this.txtErrorClave, false);
            validacion = true;
        }
        String claveRepetir = this.jtxtRepitaClave.getText().trim();
        if (claveRepetir.equals("") || claveRepetir == null) {
            this.deshabilitarHabilitarTextoError(this.txtErrorReingreso, true);
            validacion = false;
            val--;
        } else {
            this.deshabilitarHabilitarTextoError(this.txtErrorReingreso, false);
            validacion = true;
        }
        if (!(claveRepetir.equals(clave))) {
            this.deshabilitarHabilitarTextoError(this.txtErrorReingreso, true);
            validacion = false;
            val--;
        } else {
            this.deshabilitarHabilitarTextoError(this.txtErrorReingreso, false);
            validacion = true;
        }
        String piso = this.jtxtPiso.getText().trim();
        if (piso.equals("") || piso == null) {
            this.deshabilitarHabilitarTextoError(this.txtErrorPiso, true);
            validacion = false;
            val--;
        } else {
            this.deshabilitarHabilitarTextoError(this.txtErrorPiso, false);
            validacion = true;
        }
        String block = this.jtxtBlock.getText().trim();
        if (block.equals("") || block == null) {
            this.deshabilitarHabilitarTextoError(this.txtErrorBlock, true);
            validacion = false;
            val--;
        } else {
            this.deshabilitarHabilitarTextoError(this.txtErrorBlock, false);
            validacion = true;
        }
        String numero = this.jtxtNumeroCasa.getText().trim();
        if (numero.equals("") || numero == null) {
            this.deshabilitarHabilitarTextoError(this.txtErrorNumeroCasa, true);
            validacion = false;
            val--;
        } else {
            this.deshabilitarHabilitarTextoError(this.txtErrorNumeroCasa, false);
            validacion = true;
        }
        if (val == 1) {
            validacion = true;
        } else {
            validacion = false;
        }
        return validacion;
    }

    /**
     * Este metodo resetea la todos los campos del formulario y asigna el rut en
     * el jtxtRut
     *
     * @param rut Rut que se obtiene del jtxtFiltrarRut
     */
    public void resetCampos(String rut) {
        this.asignarValorJTextField(this.jtxtRut, rut);
        this.asignarValorJTextField(this.jtxtNombre, "");
        this.asignarValorJTextField(this.jtxtSegNombre, "");
        this.asignarValorJTextField(this.jtxtApellidoPaterno, "");
        this.asignarValorJTextField(this.jtxtApellidoMaterno, "");
        this.asignarValorJTextField(this.jtxtTelefono, "");
        this.asignarValorJTextField(this.jtxtCorreo, "");
        this.asignarValorJPasswordField(this.jtxtClave, "");
        this.asignarValorJPasswordField(this.jtxtRepitaClave, "");
        this.asignarValorJTextField(this.jtxtPiso, "");
        this.asignarValorJTextField(this.jtxtBlock, "");
        this.asignarValorJTextField(this.jtxtNumeroCasa, "");
    }

    /**
     * Este metodo resetea la imagen de usuario en la imagen clImagenVista Solo
     * resetea la imagen visual, no la de la bd
     */
    public void resetImagenUsuario() {
        Image perfilPrederminado = new Image(imagenReset.toURI().toString());
        this.clImagenVista.setFill(new ImagePattern(perfilPrederminado));
    }

    /**
     * Este metodo le le asigna un color y un texto al texto de confirmacion que
     * se muestra al agregar, eliminar o modficar un Residente
     *
     * @param texto es el texto de confirmacion
     * @param op es un booleano que indica ocultar o esconder el texto
     * @param color es la opcion de color que se le aplicara al texto
     */
    public void textoConfirmacion(String texto, boolean op, int color) {
        this.txtConfirmacionAccion.setVisible(op);
        this.txtConfirmacionAccion.setText(texto);
        switch (color) {
            case 1:
                this.txtConfirmacionAccion.setFill(Color.web("#FF0000"));
                break;
            case 2:
                this.txtConfirmacionAccion.setFill(Color.web("#0CFF00"));
                break;
            default:
                this.txtConfirmacionAccion.setFill(Color.web("#FF0000"));
        }
    }

    /**
     * Este metodo es para filtrar la tabla, dependiendo el texto que se le
     * ingrese
     *
     * @param event Evento de accion que se genera al precionar las teclas en el
     * jtxtField Filtrar Rut
     * @see TablaResidenteDal
     */
    @FXML
    public void accionFiltrarTabla(KeyEvent event) {
        String text = this.jtxBusquedaFiltrada.getText().trim();
        ObservableList<TablaResidente> listaTablaResidentes = new TablaResidenteDal().obtenerTablaResidentesFiltrada(text);
        this.rowRut.setCellValueFactory(new PropertyValueFactory<>("rut"));
        this.rowNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.rowApPaterno.setCellValueFactory(new PropertyValueFactory<>("apPaterno"));
        this.rowApMaterno.setCellValueFactory(new PropertyValueFactory<>("apMaterno"));
        this.rowDireccion.setCellValueFactory(new PropertyValueFactory<>("dir"));
        this.rowTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        this.rowCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        this.tvTablaResidentes.setItems(listaTablaResidentes);
    }

    /**
     * Este metodo detecta si se hace doble clic en alguna fila, para mandar el
     * residente a la vista de visualisar residente
     *
     * @throws FileNotFoundException si no encuentra el archivo de imagen, por
     * le metodo filtrar por rut.
     * @param event evento que detecta el clic
     * @see TablaResidenteDal
     */
    @FXML
    public void tvMouseCliqueado(MouseEvent event) throws FileNotFoundException {
        if (event.getClickCount() == 2 && !event.isConsumed()) {
            try {
                event.consume();
                this.resetImagenUsuario();
                this.btnImagenPresionado = "no";
                String rut = String.valueOf(this.tvTablaResidentes.getSelectionModel().getSelectedItem().getRut());
                this.jtfBusquedaP1.setText(rut);
                this.filtrarPorRut();
                this.jfxTabPane.getSelectionModel().select(this.submenuResidente);
            } catch (FileNotFoundException e) {
                System.out.println(e.toString());
            }
        }
    }

    /**
     * Este metodo muestra la información del residente obteniendo el rut de
     * jtfBusquedaP1.
     *
     * @throws FileNotFoundException Por si el archivo de imagen esta nulo
     * @see Persona
     * @see PersonaDal
     * @see CuentaDal
     * @see DireccionDal
     */
    public void filtrarPorRut() throws FileNotFoundException {
        String rut = this.jtfBusquedaP1.getText().trim();
        this.textoConfirmacion("", false, 2);
        this.desabilitarTodosTextoError();
        this.resetImagenUsuario();
        if (this.utilidadesPrograma.validarRut(rut)) {
            this.ocultarAdvertencia(this.txtResultadoBusquedaRutP1);
            Persona persona = new PersonaDal().obtenerPersonaRut(rut,"2");
            if (persona.getRut() != null) {
                if (persona.getTipoPersona() == 2) {
                    Cuenta cuenta = new CuentaDal().getCuentaPersona(persona.getIdPersona());
                    DireccionPersona direccionPersona = new DireccionPersonaDal().obtenerDireccionPersona(persona.getIdPersona());
                    Direccion direccion = new DireccionDal().obtenerDireccion(direccionPersona.getIdDireccion());
                    this.habilitarModElim();
                    this.asignarValorJTextField(this.jtxtRut, rut);
                    this.asignarValorJTextField(this.jtxtNombre, persona.getNombre());
                    this.asignarValorJTextField(this.jtxtSegNombre, persona.getSegNombre());
                    this.asignarValorJTextField(this.jtxtApellidoPaterno, persona.getApePaterno());
                    this.asignarValorJTextField(this.jtxtApellidoMaterno, persona.getApeMaterno());
                    this.asignarValorJTextField(this.jtxtTelefono, persona.getTelefono());
                    this.asignarValorJTextField(this.jtxtCorreo, persona.getEmail());
                    this.asignarValorJPasswordField(this.jtxtClave, cuenta.getClave());
                    this.asignarValorJPasswordField(this.jtxtRepitaClave, cuenta.getClave());
                    this.asignarValorJTextField(this.jtxtPiso, direccion.getPiso());
                    this.asignarValorJTextField(this.jtxtBlock, direccion.getBlock());
                    this.asignarValorJTextField(this.jtxtNumeroCasa, direccion.getNumero());
                    if (cuenta.getFoto() != null) {
                        this.clImagenVista.setFill(new ImagePattern(cuenta.getFoto()));
                    } else {
                        this.resetImagenUsuario();
                    }
                } else {
                    this.desabhilitarModElim();
                    this.resetCampos("");
                    this.resetImagenUsuario();
                    this.mostrarAdvertencia(this.txtResultadoBusquedaRutP1, "Solo se permiten residentes");
                }
            } else {
                this.desabhilitarModElim();
                this.resetCampos(rut);
                this.resetImagenUsuario();
                this.mostrarAdvertencia(this.txtResultadoBusquedaRutP1, "No existe el residente");
            }
        } else {
            this.desabhilitarModElim();
            this.resetCampos("");
            this.resetImagenUsuario();
            this.mostrarAdvertencia(this.txtResultadoBusquedaRutP1, "Rut no valido");
        }
    }

    public boolean validarIngresoCorreo(String Email) {
        Persona persona = new PersonaDal().obtenerPersonaCorreo(Email);
        if (persona.getEmail() == null) {
            this.txtErrorCorreo.setText("");
            this.txtErrorCorreo.setVisible(false);
            return true;
        } else {
            this.txtErrorCorreo.setText("Este correo ya esta registrado");
            this.txtErrorCorreo.setVisible(true);
            return false;
        }
    }

    public boolean validarModificiarCorreo(String Email, Persona residente) {
        if (residente.getEmail().toLowerCase().equals(Email)) {
            this.txtErrorCorreo.setText("");
            this.txtErrorCorreo.setVisible(false);
            return true;
        } else {
            Persona persona = new PersonaDal().obtenerPersonaCorreo(Email);
            if (persona.getEmail() == null) {
                this.txtErrorCorreo.setText("");
                this.txtErrorCorreo.setVisible(false);
                return true;
            } else {
                this.txtErrorCorreo.setText("Este correo ya esta registrado");
                this.txtErrorCorreo.setVisible(true);
                return false;
            }
        }
    }
}
