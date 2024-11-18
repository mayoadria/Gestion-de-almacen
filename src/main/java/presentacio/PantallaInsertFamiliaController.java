package presentacio;

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

/**
 * Controlador de la pantalla de inserción de una nueva familia.
 * Gestiona los eventos y validaciones del formulario de inserción de familia.
 * 
 * @author oriol
 */
public class PantallaInsertFamiliaController extends Mensajes implements Initializable {

    // Lógica de negocios de las familias
    private FamiliaLogic familiaLogica;

    // Capa de acceso a datos de la familia
    private FamiliaDAO familiaDAO;

    // Controlador de la pantalla principal de familias
    private pantallaFamiliaController familiaController;

    @FXML
    private Button btn_afegir_familia; // Botón para agregar la familia

    @FXML
    private Button btn_sortir_insfam; // Botón para salir de la pantalla

    @FXML
    private TextField data_alta_insfam; // Campo de fecha de alta

    @FXML
    private TextArea descripcio_insfam; // Campo de descripción

    @FXML
    private TextField id_proveidor_insfam; // Campo de proveedor

    @FXML
    private TextField nom_familia_insfam; // Campo de nombre de la familia

    @FXML
    private TextArea observacions_insfam; // Campo de observaciones

    /**
     * Inicializa el controlador de la pantalla de inserción de familia.
     * Se encarga de crear la lógica de negocios de la familia.
     * 
     * @param url La URL de la vista (FXML)
     * @param rb El recurso asociado
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            familiaLogica = new FamiliaLogic();
        } catch (SQLException ex) {
            Logger.getLogger(PantallaInsertReferenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Establece el controlador de la pantalla principal para actualizar la tabla de familias.
     * 
     * @param controller El controlador principal de la pantalla de familias.
     */
    public void setFamiliaController(pantallaFamiliaController controller) {
        this.familiaController = controller;
    }

    /**
     * Inserta una nueva familia en la base de datos.
     * Recoge los datos del formulario, valida y los pasa al modelo para su inserción.
     * 
     * @param event El evento de acción cuando se presiona el botón para insertar familia.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    @FXML
    private void inserirFam(ActionEvent event) throws SQLException {
        try {
            // Recogemos los valores de los campos del formulario
            familiaDAO = new FamiliaDAO();
            String nomProducte = nom_familia_insfam.getText();
            String Descripcio = descripcio_insfam.getText().toLowerCase();
            String dataAlta = data_alta_insfam.getText();
            int id_proveidor;

            // Validación del campo id_proveidor
            if (id_proveidor_insfam.getText() == null || id_proveidor_insfam.getText().trim().isEmpty()) {
                id_proveidor = 0; // Valor por defecto
            } else {
                id_proveidor = Integer.parseInt(id_proveidor_insfam.getText());
            }
            String observacions = observacions_insfam.getText();

            // Validaciones del formulario
            ValidarCamposInsertFamilia.validarDatos(familiaDAO, dataAlta, id_proveidor);

            // Validar la fecha de alta usando la clase ValidarFecha
            if (!ValidarFecha.validarFechaAlta(dataAlta)) {
                return; // Detener la inserción si la fecha es inválida
            }

            // Crear una nueva instancia de Familia con los datos obtenidos
            Familia novaReferencia = new Familia();
            novaReferencia.setNom_familia(nomProducte);
            novaReferencia.setDescripcio(Descripcio);
            novaReferencia.setData_alta_fam(dataAlta);
            novaReferencia.setId_proveidor_fam(id_proveidor);
            novaReferencia.setObservacions(observacions);

            // Insertar la nueva familia en la base de datos
            familiaLogica.afegirFamilia(novaReferencia);

            // Actualizar la tabla en el controlador principal de familias
            familiaController.actualizarTaulaFamilia(novaReferencia);

            // Cerrar la ventana de inserción de familia
            Stage stage = (Stage) btn_sortir_insfam.getScene().getWindow();
            stage.close();

            // Mostrar mensaje de éxito
            mostrarMensaje("Família inserida correctament.");
        } catch (SQLException e) {
            // Captura cualquier error SQL y muestra el mensaje de error
            System.out.println("Error en inserir la família:" + e.getMessage());
        } catch (NumberFormatException e) {
            // Captura error si los campos numéricos no están correctamente formateados
            System.out.println("Escriu correctament els valors numerals:" + e.getMessage());
        } catch (Exception e) {
            // Captura cualquier otro tipo de excepción y la muestra
            System.out.println(e.getMessage());
        }
    }

    /**
     * Cierra la ventana de inserción de familia sin guardar cambios.
     * 
     * @param event El evento de acción cuando se presiona el botón para salir.
     */
    @FXML
    private void sortir(ActionEvent event) {
        Stage stage = (Stage) this.btn_sortir_insfam.getScene().getWindow();
        stage.close();
    }
}
