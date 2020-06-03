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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cargarPuertasCom();

    }

    public void cargarPuertasCom() {
        SerialPort[] portNames = SerialPort.getCommPorts();
        for (SerialPort portName : portNames) {
            cbPuertas.getItems().add(portName.getSystemPortName());
        }

    }

    @FXML
    private void conectar(ActionEvent event) {
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

    @FXML
    private void prenderLed(ActionEvent event) {
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
