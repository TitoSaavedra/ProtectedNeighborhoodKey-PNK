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
import cl.pnk.dto.Cuenta;
import cl.pnk.dto.Direccion;
import cl.pnk.dto.Persona;
import cl.pnk.dto.TablaResidente;
import cl.pnk.utils.UtilidadesPrograma;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    private JFXTextField jtxtRepitaClave;
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
    private Text txtClavesNoCoinciden;
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

    File imagenPerfilArchivo = new File("src/cl/pnk/imagenes/ImagenPerfilPredeterminada.png");
    Image imagenPerfil = null;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.jfxTabPane.getTabs().remove(submenuSeguridad);
        this.txtClavesNoCoinciden.setVisible(false);
        this.btnEliminar.setDisable(true);
        this.btnModificar.setDisable(true);
        Image perfilPrederminado = new Image(imagenPerfilArchivo.toURI().toString());
        this.clImagenVista.setFill(new ImagePattern(perfilPrederminado));
        mostrarDatosTabla();
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
    private void accionFiltrarP1(ActionEvent event) {
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
    private void jtxtRevisarClave(KeyEvent event) {
    }

    @FXML
    private void accionEliminarResidente(ActionEvent event) {
    }

    @FXML
    private void accionModificarrResidente(ActionEvent event) {
    }

    @FXML
    private void accionAgregarResidente(ActionEvent event) throws IOException {
        int idPersona = 0;
        String rut = this.jtxtRut.getText();
        String nombre = this.jtxtNombre.getText();
        String segNombre = this.jtxtNombre.getText();
        String apePaterno = this.jtxtApellidoPaterno.getText();
        String apeMaterno = this.jtxtApellidoPaterno.getText();
        int telefono = Integer.parseInt(this.jtxtTelefono.getText());
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
}
