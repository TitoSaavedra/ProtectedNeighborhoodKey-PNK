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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author TitoS
 */
public class VistaEncomiendasControlador implements Initializable {

    @FXML
    private Text txtNombreMenu;
    @FXML
    private Text txtNombreRuta;
    @FXML
    private TextField jtfBusqueda1;
    @FXML
    private Text txtResultadoBusquedaRut1;
    @FXML
    private Tab submenuPendientes;
    @FXML
    private TextField jtfBusqueda11;
    @FXML
    private Text txtResultadoBusquedaRut11;
    @FXML
    private JFXButton btnFiltro11;
    @FXML
    private JFXButton BtnRegistroEncomienda;
    @FXML
    private Tab submenuRegistroEncomienda;
    @FXML
    private JFXButton btnFiltroRut;

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

    @FXML
    private void accionRegistroEncomienda(Event event) {
          cambioNombreMenuRuta(submenuRegistroEncomienda.getText());
    }

    @FXML
    private void accionPendiente(Event event) {
        cambioNombreMenuRuta(submenuPendientes.getText());
    }

    private void cambioNombreMenuRuta(String menu) {
        txtNombreMenu.setText(menu);
        txtNombreRuta.setText(menu);
    }

    @FXML
    private void accionRegistrarEncomienda(ActionEvent event) {
    }

}
