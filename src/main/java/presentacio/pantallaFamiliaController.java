package presentacio;

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
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static logica.Mensajes.*;

public class pantallaFamiliaController implements Initializable {

    @FXML
    private Button btn_eliminar;

    @FXML
    private Button btn_modificar;

    @FXML
    private Button btn_nova;

    @FXML
    private Button btn_sortir;

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

        // Configuración de las columnas de la tabla
        this.tc_id.setCellValueFactory(new PropertyValueFactory<>("id_fam"));
        this.tc_nom.setCellValueFactory(new PropertyValueFactory<>("nom_familia"));
        this.tc_descripcio.setCellValueFactory(new PropertyValueFactory<>("descripcio"));
        this.tc_dataAlta.setCellValueFactory(new PropertyValueFactory<>("data_alta_fam"));
        this.tc_idProveidor.setCellValueFactory(new PropertyValueFactory<>("id_proveidor_fam"));
        this.tc_observacions.setCellValueFactory(new PropertyValueFactory<>("observacions"));

        // Cargar la lista de familias
        this.tv_familia.setItems(familiaLogica.getListObservableFamilla());

        // Listener para actualizar los TextFields cuando se selecciona una familia
        tv_familia.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Actualizar los campos con la información de la familia seleccionada
                txt_nom.setText(newSelection.getNom_familia());
                txt_id.setText(String.valueOf(newSelection.getId_fam()));
                txt_idProveidor.setText(String.valueOf(newSelection.getId_proveidor_fam()));
                txt_areaDescripcio.setText(newSelection.getDescripcio());
                txt_dataAlta.setText(newSelection.getData_alta_fam());
                txt_areaObservacions.setText(newSelection.getObservacions());
            }
            configurarBotonesPorRol();
        });
    }

    // Método para configurar los botones según el rol
    public void setRol(String rol) {
        this.rol = rol;
        configurarBotonesPorRol(); // Llamar para aplicar la configuración de botones al establecer el rol
    }

    private void configurarBotonesPorRol() {
        if (rol != null && rol.equalsIgnoreCase("Venedor")) {
            // Deshabilitar botones para usuarios con rol "Venedor"
            btn_nova.setDisable(true);
            btn_eliminar.setDisable(true);
            btn_modificar.setDisable(true);
        }
    }

    // Agregar nueva familia
    @FXML
    void afegirFamilia(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PantallaInsertFamilia.fxml"));
            Parent root = loader.load();

            // Obtengo el controlador de la nueva ventana
            PantallaInsertFamiliaController controladorInsert = loader.getController();

            // Paso la referencia del controlador actual a la nueva pantalla
            controladorInsert.setFamiliaController(this);

            // Crear una nueva escena y mostrarla en un nuevo stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(pantallaFamiliaController.class.getName()).log(Level.SEVERE, "Error al abrir la ventana de inserción de familia", ex);
        }
    }

    // Método para actualizar la tabla cuando se agrega una nueva familia
    public void actualizarTaulaFamilia(Familia novaFamilia) {
        tv_familia.getItems().add(novaFamilia);
        tv_familia.refresh();
    }

    // Eliminar una familia seleccionada
    @FXML
    void eliminar(ActionEvent event) {
        Familia familiaSeleccionada = tv_familia.getSelectionModel().getSelectedItem();

        if (familiaSeleccionada != null) {
            try {
                boolean tieneReferencias = familiaLogica.tieneReferencias(familiaSeleccionada);
                if (tieneReferencias) {
                    boolean continuar = mostrarMensajeConfirmacion(
                        "La família té referències associades. Segur que vols continuar amb l'eliminació?"
                    );
                    if (!continuar) {
                        mostrarMensaje("Operació cancel·lada per l'usuari.");
                        return;
                    }
                }

                boolean continuarEliminacion = mostrarMensajeConfirmacion("Segur que vols eliminar la família?");
                if (continuarEliminacion) {
                    familiaLogica.eliminarFamilia(familiaSeleccionada);
                    tv_familia.getItems().remove(familiaSeleccionada);
                } else {
                    mostrarMensaje("Operació cancel·lada.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(PantallaReferenciaController.class.getName()).log(Level.SEVERE, "Error al eliminar la Família", ex);
                mostrarMensajeError("Error en eliminar la família: " + ex.getMessage());
            }
        } else {
            mostrarMensajeError("No s'ha seleccionat cap família per suprimir.");
        }
    }

    // Modificar los datos de una familia seleccionada
    @FXML
    void modificar(ActionEvent event) {
        Familia familiaSeleccionada = tv_familia.getSelectionModel().getSelectedItem();

        if (familiaSeleccionada != null) {
            try {
                // Obtener datos desde los campos
                String nom = txt_nom.getText();
                int idProveidor = Integer.parseInt(txt_idProveidor.getText());
                String dataAlta = txt_dataAlta.getText();
                String descripcio = txt_areaDescripcio.getText();
                String observacions = txt_areaObservacions.getText();

                // Validar los datos antes de realizar la modificación
                ValidarCamposInsertFamilia.validarDatos(familiaDAO, dataAlta, idProveidor);

                // Confirmar con el usuario antes de modificar
                boolean confirmado = mostrarMensajeConfirmacion("¿Seguro que deseas modificar esta familia?");
                if (confirmado) {
                    // Actualizar los datos de la familia seleccionada
                    familiaSeleccionada.setNom_familia(nom);
                    familiaSeleccionada.setId_proveidor_fam(idProveidor);
                    familiaSeleccionada.setData_alta_fam(dataAlta);
                    familiaSeleccionada.setDescripcio(descripcio);
                    familiaSeleccionada.setObservacions(observacions);

                    // Actualizar la tabla
                    tv_familia.refresh();

                    // Guardar cambios en la base de datos
                    familiaLogica.modificarFamilia(familiaSeleccionada);
                } else {
                    mostrarMensaje("Modificación cancelada.");
                }
            } catch (NumberFormatException e) {
                mostrarMensajeError("Por favor, introduzca valores numéricos en los campos de ID de proveedor.");
            } catch (Exception e) {
                mostrarMensajeError(e.getMessage());
            }
        } else {
            mostrarMensajeError("No se ha seleccionado ninguna familia.");
        }
    }

    // Cerrar la pantalla
    @FXML
    void sortir(ActionEvent event) {
        Stage stage = (Stage) this.btn_sortir.getScene().getWindow();
        stage.close();
    }
}
