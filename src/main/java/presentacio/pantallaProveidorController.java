/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

import dades.ProveidorDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logica.Proveidor;
import logica.ProveidorLogic;

/**
 * Classe controladora per gestionar la pantalla de proveïdors. Implementa la
 * interfície Initializable per a inicialitzar els elements.
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
    private String rol;

    /**
     * Getter per la taula de proveïdors.
     *
     * @return la taula de proveïdors.
     */
    public TableView<Proveidor> getTb_prov() {
        return tb_prov;
    }

    /**
     * Inicialitza la pantalla i carrega els proveïdors de la base de dades.
     *
     * @param url URL de localització del fitxer FXML.
     * @param rb ResourceBundle per localització.
     */
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
    
    public void setRol(String rol) {
        this.rol = rol;
        configurarBotonesPorRol(); // Llamar para aplicar la configuración de botones al establecer el rol
    }

    // Método para habilitar o deshabilitar botones según el rol
    private void configurarBotonesPorRol() {
        if (rol != null) {
             if (rol.equals("Venedor")) {
                // Deshabilitar botones para usuarios regulares
                btn_nouProv.setDisable(true);
                btn_modProv.setDisable(true);
                btn_eliProv.setDisable(true);
                btn_impProv.setDisable(true);
                btn_expProv.setDisable(true);
            }
        }
    }

    /**
     * Recull les dades del proveïdor introduïdes a la interfície.
     *
     * @throws SQLException si hi ha un error en la connexió amb la base de
     * dades.
     */
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

    /**
     * Carrega els proveïdors des de la base de dades i els mostra a la taula.
     *
     * @throws SQLException si hi ha un error en obtenir les dades.
     */
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

    /**
     * Tanca la finestra actual quan es fa clic al botó de sortir.
     *
     * @param ev l'event de l'acció.
     * @throws IOException si hi ha un error en tancar la finestra.
     */
    @FXML
    private void handlerButtonSortir(ActionEvent ev) throws IOException {

        Stage stage = (Stage) this.btn_sorProv.getScene().getWindow();

        stage.close();
        
    }

    /**
     * Esborra el proveïdor seleccionat després de confirmar l'acció.
     *
     * @param ev l'event de l'acció.
     * @throws SQLException si hi ha un error en la base de dades.
     */
    @FXML
    private void handlerButtonEsborrar(ActionEvent ev) throws SQLException {

        //Primer seleccionem el proveïdor a esborrar.
        Proveidor proveidorSeleccionat = tb_prov.getSelectionModel().getSelectedItem();

        if (proveidorSeleccionat == null) {
            // Mostrar missatge d'error si no s'ha seleccionat cap proveïdor
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selecció requerida");
            alert.setHeaderText(null);
            alert.setContentText("Selecciona un proveïdor per poder-lo esborrar");
            alert.showAndWait();
            return;
        }

        // Demanem confirmació per a esborrar el proveïdor. 
        Alert alertConfirmacio = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmacio.setTitle("Confirmació d'esborrat");
        alertConfirmacio.setHeaderText(null);
        alertConfirmacio.setContentText("Segur que vols esborrar el proveïdor seleccionat?");
        ProveidorLogic proveidorLogic = new ProveidorLogic();

        Optional<ButtonType> resultat = alertConfirmacio.showAndWait();
        if (resultat.isPresent() && resultat.get() == ButtonType.OK) {
            try {

                //Cridem a la capa lògica per a que faci d'intermediària amb el DAO.
                proveidorLogic.esborrarProveidor(proveidorSeleccionat);
                //Actualitzaem la taula una vegada el proveïdor seleccionat ha estat esborrat.
                tb_prov.refresh();

            } catch (Exception e) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText(null);
                alertError.setContentText("No es pot esborrar un proveïdor actiu.");
                alertError.showAndWait();
            }
        }

    }

    /**
     * Modifica el proveïdor seleccionat amb les noves dades introduïdes.
     *
     * @throws SQLException si hi ha un error en la base de dades.
     */
    @FXML
    private void handlerButtonModificar() throws SQLException {

        ProveidorLogic proveidorLogic = new ProveidorLogic();  // Instància de la lògica

        Proveidor proveidorSeleccionat = tb_prov.getSelectionModel().getSelectedItem();

        proveidorSeleccionat.setNom_proveidor(tf_nomProv.getText());
        //proveidorSeleccionat.setQuantitat(Integer.parseInt(txtCantidad.getText()));
        proveidorSeleccionat.setCorreu_electronic(tf_correuProv.getText());
        proveidorSeleccionat.setRating_proveidor(Float.parseFloat(tf_valoracioProv.getText()));
        proveidorSeleccionat.setCif(tf_cifProv.getText());
        proveidorSeleccionat.setData_creacio(Date.valueOf(tf_creacioProv.getText()));
        proveidorSeleccionat.setMotiu_inactiu(tf_motiuProv.getText());
        proveidorSeleccionat.setMesos_de_colaboracio(Integer.parseInt(tf_colabProv.getText()));
        proveidorSeleccionat.setActiu(Boolean.parseBoolean(tf_EstatProv.getText()));

        Alert alertConfirmacio = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmacio.setTitle("Confirmació de modificació");
        alertConfirmacio.setHeaderText(null);
        alertConfirmacio.setContentText("Segur que vols modificar el proveïdor seleccionat?");

        Optional<ButtonType> resultatConfirmacio = alertConfirmacio.showAndWait();
        if (resultatConfirmacio.isPresent() && resultatConfirmacio.get() == ButtonType.OK) {
            try {
                proveidorLogic.modificarProveidor(proveidorSeleccionat);  // Crida a la capa lògica
                tb_prov.refresh();  // Refresca la taula amb les dades modificades
            } catch (Exception e) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText(null);
                alertError.setContentText("Hi ha hagut un error en modificar el proveïdor.");
                alertError.showAndWait();
            }
        }
    }

    /**
     * Gestiona l'acció de crear un nou proveïdor. Aquest mètode carrega una
     * nova pantalla per a introduir les dades del nou proveïdor.
     *
     * @param ev l'event de l'acció del botó.
     * @throws IOException si hi ha un error en carregar la nova pantalla.
     */
    @FXML
    private void handlerButtonNou(ActionEvent ev) throws IOException {
        try {
            // Carreguem la pantalla de crear un nou proveïdor.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PantallaCrearProveidor.fxml"));

            Parent root = loader.load();

            //Amb aquesta línia estem accedint al controlador de la nova finestra.
            PantallaCrearProveidorController controladorCrear = loader.getController();

            controladorCrear.setPantallaProveidorController(this);  // Passem l'actual controlador a la nova pantalla
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(PantallaSeleccionarMenuController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

}
