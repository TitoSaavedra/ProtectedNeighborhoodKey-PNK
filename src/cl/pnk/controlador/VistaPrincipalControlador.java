/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.controlador;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author TitoS
 */
public class VistaPrincipalControlador implements Initializable {

    @FXML
    private AnchorPane apPanelPrincipal;
    @FXML
    private AnchorPane apMenu;
    @FXML
    private AnchorPane apVista;
    @FXML
    private AnchorPane apBarrera;
    @FXML
    private Button btnBarrera;
    @FXML
    private Text txtNombreApellido;
    @FXML
    private Button btnVisita;
    @FXML
    private Button bntResidentes;
    @FXML
    private Button btnEncomienda;
    @FXML
    private Button btnNotificacion;
    @FXML
    private Button btnInforme;
    @FXML
    private ImageView btnImage;
    @FXML
    private Button btnCerrarSerion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prepareSlideMenuAnimation();
    }

    private void posicionPanel(AnchorPane pane) {
        AnchorPane.setTopAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
    }

    private void limpiarPanel(AnchorPane pane) {
        // panelPrincipal.getChildren().removeAll();
    }

    @FXML
    private void accionBarrera(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/cl/pnk/vistas/VistaBarrera.fxml"));
        AnchorPane panel = tamanoPanel(pane);
        apVista.getChildren().removeAll();
        apVista.getChildren().setAll(panel);
    }

    @FXML
    private void accionVisita(ActionEvent event) {
    }

    @FXML
    private void accionResidente(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/cl/pnk/vistas/VistaResidente.fxml"));
        AnchorPane panel = tamanoPanel(pane);
        apVista.getChildren().removeAll();
        apVista.getChildren().setAll(panel);
    }

    @FXML
    private void accionEncomienda(ActionEvent event) {
    }

    @FXML
    private void accionNotificacion(ActionEvent event) {
    }

    @FXML
    private void accionInforme(ActionEvent event) {
    }

    private void prepareSlideMenuAnimation() {
        TranslateTransition openNav = new TranslateTransition(new Duration(350), apMenu);
        openNav.setToX(0);
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), apMenu);
        btnImage.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if (me.getButton() != MouseButton.MIDDLE) {
                    if (apMenu.getTranslateX() != 0) {
                        Image image = new Image(getClass().getResource("/cl/pnk/imagenes/IconoMenuCerrar.png").toString(), true);
                        btnImage.setImage(image);
                        openNav.play();
                    } else {
                        Image image = new Image(getClass().getResource("/cl/pnk/imagenes/IconoMenu.png").toString(), true);
                        btnImage.setImage(image);
                        closeNav.setToX(-(apMenu.getWidth()));
                        closeNav.play();
                    }
                }
            }
        });

    }

    private AnchorPane tamanoPanel(AnchorPane pane) {
        AnchorPane.setTopAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
        return pane;
    }

    @FXML
    private void accionCerrarSesion(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/cl/pnk/vistas/VistaIngreso.fxml"));
        AnchorPane panel = tamanoPanel(pane);
        apPanelPrincipal.getChildren().removeAll();
        apPanelPrincipal.getChildren().setAll(panel);
    }

}