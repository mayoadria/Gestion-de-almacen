package presentacio;

import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logica.ReferenciaLogica;
import model.Referencia;

/**
 * Controlador de la pantalla que muestra las referencias por familia. Permite
 * seleccionar una familia desde un ComboBox y ver las referencias asociadas en
 * un TableView.
 *
 * @author mayoa
 */
public class ReferenciasPorFamiliaController {

    @FXML
    private ComboBox<Integer> comboBoxFamilias;  // ComboBox para seleccionar la familia

    @FXML
    private TableView<Referencia> tableViewReferencias; // TableView para mostrar las referencias

    // Columnas del TableView
    @FXML
    private TableColumn<Referencia, Integer> idReferencia;
    @FXML
    private TableColumn<Referencia, String> nombre;
    @FXML
    private TableColumn<Referencia, Integer> cantidad;
    @FXML
    private TableColumn<Referencia, String> unitatMida;
    @FXML
    private TableColumn<Referencia, String> dataAlta;
    @FXML
    private TableColumn<Referencia, String> dataFab;
    @FXML
    private TableColumn<Referencia, String> descripcio;
    @FXML
    private TableColumn<Referencia, Float> preu;
    @FXML
    private TableColumn<Referencia, Integer> unitVen;
    @FXML
    private TableColumn<Referencia, Integer> idFam;
    @FXML
    private TableColumn<Referencia, Integer> idProv;

    private ReferenciaLogica referenciaLogica; // Lógica de negocio para manejar las referencias

    @FXML
    private Button VolverAtras;

    /**
     * Método de inicialización de la clase. Configura el TableView, el ComboBox
     * y carga los datos iniciales.
     */
    public void initialize() {
        try {
            referenciaLogica = new ReferenciaLogica();  // Inicialización de la lógica de negocio para las referencias

            // Configuración de las columnas del TableView
            idReferencia.setCellValueFactory(new PropertyValueFactory<>("id"));
            nombre.setCellValueFactory(new PropertyValueFactory<>("nom"));
            cantidad.setCellValueFactory(new PropertyValueFactory<>("quantitat"));
            unitatMida.setCellValueFactory(new PropertyValueFactory<>("unitat_mida"));
            dataAlta.setCellValueFactory(new PropertyValueFactory<>("data_alta"));
            dataFab.setCellValueFactory(new PropertyValueFactory<>("data_fabricacio"));
            descripcio.setCellValueFactory(new PropertyValueFactory<>("descripcio"));
            preu.setCellValueFactory(new PropertyValueFactory<>("preu"));
            unitVen.setCellValueFactory(new PropertyValueFactory<>("unitats_venudes"));
            idFam.setCellValueFactory(new PropertyValueFactory<>("id_fam"));
            idProv.setCellValueFactory(new PropertyValueFactory<>("id_proveidor"));

            // Cargar IDs de familias en el ComboBox (esto se obtiene de la lógica de negocio)
            comboBoxFamilias.getItems().addAll(referenciaLogica.getListObservableReferencia()
                    .stream()
                    .map(Referencia::getId_fam)
                    .distinct() // Aseguramos que no haya familias duplicadas
                    .collect(Collectors.toList()));

            // Agregar un listener al ComboBox para cargar las referencias cuando se seleccione una familia
            comboBoxFamilias.setOnAction(event -> cargarReferenciasPorFamilia());
        } catch (Exception e) {
            e.printStackTrace();  // Imprime el error en caso de que falle la inicialización
        }
    }

    /**
     * Método para cargar las referencias asociadas a una familia seleccionada.
     * Obtiene las referencias de la lógica de negocio y las muestra en el
     * TableView.
     */
    private void cargarReferenciasPorFamilia() {
        try {
            int selectedId = comboBoxFamilias.getValue();  // Obtenemos el ID de la familia seleccionada
            ObservableList<Referencia> referencias = referenciaLogica.getReferenciasPorFamilia(selectedId);  // Obtenemos las referencias
            tableViewReferencias.setItems(referencias);  // Asignamos las referencias al TableView
        } catch (Exception e) {
            e.printStackTrace();  // Imprime el error en caso de que falle la carga de las referencias
        }
    }

    /**
     * Cierra la ventana actual y regresa a la pantalla anterior sin cerrar el
     * programa.
     *
     * @param event el evento de acción que dispara la acción de volver.
     */
    @FXML
    void VolverAtras(ActionEvent event) {
        Stage stage = (Stage) this.VolverAtras.getScene().getWindow();
        stage.close();
    }
}
