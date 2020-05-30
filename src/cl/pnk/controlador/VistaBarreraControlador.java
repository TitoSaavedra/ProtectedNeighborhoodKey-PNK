/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.controlador;

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
public class VistaBarreraControlador implements Initializable {

    @FXML
    private Text txtNombreMenu;
    @FXML
    private Text txtNombreMenu1;
    @FXML
    private TextField jtfBusqueda;
    @FXML
    private Button btnFiltro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnFiltrar(ActionEvent event) {
    }
    
}
