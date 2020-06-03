/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.controlador;

import com.fazecast.jSerialComm.SerialPort;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class Esta clase controla las acciones del archivo FXML
 * VistaArduino.fxml
 *
 * @author TitoS
 */
public class VistaArduinoControlador implements Initializable {

    @FXML
    private Button btnConectar;

    @FXML
    private ComboBox cbPuertas;
    @FXML
    private Button btnAccion;

    private SerialPort puertaCom;
    private int led = 0;

    /**
     * Initializes the controller class.
     *
     * @param url es propio de java
     * @param rb es un archivo propio de java, que contiene los datos de
     * localización especificos Tambien inicia el metodo para cargar las puertas
     * com
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cargarPuertasCom();

    }

    /**
     * Este metodo se inicia al inicio Carga las puertas de comunicación serial
     * conectadas al equipo.
     * Y los carga en la lista cbPuertas
     *
     */
    public void cargarPuertasCom() {
        SerialPort[] portNames = SerialPort.getCommPorts();
        for (SerialPort portName : portNames) {
            cbPuertas.getItems().add(portName.getSystemPortName());
        }

    }

    /**
     * Este metodo se inicia al apretar el boton conectar
     *
     * @param event Evento de accion que se genera al apretar el boton conectar.
     * Este metodo conecta java con el arduino por medio del puerto seleccionado en la lista cbPuertas
     * @see VistaInformeControlador
     */
    @FXML
    public void conectar(ActionEvent event) {
        if (btnConectar.getText().equals("Conectar")) {
            puertaCom = SerialPort.getCommPort(cbPuertas.getSelectionModel().getSelectedItem().toString());
            puertaCom.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
            if (puertaCom.openPort()) {
                btnConectar.setText("Desconectar");
                cbPuertas.setDisable(true);
            }

        } else {
            puertaCom.closePort();
            cbPuertas.setDisable(false);
            btnConectar.setText("Conectar");

        }
    }
    
    /**
     * Este metodo se inicia al apretar el boton prender led
     *
     * @param event Evento de accion que se genera al apretar el boton prender led.
     * Este metodo envia la información al arduino para que encianda el led
     * Este metodo sera el mismo que enviara la accion de abrir/cerrar puerta
     * @see VistaInformeControlador
     */

    @FXML
    public void prenderLed(ActionEvent event) {
        PrintWriter output = new PrintWriter(puertaCom.getOutputStream());
        if (led == 0) {
            output.print("1");
            output.flush();
            led = 1;
        } else {
            output.print("0");
            output.flush();
            led = 0;
        }
    }

}
