/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cat.copernic.project1_grup2;

import aplicacio.model.Referencia;
import dades.ReferenciaDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logica.Mensajes;

/**
 * FXML Controller class
 *
 * @author mayoa
 */
public class PantallaInsertReferenciaController extends Mensajes implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    private void AfegirPersona(ActionEvent event) {
        try {
            // Obtener los datos del formulario
            String nomProducte = txtnomProducte.getText();
            int quantitat = Integer.parseInt(txtQuantitatReferencia.getText());
            String unitatMida = txtUnitatMida.getText();
            java.sql.Date dataAlta = java.sql.Date.valueOf(txtDataAlta.getText());
            java.sql.Date dataFabricacio = java.sql.Date.valueOf(txtDataFab.getText());
            String descripcioProducte = txtDescripcio.getText();
            float preu = Float.parseFloat(txtPreu.getText());
            int unitatsVenudes = Integer.parseInt(txtUnitVen.getText());
            int idFamilia = Integer.parseInt(txtIdFam.getText());
            int idProveidor = Integer.parseInt(txtIdProv.getText());
            
            
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

            referenciaDAO.insert(novaReferencia);

            //Chivato por consola para saber si se ha hecho el insert
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

    //Fer que quan es pulsi, la finestra es tanqui, però el programa segueix funcionant
    @FXML
    private void TornarMenu(ActionEvent event) {
        Stage stage = (Stage) this.btnSortir.getScene().getWindow();
        stage.close();

    }
    
    

}
