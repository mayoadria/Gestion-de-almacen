package Validaciones;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import static logica.Mensajes.*;

/**
 * La classe {@code ValidarFecha} proporciona un mètode per validar dates,
 * assegurant-se que la data introduïda compleixi un format específic
 * i que no sigui anterior ni posterior a la data actual.
 * 
 * Aquesta classe utilitza el format de data {@code yyyy-MM-dd} per validar les dates
 * i gestiona errors si el format no és correcte o si la data no és vàlida segons les regles establertes.
 * 
 * @author mayoa
 */
public class ValidarFecha {

    /**
     * Valida si la data d'alta proporcionada compleix el format {@code yyyy-MM-dd}
     * i si està dins d'un rang vàlid, és a dir, no és anterior ni posterior a la data actual.
     * 
     * El format de la data ha de ser {@code yyyy-MM-dd}. Si la data no compleix aquest format,
     * o si la data és anterior o posterior a la data actual, es mostra un missatge d'error
     * i el mètode retorna {@code false}.
     *
     * @param fechaAlta La data d'alta que es vol validar, en el format {@code yyyy-MM-dd}.
     * @return {@code true} si la data és vàlida, {@code false} en cas contrari.
     */
    public static boolean validarFechaAlta(String fechaAlta) {
        try {
            // Definir el format esperat de la data (per exemple: "yyyy-MM-dd")
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Convertir la data introduïda a un objecte LocalDate
            LocalDate fechaIngresada = LocalDate.parse(fechaAlta, formatter);

            // Obtenir la data actual
            LocalDate fechaHoy = LocalDate.now();

            // Comparar les dates
            if (fechaIngresada.isBefore(fechaHoy)) {
                // Mostrar missatge d'error si la data és anterior a avui
                mostrarMensajeError("La data no pot ser anterior a la data actual.");
                return false; // La data no és vàlida
            } else if (fechaIngresada.isAfter(fechaHoy)) {
                // Mostrar missatge d'error si la data és posterior a avui
                mostrarMensajeError("La data no pot ser posterior a la data actual.");
                return false; // La data no és vàlida
            }

            return true; // La data és vàlida
        } catch (DateTimeParseException e) {
            // Si la data no té el format correcte
            mostrarMensajeError("El format de la data és incorrecte. Ha de ser 'yyyy-MM-dd'.");
            return false;
        }
    }
}
