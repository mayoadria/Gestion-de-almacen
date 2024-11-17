/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import static logica.Mensajes.*;

/**
 *
 * @author mayoa
 */
public class ValidarFecha {
    public static boolean validarFechaAlta(String fechaAlta) {
        try {
            // Definir el formato esperado de la fecha (por ejemplo: "yyyy-MM-dd")
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Convertir la fecha ingresada a un objeto LocalDate
            LocalDate fechaIngresada = LocalDate.parse(fechaAlta, formatter);

            // Obtener la fecha actual
            LocalDate fechaHoy = LocalDate.now();

            // Comparar las fechas
            if (fechaIngresada.isBefore(fechaHoy)) {
                // Mostrar mensaje de error si la fecha es anterior a hoy
                mostrarMensajeError("La fecha no puede ser anterior a la fecha actual.");
                return false; // La fecha es inválida
            }else if (fechaIngresada.isAfter(fechaHoy)) {
                // Mostrar mensaje de error si la fecha es anterior a hoy
                mostrarMensajeError("La fecha no puede ser posterior a la fecha actual.");
                return false; // La fecha es inválida
            }
            
            return true; // La fecha es válida
        } catch (DateTimeParseException e) {
            // Si la fecha no tiene el formato correcto
            mostrarMensajeError("El formato de la fecha es incorrecto. Debe ser 'yyyy-MM-dd'.");
            return false;
        }
    }
}
