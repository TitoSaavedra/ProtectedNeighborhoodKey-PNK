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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class Esta clase controla las acciones del archivo FXML
 * VistaNotificacion.fxml
 *
 * @author TitoS
 */
public class VistaNotificacionControlador implements Initializable {

    @FXML
    private Text txtNombreMenu;
    @FXML
    private Text txtNombreRuta;
    @FXML
    private TextField jtfBusquedaP1;
    @FXML
    private Text txtResultadoBusquedaRutP1;
    @FXML
    private JFXButton btnAgregar;

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
    private void accionKeyApretadaP1(KeyEvent event) {
    }

    @FXML
    private void accionFiltrarP1(ActionEvent event) {
    }

    @FXML
    private void accionAgregarResidente(ActionEvent event) {
    }

}
