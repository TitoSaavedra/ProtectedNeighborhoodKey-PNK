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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author TitoS
 */
public class VistaBarreraUAControlador implements Initializable {

    @FXML
    private Text txtNombreMenu2;
    @FXML
    private Text txtNombreRuta;
    @FXML
    private TextField jtfBusqueda1;
    @FXML
    private Text txtResultadoBusquedaRut1;
    @FXML
    private JFXButton btnFiltro1;
    @FXML
    private TextField jtfBusqueda11;
    @FXML
    private Text txtResultadoBusquedaRut11;
    @FXML
    private JFXButton btnFiltro11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void accionFiltrar(ActionEvent event) {
    }
    
}
