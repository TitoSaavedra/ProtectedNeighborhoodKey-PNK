package cl.pnk.controlador;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Esta es la clase de arranque de PNK
 *
 * @author TitoS
 */
public class ProtectedNeighborhoodKeyPNK extends Application {

    /**
     *
     * @param args contiene los comandos para iniciar el programa
     */
    public static void main(String[] args) {
        launch(args);
    }

     /**
     * Este metodo inicia la ventana o el  primaryStage de PNK cargando la pantalla de login
     * @see VistaIngresoControlador 
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setResizable(false);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ProtectedNeighborhoodKeyPNK.class.getResource("/cl/pnk/vistas/VistaIngreso.fxml"));
            Pane ventana = (Pane) loader.load();
            Scene scene = new Scene(ventana);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image("/cl/pnk/imagenes/IconoApp.png"));
            primaryStage.setTitle("Protected Neighborhood Key");
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
