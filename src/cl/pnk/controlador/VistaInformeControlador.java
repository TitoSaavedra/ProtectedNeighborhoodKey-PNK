package cl.pnk.controlador;

import cl.pnk.dal.TablaAccesoDal;
import cl.pnk.dto.TablaAcceso;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
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

    /**
     * Initializes the controller class.
     *
     * @param url es propio de java
     * @param rb es un archivo propio de java, que contiene los datos de
     * localización especificos
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void accionFiltrarTvAcceso(KeyEvent event) {

    }

    @FXML
    private void accionExportarHistorialAcceso(ActionEvent event) {
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
}
