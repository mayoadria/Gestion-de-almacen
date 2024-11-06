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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logica.Autenticacio;
import logica.Mensajes;

/**
 * Controlador para la interfaz de autenticación de usuario. La clase 
 * PantallaAutenticacioController gestiona la lógica de autenticación de usuarios
 * y la transición a la siguiente pantalla en caso de autenticación exitosa. 
 * Extiende la clase Mensajes para mostrar mensajes de información y error.
 * Implementa Initializable para inicializar los componentes de la interfaz.
 * 
 * @author chris
 * @version 1.0
 * @since 2024
 */
public class PantallaAutenticacioController extends Mensajes implements Initializable{
    
    /**
     * Método de inicialización para configurar cualquier lógica o datos necesarios
     * al momento de cargar la interfaz. Actualmente sin implementación.
     * 
     * @param url la URL de la interfaz cargada.
     * @param rb el recurso de localización para internacionalización.
     */
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
    
    /**
     * Maneja el evento de autenticación del usuario cuando se presiona el botón
     * "Iniciar Sesión". Verifica si el usuario y rol introducidos son correctos y,
     * en caso afirmativo, muestra un mensaje de éxito y abre una nueva pantalla.
     * En caso contrario, muestra un mensaje de error.
     * 
     * @param event el evento de acción que dispara el inicio de sesión.
     */
    @FXML
    void iniciarSessio(ActionEvent event) throws Exception {
        String usuario = txtUsuari.getText();
        String rol = txtRol.getText();

        // Verificar autenticación
        if (autenticacio.verificarUsuario(usuario, rol)) {
            // Abrir la nueva pantalla si la autenticación es correcta
            mostrarMensaje("Usuari verificat correctament.");
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
            mostrarMensajeError("Usuari o rol incorrecte.");
        }
    }
    
    /**
     * Carga y abre una nueva pantalla de selección de menús después de una
     * autenticación exitosa. Si ocurre un error durante la carga, se registra y 
     * muestra un mensaje de error en el log.
     */
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
            //Excepciones.Autenticacion(ex.getMessage());
        }
    }
    
}
