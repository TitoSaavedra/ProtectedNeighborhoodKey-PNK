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
 * FXML Controller class Esta clase controla las acciones del archivo FXML
 * VistaPrincipal.fxml
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
     *
     * @param url es propio de java
     * @param rb es un archivo propio de java, que contiene los datos de
     * localización especificos
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accionAnimacionMenuDeslizante();
        try {
            inicioApp();
        } catch (IOException ex) {
            Logger.getLogger(VistaPrincipalControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que inicializa la imagen de perfil de la cuenta portero y cambia
     * el nombre que se muestra en el menu deslizante
     *
     * @param nombre Nombre que viene de la vista de loggin
     * @param imagenPerfil imagen que viene de la vista de loggin
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

    /**
     * Este metodo se inicia al apretar el boton Gestión de acceso
     *
     * @param event Evento de accion que se genera al apretar el boton. Aqui se
     * carga y se muestra el archivo VistaBarreraUA.fxml
     * @throws IOException Si no se encuntra el archivo de vista.
     * @see VistaBarreraUAControlador
     */
    @FXML
    public void accionBarrera(ActionEvent event) throws IOException {
        cerrarMenu();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/cl/pnk/vistas/VistaBarreraUA.fxml"));
        AnchorPane panel = tamanoPanel(pane);
        apVista.getChildren().removeAll();
        apVista.getChildren().setAll(panel);
    }

    /**
     * Este metodo se inicia al apretar el boton Gestión de Visitas
     *
     * @param event Evento de accion que se genera al apretar el boton. Aqui se
     * carga y se muestra el archivo VistaVisitante.fxml
     * @throws IOException Si no se encuntra el archivo de vista.
     * @see VistaVisitanteControlaldor
     */
    @FXML
    public void accionVisita(ActionEvent event) throws IOException {
        cerrarMenu();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/cl/pnk/vistas/VistaVisitante.fxml"));
        AnchorPane panel = tamanoPanel(pane);
        apVista.getChildren().removeAll();
        apVista.getChildren().setAll(panel);
    }

    /**
     * Este metodo se inicia al apretar el boton Gestión de residentes
     *
     * @param event Evento de accion que se genera al apretar el boton. Aqui se
     * carga y se muestra el archivo VistaVisitante.fxml
     * @throws IOException Si no se encuntra el archivo de vista.
     * @see VistaResidenteControlador
     */
    @FXML
    public void accionResidente(ActionEvent event) throws IOException {
        cerrarMenu();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/cl/pnk/vistas/VistaResidente.fxml"));
        AnchorPane panel = tamanoPanel(pane);
        apVista.getChildren().removeAll();
        apVista.getChildren().setAll(panel);
    }

    /**
     * Este metodo se inicia al apretar el boton Gestión de encomiendas
     *
     * @param event Evento de accion que se genera al apretar el boton. Aqui se
     * carga y se muestra el archivo VistaEncomiendas.fxml
     * @throws IOException Si no se encuntra el archivo de vista.
     * @see VistaEncomiendasControlador
     */
    @FXML
    public void accionEncomienda(ActionEvent event) throws IOException {
        cerrarMenu();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/cl/pnk/vistas/VistaEncomiendas.fxml"));
        AnchorPane panel = tamanoPanel(pane);
        apVista.getChildren().removeAll();
        apVista.getChildren().setAll(panel);
    }

    /**
     * Este metodo se inicia al apretar el boton Gestión de notificación
     *
     * @param event Evento de accion que se genera al apretar el boton. Aqui se
     * carga y se muestra el archivo VistaNotificacion.fxml
     * @throws IOException Si no se encuntra el archivo de vista.
     * @see VistaNotificacionControlador
     */
    @FXML
    public void accionNotificacion(ActionEvent event) throws IOException {
        cerrarMenu();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/cl/pnk/vistas/VistaNotificacion.fxml"));
        AnchorPane panel = tamanoPanel(pane);
        apVista.getChildren().removeAll();
        apVista.getChildren().setAll(panel);
    }

    /**
     * Este metodo se inicia al apretar el boton Gestión de Informe
     *
     * @param event Evento de accion que se genera al apretar el boton. Aqui se
     * carga y se muestra el archivo VistaInforme.fxml
     * @throws IOException Si no se encuntra el archivo de vista.
     * @throws IOException Si no se encuntra el archivo de vista.
     * @see VistaInformeControlador
     */
    @FXML
    public void accionInforme(ActionEvent event) throws IOException {
        cerrarMenu();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/cl/pnk/vistas/VistaInforme.fxml"));
        AnchorPane panel = tamanoPanel(pane);
        apVista.getChildren().removeAll();
        apVista.getChildren().setAll(panel);
    }

    /**
     * Este metódo le inserta una animación a la barra de navegación deslizante
     */
    public void accionAnimacionMenuDeslizante() {
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

    /**
     * Este metodo carga el inicio de la aplicación Aqui se carga y se muestra
     * el archivo VistaBarreraUA.fxml, en el contenedor apVista
     *
     * @throws IOException Si no se encuntra el archivo de vista.
     * @see VistaBarreraUAControlador
     */
    public void inicioApp() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/cl/pnk/vistas/VistaBarreraUA.fxml"));
        AnchorPane panel = tamanoPanel(pane);
        apVista.getChildren().removeAll();
        apVista.getChildren().setAll(panel);
        rt = new RotateTransition(Duration.millis(300), btnImage);

    }

    /**
     * Este metodo cambia la posición de un AnchorPane, para que llene todo el
     * contenedor apVista
     *
     * @param pane Es el AnchorPane que se le quiere cambiar la posición
     * @return pane Retorna el AnchorPane con el cambio de posicion hecho
     */
    public AnchorPane tamanoPanel(AnchorPane pane) {
        AnchorPane.setTopAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
        return pane;
    }

    /**
     * Este metodo se inicia al apretar el boton Cerrar Sesón
     *
     * Se destruye todo lo que se muestra en el apPanel para mostrar la vista de
     * ingreso.
     *
     * @param event Evento de accion que se genera al apretar el boton. Aqui se
     * carga y se muestra el archivo VistaIngreso.fxml
     * @throws IOException Si no se encuntra el archivo de vista.
     * @see VistaIngresoControlador
     */
    @FXML
    public void accionCerrarSesion(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/cl/pnk/vistas/VistaIngreso.fxml"));
        AnchorPane panel = tamanoPanel(pane);
        apPanelPrincipal.getChildren().removeAll();
        apPanelPrincipal.getChildren().setAll(panel);
    }

    /**
     * Este metodo abre el menu deslizante y le cambia le icono al boton Tambien
     * reproduce la animación del clic en el boton de menu deslizante
     */
    public void abrirMenu() {
        Image image = new Image(getClass().getResource("/cl/pnk/imagenes/IconoMenuCerrar.png").toString(), true);
        btnImage.setImage(image);
        rotacion();
        openNav.play();

    }

    /**
     * Este metodo cierra el menu deslizante y le cambia le icono al boton
     * Tambien reproduce la animación del clic en el boton de menu deslizante
     */
    public void cerrarMenu() {
        Image image = new Image(getClass().getResource("/cl/pnk/imagenes/IconoMenu.png").toString(), true);
        btnImage.setImage(image);
        rotacion();
        closeNav.setToX(-(apMenu.getWidth()));
        closeNav.play();

    }

    /**
     * Este metodo hace que RotateTransition sea reproducido y le añade una
     * rotación de 360 grados
     */
    public void rotacion() {
        rt.setByAngle(360);
        rt.play();
    }

    /**
     * Este metodo se inicia al arrastrar la ventana
     *
     * @param event Evento de accion que se genera al arrastrar la ventana. Aqui
     * se asigna la posision X e Y nuevas de la ventana de PNK
     */
    @FXML
    public void accMover(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);

    }

    /**
     * Este metodo se inicia al arrastrar la ventana
     *
     * @param event Evento de accion que se genera al hacer clic en la ventana.
     * Aqui se asigna la posision X e Y actuales
     */
    @FXML
    public void accPresionar(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    /**
     * Este metodo se inicia precionar el boton cerrar programa
     *
     * @param event Evento de accion que se genera al hacer clic en el boton
     * cerrar. Aqui se cierra la aplicación PNK
     */
    @FXML
    public void accionCerrar(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Este metodo se inicia precionar al miniminizar el programa
     *
     * @param event Evento de accion que se genera al hacer clic en el boton
     * miniminizar. Aqui se miniminiza la aplicación PNK
     */
    @FXML
    public void accionMiniminizar(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * Este metodo se inicia precionar el boton opcion del programa
     *
     * @param event Evento de accion que se genera al hacer clic en el boton
     * pocioon. Aqui se genera una nueva ventana con la vista VistaArduino.fxml
     * @throws IOException Si no se encuntra el archivo de vista.
     * @see VistaArduinoControlador
     */
    @FXML
    public void accionOpcion(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cl/pnk/vistas/VistaArduino.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        // stage.initModality(Modality.APPLICATION_MODAL);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Conexión Arduino");
        stage.setScene(new Scene(root1));
        stage.show();

    }

    /**
     * Este metodo se inicia al pasar el mouse por encima de la imagen de perfil
     * le añade el efecto blur por al rededor.
     *
     * @param event Evento de accion que se genera la pasar el mouse por ensima.
     */
    @FXML
    public void mouseEntroFotoPerfil(MouseEvent event) {
        this.clImagenPerfil.setEffect(new DropShadow(+25, 0d, +2d, Color.ANTIQUEWHITE));
    }

    /**
     * Este metodo se inicia al salir el mouse por encima de la imagen de perfil
     * le quita el efecto blur por al rededor.
     *
     * @param event Evento de accion que se genera la quitar el mouse de ensima.
     */
    @FXML
    public void mouseSalioFotoPerfil(MouseEvent event) {
        this.clImagenPerfil.setEffect(new DropShadow(+25, 0d, +2d, Color.TRANSPARENT));
    }
}
