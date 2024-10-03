/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cat.copernic.project1_grup2;

import aplicacio.model.Referencia;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mayoa
 */
public class PantallaReferenciaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
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
    private TableColumn<?, ?> cantidad;

    @FXML
    private TableColumn<?, ?> dataAlta;

    @FXML
    private TableColumn<?, ?> dataFab;

    @FXML
    private TableColumn<?, ?> descripcio;

    @FXML
    private TableColumn<?, ?> idFam;

    @FXML
    private TableColumn<?, ?> idProv;

    @FXML
    private TableColumn<?, ?> idReferencia;

    @FXML
    private TableColumn<?, ?> nombre;

    @FXML
    private TableColumn<?, ?> preu;

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
    private TextField txtReferencia2;

    @FXML
    private TextField txtUnitatMida;

    @FXML
    private TextField txtUnitatVenudes;

    @FXML
    private TableColumn<?, ?> unitVen;

    @FXML
    private TableColumn<?, ?> unitatMida;

    @FXML
    void Afegir(ActionEvent event) {

    }

    @FXML
    void Eliminar(ActionEvent event) {

    }

    @FXML
    void Exportar(ActionEvent event) {

    }

    @FXML
    void Importar(ActionEvent event) {

    }
    
    
    @FXML
    void Modificar(ActionEvent event) {

    }

    @FXML
    void VolverAtras(ActionEvent event) {

    }
    
}
