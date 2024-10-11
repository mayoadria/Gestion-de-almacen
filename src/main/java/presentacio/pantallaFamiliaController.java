/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

/**
 *
 * @author oriol
 */

import model.Familia;
import dades.FamiliaDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    
    private String rol;

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
     public void setRol(String rol) {
        this.rol = rol;
        configurarBotonesPorRol(); // Llamar para aplicar la configuración de botones al establecer el rol
    }

    // Método para habilitar o deshabilitar botones según el rol
    private void configurarBotonesPorRol() {
        if (rol != null) {
             if (rol.equals("Venedor")) {
                // Deshabilitar botones para usuarios regulares
                btn_nova.setDisable(true);
                btn_eliminar.setDisable(true);
                btn_modificar.setDisable(true);
            }
        }
    }  

    

    
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
    }

    @FXML
    void sortir(ActionEvent event) {
        Stage stage = (Stage) this.btn_sortir.getScene().getWindow();
        stage.close();
            
    }

}

