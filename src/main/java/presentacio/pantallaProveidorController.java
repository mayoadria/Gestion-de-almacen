/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;


import dades.ProveidorDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logica.Proveidor;

/**
 *
 * @author Anna
 */
public class pantallaProveidorController implements Initializable {

    @FXML
    private TextField tf_motiuProv;

    @FXML
    private Button btn_nouProv;

    @FXML
    private TextField tf_correuProv;

    @FXML
    private TextField tf_cifProv;

    @FXML
    private Button btn_modProv;

    @FXML
    private TextField tf_idProv;

    @FXML
    private TextField tf_nomProv;

    @FXML
    private Button btn_eliProv;

    @FXML
    private TextField tf_estatProv;

    @FXML
    private Button btn_sorProv;

    @FXML
    private TextField tf_creacioProv;

    @FXML
    private Button btn_expProv;

    @FXML
    private TextField tf_valoracioProv;

    @FXML
    private TextField tf_colabProv;

    @FXML
    private Button btn_impProv;

    @FXML
    private TableColumn<?, ?> col_renkingProv;

    @FXML
    private TableColumn<?, ?> col_cifProv;

    @FXML
    private TableColumn<?, ?> col_estatProv;

    @FXML
    private TableColumn<?, ?> col_correuProv;

    @FXML
    private TableColumn<?, ?> col_mesosProv;

    @FXML
    private TableColumn<?, ?> col_nomProv;

    @FXML
    private TableView<Proveidor> tb_prov;

    @FXML
    private TableColumn<?, ?> col_idProv;

    @FXML
    private TableColumn<?, ?> col_creacioProv;

    @FXML
    private TableColumn<?, ?> col_motiuProv;

    @FXML
    private TextField tf_EstatProv;

    private ProveidorDAO proveidorDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Vinculem les columnes amb cada atribut de la classe proveïdor de la BBDD.
        this.col_idProv.setCellValueFactory(new PropertyValueFactory<>("id_proveidor"));
        this.col_nomProv.setCellValueFactory(new PropertyValueFactory<>("nom_proveidor"));
        this.col_cifProv.setCellValueFactory(new PropertyValueFactory<>("cif"));

        //this.cb_estatProv.getItems().setAll(EstatProveidor.values());
        this.col_estatProv.setCellValueFactory(new PropertyValueFactory<>("actiu"));
        this.col_motiuProv.setCellValueFactory(new PropertyValueFactory<>("motiu_inactiu"));
        this.col_creacioProv.setCellValueFactory(new PropertyValueFactory<>("data_creacio"));
        this.col_correuProv.setCellValueFactory(new PropertyValueFactory<>("correu_electronic"));
        this.col_renkingProv.setCellValueFactory(new PropertyValueFactory<>("rating_proveidor"));
        this.col_mesosProv.setCellValueFactory(new PropertyValueFactory<>("mesos_de_colaboracio"));

        try {
            cargarProveidors();  //Cridem les dades
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void recollirDadesProveidor() throws SQLException {

        Proveidor p = new Proveidor();
        p.setId_proveidor(Integer.parseInt(tf_idProv.getText()));
        p.setNom_proveidor(tf_nomProv.getText());
        p.setCif(tf_cifProv.getText());
        String valorEstat = tf_estatProv.getText();

        if (valorEstat.equals("ACTIU")) {
            p.setActiu(true);
        } else {
            p.setActiu(false);
        }

        p.setMotiu_inactiu(tf_motiuProv.getText());
        String data = tf_creacioProv.getText();

        try {
            java.sql.Date dataCreacio = java.sql.Date.valueOf(data);
            p.setData_creacio(dataCreacio);
        } catch (IllegalArgumentException e) {
            System.out.println("El format de la data és incorrecte.");
        }
        p.setCorreu_electronic(tf_correuProv.getText());
        p.setRating_proveidor(Float.parseFloat(tf_valoracioProv.getText()));
        p.setMesos_de_colaboracio(Integer.parseInt(tf_colabProv.getText()));

        ProveidorDAO dao = new ProveidorDAO();

    }

    private void cargarProveidors() throws SQLException {
        ProveidorDAO proveidorDAO = new ProveidorDAO();
        List<Proveidor> proveidors = new ArrayList<>();

        try {
            proveidors = proveidorDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObservableList<Proveidor> observableProveidors = FXCollections.observableArrayList(proveidors);
        tb_prov.setItems(observableProveidors);

        tb_prov.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                //Aquí passem el contingut de les taules als camps inferiors.
                tf_nomProv.setText(newSelection.getNom_proveidor());
                tf_idProv.setText(String.valueOf(newSelection.getId_proveidor()));
                tf_correuProv.setText(newSelection.getCorreu_electronic());
                tf_valoracioProv.setText(String.valueOf(newSelection.getRating_proveidor()));
                tf_cifProv.setText(newSelection.getCif());
                tf_creacioProv.setText(newSelection.getData_creacio().toString());
                tf_motiuProv.setText(newSelection.getMotiu_inactiu());
                tf_colabProv.setText(String.valueOf(newSelection.getMesos_de_colaboracio()));
                tf_EstatProv.setText(newSelection.isActiu() ? "Actiu" : "Inactiu");
            }
        });

    }
    
    @FXML
    private void handlerButtonSortir(ActionEvent ev) throws IOException {
        //btn_sorProv
        
        // Es carrega la vista de la pantalla SeleccionaMenus.
            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PantallaSeleccionaMenus.fxml"));

            // Cargo el padre
            Parent root = loader.load();
            
            // Creo la scene y el stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            // Asocio el stage con el scene
            stage.setScene(scene);
            stage.show();*/
            Stage stage = (Stage) this.btn_sorProv.getScene().getWindow();

            stage.close();

    }
    
    @FXML 
    private void handlerButtonNou(ActionEvent ev) throws IOException {
        
    }

}
