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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private double x, y;

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

    @FXML
    private void accionCerrar(ActionEvent event) {
         Stage stage = (Stage) pnPanelPrincipal.getScene().getWindow();
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void accionMiniminizar(ActionEvent event) {
         Stage stage = (Stage) pnPanelPrincipal.getScene().getWindow();
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void accMover(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    private void accPresionar(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

}
