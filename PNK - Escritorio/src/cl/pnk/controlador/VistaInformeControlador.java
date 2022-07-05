package cl.pnk.controlador;

import cl.pnk.dal.TablaAccesoDal;
import cl.pnk.dal.TablaSolicitudesVisitaDal;
import cl.pnk.dto.TablaAcceso;
import cl.pnk.dto.TablaSolicitudesVisita;
import cl.pnk.utils.ExcelExport;
import com.jfoenix.controls.JFXRadioButton;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class Esta clase controla las acciones del archivo FXML
 * VistaInforme.fxml
 *
 * @author TitoS
 */
public class VistaInformeControlador implements Initializable {

    @FXML
    private TableView<TablaAcceso> tvHistorialAcceso;
    @FXML
    private TableColumn<TablaAcceso, String> rowNombreHistorialAcceso;
    @FXML
    private TableColumn<TablaAcceso, String> rowApellidosHistorialAcceso;
    @FXML
    private TableColumn<TablaAcceso, String> rowDireccionHistorialAcceso;
    @FXML
    private TableColumn<TablaAcceso, String> rowFechaAccesoHistorialAcceso;
    @FXML
    private TableColumn<TablaAcceso, String> rowTipoAccesoHistorialAcceso;
    @FXML
    private TextField jtfFiltroAcceso;
    @FXML
    private Text txtNombreMenu;
    @FXML
    private Text txtNombreRuta;
    private ExcelExport excelExport = new ExcelExport();
    @FXML
    private TableView<TablaSolicitudesVisita> tvSolicitudesHistorial;
    @FXML
    private TableColumn<TablaSolicitudesVisita, String> rowNombreApellidoVisitanteHistorial;
    @FXML
    private TableColumn<TablaSolicitudesVisita, String> rowNombreApellidoResidenteHistorial;
    @FXML
    private TableColumn<TablaSolicitudesVisita, String> rowDireccionResidenteHistorial;
    @FXML
    private TableColumn<TablaSolicitudesVisita, String> rowFechaVisitaHistorial;
    @FXML
    private JFXRadioButton rdAceptadas;
    @FXML
    private JFXRadioButton rdRechazadas;
    @FXML
    private JFXRadioButton rdPendientes;
    private ToggleGroup tgHistorialVisitas = new ToggleGroup();
    private int tgSeleccionado = 1;
    private int opcionAgregar = 0;

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
            this.mostrarDatosAcceso();
            this.rdAceptadas.setToggleGroup(tgHistorialVisitas);
            this.rdRechazadas.setToggleGroup(tgHistorialVisitas);
            this.rdPendientes.setToggleGroup(tgHistorialVisitas);
            this.mostrarDatosTablaVisitasFiltrada();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VistaInformeControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void accionFiltrarTvAcceso(KeyEvent event) throws FileNotFoundException {
        this.mostrarDatosAccesoFiltrado(this.jtfFiltroAcceso.getText());
    }

    @FXML
    private void accionExportarHistorialAcceso(ActionEvent event) {
        this.excelExport.export(tvHistorialAcceso);
    }

    public void mostrarDatosAccesoFiltrado(String text) throws FileNotFoundException {
        ObservableList<TablaAcceso> tablaAccesos = new TablaAccesoDal().obtenerAccesosFiltrado(text);
        this.rowNombreHistorialAcceso.setCellValueFactory(new PropertyValueFactory<>("nombreResidente"));
        this.rowApellidosHistorialAcceso.setCellValueFactory(new PropertyValueFactory<>("ApellidoPaternoMaternoResidente"));
        this.rowDireccionHistorialAcceso.setCellValueFactory(new PropertyValueFactory<>("direccionResidente"));
        this.rowFechaAccesoHistorialAcceso.setCellValueFactory(new PropertyValueFactory<>("fechaAcceso"));
        this.rowTipoAccesoHistorialAcceso.setCellValueFactory(new PropertyValueFactory<>("tipoAcceso"));
        this.tvHistorialAcceso.setItems(tablaAccesos);
    }

    public void mostrarDatosAcceso() throws FileNotFoundException {
        ObservableList<TablaAcceso> tablaAccesos = new TablaAccesoDal().obtenerAccesos();
        this.rowNombreHistorialAcceso.setCellValueFactory(new PropertyValueFactory<>("nombreResidente"));
        this.rowApellidosHistorialAcceso.setCellValueFactory(new PropertyValueFactory<>("ApellidoPaternoMaternoResidente"));
        this.rowDireccionHistorialAcceso.setCellValueFactory(new PropertyValueFactory<>("direccionResidente"));
        this.rowFechaAccesoHistorialAcceso.setCellValueFactory(new PropertyValueFactory<>("fechaAcceso"));
        this.rowTipoAccesoHistorialAcceso.setCellValueFactory(new PropertyValueFactory<>("tipoAcceso"));
        this.tvHistorialAcceso.setItems(tablaAccesos);
    }

    @FXML
    private void accionExportarHistorialVisita(ActionEvent event) {
        this.excelExport.export(tvSolicitudesHistorial);
    }

    public void mostrarDatosTablaVisitasFiltrada() throws FileNotFoundException {
        ObservableList<TablaSolicitudesVisita> tablaSolicitudesVisitas = new TablaSolicitudesVisitaDal().obtenerTablaSolicitudesVisita(String.valueOf(this.tgSeleccionado));
        this.rowNombreApellidoVisitanteHistorial.setCellValueFactory(new PropertyValueFactory<>("nombreApPaternoVisita"));
        this.rowNombreApellidoResidenteHistorial.setCellValueFactory(new PropertyValueFactory<>("nombreApPaternoResidente"));
        this.rowDireccionResidenteHistorial.setCellValueFactory(new PropertyValueFactory<>("direccionResidente"));
        this.rowFechaVisitaHistorial.setCellValueFactory(new PropertyValueFactory<>("fechaVisita"));
        this.tvSolicitudesHistorial.setItems(tablaSolicitudesVisitas);
    }

    @FXML
    private void accionAceptadasHS(ActionEvent event) throws FileNotFoundException {
        this.tgSeleccionado = 1;
        this.mostrarDatosTablaVisitasFiltrada();
    }

    @FXML
    private void accionRechazadassHS(ActionEvent event) throws FileNotFoundException {
        this.tgSeleccionado = 2;
        this.mostrarDatosTablaVisitasFiltrada();
    }

    @FXML
    private void accionPendientesHS(ActionEvent event) throws FileNotFoundException {
        this.tgSeleccionado = 3;
        this.mostrarDatosTablaVisitasFiltrada();
    }
}
