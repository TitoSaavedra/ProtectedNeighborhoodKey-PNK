/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.controlador;

import cl.pnk.dal.CuentaDal;
import cl.pnk.dao.Cuenta;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
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
    private TextField txtIngresoContrasena;
    @FXML
    private AnchorPane pnPanelPrincipal;
    @FXML
    private FontAwesomeIcon btnCerrar;
    @FXML
    private FontAwesomeIcon btnMinimizar;
    @FXML
    private Text txtError;
    private double x, y;
    @FXML
    private TextField txtIngresoCorreo;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnIngresar(ActionEvent event) throws IOException {
        List<Cuenta> listaCuenta = new CuentaDal().getCuentas();
        String txtContra= this.txtIngresoContrasena.getText().trim();
        String txtEmail = this.txtIngresoCorreo.getText().toLowerCase().trim();
        for (int i = 0; i < listaCuenta.size(); i++) {
            Cuenta cuenta = listaCuenta.get(i);
             if (cuenta.getClave().equals(txtContra) && cuenta.getPersona().getEmail().toLowerCase().equals(txtEmail)) {
                if (cuenta.getPersona().getTipoPersona().getIdTipoPersona()==1) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/cl/pnk/vistas/VistaPrincipal.fxml"));
                    Pane ventana = (Pane) loader.load();
                    Scene scene = new Scene(ventana);
                    VistaPrincipalControlador controlador = loader.getController();
                    String nombreApaternoAmatero = cuenta.getPersona().getNombre()+" "+cuenta.getPersona().getApePaterno()+" "+cuenta.getPersona().getApeMaterno();
                    controlador.inicializarDatos(nombreApaternoAmatero,cuenta.getFoto());
                    Stage windows = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    windows.setScene(scene);
                }else{
                     txtError.setText("Sistema de uso exclusivo para porteria");
                     i=listaCuenta.size();
                }
            } else {
                txtError.setText("Usuario o Contraseña incorrectos");
            }
        }
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

    @FXML
    private void accionCerrar(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void accionMiniminizar(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

}
