/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.project1_grup2;

/**
 *
 * @author oriol
 */

import aplicacio.model.Familia;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class pantallaFamiliaController implements Initializable{
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
    @FXML
    private Button btn_eliminar;

    @FXML
    private Button btn_modificar;

    @FXML
    private Button btn_nova;

    @FXML
    private Button btn_sortir;

    @FXML
    private Label lb_descripcio;

    @FXML
    private Label lb_observacions;

    @FXML
    private TableColumn<?, ?> tc_dataAlta;

    @FXML
    private TableColumn<?, ?> tc_descripcio;

    @FXML
    private TableColumn<?, ?> tc_id;

    @FXML
    private TableColumn<?, ?> tc_idProveidor;

    @FXML
    private TableColumn<?, ?> tc_nom;

    @FXML
    private TableColumn<?, ?> tc_observacions;

    @FXML
    private TableView<?> tv_familia;

    @FXML
    private TextArea txt_areaDescripcio;

    @FXML
    private TextArea txt_areaObservacions;

    @FXML
    private TextField txt_dataAlta;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_idProveidor;

    @FXML
    private TextField txt_nom;
    
    
    @FXML
    void Afegir(ActionEvent event) {

    }

    @FXML
    void Eliminar(ActionEvent event) {

    }
   
    @FXML
    void Modificar(ActionEvent event) {

    }

    @FXML
    void Sortir(ActionEvent event) {

    }

}

