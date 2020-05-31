package cl.pnk.controlador;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProtectedNeighborhoodKeyPNK extends Application {

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
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
