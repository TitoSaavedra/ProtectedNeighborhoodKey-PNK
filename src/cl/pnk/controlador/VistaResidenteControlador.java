/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.controlador;

import cl.pnk.dal.CuentaDal;
import cl.pnk.dal.DireccionDal;
import cl.pnk.dal.PersonaDal;
import cl.pnk.dal.TablaResidenteDal;
import cl.pnk.dao.Cuenta;
import cl.pnk.dao.Direccion;
import cl.pnk.dao.Persona;
import cl.pnk.dao.TablaResidente;
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
import java.util.List;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
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
    private TextField jtfBusquedaP3;
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
    private JFXTextField jtxtClave;
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

    private File imagenPerfilArchivo = new File("src/cl/pnk/imagenes/ImagenPerfilPredeterminada.png");
    private Image imagenPerfil = null;
    private UtilidadesPrograma utilidadesPrograma = new UtilidadesPrograma();
    private File imagenReset = new File("src/cl/pnk/imagenes/ImagenPerfilPredeterminada.png");

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
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

    @FXML
    private void accionBuscarImagen(ActionEvent event) {
        Stage stage = (Stage) this.apPanelPrincipal.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All Images", "*.*");
        fileChooser.getExtensionFilters().add(extFilter);
        this.imagenPerfilArchivo = fileChooser.showOpenDialog(stage);
        if (this.imagenPerfilArchivo != null) {
            this.imagenPerfil = new Image(this.imagenPerfilArchivo.toURI().toString());
            this.clImagenVista.setFill(new ImagePattern(imagenPerfil));
        }
    }

    @FXML
    private void accionBloquearTarjeta(ActionEvent event) {
    }

    @FXML
    private void accionDesbloquearTarjeta(ActionEvent event) {
    }

    @FXML
    private void accionMenuBusqueda(Event event) {
        cambioNombreMenuRuta(submenuResidente.getText());
    }

    @FXML
    private void accionMenuSeguridad(Event event) {
        cambioNombreMenuRuta(submenuSeguridad.getText());
    }

    @FXML
    private void accionMenuListaResidentes(Event event) {
        cambioNombreMenuRuta(submenuLista.getText());
    }

    private void cambioNombreMenuRuta(String menu) {
        txtNombreMenu.setText(menu);
        txtNombreRuta.setText(menu);
    }

    @FXML
    private void accionKeyApretadaP1(KeyEvent event) {
        this.jtfBusquedaP2.setText(this.jtfBusquedaP1.getText());
    }

    @FXML
    private void accionFiltrarP1(ActionEvent event) throws FileNotFoundException {
        String rut = this.jtfBusquedaP1.getText().trim();
        this.textoConfirmacion("", false, 2);
        this.desabilitarTodosTextoError();
        this.resetImagenUsuario();
        if (this.utilidadesPrograma.validarRut(rut)) {
            this.ocultarAdvertencia(this.txtResultadoBusquedaRutP1);
            Persona persona = new PersonaDal().obtenerPersonaRut(rut);
            if (persona.getRut() != null) {
                Direccion direccionPersona = new DireccionDal().obtenerDireccionRut(rut);
                List<Cuenta> cuentas = new CuentaDal().getCuentas();
                Cuenta cuentaRial = new Cuenta();
                for (int i = 0; i < cuentas.size(); i++) {
                    Cuenta cuenta = cuentas.get(i);
                    if (cuenta.getPersona().getRut().equals(rut)) {
                        cuentaRial = cuenta;
                        i = cuentas.size();
                    }
                }
                this.habilitarModElim();
                this.asignarValorJTextField(this.jtxtRut, rut);
                this.asignarValorJTextField(this.jtxtNombre, persona.getNombre());
                this.asignarValorJTextField(this.jtxtSegNombre, persona.getSegNombre());
                this.asignarValorJTextField(this.jtxtApellidoPaterno, persona.getApePaterno());
                this.asignarValorJTextField(this.jtxtApellidoMaterno, persona.getApeMaterno());
                this.asignarValorJTextField(this.jtxtTelefono, persona.getTelefono());
                this.asignarValorJTextField(this.jtxtCorreo, persona.getEmail());
                this.asignarValorJTextField(this.jtxtClave, cuentaRial.getClave());
                this.asignarValorJPasswordField(this.jtxtRepitaClave, cuentaRial.getClave());
                this.asignarValorJTextField(this.jtxtPiso, direccionPersona.getPiso());
                this.asignarValorJTextField(this.jtxtBlock, direccionPersona.getBlock());
                this.asignarValorJTextField(this.jtxtNumeroCasa, direccionPersona.getNumero());
                this.clImagenVista.setFill(new ImagePattern(cuentaRial.getFoto()));
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

    @FXML
    private void accionKeyApretadaP2(KeyEvent event) {
        this.jtfBusquedaP1.setText(this.jtfBusquedaP2.getText());
    }

    @FXML
    private void accionFiltrarP2(ActionEvent event) {
    }

    @FXML
    private void accionFiltrarP3(ActionEvent event) {
    }

    @FXML
    private void accionEliminarResidente(ActionEvent event) {
        if (validarCampos()) {
            String rut = this.jtxtRut.getText();
            Persona persona = new PersonaDal().obtenerPersonaRut(rut);
            new PersonaDal().eliminarPersona(persona);
            this.textoConfirmacion("Residente Eliminado", true, 2);
        }
    }

    @FXML
    private void accionModificarrResidente(ActionEvent event) {
        if (validarCampos()) {
            String rut = this.jtxtRut.getText();
        }
    }

    @FXML
    private void accionAgregarResidente(ActionEvent event) throws IOException {
        if (validarCampos()) {
            String rut = this.jtxtRut.getText();
            this.ingresarResidente(rut);
        }
    }

    private void desabhilitarModElim() {
        this.btnAgregar.setDisable(false);
        this.btnModificar.setDisable(true);
        this.btnEliminar.setDisable(true);
    }

    private void habilitarModElim() {
        this.btnAgregar.setDisable(true);
        this.btnModificar.setDisable(false);
        this.btnEliminar.setDisable(false);
    }

    private void ingresarResidente(String rut) throws FileNotFoundException {
        int idPersona = 0;
        String nombre = this.jtxtNombre.getText();
        String segNombre = this.jtxtNombre.getText();
        String apePaterno = this.jtxtApellidoPaterno.getText();
        String apeMaterno = this.jtxtApellidoMaterno.getText();
        String telefono = this.jtxtTelefono.getText();
        String email = this.jtxtCorreo.getText();
        int estado = 1;
        Persona persona = new Persona(idPersona, rut, nombre, segNombre, apePaterno, apeMaterno, telefono, email, estado);
        new PersonaDal().ingresarPersona(persona);
        int ultimoIdPersona = new PersonaDal().obtenerUltimoIdPersona();
        int idDireccion = 0;
        String piso = this.jtxtPiso.getText();
        String block = this.jtxtBlock.getText();
        String numero = this.jtxtNumeroCasa.getText();
        Persona personaDir = new PersonaDal().obtenerPersonaId(ultimoIdPersona);
        Direccion direccion = new Direccion(piso, block, numero, personaDir);
        new DireccionDal().ingresarDireccion(direccion);
        String clave = this.jtxtClave.getText();
        Cuenta cuenta = new Cuenta(clave, this.imagenPerfil);
        new CuentaDal().ingresarCuenta(cuenta, imagenPerfilArchivo, ultimoIdPersona);
        this.btnEliminar.setDisable(false);
        this.btnModificar.setDisable(false);
        this.btnAgregar.setDisable(true);
        this.textoConfirmacion("Residente agregado", true, 2);
        mostrarDatosTabla();
    }

    private void mostrarDatosTabla() {
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

    private void asignarValorJTextField(JFXTextField jfXTextField, String texto) {
        jfXTextField.setText(texto);
    }

    private void asignarValorJPasswordField(JFXPasswordField jFXPasswordField, String texto) {
        jFXPasswordField.setText(texto);
    }

    private void mostrarAdvertencia(Text texto, String mensaje) {
        texto.setText(mensaje);
        texto.setVisible(true);
    }

    private void ocultarAdvertencia(Text texto) {
        texto.setVisible(false);
    }

    private void desabilitarTodosTextoError() {
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

    private void deshabilitarHabilitarTextoError(Text texto, boolean op) {
        texto.setVisible(op);
    }

    private boolean validarCampos() {
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
        if (clave.equals("") || clave == null) {
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

    private void resetCampos(String rut) {
        this.asignarValorJTextField(this.jtxtRut, rut);
        this.asignarValorJTextField(this.jtxtNombre, "");
        this.asignarValorJTextField(this.jtxtSegNombre, "");
        this.asignarValorJTextField(this.jtxtApellidoPaterno, "");
        this.asignarValorJTextField(this.jtxtApellidoMaterno, "");
        this.asignarValorJTextField(this.jtxtTelefono, "");
        this.asignarValorJTextField(this.jtxtCorreo, "");
        this.asignarValorJTextField(this.jtxtClave, "");
        this.asignarValorJPasswordField(this.jtxtRepitaClave, "");
        this.asignarValorJTextField(this.jtxtPiso, "");
        this.asignarValorJTextField(this.jtxtBlock, "");
        this.asignarValorJTextField(this.jtxtNumeroCasa, "");
    }

    private void resetImagenUsuario() {
        Image perfilPrederminado = new Image(imagenPerfilArchivo.toURI().toString());
        this.clImagenVista.setFill(new ImagePattern(perfilPrederminado));
    }

    private void textoConfirmacion(String texto, boolean op, int color) {
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
}
