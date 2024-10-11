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
    private void AfegirPersona(ActionEvent event) {
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

            //Validar que la unitat de mida sigui correcte
            if(!referenciaLogica.unitatMidaValid(unitatMida)){
                mostrarMensajeError("La unitat de mida no es valida");
                return;
            }
            //Validar que la data d'alta sigui correcte
            if(!referenciaLogica.FechaValida(dataAlta)){
                mostrarMensajeError("La data d'alta no es valida");
                return;
            }
            //Validar que la data de fabricacio sigui correcte
            if(!referenciaLogica.FechaValida(dataFabricacio)){
                mostrarMensajeError("La data de fabricació no es valida");
                return;
            }
            //Validar que el preu sigui correcte
            if(!referenciaLogica.PreuValid(String.valueOf(preu))){
                mostrarMensajeError("La preu no es valid");
                return;
            }
            //Validar que la quantitat sigui correcte
            if(!referenciaLogica.PreuValid(String.valueOf(quantitat))){
                mostrarMensajeError("La quantitat no es valida");
                return;
            }
            // Validar que la familia existe en la base de datos
            if (!referenciaDAO.existeFamilia(idFamilia)) {
                mostrarMensajeError("La familia introducida no existe. Por favor, introduce un ID de familia válido.");
                return; // Detener el flujo si la familia no existe
            }

            // Validar que el proveedor existe en la base de datos
            if (!referenciaDAO.existeProveedor(idProveidor)) {
                mostrarMensajeError("El proveedor introducido no existe. Por favor, introduce un ID de proveedor válido.");
                return; // Detener el flujo si el proveedor no existe
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
            mostrarMensaje("Referencia insertada correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
            // Muestra un mensaje de error si hay problemas con la inserción de una nueva referencia
            mostrarMensajeError("Error al insertar la referencia: " + e.getMessage());

        } catch (NumberFormatException e) {
            //Comprovar si els camps a on s'han d'introduir numeros siguin correctes com per exemple 
            //(como Quantitat, Preu, etc.)
            e.printStackTrace();
            mostrarMensajeError("Escribe de forma correcta los valores numerales: " + e.getMessage());
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
