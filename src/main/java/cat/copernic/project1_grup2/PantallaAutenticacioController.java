/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.project1_grup2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 *
 * @author chris
 */
public class PantallaAutenticacioController implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    private Label autenticacio;
    
    @FXML
    private Label usuari;
    
    @FXML
    private Label rol;
    
    @FXML
    private PasswordField txtUsuari;
    
    @FXML
    private PasswordField txtRol;
    
    @FXML
    private Button btnIniciarSessio;
    
    @FXML
    void IniciarSessio(ActionEvent event) {

    }
    
    
}
