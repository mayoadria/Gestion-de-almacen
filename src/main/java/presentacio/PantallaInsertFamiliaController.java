/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

/**
 *
 * @author oriol
 */
import Validaciones.ValidarCamposInsertFamilia;
import Validaciones.ValidarFecha;
import presentacio.pantallaFamiliaController;
import model.Familia;
import dades.FamiliaDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logica.FamiliaLogic;
import logica.Mensajes;

public class PantallaInsertFamiliaController extends Mensajes implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            familiaLogica = new FamiliaLogic();
        } catch (SQLException ex) {
            Logger.getLogger(PantallaInsertReferenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private Button btn_afegir_familia;

    @FXML
    private Button btn_sortir_insfam;

    @FXML
    private TextField data_alta_insfam;

    @FXML
    private TextArea descripcio_insfam;

    @FXML
    private TextField id_proveidor_insfam;

    @FXML
    private TextField nom_familia_insfam;

    @FXML
    private TextArea observacions_insfam;

    private FamiliaDAO familiaDAO;

    private pantallaFamiliaController familiaController;

    private FamiliaLogic familiaLogica;

    public void setFamiliaController(pantallaFamiliaController controller) {
        this.familiaController = controller;
    }

    @FXML
    private void inserirFam(ActionEvent event) throws SQLException {
        try {
            familiaDAO = new FamiliaDAO();
            String nomProducte = nom_familia_insfam.getText();
            String Descripcio = descripcio_insfam.getText().toLowerCase();
            String dataAlta = (data_alta_insfam.getText());
            int id_proveidor;
            if (id_proveidor_insfam.getText() == null || id_proveidor_insfam.getText().trim().isEmpty()) {
                id_proveidor = 0; // Valor por defecto
            } else {
                id_proveidor = Integer.parseInt(id_proveidor_insfam.getText());
            }
            String observacions = (observacions_insfam.getText());

            ValidarCamposInsertFamilia.validarDatos(familiaDAO, dataAlta, id_proveidor);
            // Validar la fecha de alta usando la clase FechaValidator
            if (!ValidarFecha.validarFechaAlta(dataAlta)) {
                // Si la fecha es inválida, mostrar un mensaje de error y no continuar
                return; // Detener la inserción si la fecha es inválida
            }
            

            // Crear una instancia de Referencia con los datos del formulario
            Familia novaReferencia = new Familia();
            novaReferencia.setNom_familia(nomProducte);
            novaReferencia.setDescripcio(Descripcio);
            novaReferencia.setData_alta_fam(dataAlta);
            novaReferencia.setId_proveidor_fam(id_proveidor);
            novaReferencia.setObservacions(observacions);

            //Llamar al método insert() del DAO para insertar la referencia en la base de datos
            familiaLogica.afegirFamilia(novaReferencia);
            // Actualizar la tabla en el controlador principal
            familiaController.actualizarTaulaFamilia(novaReferencia);

            // Cerrar la ventana de añadir referencia
            Stage stage = (Stage) btn_sortir_insfam.getScene().getWindow();
            stage.close();
            //Mensaje para saber si se ha hecho el insert
            mostrarMensaje("Família inserida correctament.");
        } catch (SQLException e) {
            // Muestra un mensaje de error si hay problemas con la inserción de una nueva referencia
            System.out.println("Error en inserir la família:" + e.getMessage());

        } catch (NumberFormatException e) {
            //Comprovar si els camps a on s'han d'introduir numeros siguin correctes com per exemple 
            //(como Quantitat, Preu, etc.)
            System.out.println("Escriu correctament els valors numerals:" + e.getMessage());
        } catch (Exception e) {
            // Capturar las excepciones propias (ValidacionException y DatabaseException)
           System.out.println(e.getMessage());
        }
    }

    @FXML
    private void sortir(ActionEvent event) {
        Stage stage = (Stage) this.btn_sortir_insfam.getScene().getWindow();
        stage.close();

    }
}
