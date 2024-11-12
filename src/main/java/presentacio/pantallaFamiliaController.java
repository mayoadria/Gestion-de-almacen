/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

/**
 *
 * @author oriol
 */

import Validaciones.ValidarCamposInsertFamilia;
import model.Familia;
import logica.FamiliaLogic;
import dades.FamiliaDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static logica.Mensajes.*;


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
    
    private FamiliaLogic familiaLogica;
    
    private String rol;

     @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.familiaLogica = new FamiliaLogic();
        } catch (SQLException ex) {
            Logger.getLogger(PantallaReferenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.tc_id.setCellValueFactory(new PropertyValueFactory<>("id_fam"));
        this.tc_nom.setCellValueFactory(new PropertyValueFactory<>("nom_familia"));
        this.tc_descripcio.setCellValueFactory(new PropertyValueFactory<>("descripcio"));
        this.tc_dataAlta.setCellValueFactory(new PropertyValueFactory<>("data_alta_fam"));
        this.tc_idProveidor.setCellValueFactory(new PropertyValueFactory<>("id_proveidor_fam"));
        this.tc_observacions.setCellValueFactory(new PropertyValueFactory<>("observacions"));


        // Obtener la lista de referencias desde ReferenciaDAO
        this.tv_familia.setItems(familiaLogica.getListObservableFamilla());

        // Añadir el listener a la tabla para que se actualicen los TextFields cuando cambie la selección
        tv_familia.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Actualizar los TextFields con la información del elemento seleccionado

                txt_nom.setText(newSelection.getNom_familia());
                txt_id.setText(String.valueOf(newSelection.getId_fam()));
                txt_idProveidor.setText(String.valueOf(newSelection.getId_proveidor_fam()));
                txt_areaDescripcio.setText(newSelection.getDescripcio());
                txt_dataAlta.setText(newSelection.getData_alta_fam().toString());
                txt_areaObservacions.setText(newSelection.getObservacions());
            }
            configurarBotonesPorRol();
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
       Familia FamiliaSeleccionada = tv_familia.getSelectionModel().getSelectedItem();

        if (FamiliaSeleccionada != null) {
            try {

                // Llamar al método delete en la referencia seleccionada
                familiaLogica.eliminarFamilia(FamiliaSeleccionada);

                // Remover la referencia eliminada de la tabla
                tv_familia.getItems().remove(FamiliaSeleccionada);

                mostrarMensaje("Família eliminada amb èxit.");
            } catch (SQLException ex) {
                Logger.getLogger(PantallaReferenciaController.class.getName()).log(Level.SEVERE, "Error al eliminar la Familia", ex);
            }
        } else {
            mostrarMensajeError("No s'ha seleccionat cap família per suprimir.");
        }
    }
   
   @FXML
void modificar(ActionEvent event) throws SQLException {
    Familia familiaSeleccionada = tv_familia.getSelectionModel().getSelectedItem();
    familiaDAO = new FamiliaDAO();
    
    if (familiaSeleccionada != null) {
        try {
            // Obtener los valores del formulario
            String nom = txt_nom.getText();
            int idProveidor = Integer.parseInt(txt_idProveidor.getText());
            String dataAlta = txt_dataAlta.getText();
            String descripcio = txt_areaDescripcio.getText();
            String observacions = txt_areaObservacions.getText();

            // Validar los datos antes de intentar actualizar en la base de datos
            ValidarCamposInsertFamilia.validarDatos(familiaDAO, dataAlta, idProveidor);

            // Confirmar modificación con el usuario
            boolean confirmado = mostrarMensajeConfirmacion("¿Seguro que deseas modificar esta familia?");
            if (confirmado) {
                // Si el usuario confirma, actualizar el objeto seleccionado
                familiaSeleccionada.setNom_familia(nom);
                familiaSeleccionada.setId_proveidor_fam(idProveidor);
                familiaSeleccionada.setData_alta_fam(dataAlta);
                familiaSeleccionada.setDescripcio(descripcio);
                familiaSeleccionada.setObservacions(observacions);

                // Refrescar la tabla visualmente
                tv_familia.refresh();

                // Llamar al método de lógica de negocio para guardar los cambios en la base de datos
                familiaLogica.modificarFamilia(familiaSeleccionada);
                mostrarMensaje("Familia modificada correctamente.");
            } else {
                // Si el usuario no confirma, no hacer nada
                mostrarMensaje("Modificación cancelada.");
            }

        } catch (NumberFormatException e) {
            // Mensaje de error para valores que no pueden ser convertidos a enteros
            mostrarMensajeError("Por favor, introduzca valores numéricos en los campos de ID de proveedor.");
        } catch (Exception e) {
            // Captura de excepciones de validación y muestra el mensaje personalizado
            mostrarMensajeError(e.getMessage());
        }
    } else {
        mostrarMensajeError("No se ha seleccionado ninguna familia.");
    }
}

    @FXML
    void sortir(ActionEvent event) {
        Stage stage = (Stage) this.btn_sortir.getScene().getWindow();
        stage.close();
            
    }

}

