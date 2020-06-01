/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author TitoS
 */
public class VistaResidenteControlador implements Initializable {

    @FXML
    private AnchorPane apPanelPrincipal;
    @FXML
    private Text txtNombreMenu;
    @FXML
    private TextField jtfBusqueda;
    @FXML
    private Button btnFiltro;
    @FXML
    private Text txtNombreRuta;
    @FXML
    private Text txtResultadoBusquedaRut;
    @FXML
    private JFXButton btnImagen;
    @FXML
    private TextField jtfBusqueda1;
    @FXML
    private Text txtResultadoBusquedaRut1;
    @FXML
    private JFXButton btnFiltro1;
    @FXML
    private Text txtUID;
    @FXML
    private Text txtFechaRegistro;
    @FXML
    private JFXButton btnBloquearTarjeta;
    @FXML
    private JFXButton btnDesbloquearTarjea;
    @FXML
    private JFXButton btnImagen13;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private TextField jtfBusqueda11;
    @FXML
    private Text txtResultadoBusquedaRut11;
    @FXML
    private JFXButton btnFiltro11;
    @FXML
    private Tab submenuResidente;
    @FXML
    private Tab submenuSeguridad;
    @FXML
    private Tab submenuLista;

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
    private void accionBuscarImagen(ActionEvent event) {
    }

    @FXML
    private void accionBloquearTarjeta(ActionEvent event) {
    }

    @FXML
    private void accionDesbloquearTarjeta(ActionEvent event) {
    }

    @FXML
    private void accionMenuBusqueda(Event event) {
        cambioNombreMenuRuta(submenuResidente.getText());
    }

    @FXML
    private void accionMenuSeguridad(Event event) {
        cambioNombreMenuRuta(submenuSeguridad.getText());
    }

    @FXML
    private void accionMenuListaResidentes(Event event) {
        cambioNombreMenuRuta(submenuLista.getText());
    }
    
    
    private void cambioNombreMenuRuta(String menu){
        txtNombreMenu.setText(menu);
        txtNombreRuta.setText(menu);
    }
}
