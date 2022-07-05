/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.controlador;

import cl.pnk.dal.BarreraDal;
import cl.pnk.dto.Barrera;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

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
    @FXML
    private AnchorPane apConectando;
    String readline = "";
    private int motorBrazo = 0;
    private int abrirBarrera = 0;
    private int listenerUp = 1;
    @FXML
    private Text txtEstadoBarrera;

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
        this.txtEstadoBarrera.setText("Cerrada");
        cargarPuertasCom();
        this.btnAccion.setVisible(false);
        this.btnAccion.setText("Abrir/Cerrar");
        this.apConectando.setVisible(false);
        buscarCambioDBBarrera.setCycleCount(Timeline.INDEFINITE);

    }

    /**
     * Este metodo se inicia al inicio Carga las puertas de comunicación serial
     * conectadas al equipo. Y los carga en la lista cbPuertas
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
     * Este metodo conecta java con el arduino por medio del puerto seleccionado
     * en la lista cbPuertas
     * @see VistaInformeControlador
     */
    @FXML
    public void conectar(ActionEvent event) {
        if (btnConectar.getText().equals("Conectar")) {
            puertaCom = SerialPort.getCommPort(cbPuertas.getSelectionModel().getSelectedItem().toString());
            puertaCom.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
            if (puertaCom.openPort()) {
                this.apConectando.setVisible(true);
                btnConectar.setText("Desconectar");
                buscarCambioDBBarrera.play();
                cbPuertas.setDisable(true);
                btnAccion.setDisable(false);
                addPortListener();
                this.btnAccion.setVisible(true);
            }
        } else {
            this.btnAccion.setVisible(false);
            this.apConectando.setVisible(false);
            puertaCom.closePort();
            cbPuertas.setDisable(false);
            btnAccion.setDisable(true);
            btnConectar.setText("Conectar");
            puertaCom.removeDataListener();
        }
    }

    private void addPortListener() {
        puertaCom.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                    System.err.println("No data on SerialPort");
                    return;
                }
                int bytesAvailable = puertaCom.bytesAvailable();
                if (bytesAvailable < 1) {
                    System.err.println("Can not read from SerialPort");
                    return;
                }
                byte[] newData = new byte[bytesAvailable];
                int numRead = puertaCom.readBytes(newData, newData.length);
                if (numRead > 0) {
                    for (int i = 0; i < newData.length; ++i) {
                        if ((char) newData[i] == '\n' || (char) newData[i] == '\r') {
                            readline = readline.trim();
                            if (readline.length() > 0) {
                                if (listenerUp == 1) {
                                    receive(readline);
                                }
                            }
                            readline = "";
                        } else {
                            readline = readline + (char) newData[i];
                        }
                    }
                }
            }
        });
    }

    public void receive(String line) {
        if (line.equals("57 07 34 52")) {
            listenerUp = 0;
            abrirPuerta();
        } else if (line.equals("B3 F3 68 19")) {
            denegado();
        } else {
            addPortListener();
        }
    }

    private void denegado() {
        PrintWriter output = new PrintWriter(puertaCom.getOutputStream());
        output.print("2");
        output.flush();
    }

    /**
     * Este metodo se inicia al apretar el boton prender motorBrazo
     *
     * @param event Evento de accion que se genera al apretar el boton prender
     * motorBrazo. Este metodo envia la información al arduino para que encianda
     * el motorBrazo Este metodo sera el mismo que enviara la accion de
     * abrir/cerrar puerta
     * @see VistaInformeControlador
     */
    @FXML
    public void prenderLed(ActionEvent event) {
        abrirPuerta();
    }

    private void abrirPuerta() {
        PrintWriter output = new PrintWriter(puertaCom.getOutputStream());
        if (motorBrazo == 0) {
            listenerUp = 0;
            this.txtEstadoBarrera.setText("Abierta");
            output.print("1");
            output.flush();
            motorBrazo = 1;
            new BarreraDal().accionBarrera(1);
        } else {
            listenerUp = 1;
            this.txtEstadoBarrera.setText("Cerrada");
            output.print("0");
            output.flush();
            motorBrazo = 0;
            new BarreraDal().accionBarrera(2);
        }
    }
    Timeline buscarCambioDBBarrera = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Barrera barrera = new BarreraDal().estadoBarrera();
            if (barrera.getEstadoBarrera() == 1) {
                abrirPuerta();
                buscarCambioDBBarrera.pause();
            }
        }
    }));
}
