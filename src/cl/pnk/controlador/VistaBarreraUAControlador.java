/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.controlador;

import cl.pnk.dal.CuentaDal;
import cl.pnk.dal.TablaAccesoDal;
import cl.pnk.dto.Cuenta;
import cl.pnk.dto.TablaAcceso;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * FXML Controller class Esta clase controla las acciones del archivo FXML
 * VistaBarreraUA.fxml
 *
 * @author TitoS
 */
public class VistaBarreraUAControlador implements Initializable {

    @FXML
    private Text txtNombreRuta;
    @FXML
    private Text txtNombreMenu;
    @FXML
    private Tab submenuUltimosAccesos;
    @FXML
    private TableView<TablaAcceso> tvRegistroAcceso;
    @FXML
    private Text txtRutResidente;
    @FXML
    private Text txtNombreResidente;
    @FXML
    private Text txtApellidoPaternoResidente;
    @FXML
    private Text txtTelefonoResidente;
    @FXML
    private Text txtDireccionResidente;
    @FXML
    private Circle clImagenPerfil;
    @FXML
    private TableColumn<TablaAcceso, String> rowNombre;
    @FXML
    private TableColumn<TablaAcceso, String> rowApellidos;
    @FXML
    private TableColumn<TablaAcceso, String> rowDireccion;
    @FXML
    private TableColumn<TablaAcceso, String> rowFechaAcceso;
    @FXML
    private TableColumn<TablaAcceso, String> rowTipoAcceso;
    @FXML
    private TextField jtfFiltroAcceso;

    /**
     * Initializes the controller class.
     *
     * @param url es propio de java
     * @param rb es un archivo propio de java, que contiene los datos de
     * localización especificos
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mostrarDatosAcceso();
            // TODO
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VistaBarreraUAControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void accionUltimoAcceso(Event event) throws FileNotFoundException {
        cambioNombreMenuRuta(submenuUltimosAccesos.getText());
    }

    private void cambioNombreMenuRuta(String menu) {
        txtNombreMenu.setText(menu);
        txtNombreRuta.setText(menu);
    }

    @FXML
    private void accionTvCliqueado(MouseEvent event) {
        if (event.getClickCount() == 2 && !event.isConsumed()) {
            try {
                event.consume();
                String idCuenta = String.valueOf(this.tvRegistroAcceso.getSelectionModel().getSelectedItem().getIdCuenta());
                String direccion = String.valueOf(this.tvRegistroAcceso.getSelectionModel().getSelectedItem().getDireccionResidente());
                this.datosResidente(idCuenta, direccion);
            } catch (Exception e) {
            }
        }
    }

    private void datosResidente(String idCuenta, String direccion) throws FileNotFoundException {
        Cuenta cuenta = new CuentaDal().getCuenta(Integer.parseInt(idCuenta));
        this.txtRutResidente.setText(cuenta.getPersona().getRut());
        this.txtNombreResidente.setText(cuenta.getPersona().getNombre());
        this.txtApellidoPaternoResidente.setText(cuenta.getPersona().getApePaterno());
        this.txtTelefonoResidente.setText(cuenta.getPersona().getTelefono());
        this.txtDireccionResidente.setText(direccion);
        this.clImagenPerfil.setFill(new ImagePattern(cuenta.getFoto()));
    }

    private void resetCampos() throws IOException {
        String reset = "";
        this.txtRutResidente.setText(reset);
        this.txtNombreResidente.setText(reset);
        this.txtApellidoPaternoResidente.setText(reset);
        this.txtTelefonoResidente.setText(reset);
        this.txtDireccionResidente.setText(reset);
    }

    @FXML
    private void accionFiltrarTvAcceso(KeyEvent event) throws FileNotFoundException {
        mostrarDatosAccesoFiltrado(this.jtfFiltroAcceso.getText().trim());
    }

    public void mostrarDatosAcceso() throws FileNotFoundException {
        ObservableList<TablaAcceso> tablaAccesos = new TablaAccesoDal().obtenerAccesos();
        this.rowNombre.setCellValueFactory(new PropertyValueFactory<>("nombreResidente"));
        this.rowApellidos.setCellValueFactory(new PropertyValueFactory<>("ApellidoPaternoMaternoResidente"));
        this.rowDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionResidente"));
        this.rowFechaAcceso.setCellValueFactory(new PropertyValueFactory<>("fechaAcceso"));
        this.rowTipoAcceso.setCellValueFactory(new PropertyValueFactory<>("tipoAcceso"));
        this.tvRegistroAcceso.setItems(tablaAccesos);
    }
    public void mostrarDatosAccesoFiltrado(String text) throws FileNotFoundException {
        ObservableList<TablaAcceso> tablaAccesos = new TablaAccesoDal().obtenerAccesosFiltrado(text);
        this.rowNombre.setCellValueFactory(new PropertyValueFactory<>("nombreResidente"));
        this.rowApellidos.setCellValueFactory(new PropertyValueFactory<>("ApellidoPaternoMaternoResidente"));
        this.rowDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionResidente"));
        this.rowFechaAcceso.setCellValueFactory(new PropertyValueFactory<>("fechaAcceso"));
        this.rowTipoAcceso.setCellValueFactory(new PropertyValueFactory<>("tipoAcceso"));
        this.tvRegistroAcceso.setItems(tablaAccesos);
    }

    @FXML
    private void refreshPage(MouseEvent event) {
           if (event.getClickCount() == 3 && !event.isConsumed()) {
            try {
                event.consume();
             this.mostrarDatosAcceso();
            } catch (Exception e) {
            }
        }
    }
}
