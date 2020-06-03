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
 * FXML Controller class Esta clase controla las acciones del archivo FXML
 * VistaVisitante.fxml
 *
 * @author TitoS
 */
public class VistaVisitanteControlaldor implements Initializable {

    @FXML
    private Text txtNombreRuta;
    @FXML
    private TextField jtfBusqueda;
    @FXML
    private Text txtResultadoBusquedaRut;
    @FXML
    private JFXButton btnFiltro;
    @FXML
    private JFXButton btnImagen;
    @FXML
    private JFXButton btnFiltro1;
    @FXML
    private Text txtResultadoBusquedaRut1;
    @FXML
    private TextField jtfBusqueda1;
    @FXML
    private TextField jtfBusqueda11;
    @FXML
    private Text txtResultadoBusquedaRut11;
    @FXML
    private JFXButton btnFiltro11;
    @FXML
    private Text txtNombreMenu;
    @FXML
    private Tab submenuRegistroVista;
    @FXML
    private Tab submenuSolicitudVisita;

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
    private void accionBuscarImagen(ActionEvent event) {
    }

    @FXML
    private void accionRegistroVista(Event event) {
         cambioNombreMenuRuta(submenuRegistroVista.getText());
    }

    @FXML
    private void accionSolicitudVisita(Event event) {
         cambioNombreMenuRuta(submenuSolicitudVisita.getText());
    }
        
    private void cambioNombreMenuRuta(String menu){
        txtNombreMenu.setText(menu);
        txtNombreRuta.setText(menu);
    }
}
