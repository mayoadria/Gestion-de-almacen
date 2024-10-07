/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.project1_grup2;

import aplicacio.model.Proveidor;
import aplicacio.model.Proveidor.EstatProveidor;
import dades.ProveidorDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private ComboBox<EstatProveidor> cb_estatProv;
    
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

    public void initialize() {

        

    }

    private void recollirDadesProveidor() throws SQLException {

        Proveidor p = new Proveidor();
        p.setId_proveidor(Integer.parseInt(tf_idProv.getText()));
        p.setNom_proveidor(tf_nomProv.getText());
        p.setCif(tf_cifProv.getText());
        
        p.setEstat(cb_estatProv.getValue());
        
        

        //p.setEstat(Proveidor.EstatProveidor.valueOf(this.tf_estatProv.getText().toUpperCase()));
        // EstatProveidor és una enum, però com no hi ha cap mètode "getEnum", l'hem de castejar a String. 
        p.setMotiu_inactiu(tf_motiuProv.getText());
        String data = tf_creacioProv.getText();

        try {
            java.sql.Date dataCreacio = java.sql.Date.valueOf(data);
            p.setData_creacio(dataCreacio);
        } catch (IllegalArgumentException e) {
            System.out.println("El format de la data és incorrecte.");
            // Aquí puedes mostrar un mensaje de error al usuario o manejar el error como necesites
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

    }

    /*public void initialize(TextField tf_motiuProv, Button btn_nouProv, TextField tf_correuProv, TextField tf_cifProv, Button btn_modProv, TextField tf_idProv, TextField tf_nomProv, Button btn_eliProv, TextField tf_estatProv, Button btn_sorProv, TextField tf_creacioProv, Button btn_expProv, TextField tf_valoracioProv, TextField tf_colabProv, Button btn_impProv, TableColumn<?, ?> col_renkingProv, TableColumn<?, ?> col_cifProv, TableColumn<?, ?> col_estatProv, TableColumn<?, ?> col_correuProv, TableColumn<?, ?> col_mesosProv, TableColumn<?, ?> col_nomProv, TableView<?> tb_prov, TableColumn<?, ?> col_idProv, TableColumn<?, ?> col_creacioProv, TableColumn<?, ?> col_motiuProv) {
        this.tf_motiuProv = tf_motiuProv;
        this.btn_nouProv = btn_nouProv;
        this.tf_correuProv = tf_correuProv;
        this.tf_cifProv = tf_cifProv;
        this.btn_modProv = btn_modProv;
        this.tf_idProv = tf_idProv;
        this.tf_nomProv = tf_nomProv;
        this.btn_eliProv = btn_eliProv;
        this.tf_estatProv = tf_estatProv;
        this.btn_sorProv = btn_sorProv;
        this.tf_creacioProv = tf_creacioProv;
        this.btn_expProv = btn_expProv;
        this.tf_valoracioProv = tf_valoracioProv;
        this.tf_colabProv = tf_colabProv;
        this.btn_impProv = btn_impProv;
        this.col_renkingProv = col_renkingProv;
        this.col_cifProv = col_cifProv;
        this.col_estatProv = col_estatProv;
        this.col_correuProv = col_correuProv;
        this.col_mesosProv = col_mesosProv;
        this.col_nomProv = col_nomProv;
        this.tb_prov = tb_prov;
        this.col_idProv = col_idProv;
        this.col_creacioProv = col_creacioProv;
        this.col_motiuProv = col_motiuProv;
    
    
}*/

    
}
