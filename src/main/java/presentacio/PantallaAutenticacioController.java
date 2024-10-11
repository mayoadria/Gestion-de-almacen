/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacio;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logica.Autenticacio;
import logica.Excepciones;
import logica.Mensajes;

/**
 *
 * @author chris
 */
public class PantallaAutenticacioController extends Mensajes implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    private Autenticacio autenticacio = new Autenticacio();
    

    
    @FXML
    private TextField txtUsuari;
    
    @FXML
    private TextField txtRol;
    
    @FXML
    private Button btnIniciarSessio;
    
    @FXML
    void iniciarSessio(ActionEvent event) throws Exception {
        String usuario = txtUsuari.getText();
        String rol = txtRol.getText();

        // Verificar autenticación
        if (autenticacio.verificarUsuario(usuario, rol)) {
            // Abrir la nueva pantalla si la autenticación es correcta
            mostrarMensaje("Usuario autenticado correctamente.");
            try {
            abrirNuevaPantalla(txtRol.getText());
            // Cerrar la pantalla de autenticación
            ((Stage) txtUsuari.getScene().getWindow()).close();
        } catch (IOException e) {
            // Manejo de la excepción si ocurre al abrir la nueva pantalla
            mostrarMensajeError(e.getMessage());
        }
        } else {
            // Mostrar mensaje de error
            mostrarMensajeError("Usuario o rol incorrecto.");
        }
    }
    
    @FXML
    void abrirNuevaPantalla(String rol) throws Exception {
        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PantallaSeleccionaMenus.fxml"));

            // Cargo el padre
            Parent root = loader.load();
            
            PantallaSeleccionarMenuController controller = loader.getController();
            controller.setRol(rol);  // Pasar el rol al controlador de seleccionar menú
            // Creo la scene y el stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            // Asocio el stage con el scene
            stage.setScene(scene);
            stage.show();
            
           

        } catch (IOException ex) {
            Excepciones.Autenticacion(ex.getMessage());
        }
    }
    
}
