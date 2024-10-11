/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import javafx.scene.control.Alert;

/**
 * Clase utilitaria para mostrar mensajes de alerta en la interfaz gráfica.
 * 
 * Proporciona métodos estáticos para mostrar mensajes de error y mensajes de confirmación
 * al usuario mediante ventanas de alerta de JavaFX.
 * 
 * @author mayoa
 */
public class Mensajes {
    
    /**
     * Muestra una alerta de error con el mensaje especificado.
     * 
     * @param mensaje El mensaje de error a mostrar en la alerta.
     */
     public static void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
     
    /**
     * Muestra una alerta de confirmación con el mensaje especificado.
     * 
     * @param mensaje El mensaje de confirmación a mostrar en la alerta.
     */
    public static void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mensaje ");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();

    }
}
