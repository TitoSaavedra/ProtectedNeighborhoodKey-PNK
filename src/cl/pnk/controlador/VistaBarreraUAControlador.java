/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.controlador;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * FXML Controller class 
 * Esta clase controla las acciones del archivo FXML
 * VistaBarreraUA.fxml
 *
 * @author TitoS
 */
public class VistaBarreraUAControlador implements Initializable {

    @FXML
    private Text txtNombreRuta;
    @FXML
    private TextField jtfBusqueda11;
    @FXML
    private Text txtResultadoBusquedaRut11;
    @FXML
    private JFXButton btnFiltro11;
    @FXML
    private Text txtNombreMenu;
    @FXML
    private Tab submenuUltimosAccesos;
    @FXML
    private Tab submenuHistorialAceeso;
    @FXML
    private TableView<?> tvRegistroAcceso;
    @FXML
    private TextField jtfBusqueda111;
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
    private void accionFiltrar(ActionEvent event) {
    }

    @FXML
    private void accionUltimoAcceso(Event event) {
        cambioNombreMenuRuta(submenuUltimosAccesos.getText());
    }

    @FXML
    private void accionHistorial(Event event) {
        cambioNombreMenuRuta(submenuHistorialAceeso.getText());
    }
        
    private void cambioNombreMenuRuta(String menu){
        txtNombreMenu.setText(menu);
        txtNombreRuta.setText(menu);
    }

    @FXML
    private void accionTvCliqueado(MouseEvent event) {
    }

    @FXML
    private void accionFiltrarTvAcceso(KeyEvent event) {
    }
}
