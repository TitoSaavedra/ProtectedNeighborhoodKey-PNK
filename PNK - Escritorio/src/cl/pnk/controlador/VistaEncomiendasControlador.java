/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.controlador;

import cl.pnk.dal.CuentaDal;
import cl.pnk.dal.DireccionDal;
import cl.pnk.dal.DireccionPersonaDal;
import cl.pnk.dal.EncomiendaDal;
import cl.pnk.dal.PersonaDal;
import cl.pnk.dal.SolicitudEncomiendaDal;
import cl.pnk.dal.TablaResidenteDal;
import cl.pnk.dal.TablaSolicitudEncomiendaDal;
import cl.pnk.dal.TokenDal;
import cl.pnk.dto.Cuenta;
import cl.pnk.dto.Direccion;
import cl.pnk.dto.DireccionPersona;
import cl.pnk.dto.Encomienda;
import cl.pnk.dto.Persona;
import cl.pnk.dto.SolicitudEncomienda;
import cl.pnk.dto.TablaResidente;
import cl.pnk.dto.TablaSolicitudEncomienda;
import cl.pnk.dto.Token;
import cl.pnk.notificaciones.NotificacionCelular;
import cl.pnk.utils.UtilidadesPrograma;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class Esta clase controla las acciones del archivo FXML
 * VistaEncomiendas.fxml
 *
 * @author TitoS
 */
public class VistaEncomiendasControlador implements Initializable {

    @FXML
    private Text txtNombreMenu;
    @FXML
    private Text txtNombreRuta;
    @FXML
    private JFXButton BtnRegistroEncomienda;
    @FXML
    private Tab submenuRegistroEncomienda;
    @FXML
    private TextField jtfFiltroResidente;
    @FXML
    private TableView<TablaResidente> tvTablaResidentes;
    @FXML
    private TableColumn<TablaResidente, String> rowRut;
    @FXML
    private TableColumn<TablaResidente, String> rowNombre;
    @FXML
    private TableColumn<TablaResidente, String> rowApPaterno;
    @FXML
    private TableColumn<TablaResidente, String> rowDireccion;
    @FXML
    private Text txtRutResidenteEncomienda;
    @FXML
    private Text txtNombreResidenteEncomienda;
    @FXML
    private Text txtDireccionResidenteEncomienda;
    @FXML
    private JFXTextArea jtxtDescripcionEncomienda;
    @FXML
    private Text txtErrorDescripcion;
    @FXML
    private Text txtConfirmacion;
    @FXML
    private Text txtErrorNombreTransporte;
    @FXML
    private JFXTextField jtxtNombreTransporte;
    @FXML
    private Text txtRutResidenteSV;
    @FXML
    private Text txtNombreResidenteSV;
    @FXML
    private Text txtApellidoPResidenteSV;
    @FXML
    private Text txtApellidoMResidenteSV;
    @FXML
    private Text txtFechaMuestra;
    @FXML
    private Text txtHoraMuestra;
    @FXML
    private Text txtNombreEncomienda;
    @FXML
    private Text txtFecha;
    @FXML
    private Text txtHora;
    @FXML
    private Text txtEstadoEncomienda;
    @FXML
    private Text txtDescripcionEncomienda;
    @FXML
    private JFXButton btnEntregarEncomienda;
    @FXML
    private TableView<TablaSolicitudEncomienda> tvEncomiendasPendientes;
    @FXML
    private TableColumn<TablaSolicitudEncomienda, String> rowNombreApellidoResidente;
    @FXML
    private TableColumn<TablaSolicitudEncomienda, String> rowFechaRecepcion;
    @FXML
    private TableColumn<TablaSolicitudEncomienda, String> rowHoraRecepcion;
    @FXML
    private TableView<TablaSolicitudEncomienda> tvSolicitudesHistorial;
    @FXML
    private TableColumn<TablaSolicitudEncomienda, String> rowNombreApellidoResidenteHistorial;
    @FXML
    private TableColumn<TablaSolicitudEncomienda, String> rowFechaRecepcionHistorial;
    @FXML
    private TableColumn<TablaSolicitudEncomienda, String> rowHoraRecepcionHistorial;
    @FXML
    private JFXRadioButton rdEntregadas;
    @FXML
    private JFXRadioButton rdPendientes;
    @FXML
    private TextField jtfFiltroEncomienda;
    @FXML
    private Text txtNroTelefonoResidenteSV;
    private final UtilidadesPrograma utilidadesPrograma = new UtilidadesPrograma();
    private ToggleGroup tgHistorialEncomiendass = new ToggleGroup();
    private int tgSeleccionado = 1;
    @FXML
    private Tab submenuEncomiendas;
    @FXML
    private Text txtIdSolicitudEncomienda;
    private NotificacionCelular notificacionCelular = new NotificacionCelular();

    /**
     * Initializes the controller class.
     *
     * @param url es propio de java
     * @param rb es un archivo propio de java, que contiene los datos de
     * localización especificos
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rdEntregadas.setToggleGroup(tgHistorialEncomiendass);
        this.rdPendientes.setToggleGroup(tgHistorialEncomiendass);
        this.rowFechaRecepcionHistorial.setText("Fecha entrega");
        this.rowHoraRecepcionHistorial.setText("Hora entrega");
        mostrarDatosTabla();
        mostrarDatosTablaEncomiendasPendientes();
        mostrarDatosTablaHistorialEncomiendas();
    }

    @FXML
    private void accionRegistroEncomienda(Event event) {
        cambioNombreMenuRuta(submenuRegistroEncomienda.getText());
    }

    private void cambioNombreMenuRuta(String menu) {
        txtNombreMenu.setText(menu);
        txtNombreRuta.setText(menu);
    }

    @FXML
    private void accionRegistrarEncomienda(ActionEvent event) {
        this.textoConfirmacion("", false, 2);
        if (this.validarCampos(1)) {
            String rutResidente = this.txtRutResidenteEncomienda.getText();
            this.agregarEncomienda(rutResidente);
            resetCamposEncomienda();
            mostrarDatosTabla();
        }
    }

    private void agregarEncomienda(String rutResidente) {
        String nombreEncomienda = this.jtxtNombreTransporte.getText().trim();
        String descripcionEncomienda = this.jtxtDescripcionEncomienda.getText().trim();
        Encomienda encomienda = new Encomienda();
        encomienda.setNombre(nombreEncomienda);
        encomienda.setDescripcion(descripcionEncomienda);
        encomienda.setEstado(1);
        new EncomiendaDal().ingresarEncomienda(encomienda);
        Encomienda ultimaEncomienda = new EncomiendaDal().obtenerUltimaEncomienda();
        Persona residente = new PersonaDal().obtenerPersonaRut(rutResidente, "2");
        Cuenta cuenta = new CuentaDal().getCuentaPersonaSinFoto(residente.getIdPersona());
        SolicitudEncomienda solicitudEncomienda = new SolicitudEncomienda();
        solicitudEncomienda.setIdCuenta(cuenta.getIdCuenta());
        solicitudEncomienda.setEstadoSolicitudEncomienda(3);
        solicitudEncomienda.setIdEncomienda(ultimaEncomienda.getIdEncomienda());
        String fecha = this.utilidadesPrograma.obtenerDiaActual();
        String hora = this.utilidadesPrograma.obtenerHoraActual();
        solicitudEncomienda.setFechaEntrega(fecha);
        solicitudEncomienda.setHoraEntrega(hora);
        new SolicitudEncomiendaDal().ingresarSolicitudEncomienda(solicitudEncomienda);
        this.textoConfirmacion("Encomienda registrada", true, 2);
        Token token = new TokenDal().getSession(cuenta.getIdCuenta());
        if (token.getIdCuenta() == cuenta.getIdCuenta()) {
            notificacionCelular.enviarNotificacion("Has+recibido+una+encomienda+de:", ultimaEncomienda.getNombre());
        }
    }

    @FXML
    private void accionFiltroResidentes(KeyEvent event) {
        ObservableList<TablaResidente> listaTablaResidentes = new TablaResidenteDal().obtenerTablaResidentesFiltrada(this.jtfFiltroResidente.getText().trim());
        this.rowRut.setCellValueFactory(new PropertyValueFactory<>("rut"));
        this.rowNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.rowApPaterno.setCellValueFactory(new PropertyValueFactory<>("apPaterno"));
        this.rowDireccion.setCellValueFactory(new PropertyValueFactory<>("dir"));
        this.tvTablaResidentes.setItems(listaTablaResidentes);
    }

    @FXML
    private void tvMouseCliqueadoTvResidentes(MouseEvent event) {
        if (event.getClickCount() == 2 && !event.isConsumed()) {
            try {
                event.consume();
                String rut = String.valueOf(this.tvTablaResidentes.getSelectionModel().getSelectedItem().getRut());
                this.datosResidente(rut);
            } catch (Exception e) {
            }
        }
    }

    public void mostrarDatosTabla() {
        ObservableList<TablaResidente> listaTablaResidentes = new TablaResidenteDal().obtenerTablaResidentes();
        this.rowRut.setCellValueFactory(new PropertyValueFactory<>("rut"));
        this.rowNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.rowApPaterno.setCellValueFactory(new PropertyValueFactory<>("apPaterno"));
        this.rowDireccion.setCellValueFactory(new PropertyValueFactory<>("dir"));
        this.tvTablaResidentes.setItems(listaTablaResidentes);
    }

    private void datosResidente(String rut) {
        Persona residente = new PersonaDal().obtenerPersonaRut(rut, "2");
        this.txtRutResidenteEncomienda.setText(residente.getRut());
        this.txtNombreResidenteEncomienda.setText(residente.getNombre());
        DireccionPersona direccionPersona = new DireccionPersonaDal().obtenerDireccionPersona(residente.getIdPersona());
        Direccion direccion = new DireccionDal().obtenerDireccion(direccionPersona.getIdDireccion());
        this.txtDireccionResidenteEncomienda.setText(direccion.getPiso() + " " + direccion.getBlock() + " " + direccion.getNumero());

    }

    public boolean validarCampos(int op) {
        boolean validacion = true;
        String transporte = this.jtxtNombreTransporte.getText().trim();
        if (transporte.equals("") || transporte == null) {
            this.txtErrorNombreTransporte.setText("Falta ingresar nombre del transporte");
            this.deshabilitarHabilitarTextoError(this.txtErrorNombreTransporte, true);
            return false;
        } else {
            this.deshabilitarHabilitarTextoError(this.txtErrorNombreTransporte, false);
        }
        String descripcionEncomienda = this.jtxtDescripcionEncomienda.getText().trim();
        if (descripcionEncomienda.equals("") || descripcionEncomienda == null) {
            this.txtErrorDescripcion.setText("Falta la descripción de la encomienda");
            this.deshabilitarHabilitarTextoError(this.txtErrorDescripcion, true);
            return false;
        } else {
            this.deshabilitarHabilitarTextoError(this.txtErrorDescripcion, false);
        }
        if (op == 1) {
            if (this.txtRutResidenteEncomienda.getText().trim().equals("")) {
                this.textoConfirmacion("Necesita selecciónar un residente", true, 1);
                return false;
            } else {
                this.textoConfirmacion("", false, 1);
            }
        }
        return validacion;
    }

    public void deshabilitarHabilitarTextoError(Text texto, boolean op) {
        texto.setVisible(op);
    }

    public void textoConfirmacion(String texto, boolean op, int color) {
        this.txtConfirmacion.setVisible(op);
        this.txtConfirmacion.setText(texto);
        switch (color) {
            case 1:
                this.txtConfirmacion.setFill(Color.web("#FF0000"));
                break;
            case 2:
                this.txtConfirmacion.setFill(Color.web("#0CFF00"));
                break;
            default:
                this.txtConfirmacion.setFill(Color.web("#FF0000"));
        }
    }

    private void resetCamposEncomienda() {
        String reset = "";
        this.txtRutResidenteEncomienda.setText(reset);
        this.txtNombreResidenteEncomienda.setText(reset);
        this.txtDireccionResidenteEncomienda.setText(reset);
        this.jtxtDescripcionEncomienda.setText(reset);
        this.jtxtNombreTransporte.setText(reset);
    }

    @FXML
    private void accionEntregarEncomienda(ActionEvent event) {
        String idSolicitudEncomienda = this.txtIdSolicitudEncomienda.getText();
        entregarEncomienda(idSolicitudEncomienda);
        resetCamposEncomiendaPendiente();
        mostrarDatosTablaEncomiendasPendientes();
        mostrarDatosTablaHistorialEncomiendas();
        this.btnEntregarEncomienda.setDisable(true);
    }

    private void entregarEncomienda(String idSolicitudEncomienda) {
        new SolicitudEncomiendaDal().entregarEncomienda(Integer.parseInt(idSolicitudEncomienda));
    }

    @FXML
    private void tvMouseCliqueadoTvHistorialVisitasB(MouseEvent event) {
        if (event.getClickCount() == 2 && !event.isConsumed()) {
            try {
                event.consume();
                String idSolicitudEncomienda = String.valueOf(this.tvSolicitudesHistorial.getSelectionModel().getSelectedItem().getIdSolicitudEncomienda());
                this.datosEncomiendaHistorial(idSolicitudEncomienda);
            } catch (Exception e) {
            }
        }
    }

    private void datosEncomiendaHistorial(String idSolicitudEncomienda) {
        SolicitudEncomienda solicitudEncomienda = new SolicitudEncomiendaDal().obtenerSolicitudEncomienda(Integer.parseInt(idSolicitudEncomienda), this.tgSeleccionado);
        Cuenta cuenta = new CuentaDal().getCuentaSinFoto(solicitudEncomienda.getIdCuenta());
        Encomienda encomienda = new EncomiendaDal().obtenerEncomienda(solicitudEncomienda.getIdEncomienda());
        this.txtRutResidenteSV.setText(cuenta.getPersona().getRut());
        this.txtNombreResidenteSV.setText(cuenta.getPersona().getNombre());
        this.txtApellidoPResidenteSV.setText(cuenta.getPersona().getApePaterno());
        this.txtApellidoMResidenteSV.setText(cuenta.getPersona().getApeMaterno());
        this.txtNroTelefonoResidenteSV.setText(cuenta.getPersona().getTelefono());
        this.txtRutResidenteSV.setVisible(true);
        this.txtNombreResidenteSV.setVisible(true);
        this.txtApellidoPResidenteSV.setVisible(true);
        this.txtApellidoMResidenteSV.setVisible(true);
        this.txtNroTelefonoResidenteSV.setVisible(true);
        this.txtNombreEncomienda.setText(encomienda.getNombre());
        this.txtDescripcionEncomienda.setText(encomienda.getDescripcion());
        this.txtNombreEncomienda.setVisible(true);
        this.txtDescripcionEncomienda.setVisible(true);
        if (this.tgSeleccionado == 1) {
            this.cambioTextoEncomienda(this.txtFechaMuestra, "Fecha de entrega");
            this.cambioTextoEncomienda(this.txtHoraMuestra, "Hora de entrega");
            this.txtEstadoEncomienda.setText("Entregado");
            this.btnEntregarEncomienda.setDisable(true);
        } else {
            this.cambioTextoEncomienda(this.txtFechaMuestra, "Fecha de recepción");
            this.cambioTextoEncomienda(this.txtHoraMuestra, "Hora de recepción");
            this.txtEstadoEncomienda.setText("Pendiente de entrega");
            this.btnEntregarEncomienda.setDisable(false);
        }
        this.txtFecha.setText(solicitudEncomienda.getFechaEntrega());
        this.txtHora.setText(solicitudEncomienda.getHoraEntrega());
        this.txtFecha.setVisible(true);
        this.txtHora.setVisible(true);
        this.txtEstadoEncomienda.setVisible(true);
        this.txtIdSolicitudEncomienda.setText(idSolicitudEncomienda);
    }

    @FXML
    private void accionEncomiendas(Event event) {
        cambioNombreMenuRuta(submenuEncomiendas.getText());
    }

    @FXML
    private void accionEntregadasHE(ActionEvent event) {
        this.tgSeleccionado = 1;
        this.rowFechaRecepcionHistorial.setText("Fecha entrega :");
        this.rowHoraRecepcionHistorial.setText("Hora entrega :");
        mostrarDatosTablaHistorialEncomiendas();
    }

    @FXML
    private void accionPendientesHE(ActionEvent event) {
        this.tgSeleccionado = 3;
        this.rowFechaRecepcionHistorial.setText("Fecha recepción :");
        this.rowHoraRecepcionHistorial.setText("Hora recepción :");
        mostrarDatosTablaHistorialEncomiendas();
    }

    public void mostrarDatosTablaEncomiendasPendientes() {
        ObservableList<TablaSolicitudEncomienda> listaSolicitudEncomiendas = new TablaSolicitudEncomiendaDal().obtenerTablaResidentes(3);
        this.rowNombreApellidoResidente.setCellValueFactory(new PropertyValueFactory<>("nombreApPaternoResidente"));
        this.rowFechaRecepcion.setCellValueFactory(new PropertyValueFactory<>("fechaEncomienda"));
        this.rowHoraRecepcion.setCellValueFactory(new PropertyValueFactory<>("horaEncomienda"));
        this.tvEncomiendasPendientes.setItems(listaSolicitudEncomiendas);
    }

    @FXML
    private void accionFiltroEncomiendas(KeyEvent event) {
        ObservableList<TablaSolicitudEncomienda> listaSolicitudEncomiendas = new TablaSolicitudEncomiendaDal().obtenerTablaResidentesFiltrada(3, this.jtfFiltroEncomienda.getText().trim());
        this.rowNombreApellidoResidente.setCellValueFactory(new PropertyValueFactory<>("nombreApPaternoResidente"));
        this.rowFechaRecepcion.setCellValueFactory(new PropertyValueFactory<>("fechaEncomienda"));
        this.rowHoraRecepcion.setCellValueFactory(new PropertyValueFactory<>("horaEncomienda"));
        this.tvEncomiendasPendientes.setItems(listaSolicitudEncomiendas);
    }

    @FXML
    private void tvMouseCliqueadoTvEncomiendasPendientes(MouseEvent event) {
        if (event.getClickCount() == 2 && !event.isConsumed()) {
            try {
                event.consume();
                String idSolicitudEncomienda = String.valueOf(this.tvEncomiendasPendientes.getSelectionModel().getSelectedItem().getIdSolicitudEncomienda());
                this.datosEncomiendaPendientes(idSolicitudEncomienda);
            } catch (Exception e) {
            }
        }
    }

    private void datosEncomiendaPendientes(String idSolicitudEncomienda) {
        SolicitudEncomienda solicitudEncomienda = new SolicitudEncomiendaDal().obtenerSolicitudEncomienda(Integer.parseInt(idSolicitudEncomienda), 3);
        Cuenta cuenta = new CuentaDal().getCuentaSinFoto(solicitudEncomienda.getIdCuenta());
        Encomienda encomienda = new EncomiendaDal().obtenerEncomienda(solicitudEncomienda.getIdEncomienda());
        this.txtRutResidenteSV.setText(cuenta.getPersona().getRut());
        this.txtNombreResidenteSV.setText(cuenta.getPersona().getNombre());
        this.txtApellidoPResidenteSV.setText(cuenta.getPersona().getApePaterno());
        this.txtApellidoMResidenteSV.setText(cuenta.getPersona().getApeMaterno());
        this.txtNroTelefonoResidenteSV.setText(cuenta.getPersona().getTelefono());
        this.txtRutResidenteSV.setVisible(true);
        this.txtNombreResidenteSV.setVisible(true);
        this.txtApellidoPResidenteSV.setVisible(true);
        this.txtApellidoMResidenteSV.setVisible(true);
        this.txtNroTelefonoResidenteSV.setVisible(true);
        this.txtNombreEncomienda.setText(encomienda.getNombre());
        this.txtDescripcionEncomienda.setText(encomienda.getDescripcion());
        this.txtNombreEncomienda.setVisible(true);
        this.txtDescripcionEncomienda.setVisible(true);
        this.cambioTextoEncomienda(this.txtFechaMuestra, "Fecha de recepción :");
        this.cambioTextoEncomienda(this.txtHoraMuestra, "Hora de recepción :");
        this.txtFecha.setText(solicitudEncomienda.getFechaEntrega());
        this.txtHora.setText(solicitudEncomienda.getHoraEntrega());
        this.txtFecha.setVisible(true);
        this.txtHora.setVisible(true);
        this.txtEstadoEncomienda.setText("Pendiente de entrega");
        this.txtEstadoEncomienda.setVisible(true);
        this.txtIdSolicitudEncomienda.setText(idSolicitudEncomienda);
        this.btnEntregarEncomienda.setDisable(false);
    }

    private void cambioTextoEncomienda(Text textoCambiar, String texto) {
        textoCambiar.setText(texto);
    }

    private void resetCamposEncomiendaPendiente() {
        String reset = "";
        this.txtIdSolicitudEncomienda.setText(reset);
        this.txtRutResidenteSV.setText(reset);
        this.txtNombreResidenteSV.setText(reset);
        this.txtApellidoPResidenteSV.setText(reset);
        this.txtApellidoMResidenteSV.setText(reset);
        this.txtNroTelefonoResidenteSV.setText(reset);
        this.txtNombreEncomienda.setText(reset);
        this.txtDescripcionEncomienda.setText(reset);
        this.txtFecha.setText(reset);
        this.txtHora.setText(reset);
        this.txtEstadoEncomienda.setText(reset);
    }

    public void mostrarDatosTablaHistorialEncomiendas() {
        ObservableList<TablaSolicitudEncomienda> listaSolicitudEncomiendas = new TablaSolicitudEncomiendaDal().obtenerTablaResidentes(this.tgSeleccionado);
        this.rowNombreApellidoResidenteHistorial.setCellValueFactory(new PropertyValueFactory<>("nombreApPaternoResidente"));
        this.rowFechaRecepcionHistorial.setCellValueFactory(new PropertyValueFactory<>("fechaEncomienda"));
        this.rowHoraRecepcionHistorial.setCellValueFactory(new PropertyValueFactory<>("horaEncomienda"));
        this.tvSolicitudesHistorial.setItems(listaSolicitudEncomiendas);
    }
}
