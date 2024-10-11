
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

/**
 *
 * @author oriol
 */
import presentacio.pantallaFamiliaController;
import model.Familia;
import dades.FamiliaDAO;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PantallaInsertFamiliaController {

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
    
    public void setFamiliaController(pantallaFamiliaController controller) {
        this.familiaController = controller;
    }
    
    @FXML
    private void inserirFam(ActionEvent event) {
            try{
        
            familiaDAO = new FamiliaDAO(); 
            
            String nomFamilia = nom_familia_insfam.getText();
            String descripcio = descripcio_insfam.getText();
            java.sql.Date dataAlta = java.sql.Date.valueOf(data_alta_insfam.getText());
            int idProveidorDef = Integer.parseInt(id_proveidor_insfam.getText());
            String observacions = observacions_insfam.getText();
            
            //TODO existeix proveidor?
            
            Familia familiaAfegir = new Familia();
            familiaAfegir.setNom_familia(nomFamilia);
            familiaAfegir.setDescripcio(descripcio);
            familiaAfegir.setData_alta_fam(dataAlta);
            familiaAfegir.setId_proveidor_fam(idProveidorDef);
            familiaAfegir.setObservacions(observacions);
            
            familiaDAO.insert(familiaAfegir);
            
            familiaController.actualizarTaulaFamilia(familiaAfegir);
                      
            System.out.println("Familia inserida correctament");
        
        }catch(SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error en inserir una nova familia");
            alert.showAndWait();
           
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Escriu correctament els valors numèrics");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void sortir(ActionEvent event) {
        Stage stage = (Stage) this.btn_sortir_insfam.getScene().getWindow();
        stage.close();

    }

}
//public class PantallaInsertFamiliaController {
//    
//}

