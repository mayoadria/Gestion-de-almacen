/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

import dades.ProveidorDAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logica.Mensajes;
import logica.Proveidor;
import logica.ProveidorLogic;

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
    private Mensajes mensajes = new Mensajes();

    public TableView<Proveidor> getTb_prov() {
        return tb_prov;
    }

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

        Stage stage = (Stage) this.btn_sorProv.getScene().getWindow();

        stage.close();

    }

    @FXML
    private void handlerButtonEsborrar(ActionEvent ev) {

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

        Optional<ButtonType> resultat = alertConfirmacio.showAndWait();
        if (resultat.isPresent() && resultat.get() == ButtonType.OK) {
            try {

                ProveidorLogic proveidorLogic = new ProveidorLogic();

                //Cridem a la capa lògica per a que faci d'intermediària amb el DAO.
                proveidorLogic.esborrarProveidor(proveidorSeleccionat);
                //Actualitzaem la taula una vegada el proveïdor seleccionat ha estat esborrat.
                tb_prov.getItems().remove(proveidorSeleccionat);

            } catch (Exception e) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText(null);
                alertError.setContentText("Hi ha hagut un error en esborrar el proveïdor.");
                alertError.showAndWait();
            }
        }

    }

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

    @FXML
    private void handlerButtonExportar(ActionEvent ev) {
        try {
            ProveidorLogic proveidorLogic = new ProveidorLogic();
            List<Proveidor> proveidors = proveidorLogic.getAllProveidors();

            // Crear el nombre del archivo CSV con marca de tiempo
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = LocalDateTime.now().format(formatter);
            String fileName = "export_proveidors_" + timestamp + ".csv";

            // Crear el archivo CSV y escribir los datos
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.append("ID,Nom,CIF,Estat,Correu Electrònic,Data Creació,Rating,Mesos de Colaboració,Motiu Inactiu\n");

                for (Proveidor proveidor : proveidors) {
                    writer.append(String.format(
                            "%d,%s,%s,%s,%s,%s,%.2f,%d,%s\n",
                            proveidor.getId_proveidor(),
                            proveidor.getNom_proveidor(),
                            proveidor.getCif(),
                            proveidor.isActiu() ? "Actiu" : "Inactiu",
                            proveidor.getCorreu_electronic(),
                            proveidor.getData_creacio().toString(),
                            proveidor.getRating_proveidor(),
                            proveidor.getMesos_de_colaboracio(),
                            proveidor.getMotiu_inactiu() != null ? proveidor.getMotiu_inactiu() : ""
                    ));
                }
            }

            mensajes.mostrarMensaje("L'exportació s'ha completat correctament. Fitxer: " + fileName);

        } catch (IOException e) {
            mensajes.mostrarMensajeError("Hi ha hagut un error en exportar les dades.");
            e.printStackTrace();
        } catch (SQLException e) {
            mensajes.mostrarMensajeError("No s'ha pogut recuperar les dades de la base de dades.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handlerButtonImportar(ActionEvent event) {
        // Permitir al usuario seleccionar un archivo CSV
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona el fitxer CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile == null) {
            mensajes.mostrarMensajeError("No s'ha seleccionat cap fitxer.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
            ProveidorLogic proveidorLogic = new ProveidorLogic();
            String line;
            HashSet<Integer> idsExistents = proveidorLogic.obtenerTodosIdsProveidors();  // Lista de IDs ya existentes en BBDD

            // Leer cada línea del archivo CSV (omitir encabezado)
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                // Validar si el número de campos es correcto
                if (data.length != 8) {
                    mensajes.mostrarMensajeError("Format de fitxer incorrecte en la línia: " + line);
                    continue;  // Continuar a la siguiente línea sin terminar el proceso
                }

                try {
                    int id = Integer.parseInt(data[0].trim());
                    String nom = data[1].trim();
                    String cif = data[2].trim();
                    boolean estat = data[3].trim().equalsIgnoreCase("Actiu");
                    String correu = data[4].trim();
                    LocalDate dataCreacio = LocalDate.parse(data[5].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    float rating = Float.parseFloat(data[6].trim());
                    int mesosColaboracio = Integer.parseInt(data[7].trim());

                    Proveidor proveidor = new Proveidor();
                    proveidor.setId_proveidor(id);
                    proveidor.setNom_proveidor(nom);
                    proveidor.setCif(cif);
                    proveidor.setActiu(estat);
                    proveidor.setCorreu_electronic(correu);
                    proveidor.setData_creacio(java.sql.Date.valueOf(dataCreacio));
                    proveidor.setRating_proveidor(rating);
                    proveidor.setMesos_de_colaboracio(mesosColaboracio);

                    // Comprobar si el proveedor con el mismo ID ya existe
                    if (idsExistents.contains(id)) {
                        // Duplicado: Preguntar al usuario si quiere reemplazar el registro existente
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("ID duplicat");
                        alert.setHeaderText("L'ID " + id + " ja existeix.");
                        alert.setContentText("Vols modificar les dades de la BBDD amb les noves dades?");
                        Optional<ButtonType> result = alert.showAndWait();

                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            proveidorLogic.modificarProveidor(proveidor);  // Reemplazar datos en la base de datos
                            mensajes.mostrarMensaje("Les dades de l'proveïdor s'han modificat correctament.");
                        } else {
                            continue;  // Omitir esta entrada si no se quiere modificar
                        }
                    } else {
                        // Insertar un nuevo proveedor
                        proveidorLogic.afegirProveidor(proveidor);
                        idsExistents.add(id);
                    }

                } catch (NumberFormatException e) {
                    mensajes.mostrarMensajeError("Format de nombre incorrecte en el fitxer a la línia: " + line + ". Verifica els valors numèrics.");
                } catch (DateTimeParseException e) {
                    mensajes.mostrarMensajeError("Data incorrecta en el fitxer a la línia: " + line + ". Verifica els formats de les dates.");
                } catch (SQLException e) {
                    mensajes.mostrarMensajeError("Error d'accés a la base de dades en la línia: " + line);
                    e.printStackTrace();
                } catch (Exception ex) {
                    Logger.getLogger(pantallaProveidorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            mensajes.mostrarMensaje("Importació completada correctament.");
        } catch (IOException e) {
            mensajes.mostrarMensajeError("Error al llegir el fitxer.");
            e.printStackTrace();
        } catch (SQLException e) {
            mensajes.mostrarMensajeError("Error d'accés a la base de dades.");
            e.printStackTrace();
        }
    }

}
