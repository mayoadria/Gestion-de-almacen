
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

import Validaciones.ValidarCamposInsertProveidor;
import dades.MyDataSource;
import dades.ProveidorDAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logica.Mensajes;
import static logica.Mensajes.*;
import model.Proveidor;
import logica.ProveidorLogic;
import model.Familia;

/**
 * Classe controladora per gestionar la pantalla de proveïdors. Implementa la
 * interfície Initializable per a inicialitzar els elements.
 *
 * @author Anna
 */
public class pantallaProveidorController implements Initializable {

    @FXML
    private CheckBox chkActiu;
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

    private ProveidorDAO proveidorDAO;
    private String rol;
    private ProveidorLogic proveidorLogico;

//    /**
//     * Getter per la taula de proveïdors.
//     *
//     * @return la taula de proveïdors.
//     */
//    public TableView<Proveidor> getTb_prov() {
//        return tb_prov;
//    }
    /**
     * Inicialitza la pantalla i carrega els proveïdors de la base de dades.
     *
     * @param url URL de localització del fitxer FXML.
     * @param rb ResourceBundle per localització.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            this.proveidorLogico = new ProveidorLogic();
        } catch (SQLException ex) {
            Logger.getLogger(pantallaProveidorController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        // Obtener la lista de referencias desde ReferenciaDAO
        this.tb_prov.setItems(proveidorLogico.getListObservableProveidor());

        // Añadir el listener a la tabla para que se actualicen los TextFields cuando cambie la selección
        tb_prov.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Actualizar los TextFields con la información del elemento seleccionado

                tf_nomProv.setText(newSelection.getNom_proveidor());
                tf_idProv.setText(String.valueOf(newSelection.getId_proveidor()));
                tf_cifProv.setText(newSelection.getCif());
                chkActiu.setSelected(newSelection.isActiu());
                tf_motiuProv.setText(newSelection.getMotiu_inactiu());
                tf_creacioProv.setText(newSelection.getData_creacio());
                tf_correuProv.setText(newSelection.getCorreu_electronic());
                tf_valoracioProv.setText(String.valueOf(newSelection.getRating_proveidor()));
                tf_colabProv.setText(String.valueOf(newSelection.getMesos_de_colaboracio()));
            }
            configurarBotonesPorRol();
        });

    }

    /**
     * Estableis el rol d'usuari i configura els botons d'acord a aquest rol.
     *
     * @param rol El rol de l'usuari que s'establirà.
     */
    public void setRol(String rol) {
        this.rol = rol;
        configurarBotonesPorRol(); // Crida per aplicar la configuració de botons depenent del rol.
    }

    /**
     * Configura l'habilitació o deshabilitació dels botons segons el rol de
     * l'usuari.
     */
    private void configurarBotonesPorRol() {
        if (rol != null) {
            if (rol.equalsIgnoreCase("Venedor")) {
                // Deshabilitar botons pels usuaris regulars.
                btn_nouProv.setDisable(true);
                btn_modProv.setDisable(true);
                btn_eliProv.setDisable(true);
                btn_impProv.setDisable(true);
                btn_expProv.setDisable(true);
            }
        }
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
private void handlerButtonEsborrar(ActionEvent ev) throws Exception {
    // Primer seleccionem el proveïdor a esborrar.
    Proveidor proveidorSeleccionat = tb_prov.getSelectionModel().getSelectedItem();

    if (proveidorSeleccionat != null) {
        try {
            // Verificar si el proveïdor té referències associades
            boolean tieneReferencias = proveidorLogico.tieneReferencias(proveidorSeleccionat);

            if (tieneReferencias) {
                // Mostrar confirmació si té referències associades
                boolean continuarConReferencias = Mensajes.mostrarMensajeConfirmacion(
                    "El proveïdor té referències associades. Segur que vols continuar amb l'eliminació?"
                );

                if (!continuarConReferencias) {
                    mostrarMensaje("Operació cancel·lada per l'usuari.");
                    return; // Sortir si l'usuari decideix no continuar
                }
            }

            // Confirmació d'eliminació
            boolean confirmado = Mensajes.mostrarMensajeConfirmacion("Segur que vols esborrar aquest proveïdor?");
            if (confirmado) {
                // Cridem a la capa lògica per a que faci d'intermediària amb el DAO.
                proveidorLogico.esborrarProveidor(proveidorSeleccionat);

                // Actualitzem la taula un cop el proveïdor seleccionat ha estat esborrat.
                tb_prov.getItems().remove(proveidorSeleccionat);
            } else {
                mostrarMensaje("Operació cancel·lada.");
            }

        } catch (Exception ex) {
            Logger.getLogger(PantallaReferenciaController.class.getName()).log(Level.SEVERE, "Error en eliminar el proveïdor", ex);
            mostrarMensajeError("Error en eliminar el proveïdor: " + ex.getMessage());
        }
    } else {
        mostrarMensajeError("No s'ha seleccionat cap proveïdor per a esborrar.");
    }
}
    
    /**
     * Modifica el proveïdor seleccionat amb les noves dades introduïdes.
     *
     * @throws SQLException si hi ha un error en la base de dades.
     */
    @FXML
    private void handlerButtonModificar() throws SQLException {
        // Obtener el proveedor seleccionado de la tabla
        Proveidor proveidorSeleccionat = tb_prov.getSelectionModel().getSelectedItem();
        proveidorDAO = new ProveidorDAO();

        if (proveidorSeleccionat != null) {
            try {
                // Obtener los valores del formulario
                String nomProveidor = tf_nomProv.getText();
                String cif = tf_cifProv.getText();
                String motiuInactiu = tf_motiuProv.getText();
                String dataCreacio = tf_creacioProv.getText(); // Convierte de String a Date
                String correuElectronic = tf_correuProv.getText();
                float ratingProveidor = Float.parseFloat(tf_valoracioProv.getText());
                int mesosColaboracio = Integer.parseInt(tf_colabProv.getText());
                boolean actiu = chkActiu.isSelected(); // Obtiene el valor booleano del CheckBox

                // Validar los datos antes de intentar actualizar en la base de datos (opcional)
                ValidarCamposInsertProveidor.validarDatos(proveidorDAO, cif, dataCreacio, correuElectronic, ratingProveidor, mesosColaboracio);

                // Confirmación de modificación
                boolean confirmado = Mensajes.mostrarMensajeConfirmacion("Segur que vols modificar aquest proveïdor?");

                if (confirmado) {
                    // Actualizar el objeto seleccionado con los nuevos valores
                    proveidorSeleccionat.setNom_proveidor(nomProveidor);
                    proveidorSeleccionat.setCif(cif);
                    proveidorSeleccionat.setMotiu_inactiu(motiuInactiu);
                    proveidorSeleccionat.setData_creacio(dataCreacio);
                    proveidorSeleccionat.setCorreu_electronic(correuElectronic);
                    proveidorSeleccionat.setRating_proveidor(ratingProveidor);
                    proveidorSeleccionat.setMesos_de_colaboracio(mesosColaboracio);
                    proveidorSeleccionat.setActiu(actiu); // Asigna el valor booleano obtenido del CheckBox

                    // Refrescar la tabla visualmente
                    tb_prov.refresh();

                    // Llamar al método de lógica de negocio para guardar los cambios en la base de datos
                    proveidorLogico.modificarProveidor(proveidorSeleccionat);

                } else {
                    mostrarMensaje("Modificació cancel·lada.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Si us plau, ingresseu valors numèrics vàlids en els camps de rating i mesos de col·laboració.");
            } catch (IllegalArgumentException e) {
                System.out.println("Format de data incorrecte. Si us plau, fes servir el format AAAA-MM-DD.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("No s'ha seleccionat cap proveïdor.");
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
        }
    }

    public void actualizarTablaConNuevoProveedor(Proveidor nuevoProveidor) {
        tb_prov.getItems().add(nuevoProveidor);
        tb_prov.refresh();  // Refrescar la tabla para que se vea la nueva entrada
    }

    /**
     * Maneja la acción del botón de exportar proveedores. Se exporta la lista
     * de proveedores a un archivo especificado. Se muestra un mensaje de éxito
     * o error según corresponda.
     *
     * @param ev El evento de acción que activa este método.
     * @throws IOException Si ocurre un error al exportar el archivo.
     */
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
                    writer.append(String.format("%d,%s,%s,%s,%s,%s,%.2f,%d,%s\n",
                            proveidor.getId_proveidor(),
                            proveidor.getNom_proveidor(),
                            proveidor.getCif(),
                            proveidor.isActiu() ? "Actiu" : "Inactiu",
                            proveidor.getCorreu_electronic(),
                            proveidor.getData_creacio(),
                            proveidor.getRating_proveidor(),
                            proveidor.getMesos_de_colaboracio(),
                            proveidor.getMotiu_inactiu() != null ? proveidor.getMotiu_inactiu() : ""
                    ));
                }
            }

            mostrarMensaje("L'exportació s'ha completat correctament. Fitxer: " + fileName);

        } catch (IOException e) {
            mostrarMensajeError("Hi ha hagut un error en exportar les dades.");
        } catch (SQLException e) {
            mostrarMensajeError("No s'ha pogut recuperar les dades de la base de dades.");
        }
    }

//    /**
//     * Maneja la acción del botón de importar proveedores. Se importa la lista
//     * de proveedores desde un archivo especificado. Se muestra un mensaje de
//     * éxito o error según corresponda.
//     *
//     * @param ev El evento de acción que activa este método.
//     * @throws IOException Si ocurre un error al importar el archivo.
//     */
    @FXML
    private void handlerButtonImportar(ActionEvent ev) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona el fitxer CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        // Mostrar el selector de archivos y obtener el archivo seleccionado
        File file = fileChooser.showOpenDialog(btn_impProv.getScene().getWindow());
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                boolean isFirstLine = true;

                while ((line = reader.readLine()) != null) {
                    // Ignorar la primera línea si es de encabezado
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }

                    // Separar los campos por coma (CSV)
                    String[] fields = line.split(",");
                    if (fields.length < 9) {
                        mostrarMensajeError("El fitxer CSV no té el format esperat.");
                        return;
                    }

                    // Crear un nuevo objeto Proveidor con los datos de la línea CSV
                    Proveidor proveidor = new Proveidor();
                    proveidor.setId_proveidor(Integer.parseInt(fields[0].trim()));
                    proveidor.setNom_proveidor(fields[1].trim());
                    proveidor.setCif(fields[2].trim());
                    proveidor.setActiu("Actiu".equals(fields[3].trim()));
                    proveidor.setCorreu_electronic(fields[4].trim());
                    proveidor.setData_creacio(fields[5].trim()); // Formato de fecha como texto (recomendable adaptar si es necesario)
                    proveidor.setRating_proveidor(Float.parseFloat(fields[6].trim()));
                    proveidor.setMesos_de_colaboracio(Integer.parseInt(fields[7].trim()));
                    proveidor.setMotiu_inactiu(fields[8].trim().isEmpty() ? null : fields[8].trim());

                    // Añadir el proveedor a la base de datos y a la tabla
                    proveidorLogico.afegirProveidor(proveidor);
                }

                tb_prov.refresh();
                mostrarMensaje("Importació completada amb èxit.");

            } catch (IOException e) {
                mostrarMensajeError("Error en llegir el fitxer CSV.");
            } catch (SQLException e) {
                mostrarMensajeError("Error en guardar el proveïdor a la base de dades.");
            } catch (NumberFormatException e) {
                mostrarMensajeError("Error en el format de dades numèriques.");
            }
        }
    }

}
