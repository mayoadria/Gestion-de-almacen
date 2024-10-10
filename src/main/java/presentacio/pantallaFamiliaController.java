/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

/**
 *
 * @author oriol
 */

import logica.Familia;
import dades.FamiliaDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
  
    private FamiliaDAO familiaDAO;

     @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        try{
            familiaDAO = new FamiliaDAO();
        }catch(SQLException ex){
            System.out.println("TODO");
        }
        //families =  FXCollections.observableArrayList();
        this.tc_id.setCellValueFactory(new PropertyValueFactory<>("id_fam"));
        this.tc_nom.setCellValueFactory(new PropertyValueFactory<>("nom_familia"));
        this.tc_descripcio.setCellValueFactory(new PropertyValueFactory<>("descripcio"));
        this.tc_dataAlta.setCellValueFactory(new PropertyValueFactory<>("data_alta_fam"));
        this.tc_idProveidor.setCellValueFactory(new PropertyValueFactory<>("id_proveidor_fam"));
        this.tc_observacions.setCellValueFactory(new PropertyValueFactory<>("Observacions"));
        
        
        try{
            ObservableList<Familia> itemFam = FXCollections.observableArrayList(familiaDAO.getAll());
            this.tv_familia.setItems(itemFam);
            }catch(SQLException ex){
            System.out.println("TODO");
            }
            tv_familia.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    txt_id.setText(String.valueOf(newSelection.getId_fam()));
                    txt_nom.setText(newSelection.getNom_familia());
                    txt_areaDescripcio.setText(newSelection.getDescripcio());
                    txt_dataAlta.setText(newSelection.getData_alta_fam().toString());
                    txt_idProveidor.setText(String.valueOf(newSelection.getId_proveidor_fam()));
                    txt_areaObservacions.setText(newSelection.getObservacions());
                }
            });
                    
        
    }
    /* private int id_fam;
    private String nom_familia;
    private String descripcio;
    private Date data_alta_fam;
    private int id_proveidor_fam;
    private String Observacions;*/
    

    
    @FXML
    void afegirFamilia(ActionEvent event) {
        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PantallaInsertFamilia.fxml"));

            // Cargo el padre
            Parent root = loader.load();

            // Obtengo el controlador de la nueva ventana
            PantallaInsertFamiliaController controladorInsert = loader.getController();
            

            // Paso la referencia de la tabla y de la lista observable al controlador de la nueva ventana
            controladorInsert.setFamiliaController(this);
            // Creo la scene y el stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            // Asocio el stage con el scene
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println("");
        }
    }
    
    public void actualizarTaulaFamilia(Familia novaFamilia) {
        tv_familia.getItems().add(novaFamilia);
        tv_familia.refresh(); 
    }
//        try{
//            
//        
//        int id = Integer.parseInt(this.txt_id.getText());
//        String nom = this.txt_nom.getText();
//        String descripcio = this.txt_areaDescripcio.getText();
//        //todo mirar tipus data sql.date apunts
//        String dataAltaText = this.txt_dataAlta.getText(); // Texto introducido por el usuario
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate dataAlta = LocalDate.parse(dataAltaText, formatter);
//        java.sql.Date sqlDate = java.sql.Date.valueOf(dataAlta);
//        int id_proveidor = Integer.parseInt(this.txt_idProveidor.getText());
//        String observacions = this.txt_areaObservacions.getText();
//        
//        Familia f = new Familia(id, nom, descripcio, sqlDate, id_proveidor, observacions);
//        
//        if (!this.families.contains(f)) {
//            this.families.add(f);
//            this.tv_familia.setItems(families);
//        }else{
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setTitle("Error");
//            alert.setContentText("la familia existeix");
//            alert.showAndWait();
//        }
//        }catch(NumberFormatException e){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setTitle("Error");
//            alert.setContentText("la familia existeix");
//            alert.showAndWait();
//        }
    
//        @FXML
//    void seleccionar(ActionEvent event) {
//        
//        Familia f = this.tv_familia.getSelectionModel().getSelectedItem(); //torna la entitat seleccionada dela taula
//        
//            if (f != null) 
//            {
//                
//                this.txt_id.setText(f.getId_fam() + "");
//                this.txt_nom.setText(f.getNom_familia());
//                this.txt_areaDescripcio.setText(f.getDescripcio());
//                this.txt_dataAlta.setText(f.getData_alta_fam().toString());
//                this.txt_idProveidor.setText(f.getId_proveidor_fam() + "");
//                this.txt_areaObservacions.setText(f.getObservacions());
//            }
//        
//   }

    @FXML
    void eliminar(ActionEvent event) {
        Familia familiaSeleccionada = tv_familia.getSelectionModel().getSelectedItem();
        
        if (familiaSeleccionada != null) {
            
            try{
                familiaDAO.delete(familiaSeleccionada);
                tv_familia.getItems().remove(familiaSeleccionada);
                
            }catch(SQLException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("has de seleccionar una família, o bé "
                            + "estás intentant eliminar una familia associada amb una referència");
                    alert.showAndWait();
                }
        }
    }
   
    @FXML
    void modificar(ActionEvent event) throws SQLException {
        Familia familiaSeleccionada = tv_familia.getSelectionModel().getSelectedItem();
        
        if (familiaSeleccionada != null) {
            
            familiaSeleccionada.setNom_familia(txt_nom.getText());
            familiaSeleccionada.setDescripcio(txt_areaDescripcio.getText());
            familiaSeleccionada.setData_alta_fam(Date.valueOf(txt_dataAlta.getText()));
            familiaSeleccionada.setId_proveidor_fam(Integer.parseInt(txt_idProveidor.getText()));
            familiaSeleccionada.setObservacions(txt_areaObservacions.getText());
            
            tv_familia.refresh();
            familiaDAO.update(familiaSeleccionada);
        }else{
            System.out.println("TODO error no sha selecionat cap familia");
        }
//        Familia f = this.tv_familia.getSelectionModel().getSelectedItem(); //torna la entitat seleccionada dela taula
//        
//            if (f == null) 
//            {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setHeaderText(null);
//                alert.setTitle("Error");
//                alert.setContentText("has de seleccionar una familia");
//                alert.showAndWait();
//                
//            }else{
//                
//                try{
//                
//                int id = Integer.parseInt(this.txt_id.getText());
//                String nom = this.txt_nom.getText();
//                String descripcio = this.txt_areaDescripcio.getText();
//                //todo mirar tipus data sql.date apunts
//                String dataAltaText = this.txt_dataAlta.getText(); // Texto introducido por el usuario
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//                LocalDate dataAlta = LocalDate.parse(dataAltaText, formatter);
//                java.sql.Date sqlDate = java.sql.Date.valueOf(dataAlta);
//                int id_proveidor = Integer.parseInt(this.txt_idProveidor.getText());
//                String observacions = this.txt_areaObservacions.getText();
//
//                Familia aux = new Familia(id, nom, descripcio, sqlDate, id_proveidor, observacions);
//
//                if (!this.families.contains(aux)) {
//                    
//                    f.setId_fam(aux.getId_fam());
//                    f.setNom_familia(aux.getNom_familia());
//                    f.setDescripcio(aux.getDescripcio());
//                    f.setData_alta_fam(aux.getData_alta_fam());
//                    f.setId_proveidor_fam(aux.getId_proveidor_fam());
//                    f.setObservacions(aux.getObservacions());
//                    
//                    this.tv_familia.refresh();
//                    
//                }else{
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setHeaderText(null);
//                    alert.setTitle("Error");
//                    alert.setContentText("la familia existeix");
//                    alert.showAndWait();
//                }
//                }catch(NumberFormatException e){
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setHeaderText(null);
//                    alert.setTitle("Error");
//                    alert.setContentText("la familia existeix");
//                    alert.showAndWait();
//                }
//            }
    }

    @FXML
    void sortir(ActionEvent event) {
        
        
            
    }

}

