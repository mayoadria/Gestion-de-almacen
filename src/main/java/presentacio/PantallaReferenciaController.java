/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacio;

import logica.Referencia;
import dades.ReferenciaDAO;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logica.Mensajes;
import logica.ReferenciaLogica;

/**
 * FXML Controller class
 *
 * @author mayoa
 */
public class PantallaReferenciaController extends Mensajes implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label Descripcio;

    @FXML
    private Button btnEliminarReferencia;

    @FXML
    private Button btnExportar;

    @FXML
    private Button btnImportar;

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

    //Poner que el valor por defecto de cada columna sea el correspondiente
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
        });
    }

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
    // Método para añadir una referencia directamente a la tabla

    public void actualizarTablaConNuevaReferencia(Referencia nuevaReferencia) {
        tblReferencia.getItems().add(nuevaReferencia);
        tblReferencia.refresh();  // Refrescar la tabla para que se vea la nueva entrada
    }

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

    //Volver a la pantalla anterior sin cerrar el programa
    @FXML
    void VolverAtras(ActionEvent event) {
        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
        stage.close();
    }

}
