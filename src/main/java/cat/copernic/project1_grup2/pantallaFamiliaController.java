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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class pantallaFamiliaController implements Initializable{
    
   
    
    
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
    private TableColumn<Familia, Date> tc_dataAlta;

    @FXML
    private TableColumn<Familia, String> tc_descripcio;

    @FXML
    private TableColumn<Familia, Integer> tc_id;

    @FXML
    private TableColumn<Familia, Integer> tc_idProveidor;

    @FXML
    private TableColumn<Familia, String> tc_nom;

    @FXML
    private TableColumn<Familia, String> tc_observacions;

    @FXML
    private TableView<Familia> tv_familia;

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
    
    private ObservableList<Familia> families;

     @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        families =  FXCollections.observableArrayList();
        this.tc_id.setCellValueFactory(new PropertyValueFactory("id_fam"));
        this.tc_nom.setCellValueFactory(new PropertyValueFactory("nom_familia"));
        this.tc_descripcio.setCellValueFactory(new PropertyValueFactory("descripcio"));
        this.tc_dataAlta.setCellValueFactory(new PropertyValueFactory("data_alta_fam"));
        this.tc_idProveidor.setCellValueFactory(new PropertyValueFactory("id_proveidor_fam"));
        this.tc_observacions.setCellValueFactory(new PropertyValueFactory("Observacions"));
        
    }
    /* private int id_fam;
    private String nom_familia;
    private String descripcio;
    private Date data_alta_fam;
    private int id_proveidor_fam;
    private String Observacions;*/
    
    @FXML
    void afegirFamilia(ActionEvent event) {
        
        try{
            
        
        int id = Integer.parseInt(this.txt_id.getText());
        String nom = this.txt_nom.getText();
        String descripcio = this.txt_areaDescripcio.getText();
        //todo mirar tipus data sql.date apunts
        String dataAltaText = this.txt_dataAlta.getText(); // Texto introducido por el usuario
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataAlta = LocalDate.parse(dataAltaText, formatter);
        java.sql.Date sqlDate = java.sql.Date.valueOf(dataAlta);
        int id_proveidor = Integer.parseInt(this.txt_idProveidor.getText());
        String observacions = this.txt_areaObservacions.getText();
        
        Familia f = new Familia(id, nom, descripcio, sqlDate, id_proveidor, observacions);
        
        if (!this.families.contains(f)) {
            this.families.add(f);
            this.tv_familia.setItems(families);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("la familia existeix");
            alert.showAndWait();
        }
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("la familia existeix");
            alert.showAndWait();
        }
    }
        @FXML
    void seleccionar(ActionEvent event) {
        
        Familia f = this.tv_familia.getSelectionModel().getSelectedItem(); //torna la entitat seleccionada dela taula
        
            if (f != null) 
            {
                
                this.txt_id.setText(f.getId_fam() + "");
                this.txt_nom.setText(f.getNom_familia());
                this.txt_areaDescripcio.setText(f.getDescripcio());
                this.txt_dataAlta.setText(f.getData_alta_fam() + "");
                this.txt_idProveidor.setText(f.getId_proveidor_fam() + "");
                this.txt_areaObservacions.setText(f.getObservacions());
            }
        
   }

    @FXML
    void eliminar(ActionEvent event) {

    }
   
    @FXML
    void modificar(ActionEvent event) {
        Familia f = this.tv_familia.getSelectionModel().getSelectedItem(); //torna la entitat seleccionada dela taula
        
            if (f == null) 
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("has de seleccionar una familia");
                alert.showAndWait();
                
            }else{
                
                try{
                
                int id = Integer.parseInt(this.txt_id.getText());
                String nom = this.txt_nom.getText();
                String descripcio = this.txt_areaDescripcio.getText();
                //todo mirar tipus data sql.date apunts
                String dataAltaText = this.txt_dataAlta.getText(); // Texto introducido por el usuario
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataAlta = LocalDate.parse(dataAltaText, formatter);
                java.sql.Date sqlDate = java.sql.Date.valueOf(dataAlta);
                int id_proveidor = Integer.parseInt(this.txt_idProveidor.getText());
                String observacions = this.txt_areaObservacions.getText();

                Familia aux = new Familia(id, nom, descripcio, sqlDate, id_proveidor, observacions);

                if (!this.families.contains(aux)) {
                    
                    f.setId_fam(aux.getId_fam());
                    f.setNom_familia(aux.getNom_familia());
                    f.setDescripcio(aux.getDescripcio());
                    f.setData_alta_fam(aux.getData_alta_fam());
                    f.setId_proveidor_fam(aux.getId_proveidor_fam());
                    f.setObservacions(aux.getObservacions());
                    
                    this.tv_familia.refresh();
                    
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("la familia existeix");
                    alert.showAndWait();
                }
                }catch(NumberFormatException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("la familia existeix");
                    alert.showAndWait();
                }
            }
    }

    @FXML
    void sortir(ActionEvent event) {
        
        Familia f = this.tv_familia.getSelectionModel().getSelectedItem(); //torna la entitat seleccionada dela taula
        
            if (f == null) 
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("has de seleccionar una familia");
                alert.showAndWait();
                
            }else{
                this.families.remove(f);
                this.tv_familia.refresh();
            }
    }

}

