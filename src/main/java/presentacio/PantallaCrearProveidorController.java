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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logica.Proveidor;
import logica.ProveidorLogic;

/**
 * Controlador per gestionar la creació de nous proveïdors.
 * Aquest controlador maneja les accions relacionades amb la interfície de creació de proveïdors.
 *
 * @author Anna
 */
public class PantallaCrearProveidorController {

    @FXML
    private TextField tf_colabNouProv;

    @FXML
    private Button btn_canNouProv;

    @FXML
    private Button btn_conNouProv;

    @FXML
    private TextField tf_creacioNouProv;

    @FXML
    private Label label_cifProv1;

    @FXML
    private CheckBox cb_estatNouProv;

    @FXML
    private TextField tf_motiuNouProv;

    @FXML
    private Label label_cifProv2;

    @FXML
    private TextField tf_correuNouProv;

    @FXML
    private Button btn_expProv;

    @FXML
    private TextField tf_cifNouProv;

    @FXML
    private TextField tf_idNouProv;

    @FXML
    private TextField tf_nomNouProv;

    @FXML
    private Label label_cifProv;

    @FXML
    private Button btn_impProv;

    @FXML
    private TextField tf_valoracioNouProv;

    @FXML
    private Label label_cifProv11;

    private pantallaProveidorController pantallaProveidorController;

    private ProveidorDAO proveidorDAO;

   /**
     * Obté el controlador de la pantalla de proveïdors.
     *
     * @return Retorna el controlador de la pantalla de proveïdors.
     */
    public pantallaProveidorController getPantallaProveidorController() {
        return pantallaProveidorController;
    }

    /**
     * Estableix el controlador de la pantalla de proveïdors.
     *
     * @param pantallaProveidorController El controlador a establir.
     */
    public void setPantallaProveidorController(pantallaProveidorController pantallaProveidorController) {
        this.pantallaProveidorController = pantallaProveidorController;
    }

    /**
     * Inicialitza el controlador quan es carrega la vista.
     *
     * @param url L'URL d'inicialització.
     * @param rb El conjunt de recursos.
     */
    @FXML
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Crea un nou proveïdor basant-se en les dades introduïdes en el formulari.
     *
     * @param ev L'esdeveniment d'acció.
     * @throws IOException Si es produeix un error d'entrada/sortida.
     * @throws SQLException Si es produeix un error en la consulta SQL.
     * @throws Exception Si es produeix qualsevol altra excepció.
     */
    @FXML
    private void CrearProveidorNou(ActionEvent ev) throws IOException, SQLException, Exception {

        proveidorDAO = new ProveidorDAO();
        String nom_Proveidor = tf_nomNouProv.getText();
        String cif = tf_cifNouProv.getText();
        String actiu;
        if (cb_estatNouProv.isSelected()) {
            actiu = "ACTIU";
        } else {
            actiu = "INACTIU";
        }

        String motiu = tf_motiuNouProv.getText();
        String creacio = tf_creacioNouProv.getText();
        String correu = tf_correuNouProv.getText();
        Float valoracio = Float.parseFloat(tf_valoracioNouProv.getText());
        int mesos = Integer.parseInt(tf_colabNouProv.getText());

        // Recollim les dades introduïdes en el formulari dins d'un objecte Proveidor
        Proveidor nouProveidor = new Proveidor();

        nouProveidor.setNom_proveidor(nom_Proveidor);
        nouProveidor.setCif(cif);

        // Convertim el valor del ComboBox a boolean per l'estat actiu/inactiu
        if (actiu.equals("ACTIU")) {
            nouProveidor.setActiu(true);
        } else {
            nouProveidor.setActiu(false);
        }

        nouProveidor.setMotiu_inactiu(motiu);

        try {
            nouProveidor.setData_creacio(Date.valueOf(creacio));
        } catch (IllegalArgumentException e) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error de format");
            alertError.setHeaderText("Error en la data de creació");
            alertError.setContentText("El format de la data de creació no és correcte. Ha de ser YYYY-MM-DD.");
            alertError.showAndWait();
            return;
        }
        nouProveidor.setCorreu_electronic(correu);
        nouProveidor.setRating_proveidor(valoracio);
        nouProveidor.setMesos_de_colaboracio(mesos);

        ProveidorLogic proveidorLogic = new ProveidorLogic();

        Alert alertConfirmacio = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmacio.setTitle("Confirmació");
        alertConfirmacio.setHeaderText("Confirma la creació del proveïdor");
        alertConfirmacio.setContentText("Estàs segur de voler crear el proveïdor amb aquestes dades?");

        Optional<ButtonType> resultatConfirmacio = alertConfirmacio.showAndWait();

        // Si  l'usuari confirma, guardem les dades.
        if (resultatConfirmacio.isPresent() && resultatConfirmacio.get() == ButtonType.OK) {
            try {
                proveidorLogic.afegirProveidor(nouProveidor);

                //Actualitzem la taula.
                pantallaProveidorController.getTb_prov().refresh();
                Stage stage = (Stage) btn_conNouProv.getScene().getWindow();
                stage.close();

            } catch (Exception e) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText("Error en crear el proveïdor");
                alertError.setContentText("S'ha produït un error en intentar guardar el proveïdor." + e.getMessage());
                alertError.showAndWait();
            }
        }

    }

    /**
     * Tanca la finestra de creació de proveïdors.
     *
     * @param ev L'esdeveniment d'acció.
     * @throws IOException Si es produeix un error d'entrada/sortida.
     */
    @FXML
    private void handlerButtonNouSortir(ActionEvent ev) throws IOException {

        Stage stage = (Stage) this.btn_canNouProv.getScene().getWindow();

        stage.close();
    }
}
