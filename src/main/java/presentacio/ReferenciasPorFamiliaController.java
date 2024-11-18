/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

/**
 *
 * @author mayoa
 */
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logica.ReferenciaLogica;
import model.Referencia;

public class ReferenciasPorFamiliaController {

    @FXML
    private ComboBox<Integer> comboBoxFamilias;

    @FXML
    private TableView<Referencia> tableViewReferencias;

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

    private ReferenciaLogica referenciaLogica;

    public void initialize() {
        try {
            referenciaLogica = new ReferenciaLogica();

            // Configurar columnas del TableView
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

            // Cargar IDs de familias en el ComboBox (esto podría venir de otra clase lógica)
            comboBoxFamilias.getItems().addAll(referenciaLogica.getListObservableReferencia()
                    .stream()
                    .map(Referencia::getId_fam)
                    .distinct()
                    .collect(Collectors.toList()));

            // Agregar listener al ComboBox para cargar referencias al seleccionar una familia
            comboBoxFamilias.setOnAction(event -> cargarReferenciasPorFamilia());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarReferenciasPorFamilia() {
        try {
            int selectedId = comboBoxFamilias.getValue();
            ObservableList<Referencia> referencias = referenciaLogica.getReferenciasPorFamilia(selectedId);
            tableViewReferencias.setItems(referencias);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
