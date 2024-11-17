/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import static logica.Mensajes.mostrarMensajeError;

/**
 * Controlador FXML para la pantalla de selección de menú. Gestiona la
 * navegación hacia otras secciones de la aplicación como Proveedor, Referencia,
 * y Familia. Implementa la interfaz Initializable.
 *
 * @autor mayoa
 */
public class PantallaSeleccionarMenuController implements Initializable {

    @FXML
    private Button btnProveidor;
    @FXML
    private Button btnReferencia;
    @FXML
    private Button btnFamilia1;

    private String rol;
    private PantallaReferenciaController r;

    /**
     * Inicializa el controlador de la clase.
     *
     * @param url ubicación para inicializar, o null si no se usa.
     * @param rb recursos para internacionalización, o null si no se usa.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    // Método para establecer el rol
    public void setRol(String rol) {
        this.rol = rol; // Almacena el rol recibido
    }

    @FXML
    private void AbrirProveidor(ActionEvent event) {
        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pantallaProveidor.fxml"));

            // Cargo el padre
            Parent root = loader.load();

            pantallaProveidorController controllerProveidor = loader.getController();
            controllerProveidor.setRol(this.rol);
            // Creo la scene y el stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            // Asocio el stage con el scene
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            // Si ocurre un error, muestra el mensaje de error en lugar de solo registrarlo
            mostrarMensajeError(e.getMessage());
        }
    }

    /**
     * Abre la pantalla de Referencia cuando se hace clic en el botón
     * correspondiente. Carga y muestra la vista de la pantalla de Referencias
     * en una nueva ventana.
     *
     * @param event evento de acción asociado al botón btnReferencia.
     */
    @FXML
    private void AbrirReferencia(ActionEvent event) {
        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PantallaReferencias.fxml"));

            // Cargo el padre
            Parent root = loader.load();
            PantallaReferenciaController controllerReferencia = loader.getController();
            controllerReferencia.setRol(this.rol);

            // Creo la scene y el stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            // Asocio el stage con el scene
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            // Si ocurre un error, muestra el mensaje de error en lugar de solo registrarlo
            mostrarMensajeError(e.getMessage());
        }
    }

    /**
     * Abre la ventana de la pantalla de familia al recibir un evento de acción.
     *
     * Este método se ejecuta al activar el evento de acción asociado, como un
     * clic en un botón. Carga la vista de la pantalla de familia, establece el
     * rol del usuario en el controlador de la nueva pantalla y muestra la
     * ventana correspondiente.
     *
     * @param event El evento de acción que activa este método, generalmente un
     * clic en un botón.
     */
    @FXML
    private void AbrirFamilia(ActionEvent event) {
        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PantallaFamilia.fxml"));

            // Cargo el padre
            Parent root = loader.load();
            pantallaFamiliaController controllerFamilia = loader.getController();
            controllerFamilia.setRol(this.rol);
            // Creo la scene y el stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            // Asocio el stage con el scene
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            // Si ocurre un error, muestra el mensaje de error en lugar de solo registrarlo
            mostrarMensajeError(e.getMessage());
        }
    }

}
