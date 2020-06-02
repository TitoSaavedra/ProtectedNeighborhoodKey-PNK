/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.controlador;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    @FXML
    private FontAwesomeIcon btnCerrar;
    @FXML
    private FontAwesomeIcon btnOpciones;
    @FXML
    private FontAwesomeIcon btnMinimizar;

    //Atributos utils
    private TranslateTransition openNav = null;
    private TranslateTransition closeNav = null;
    private RotateTransition rt;
    private double x, y;
    @FXML
    private Circle clImagenPerfil;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prepareSlideMenuAnimation();
        try {
            inicioApp();
        } catch (IOException ex) {
            Logger.getLogger(VistaPrincipalControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param nombre
     * @param imagenPerfil
     */
    public void inicializarDatos(String nombre, Image imagenPerfil) {
        this.clImagenPerfil.setFill(new ImagePattern(imagenPerfil));
        txtNombreApellido.setText(nombre);
    }

    private void posicionPanel(AnchorPane pane) {
        AnchorPane.setTopAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
    }

    @FXML
    private void accionBarrera(ActionEvent event) throws IOException {
        cerrarMenu();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/cl/pnk/vistas/VistaBarreraUA.fxml"));
        AnchorPane panel = tamanoPanel(pane);
        apVista.getChildren().removeAll();
        apVista.getChildren().setAll(panel);
    }

    @FXML
    private void accionVisita(ActionEvent event) throws IOException {
        cerrarMenu();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/cl/pnk/vistas/VistaVisitante.fxml"));
        AnchorPane panel = tamanoPanel(pane);
        apVista.getChildren().removeAll();
        apVista.getChildren().setAll(panel);
    }

    @FXML
    private void accionResidente(ActionEvent event) throws IOException {
        cerrarMenu();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/cl/pnk/vistas/VistaResidente.fxml"));
        AnchorPane panel = tamanoPanel(pane);
        apVista.getChildren().removeAll();
        apVista.getChildren().setAll(panel);
    }

    @FXML
    private void accionEncomienda(ActionEvent event) throws IOException {
        cerrarMenu();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/cl/pnk/vistas/VistaEncomiendas.fxml"));
        AnchorPane panel = tamanoPanel(pane);
        apVista.getChildren().removeAll();
        apVista.getChildren().setAll(panel);
    }

    @FXML
    private void accionNotificacion(ActionEvent event) throws IOException {
        cerrarMenu();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/cl/pnk/vistas/VistaNotificacion.fxml"));
        AnchorPane panel = tamanoPanel(pane);
        apVista.getChildren().removeAll();
        apVista.getChildren().setAll(panel);
    }

    @FXML
    private void accionInforme(ActionEvent event) throws IOException {
        cerrarMenu();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/cl/pnk/vistas/VistaInforme.fxml"));
        AnchorPane panel = tamanoPanel(pane);
        apVista.getChildren().removeAll();
        apVista.getChildren().setAll(panel);
    }

    private void prepareSlideMenuAnimation() {
        openNav = new TranslateTransition(new Duration(350), apMenu);
        openNav.setToX(0);
        closeNav = new TranslateTransition(new Duration(350), apMenu);
        btnImage.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if (me.getButton() != MouseButton.MIDDLE) {
                    if (apMenu.getTranslateX() != 0) {
                        abrirMenu();
                    } else {
                        cerrarMenu();
                    }
                }
            }
        });
    }

    private void inicioApp() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/cl/pnk/vistas/VistaBarreraUA.fxml"));
        AnchorPane panel = tamanoPanel(pane);
        apVista.getChildren().removeAll();
        apVista.getChildren().setAll(panel);
        rt = new RotateTransition(Duration.millis(300), btnImage);

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

    private void abrirMenu() {
        Image image = new Image(getClass().getResource("/cl/pnk/imagenes/IconoMenuCerrar.png").toString(), true);
        btnImage.setImage(image);
        rotacion();
        openNav.play();

    }

    private void cerrarMenu() {
        Image image = new Image(getClass().getResource("/cl/pnk/imagenes/IconoMenu.png").toString(), true);
        btnImage.setImage(image);
        rotacion();
        closeNav.setToX(-(apMenu.getWidth()));
        closeNav.play();

    }

    private void rotacion() {
        rt.setByAngle(360);
        rt.play();
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

    @FXML
    private void accionOpcion(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        // stage.initModality(Modality.APPLICATION_MODAL);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Conexión Arduino");
        stage.setScene(new Scene(root1));
        stage.show();

    }

    @FXML
    private void mouseEntroFotoPerfil(MouseEvent event) {
        this.clImagenPerfil.setEffect(new DropShadow(+25, 0d, +2d, Color.ANTIQUEWHITE));
    }

    @FXML
    private void mouseSalioFotoPerfil(MouseEvent event) {
        this.clImagenPerfil.setEffect(new DropShadow(+25, 0d, +2d, Color.TRANSPARENT));
    }
}
