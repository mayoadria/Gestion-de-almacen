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
       
    }
   
    @FXML
    void modificar(ActionEvent event) throws SQLException {
        
    }

    @FXML
    void sortir(ActionEvent event) {
        Stage stage = (Stage) this.btn_sortir.getScene().getWindow();
        stage.close();
            
    }

}

