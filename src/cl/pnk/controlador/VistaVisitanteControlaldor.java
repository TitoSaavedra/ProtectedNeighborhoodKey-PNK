/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.controlador;

import cl.pnk.dal.CuentaDal;
import cl.pnk.dal.DireccionDal;
import cl.pnk.dal.DireccionPersonaDal;
import cl.pnk.dal.PersonaDal;
import cl.pnk.dal.SolicitudVisitaDal;
import cl.pnk.dal.TablaResidenteDal;
import cl.pnk.dal.TablaSolicitudesVisitaDal;
import cl.pnk.dto.Cuenta;
import cl.pnk.dto.Direccion;
import cl.pnk.dto.DireccionPersona;
import cl.pnk.dto.Persona;
import cl.pnk.dto.SolicitudVisita;
import cl.pnk.dto.TablaResidente;
import cl.pnk.dto.TablaSolicitudesVisita;
import cl.pnk.utils.UtilidadesPrograma;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * VistaVisitante.fxml
 *
 * @author TitoS
 */
public class VistaVisitanteControlaldor implements Initializable {

    @FXML
    private Text txtNombreRuta;
    @FXML
    private Text txtResultadoBusquedaRut;
    @FXML
    private Text txtNombreMenu;
    @FXML
    private Tab submenuRegistroVista;
    @FXML
    private Tab submenuSolicitudVisita;
    @FXML
    private TextField jtfBusquedaVisita;
    @FXML
    private JFXButton btnFiltroVisita;
    @FXML
    private JFXTextField jtxtRutVisita;
    @FXML
    private Text txtErrorRut;
    @FXML
    private Text txtErrorNombre;
    @FXML
    private Text txtErrorSegNombre;
    @FXML
    private Text txtErrorApPaterno;
    @FXML
    private Text txtErrorApMaterno;
    @FXML
    private Text txtRutResidente;
    @FXML
    private Text txtNombreResidente;
    @FXML
    private Text txtApePaternoResidente;
    @FXML
    private Text txtApeMaternoResidente;
    @FXML
    private Text txtPisoResidente;
    @FXML
    private Text txtBlockResidente;
    @FXML
    private Text txtNumeroResidente;
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
    private JFXTextField jtxtNombreVisita;
    @FXML
    private JFXTextField jtxtSegNombreVisita;
    @FXML
    private JFXTextField jtxtApellidoPaternoVisita;
    @FXML
    private JFXTextField jtxtApellidoMaternoVisita;
    @FXML
    private JFXButton btnModificar;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private TableView<TablaSolicitudesVisita> tvSolicitudesPendiente;
    @FXML
    private TableView<TablaSolicitudesVisita> tvSolicitudesHistorial;
    @FXML
    private Text txtRutResidenteSV;
    @FXML
    private Text txtNombreResidenteSV;
    @FXML
    private Text txtApellidoPResidenteSV;
    @FXML
    private Text txtApellidoMResidenteSV;
    @FXML
    private Text txtDireccionResidenteSV;
    @FXML
    private Text txtRutVisitaSV;
    @FXML
    private Text txtNombreVisitaSV;
    @FXML
    private Text txtApellidoPVisitaSV;
    @FXML
    private Text txtApellidoMVisitaSV;
    @FXML
    private Text txtFechaVisitaSV;
    @FXML
    private Text txtEstadoVisitaSV;
    @FXML
    private JFXButton btnDenegar;
    @FXML
    private JFXButton btnAceptarVisita;
    @FXML
    private Text txtConfirmacionAccion;
    @FXML
    private TableColumn<TablaSolicitudesVisita, String> rowNombreApellidoVisitante;
    @FXML
    private TableColumn<TablaSolicitudesVisita, String> rowNombreApellidoResidente;
    @FXML
    private TableColumn<TablaSolicitudesVisita, String> rowDireccionResidente;
    @FXML
    private TableColumn<TablaSolicitudesVisita, String> rowFechaVisita;
    @FXML
    private TableColumn<TablaSolicitudesVisita, String> rowNombreApellidoVisitanteHistorial;
    @FXML
    private TableColumn<TablaSolicitudesVisita, String> rowNombreApellidoResidenteHistorial;
    @FXML
    private TableColumn<TablaSolicitudesVisita, String> rowDireccionResidenteHistorial;
    @FXML
    private TableColumn<TablaSolicitudesVisita, String> rowFechaVisitaHistorial;
    @FXML
    private JFXRadioButton rdAceptadas;
    @FXML
    private JFXRadioButton rdRechazadas;
    @FXML
    private JFXRadioButton rdPendientes;
    private final UtilidadesPrograma utilidadesPrograma = new UtilidadesPrograma();
    private ToggleGroup tgHistorialVisitas = new ToggleGroup();
    private int tgSeleccionado = 1;
    private int opcionAgregar = 0;
    @FXML
    private Text txtIdSolicitudVisitaHistorial;

    /**
     * Initializes the controller class.
     *
     * @param url es propio de java
     * @param rb es un archivo propio de java, que contiene los datos de
     * localización especificos
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.rdAceptadas.setToggleGroup(tgHistorialVisitas);
            this.rdRechazadas.setToggleGroup(tgHistorialVisitas);
            this.rdPendientes.setToggleGroup(tgHistorialVisitas);
            mostrarDatosTabla();
            mostrarDatosTablaVisitas();
            mostrarDatosTablaVisitasFiltrada();
            estadoBotonesSolicitudVisita(1);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VistaVisitanteControlaldor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void accionRegistroVista(Event event) {
        cambioNombreMenuRuta(submenuRegistroVista.getText());
    }

    @FXML
    private void accionSolicitudVisita(Event event) {
        cambioNombreMenuRuta(submenuSolicitudVisita.getText());
    }

    private void cambioNombreMenuRuta(String menu) {
        txtNombreMenu.setText(menu);
        txtNombreRuta.setText(menu);
    }

    @FXML
    private void accionFiltrarVisita(ActionEvent event) {
        String rutVisita = this.jtfBusquedaVisita.getText().trim();
        if (this.utilidadesPrograma.validarRut(rutVisita)) {
            this.txtResultadoBusquedaRut.setVisible(false);
            Persona persona = new PersonaDal().obtenerPersonaRut(rutVisita, "3");
            if (!(persona.getRut() == null)) {
                if (persona.getTipoPersona() == 3) {
                    this.mostrarVisita(rutVisita);
                    this.opcionAgregar = 0;
                } else {
                    this.txtResultadoBusquedaRut.setText("Solo se permiten visitas");
                    this.txtResultadoBusquedaRut.setVisible(true);
                }
            } else {
                this.jtxtRutVisita.setText(rutVisita);
                this.txtResultadoBusquedaRut.setText("Rut no registrado");
                this.txtResultadoBusquedaRut.setVisible(true);
                this.opcionAgregar = 1;
            }
        } else {
            this.txtResultadoBusquedaRut.setText("Rut no valido");
            this.txtResultadoBusquedaRut.setVisible(true);
        }
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

    @FXML
    private void tvMouseCliqueadoTvHistorialVisitas(MouseEvent event) {
        if (event.getClickCount() == 2 && !event.isConsumed()) {
            try {
                event.consume();
                String rutResidente = String.valueOf(this.tvSolicitudesPendiente.getSelectionModel().getSelectedItem().getRutResidente());
                String rutVisita = String.valueOf(this.tvSolicitudesPendiente.getSelectionModel().getSelectedItem().getRutVisita());
                String idVisita = String.valueOf(this.tvSolicitudesPendiente.getSelectionModel().getSelectedItem().getIdVisita());
                this.datosVisitaPendiente(rutResidente, rutVisita, idVisita);
            } catch (Exception e) {
            }
        }
    }

    private void datosVisitaPendiente(String rutResidente, String rutVisita, String idVisita) {
        Persona residente = new PersonaDal().obtenerPersonaRut(rutResidente, "2");
        Persona visita = new PersonaDal().obtenerPersonaRut(rutVisita, "3");
        SolicitudVisita solicitudVisita = new SolicitudVisitaDal().obtenerSolicitudVisita(Integer.parseInt(idVisita));
        //Residente
        this.txtRutResidenteSV.setText(residente.getRut());
        this.txtNombreResidenteSV.setText(residente.getNombre());
        this.txtApellidoPResidenteSV.setText(residente.getApePaterno());
        this.txtApellidoMResidenteSV.setText(residente.getApeMaterno());
        this.txtRutResidenteSV.setVisible(true);
        this.txtNombreResidenteSV.setVisible(true);
        this.txtApellidoPResidenteSV.setVisible(true);
        this.txtApellidoMResidenteSV.setVisible(true);
        DireccionPersona direccionPersona = new DireccionPersonaDal().obtenerDireccionPersona(residente.getIdPersona());
        Direccion direccion = new DireccionDal().obtenerDireccion(direccionPersona.getIdDireccion());
        String direccionResidente = direccion.getPiso() + " " + direccion.getBlock() + " " + direccion.getNumero();
        this.txtDireccionResidenteSV.setText(direccionResidente);
        this.txtDireccionResidenteSV.setVisible(true);
        //Visita
        this.txtRutVisitaSV.setText(visita.getRut());
        this.txtNombreVisitaSV.setText(visita.getNombre());
        this.txtApellidoPVisitaSV.setText(visita.getApePaterno());
        this.txtApellidoMVisitaSV.setText(visita.getApeMaterno());
        this.txtRutVisitaSV.setVisible(true);
        this.txtNombreVisitaSV.setVisible(true);
        this.txtApellidoPVisitaSV.setVisible(true);
        this.txtApellidoMVisitaSV.setVisible(true);
        //Solicitud visita
        this.txtFechaVisitaSV.setText(solicitudVisita.getFechaVisita() + " " + solicitudVisita.getHoraVisita());
        String estadoSolicitud = "";
        switch (solicitudVisita.getEstadoSolicitud()) {
            case 1:
                estadoSolicitud = "Aceptada";
                break;
            case 2:
                estadoSolicitud = "Rechazada";
                break;
            case 3:
                estadoSolicitud = "Pendiente";
                break;
        }
        this.txtEstadoVisitaSV.setText(estadoSolicitud);
        this.txtFechaVisitaSV.setVisible(true);
        this.txtEstadoVisitaSV.setVisible(true);
        estadoBotonesSolicitudVisita(0);
        this.txtIdSolicitudVisitaHistorial.setText(idVisita);
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
        this.txtRutResidente.setText(residente.getRut());
        this.txtNombreResidente.setText(residente.getNombre());
        this.txtApePaternoResidente.setText(residente.getApePaterno());
        this.txtApeMaternoResidente.setText(residente.getApeMaterno());
        this.txtRutResidente.setVisible(true);
        this.txtNombreResidente.setVisible(true);
        this.txtApePaternoResidente.setVisible(true);
        this.txtApeMaternoResidente.setVisible(true);
        DireccionPersona direccionPersona = new DireccionPersonaDal().obtenerDireccionPersona(residente.getIdPersona());
        Direccion direccion = new DireccionDal().obtenerDireccion(direccionPersona.getIdDireccion());
        this.txtPisoResidente.setVisible(true);
        this.txtBlockResidente.setVisible(true);
        this.txtNumeroResidente.setVisible(true);
        this.txtPisoResidente.setText(direccion.getPiso());
        this.txtBlockResidente.setText(direccion.getBlock());
        this.txtNumeroResidente.setText(direccion.getNumero());
    }

    @FXML
    private void accionModificarVisita(ActionEvent event) throws FileNotFoundException {
        if (this.validarCampos(2)) {
            String rutVisita = this.jtxtRutVisita.getText();
            this.modificarVisita(rutVisita);
            this.resetCamposVistaVisita();
            resetCamposVisita();
        }
    }

    @FXML
    private void accionAgregarVisita(ActionEvent event) throws FileNotFoundException {
        if (this.validarCampos(1)) {
            String rutVisita = this.jtxtRutVisita.getText();
            if (opcionAgregar == 1) {
                this.ingresarVisita(rutVisita, 1);
            } else {
                this.ingresarVisita(rutVisita, 2);
            }
            resetCamposVisita();
            mostrarDatosTabla();
        }
    }

    @FXML
    private void accionDenegarVisita(ActionEvent event) throws FileNotFoundException {
        SolicitudVisita solicitudVisita = new SolicitudVisitaDal().obtenerSolicitudVisita(Integer.parseInt(this.txtIdSolicitudVisitaHistorial.getText()));
        new SolicitudVisitaDal().modificarSolicitudVisita(solicitudVisita.getIdSolicitud(), 2);
        this.mostrarDatosTablaVisitas();
        this.mostrarDatosTablaVisitasFiltrada();
        this.mostrarDatosTabla();
        this.resetCamposVisitaHistorial();
    }

    @FXML
    private void accionAceptarVisita(ActionEvent event) throws FileNotFoundException {
        SolicitudVisita solicitudVisita = new SolicitudVisitaDal().obtenerSolicitudVisita(Integer.parseInt(this.txtIdSolicitudVisitaHistorial.getText()));
        new SolicitudVisitaDal().modificarSolicitudVisita(solicitudVisita.getIdSolicitud(), 1);
        this.mostrarDatosTablaVisitas();
        this.mostrarDatosTablaVisitasFiltrada();
        this.mostrarDatosTabla();
        this.resetCamposVisitaHistorial();
    }

    /////////////////
    public void textoConfirmacion(String texto, boolean op, int color) {
        this.txtConfirmacionAccion.setVisible(op);
        this.txtConfirmacionAccion.setText(texto);
        switch (color) {
            case 1:
                this.txtConfirmacionAccion.setFill(Color.web("#FF0000"));
                break;
            case 2:
                this.txtConfirmacionAccion.setFill(Color.web("#0CFF00"));
                break;
            default:
                this.txtConfirmacionAccion.setFill(Color.web("#FF0000"));
        }
    }

    public boolean validarCampos(int op) {
        boolean validacion = true;
        String rut = this.jtxtRutVisita.getText().trim();
        if (rut.equals("") || rut == null) {
            this.txtErrorRut.setText("Falta ingresar el rut");
            this.deshabilitarHabilitarTextoError(this.txtErrorRut, true);
            return false;
        } else {
            this.deshabilitarHabilitarTextoError(this.txtErrorRut, false);
        }
        String nombre = this.jtxtNombreVisita.getText().trim();
        if (nombre.equals("") || nombre == null) {
            this.txtErrorNombre.setText("Falta ingresar el nombre");
            this.deshabilitarHabilitarTextoError(this.txtErrorNombre, true);
            return false;
        } else {
            this.deshabilitarHabilitarTextoError(this.txtErrorNombre, false);
        }
        String segNombre = this.jtxtSegNombreVisita.getText().trim();
        if (segNombre.equals("") || segNombre == null) {
            this.txtErrorSegNombre.setText("Falta ingresar el segundo nombre");
            this.deshabilitarHabilitarTextoError(this.txtErrorSegNombre, true);
            return false;
        } else {
            this.deshabilitarHabilitarTextoError(this.txtErrorSegNombre, false);
        }
        String aPaterno = this.jtxtApellidoPaternoVisita.getText().trim();
        if (aPaterno.equals("") || aPaterno == null) {
            this.txtErrorApPaterno.setText("Falta ingresar el Apellido Paterno");
            this.deshabilitarHabilitarTextoError(this.txtErrorApPaterno, true);
            return false;
        } else {
            this.deshabilitarHabilitarTextoError(this.txtErrorApPaterno, false);
        }
        String aMaterno = this.jtxtApellidoMaternoVisita.getText().trim();
        if (aMaterno.equals("") || aMaterno == null) {
            this.txtErrorApMaterno.setText("Falta ingresar el Apellido Materno");
            this.deshabilitarHabilitarTextoError(this.txtErrorApMaterno, true);
            return false;
        } else {
            this.deshabilitarHabilitarTextoError(this.txtErrorApMaterno, false);
        }
        if (op == 1) {
            if (this.txtRutResidente.getText().trim().equals("")) {
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

    public void mostrarDatosTablaVisitas() throws FileNotFoundException {
        ObservableList<TablaSolicitudesVisita> tablaSolicitudesVisitas = new TablaSolicitudesVisitaDal().obtenerTablaSolicitudesVisita("3");
        this.rowNombreApellidoVisitante.setCellValueFactory(new PropertyValueFactory<>("nombreApPaternoVisita"));
        this.rowNombreApellidoResidente.setCellValueFactory(new PropertyValueFactory<>("nombreApPaternoResidente"));
        this.rowDireccionResidente.setCellValueFactory(new PropertyValueFactory<>("direccionResidente"));
        this.rowFechaVisita.setCellValueFactory(new PropertyValueFactory<>("fechaVisita"));
        this.tvSolicitudesPendiente.setItems(tablaSolicitudesVisitas);
    }

    public void modificarVisita(String rut) {
        String nombre = this.jtxtNombreVisita.getText();
        String segNombre = this.jtxtSegNombreVisita.getText();
        String apePaterno = this.jtxtApellidoPaternoVisita.getText();
        String apeMaterno = this.jtxtApellidoMaternoVisita.getText();
        Persona persona = new PersonaDal().obtenerPersonaRut(rut, "3");
        persona.setNombre(nombre);
        persona.setSegNombre(segNombre);
        persona.setApePaterno(apePaterno);
        persona.setApeMaterno(apeMaterno);
        new PersonaDal().modificarVisita(persona);
        this.resetCamposVistaVisita();
        this.textoConfirmacion("Visitante modificado", true, 2);
    }

    public void ingresarVisita(String rut, int op) throws FileNotFoundException {
        String nombre = this.jtxtNombreVisita.getText();
        String segNombre = this.jtxtNombreVisita.getText();
        String apePaterno = this.jtxtApellidoPaternoVisita.getText();
        String apeMaterno = this.jtxtApellidoMaternoVisita.getText();
        Persona persona = new Persona(0, rut, nombre, segNombre, apePaterno, apeMaterno, 1, 3);
        if (op == 1) {
            new PersonaDal().ingresarVisita(persona);
        }
        Persona visita = new PersonaDal().obtenerPersonaRut(rut, "3");
        Persona residente = new PersonaDal().obtenerPersonaRut(this.txtRutResidente.getText().trim(), "2");
        Cuenta cuentaResidtente = new CuentaDal().getCuentaPersona(residente.getIdPersona());
        String diaActual = this.utilidadesPrograma.obtenerDiaActual();
        String horaActual = this.utilidadesPrograma.obtenerHoraActual();
        SolicitudVisita solicitudVisita = new SolicitudVisita(0, cuentaResidtente.getIdCuenta(), 1, diaActual, visita.getIdPersona(), horaActual);
        new SolicitudVisitaDal().ingresarSolicitudVisita(solicitudVisita);
        this.btnModificar.setDisable(false);
        this.btnAgregar.setDisable(true);
        if (op == 1) {
            this.textoConfirmacion("Visitante agregado", true, 2);
        } else {
            this.textoConfirmacion("Visita registrada", true, 2);
        }
        habilitarModElim();
        mostrarDatosTablaVisitas();
    }

    public void mostrarVisita(String rutVisita) {
        Persona persona = new PersonaDal().obtenerPersonaRut(rutVisita, "3");
        this.jtxtRutVisita.setText(persona.getRut());
        this.jtxtNombreVisita.setText(persona.getNombre());
        this.jtxtSegNombreVisita.setText(persona.getSegNombre());
        this.jtxtApellidoPaternoVisita.setText(persona.getApePaterno());
        this.jtxtApellidoMaternoVisita.setText(persona.getApeMaterno());
        habilitarModElim();
    }

    public void desabhilitarModElim() {
        this.btnAgregar.setDisable(false);
        this.btnModificar.setDisable(true);
    }

    public void habilitarModElim() {
        this.btnAgregar.setDisable(false);
        this.btnModificar.setDisable(false);
    }

    public void resetCamposVistaVisita() {
        String reset = "";
        this.jtxtRutVisita.setText(reset);
        this.jtxtNombreVisita.setText(reset);
        this.jtxtSegNombreVisita.setText(reset);
        this.jtxtApellidoPaternoVisita.setText(reset);
        this.jtxtApellidoMaternoVisita.setText(reset);
        desabhilitarModElim();
    }

    @FXML
    private void accionAceptadasHS(ActionEvent event) throws FileNotFoundException {
        this.tgSeleccionado = 1;
        this.mostrarDatosTablaVisitasFiltrada();
    }

    @FXML
    private void accionRechazadassHS(ActionEvent event) throws FileNotFoundException {
        this.tgSeleccionado = 2;
        this.mostrarDatosTablaVisitasFiltrada();
    }

    @FXML
    private void accionPendientesHS(ActionEvent event) throws FileNotFoundException {
        this.tgSeleccionado = 3;
        this.mostrarDatosTablaVisitasFiltrada();
    }

    public void mostrarDatosTablaVisitasFiltrada() throws FileNotFoundException {
        ObservableList<TablaSolicitudesVisita> tablaSolicitudesVisitas = new TablaSolicitudesVisitaDal().obtenerTablaSolicitudesVisita(String.valueOf(this.tgSeleccionado));
        this.rowNombreApellidoVisitanteHistorial.setCellValueFactory(new PropertyValueFactory<>("nombreApPaternoVisita"));
        this.rowNombreApellidoResidenteHistorial.setCellValueFactory(new PropertyValueFactory<>("nombreApPaternoResidente"));
        this.rowDireccionResidenteHistorial.setCellValueFactory(new PropertyValueFactory<>("direccionResidente"));
        this.rowFechaVisitaHistorial.setCellValueFactory(new PropertyValueFactory<>("fechaVisita"));
        this.tvSolicitudesHistorial.setItems(tablaSolicitudesVisitas);
    }

    @FXML
    private void tvMouseCliqueadoTvHistorialVisitasB(MouseEvent event) {
        if (event.getClickCount() == 2 && !event.isConsumed()) {
            try {
                event.consume();
                String rutResidente = String.valueOf(this.tvSolicitudesHistorial.getSelectionModel().getSelectedItem().getRutResidente());
                String rutVisita = String.valueOf(this.tvSolicitudesHistorial.getSelectionModel().getSelectedItem().getRutVisita());
                String idVisita = String.valueOf(this.tvSolicitudesHistorial.getSelectionModel().getSelectedItem().getIdVisita());
                this.datosVisitaHistorial(rutResidente, rutVisita, idVisita);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    private void estadoBotonesSolicitudVisita(int op) {
        if (op == 1) {
            this.btnAceptarVisita.setDisable(true);
            this.btnDenegar.setDisable(true);
        } else {
            this.btnAceptarVisita.setDisable(false);
            this.btnDenegar.setDisable(false);
        }
    }

    private void datosVisitaHistorial(String rutResidente, String rutVisita, String idVisita) {
        Persona residente = new PersonaDal().obtenerPersonaRut(rutResidente, "2");
        Persona visita = new PersonaDal().obtenerPersonaRut(rutVisita, "3");
        SolicitudVisita solicitudVisita = new SolicitudVisitaDal().obtenerSolicitudVisita(Integer.parseInt(idVisita));
        //Residente
        this.txtRutResidenteSV.setText(residente.getRut());
        this.txtNombreResidenteSV.setText(residente.getNombre());
        this.txtApellidoPResidenteSV.setText(residente.getApePaterno());
        this.txtApellidoMResidenteSV.setText(residente.getApeMaterno());
        this.txtRutResidenteSV.setVisible(true);
        this.txtNombreResidenteSV.setVisible(true);
        this.txtApellidoPResidenteSV.setVisible(true);
        this.txtApellidoMResidenteSV.setVisible(true);
        DireccionPersona direccionPersona = new DireccionPersonaDal().obtenerDireccionPersona(residente.getIdPersona());
        Direccion direccion = new DireccionDal().obtenerDireccion(direccionPersona.getIdDireccion());
        String direccionResidente = direccion.getPiso() + " " + direccion.getBlock() + " " + direccion.getNumero();
        this.txtDireccionResidenteSV.setText(direccionResidente);
        this.txtDireccionResidenteSV.setVisible(true);
        //Visita
        this.txtRutVisitaSV.setText(visita.getRut());
        this.txtNombreVisitaSV.setText(visita.getNombre());
        this.txtApellidoPVisitaSV.setText(visita.getApePaterno());
        this.txtApellidoMVisitaSV.setText(visita.getApeMaterno());
        this.txtRutVisitaSV.setVisible(true);
        this.txtNombreVisitaSV.setVisible(true);
        this.txtApellidoPVisitaSV.setVisible(true);
        this.txtApellidoMVisitaSV.setVisible(true);
        //Solicitud visita
        this.txtFechaVisitaSV.setText(solicitudVisita.getFechaVisita() + " " + solicitudVisita.getHoraVisita());
        String estadoSolicitud = "";
        switch (solicitudVisita.getEstadoSolicitud()) {
            case 1:
                estadoSolicitud = "Aceptada";
                break;
            case 2:
                estadoSolicitud = "Rechazada";
                break;
            case 3:
                estadoSolicitud = "Pendiente";
                break;
        }
        this.txtEstadoVisitaSV.setText(estadoSolicitud);
        this.txtFechaVisitaSV.setVisible(true);
        this.txtEstadoVisitaSV.setVisible(true);
        this.txtIdSolicitudVisitaHistorial.setText(idVisita);
        if (this.tgSeleccionado == 3) {
            estadoBotonesSolicitudVisita(0);
        } else {
            estadoBotonesSolicitudVisita(1);
        }
    }

    private void resetCamposVisita() throws FileNotFoundException {
        String reset = "";
        this.jtxtRutVisita.setText(reset);
        this.jtxtNombreVisita.setText(reset);
        this.jtxtSegNombreVisita.setText(reset);
        this.jtxtApellidoPaternoVisita.setText(reset);
        this.jtxtApellidoMaternoVisita.setText(reset);
        this.btnModificar.setDisable(true);
        this.btnAgregar.setDisable(false);
        this.txtRutResidente.setText(reset);
        this.txtNombreResidente.setText(reset);
        this.txtApePaternoResidente.setText(reset);
        this.txtApeMaternoResidente.setText(reset);
        this.txtPisoResidente.setText(reset);
        this.txtBlockResidente.setText(reset);
        this.txtNumeroResidente.setText(reset);
        mostrarDatosTablaVisitas();
    }

    private void resetCamposVisitaHistorial() {
        this.txtRutResidenteSV.setText("");
        this.txtNombreResidenteSV.setText("");
        this.txtApellidoPResidenteSV.setText("");
        this.txtApellidoMResidenteSV.setText("");
        this.txtDireccionResidenteSV.setText("");
        this.txtIdSolicitudVisitaHistorial.setText("");
        this.txtRutVisitaSV.setText("");
        this.txtNombreVisitaSV.setText("");
        this.txtApellidoPVisitaSV.setText("");
        this.txtApellidoMVisitaSV.setText("");
        this.txtFechaVisitaSV.setText("");
        this.txtEstadoVisitaSV.setText("");
        estadoBotonesSolicitudVisita(1);
    }
}
