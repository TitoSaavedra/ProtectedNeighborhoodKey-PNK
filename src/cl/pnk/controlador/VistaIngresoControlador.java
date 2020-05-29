/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author TitoS
 */
public class VistaIngresoControlador implements Initializable {

    @FXML
    private Button btnIngresar;
    @FXML
    private TextField txtIngresoRut;
    @FXML
    private TextField txtIngresoContrasena;
    @FXML
    private AnchorPane pnPanelPrincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnIngresar(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/cl/pnk/vistas/VistaPrincipal.fxml"));
        AnchorPane.setTopAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
        pnPanelPrincipal.getChildren().removeAll();
        pnPanelPrincipal.getChildren().setAll(pane);
    }

    @FXML
    private void ingresoRut(ActionEvent event) {
    }

    @FXML
    private void ingresoContrasena(ActionEvent event) {
    }

}
