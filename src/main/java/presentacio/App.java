package presentacio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.scene.layout.Pane;

/**
 * Clase principal de la aplicación JavaFX que inicializa y muestra la interfaz de usuario.
 * Extiende la clase {@link Application} y define el método `start` para cargar 
 * y mostrar la ventana principal.
 *
 * @author chris
 * @since 2024
 */
public class App extends Application {
    
    /**
     * Método de inicio de la aplicación JavaFX. Carga el archivo FXML correspondiente 
     * y establece la escena en la ventana principal.
     *
     * @param stage El escenario principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        try {
            //Cargo la vista
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/fxml/PantallaAutenticacio.fxml"));

            // Cargo la ventana
            Pane ventana = (Pane) loader.load();

            // Cargo el scene
            Scene scene = new Scene(ventana);

            // Seteo la scene y la muestro
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método principal de la aplicación que lanza la aplicación JavaFX.
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        launch();
    }
    
}
