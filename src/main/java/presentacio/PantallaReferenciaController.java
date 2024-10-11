/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacio;

import model.Referencia;
import dades.ReferenciaDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logica.Mensajes;
import logica.ReferenciaLogica;

/**
 * Controlador de la interfaz gráfica para gestionar referencias en una base de datos.
 * Proporciona funcionalidades para añadir, modificar, eliminar y seleccionar referencias.
 * Extiende la clase Mensajes para mostrar mensajes informativos y de error, e implementa
 * Initializable para la configuración inicial de la interfaz.
 * 
 * @author mayoa
 * @version 1.0
 * @since 2024
 */
public class PantallaReferenciaController extends Mensajes implements Initializable {
    @FXML
    private Button btnEliminarReferencia;

    @FXML
    private Button btnModificarReferencia;

    @FXML
    private Button btnNovaReferencia;

    @FXML
    private Button btnSalir;

    @FXML
    private TableColumn<Referencia, Integer> cantidad;

    @FXML
    private TableColumn<Referencia, Date> dataAlta;

    @FXML
    private TableColumn<Referencia, Date> dataFab;

    @FXML
    private TableColumn<Referencia, String> descripcio;

    @FXML
    private TableColumn<Referencia, Integer> idFam;

    @FXML
    private TableColumn<Referencia, Integer> idProv;

    @FXML
    private TableColumn<Referencia, Integer> idReferencia;

    @FXML
    private TableColumn<Referencia, String> nombre;

    @FXML
    private TableColumn<Referencia, Float> preu;

    @FXML
    private TableView<Referencia> tblReferencia;

    @FXML
    private TextArea txtAreaDescripcio;

    @FXML
    private TextField txtDataAlta;

    @FXML
    private TextField txtDataFabricacio;

    @FXML
    private TextField txtIdFamilia;

    @FXML
    private TextField txtIdProveidor;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtPreu;

    @FXML
    private TextField txtReferencia;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtUnitatMida;

    @FXML
    private TextField txtUnitatVenudes;

    @FXML
    private TableColumn<Referencia, Integer> unitVen;

    @FXML
    private TableColumn<Referencia, String> unitatMida;

    private ReferenciaDAO referenciaDAO;
    
    private ReferenciaLogica referenciaLogica;
    private String rol;

    /**
     * Inicializa la clase del controlador configurando la lógica de negocio, el modelo de datos
     * y la visualización de la tabla de referencias. Establece la configuración de las columnas
     * de la tabla y añade un listener para actualizar los campos de texto con los datos de la 
     * referencia seleccionada.
     * 
     * @param url la URL de la ubicación de los recursos.
     * @param rb el conjunto de recursos para localización.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        try {
            this.referenciaLogica = new ReferenciaLogica(); 
        } catch (SQLException ex) {
            Logger.getLogger(PantallaReferenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.idReferencia.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nombre.setCellValueFactory(new PropertyValueFactory<>("nom"));
        this.cantidad.setCellValueFactory(new PropertyValueFactory<>("quantitat"));
        this.unitatMida.setCellValueFactory(new PropertyValueFactory<>("unitat_mida"));
        this.dataAlta.setCellValueFactory(new PropertyValueFactory<>("data_alta"));
        this.dataFab.setCellValueFactory(new PropertyValueFactory<>("data_fabricacio"));
        this.descripcio.setCellValueFactory(new PropertyValueFactory<>("descripcio"));
        this.preu.setCellValueFactory(new PropertyValueFactory<>("preu"));
        this.unitVen.setCellValueFactory(new PropertyValueFactory<>("unitats_venudes"));
        this.idFam.setCellValueFactory(new PropertyValueFactory<>("id_fam"));
        this.idProv.setCellValueFactory(new PropertyValueFactory<>("id_proveidor"));

        
        // Obtener la lista de referencias desde ReferenciaDAO
        this.tblReferencia.setItems(referenciaLogica.getListObservableReferencia());

        // Añadir el listener a la tabla para que se actualicen los TextFields cuando cambie la selección
        tblReferencia.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Actualizar los TextFields con la información del elemento seleccionado
                
                txtNom.setText(newSelection.getNom());
                txtReferencia.setText(String.valueOf(newSelection.getId()));
                txtCantidad.setText(String.valueOf(newSelection.getQuantitat()));
                txtUnitatMida.setText(newSelection.getUnitat_mida());
                txtDataAlta.setText(newSelection.getData_alta().toString());
                txtDataFabricacio.setText(newSelection.getData_fabricacio().toString());
                txtAreaDescripcio.setText(newSelection.getDescripcio());
                txtPreu.setText(String.valueOf(newSelection.getPreu()));
                txtUnitatVenudes.setText(String.valueOf(newSelection.getUnitats_venudes()));
                txtIdFamilia.setText(String.valueOf(newSelection.getId_fam()));
                txtIdProveidor.setText(String.valueOf(newSelection.getId_proveidor()));
            }
            configurarBotonesPorRol();
        });
    }
    public void setRol(String rol) {
        this.rol = rol;
        configurarBotonesPorRol(); // Llamar para aplicar la configuración de botones al establecer el rol
    }

    // Método para habilitar o deshabilitar botones según el rol
    private void configurarBotonesPorRol() {
        if (rol != null) {
             if (rol.equals("Venedor")) {
                // Deshabilitar botones para usuarios regulares
                btnNovaReferencia.setDisable(true);
                btnModificarReferencia.setDisable(true);
                btnEliminarReferencia.setDisable(true);
            }
        }
    }

    
    /**
     * Abre la interfaz para añadir una nueva referencia. Este método configura una nueva ventana
     * donde se puede crear una nueva referencia, y pasa la referencia de la tabla y lista 
     * observable al nuevo controlador.
     * 
     * @param event el evento de acción que dispara la creación de una nueva referencia.
     */
    @FXML
    void Afegir(ActionEvent event) {
        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PantallaInsertReferencia.fxml"));

            // Cargo el padre
            Parent root = loader.load();

            // Obtengo el controlador de la nueva ventana
            PantallaInsertReferenciaController controladorInsert = loader.getController();

            // Paso la referencia de la tabla y de la lista observable al controlador de la nueva ventana
            controladorInsert.setReferenciaController(this);
            // Creo la scene y el stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            // Asocio el stage con el scene
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(PantallaSeleccionarMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Añade una nueva referencia a la tabla visualizando directamente en la interfaz.
     * 
     * @param nuevaReferencia la nueva referencia a añadir en la tabla.
     */
    public void actualizarTablaConNuevaReferencia(Referencia nuevaReferencia) {
        tblReferencia.getItems().add(nuevaReferencia);
        tblReferencia.refresh();  // Refrescar la tabla para que se vea la nueva entrada
    }
    
    /**
     * Elimina la referencia seleccionada en la tabla. Si no hay ninguna referencia seleccionada,
     * se muestra un mensaje de error.
     * 
     * @param event el evento de acción que dispara la eliminación de la referencia.
     */
    @FXML
    void Eliminar(ActionEvent event) {
        // Obtener la referencia seleccionada en la tabla
        Referencia referenciaSeleccionada = tblReferencia.getSelectionModel().getSelectedItem();

        if (referenciaSeleccionada != null) {
            try {

                // Llamar al método delete en la referencia seleccionada
                referenciaLogica.eliminarReferencia(referenciaSeleccionada);

                // Remover la referencia eliminada de la tabla
                tblReferencia.getItems().remove(referenciaSeleccionada);

                mostrarMensaje("Referencia eliminada exitosamente.");
            } catch (SQLException ex) {
                Logger.getLogger(PantallaReferenciaController.class.getName()).log(Level.SEVERE, "Error al eliminar la referencia", ex);
            }
        } else {
            mostrarMensajeError("No se ha seleccionado ninguna referencia para eliminar.");
        }
    }
    
    /**
     * Modifica los datos de la referencia seleccionada en la tabla de acuerdo a los valores
     * introducidos en los campos de texto.
     * 
     * @param event el evento de acción que dispara la modificación de la referencia.
     * @throws SQLException si ocurre un error al intentar actualizar la referencia en la base de datos.
     */
    @FXML
    void Modificar(ActionEvent event) throws SQLException {
        Referencia referenciaSeleccionada = tblReferencia.getSelectionModel().getSelectedItem();
        if (referenciaSeleccionada != null) {
            referenciaSeleccionada.setNom(txtNom.getText());
            referenciaSeleccionada.setQuantitat(Integer.parseInt(txtCantidad.getText()));
            referenciaSeleccionada.setUnitat_mida(txtUnitatMida.getText());
            referenciaSeleccionada.setData_alta(txtDataAlta.getText());  // Asegúrate de que esté en formato correcto
            referenciaSeleccionada.setData_fabricacio(txtDataFabricacio.getText());
            referenciaSeleccionada.setDescripcio(txtAreaDescripcio.getText());
            referenciaSeleccionada.setPreu(txtPreu.getText());
            referenciaSeleccionada.setUnitats_venudes(Integer.parseInt(txtUnitatVenudes.getText()));
            referenciaSeleccionada.setId_fam(Integer.parseInt(txtIdFamilia.getText()));
            referenciaSeleccionada.setId_proveidor(Integer.parseInt(txtIdProveidor.getText()));
            
            // Actualizar la tabla visualmente
            tblReferencia.refresh();
            referenciaLogica.modificarReferencia(referenciaSeleccionada);
        } else {
            mostrarMensajeError("No se ha seleccionado ninguna referencia.");
        }

    }

    /**
     * Cierra la ventana actual y regresa a la pantalla anterior sin cerrar el programa.
     * 
     * @param event el evento de acción que dispara la acción de volver.
     */
    @FXML
    void VolverAtras(ActionEvent event) {
        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
        stage.close();
    }

}
