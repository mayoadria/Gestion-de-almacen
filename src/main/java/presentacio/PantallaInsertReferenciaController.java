/*
         * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
         * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacio;

import model.Referencia;
import dades.ReferenciaDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logica.Mensajes;
import logica.ReferenciaLogica;
import Validaciones.ValidarCamposInsertReferencia;
import Validaciones.ValidarFecha;

/**
 * Controlador de la pantalla para insertar referencias de producto.
 * Esta clase se encarga de gestionar la lógica de la interfaz de usuario 
 * relacionada con la inserción de nuevas referencias y la validación de datos.
 * Extiende la clase Mensajes para utilizar métodos de manejo de mensajes.
 * 
 * @author mayoa
 */
public class PantallaInsertReferenciaController extends Mensajes implements Initializable {

    /**
     * Inicializa el controlador de la clase.
     * 
     * @param url URL de localización para el recurso.
     * @param rb Recurso que contiene las propiedades de la clase.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            referenciaLogica = new ReferenciaLogica();
        } catch (SQLException ex) {
            Logger.getLogger(PantallaInsertReferenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private Button btnAfegir;

    @FXML
    private Button btnSortir;

    @FXML
    private TextField txtDataAlta;

    @FXML
    private TextField txtDataFab;

    @FXML
    private TextArea txtDescripcio;

    @FXML
    private TextField txtIdFam;

    @FXML
    private TextField txtIdProv;

    @FXML
    private TextField txtPreu;

    @FXML
    private TextField txtQuantitatReferencia;

    @FXML
    private TextField txtUnitVen;

    @FXML
    private TextField txtUnitatMida;

    @FXML
    private TextField txtnomProducte;

    private ReferenciaDAO referenciaDAO;

    private ReferenciaLogica referenciaLogica;

    private PantallaReferenciaController referenciaController;

    /**
     * Establece el controlador de la pantalla de referencia.
     * 
     * @param controller El controlador de la pantalla de referencia principal.
     */
    public void setReferenciaController(PantallaReferenciaController controller) {
        this.referenciaController = controller;
    }
    
    /**
     * Agrega una nueva referencia utilizando los datos del formulario.
     * 
     * @param event El evento de acción que desencadena la inserción.
     */
    @FXML
    private void AfegirPersona(ActionEvent event) throws Exception {
        try {
            // Obtener los datos del formulario
            referenciaDAO = new ReferenciaDAO();
            String nomProducte = txtnomProducte.getText();
            int quantitat = Integer.parseInt(txtQuantitatReferencia.getText());
            String unitatMida = txtUnitatMida.getText().toLowerCase();
            String dataAlta = (txtDataAlta.getText());
            String dataFabricacio = (txtDataFab.getText());
            String descripcioProducte = txtDescripcio.getText();
            String preu = txtPreu.getText();
            int unitatsVenudes = Integer.parseInt(txtUnitVen.getText());
            int idFamilia = Integer.parseInt(txtIdFam.getText());
            int idProveidor = Integer.parseInt(txtIdProv.getText());

            ValidarCamposInsertReferencia.validarDatos(referenciaDAO, unitatMida, dataAlta, dataFabricacio, preu, quantitat, idFamilia, idProveidor,unitatsVenudes);
            if (!ValidarFecha.validarFechaAlta(dataAlta)) {
                // Si la fecha es inválida, mostrar un mensaje de error y no continuar
                return; // Detener la inserción si la fecha es inválida
            }
            
            // Crear una instancia de Referencia con los datos del formulario
            Referencia novaReferencia = new Referencia();
            novaReferencia.setNom(nomProducte);
            novaReferencia.setQuantitat(quantitat);
            novaReferencia.setUnitat_mida(unitatMida);
            novaReferencia.setData_alta(dataAlta);
            novaReferencia.setData_fabricacio(dataFabricacio);
            novaReferencia.setDescripcio(descripcioProducte);
            novaReferencia.setPreu(preu);
            novaReferencia.setUnitats_venudes(unitatsVenudes);
            novaReferencia.setId_fam(idFamilia);
            novaReferencia.setId_proveidor(idProveidor);

            //Llamar al método insert() del DAO para insertar la referencia en la base de datos
            referenciaLogica.afegirReferencia(novaReferencia);
            // Actualizar la tabla en el controlador principal
            referenciaController.actualizarTablaConNuevaReferencia(novaReferencia);

            // Cerrar la ventana de añadir referencia
            Stage stage = (Stage) btnSortir.getScene().getWindow();
            stage.close();
            //Mensaje para saber si se ha hecho el insert
            mostrarMensaje("Referència inserida correctament.");

        } catch (NumberFormatException e) {
        // Captura excepciones de conversión y muestra un mensaje específico para el usuario
        mostrarMensajeError("Si us plau, introduïu valors numèrics als camps de quantitat, preu, etc.");
        } catch (Exception e) {
            // Capturar las excepciones propias (ValidacionException y DatabaseException)
            mostrarMensajeError(e.getMessage());
        }
    }

    /**
     * Cierra la ventana actual sin cerrar la aplicación.
     * 
     * @param event El evento de acción que desencadena el cierre de la ventana.
     */
    @FXML
    private void TornarMenu(ActionEvent event) {
        Stage stage = (Stage) this.btnSortir.getScene().getWindow();
        stage.close();

    }

}
