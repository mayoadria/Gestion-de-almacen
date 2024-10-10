/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacio;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controlador FXML para la pantalla de selección de menú.
 * Gestiona la navegación hacia otras secciones de la aplicación como Proveedor, Referencia, y Familia.
 * Implementa la interfaz Initializable.
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

    /**
     * Inicializa el controlador de la clase.
     * 
     * @param url ubicación para inicializar, o null si no se usa.
     * @param rb recursos para internacionalización, o null si no se usa.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    @FXML
    private void AbrirProveidor(ActionEvent event) {
    }
    
    /**
     * Abre la pantalla de Referencia cuando se hace clic en el botón correspondiente.
     * Carga y muestra la vista de la pantalla de Referencias en una nueva ventana.
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
            
            // Creo la scene y el stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            // Asocio el stage con el scene
            stage.setScene(scene);
            stage.show();

           

        } catch (IOException ex) {
            Logger.getLogger(PantallaSeleccionarMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AbrirFamilia(ActionEvent event) {
    }
    
}

