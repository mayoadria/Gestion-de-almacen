package cat.copernic.project1_grup2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;
import javafx.scene.layout.Pane;

/**
 * JavaFX App
 */
public class App extends Application {
    
    Scanner sc = new Scanner(System.in);

    @Override
    public void start(Stage stage) throws IOException {
        try {
            //Cargo la vista
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/fxml/PantallaSeleccionaMenus.fxml"));

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

    public static void main(String[] args) {
        launch();
    }

//    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

}
