/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.controlador;

import cl.pnk.dal.DireccionDal;
import cl.pnk.dal.DireccionPersonaDal;
import cl.pnk.dal.PersonaDal;
import cl.pnk.dal.TablaResidenteDal;
import cl.pnk.dto.Direccion;
import cl.pnk.dto.DireccionPersona;
import cl.pnk.dto.Persona;
import cl.pnk.dto.TablaResidente;
import cl.pnk.utils.GUIUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class Esta clase controla las acciones del archivo FXML
 * VistaVisitante.fxml
 *
 * @author TitoS
 */
public class VistaVisitanteControlaldor implements Initializable {

    @FXML
    private Text txtNombreRuta;
    @FXML
    private Text txtResultadoBusquedaRut;
    @FXML
    private Text txtNombreMenu;
    @FXML
    private Tab submenuRegistroVista;
    @FXML
    private Tab submenuSolicitudVisita;
    @FXML
    private TextField jtfBusquedaVisita;
    @FXML
    private JFXButton btnFiltroVisita;
    @FXML
    private JFXTextField jtxtRutVisita;
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
    private Text txtRutResidente;
    @FXML
    private Text txtNombreResidente;
    @FXML
    private Text txtApePaternoResidente;
    @FXML
    private Text txtApeMaternoResidente;
    @FXML
    private Text txtPisoResidente;
    @FXML
    private Text txtBlockResidente;
    @FXML
    private Text txtNumeroResidente;
    @FXML
    private TextField jtfFiltroResidente;
    @FXML
    private TableView<TablaResidente> tvTablaResidentes;
    @FXML
    private TableColumn<TablaResidente, String> rowRut;
    @FXML
    private TableColumn<TablaResidente, String> rowNombre;
    @FXML
    private TableColumn<TablaResidente, String> rowApPaterno;
    @FXML
    private TableColumn<TablaResidente, String> rowDireccion;
    @FXML
    private JFXTextField jtxtNombreVisita;
    @FXML
    private JFXTextField jtxtSegNombreVisita;
    @FXML
    private JFXTextField jtxtApellidoPaternoVisita;
    @FXML
    private JFXTextField jtxtApellidoMaternoVisita;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXButton btnModificar;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private TableView<?> tvSolicitudesPendiente;
    @FXML
    private TableView<?> tvSolicitudesHistorial;


    /**
     * Initializes the controller class.
     *
     * @param url es propio de java
     * @param rb es un archivo propio de java, que contiene los datos de
     * localización especificos
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarDatosTabla();
        GUIUtils.autoFitTable(tvTablaResidentes);
    }

    @FXML
    private void accionRegistroVista(Event event) {
        cambioNombreMenuRuta(submenuRegistroVista.getText());
    }

    @FXML
    private void accionSolicitudVisita(Event event) {
        cambioNombreMenuRuta(submenuSolicitudVisita.getText());
    }

    private void cambioNombreMenuRuta(String menu) {
        txtNombreMenu.setText(menu);
        txtNombreRuta.setText(menu);
    }

    @FXML
    private void accionFiltrarVisita(ActionEvent event) {
    }

    @FXML
    private void tvMouseCliqueado(MouseEvent event) {
        if (event.getClickCount() == 2 && !event.isConsumed()) {
            try {
                event.consume();
                String rut = String.valueOf(this.tvTablaResidentes.getSelectionModel().getSelectedItem().getRut());
                this.datosResidente(rut);
            } catch (Exception e) {
            }
        }
    }


    @FXML
    private void accionFiltroResidentes(KeyEvent event) {
        ObservableList<TablaResidente> listaTablaResidentes = new TablaResidenteDal().obtenerTablaResidentesFiltrada(this.jtfFiltroResidente.getText().trim());
        this.rowRut.setCellValueFactory(new PropertyValueFactory<>("rut"));
        this.rowNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.rowApPaterno.setCellValueFactory(new PropertyValueFactory<>("apPaterno"));
        this.rowDireccion.setCellValueFactory(new PropertyValueFactory<>("dir"));
        this.tvTablaResidentes.setItems(listaTablaResidentes);
    }

    public void mostrarDatosTabla() {
        ObservableList<TablaResidente> listaTablaResidentes = new TablaResidenteDal().obtenerTablaResidentes();
        this.rowRut.setCellValueFactory(new PropertyValueFactory<>("rut"));
        this.rowNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.rowApPaterno.setCellValueFactory(new PropertyValueFactory<>("apPaterno"));
        this.rowDireccion.setCellValueFactory(new PropertyValueFactory<>("dir"));
        this.tvTablaResidentes.setItems(listaTablaResidentes);
    }

    private void datosResidente(String rut) {
        Persona residente = new PersonaDal().obtenerPersonaRut(rut);
        this.txtRutResidente.setText(residente.getRut());
        this.txtNombreResidente.setText(residente.getNombre());
        this.txtApePaternoResidente.setText(residente.getApePaterno());
        this.txtApeMaternoResidente.setText(residente.getApeMaterno());
        this.txtRutResidente.setVisible(true);
        this.txtNombreResidente.setVisible(true);
        this.txtApePaternoResidente.setVisible(true);
        this.txtApeMaternoResidente.setVisible(true);
        DireccionPersona direccionPersona = new DireccionPersonaDal().obtenerDireccionPersona(residente.getIdPersona());
        Direccion direccion = new DireccionDal().obtenerDireccion(direccionPersona.getIdDireccion());
        this.txtPisoResidente.setVisible(true);
        this.txtBlockResidente.setVisible(true);
        this.txtNumeroResidente.setVisible(true);
        this.txtPisoResidente.setText(direccion.getPiso());
        this.txtBlockResidente.setText(direccion.getBlock());
        this.txtNumeroResidente.setText(direccion.getNumero());
    }

    @FXML
    private void accionEliminarVisita(ActionEvent event) {
    }

    @FXML
    private void accionModificarVisita(ActionEvent event) {
    }

    @FXML
    private void accionAgregarVisita(ActionEvent event) {
    }
}
