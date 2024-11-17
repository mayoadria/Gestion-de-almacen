/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
/**
 * Clase de pruebas unitarias para verificar la validación de fechas
 * en la lógica de referencia, utilizando la biblioteca JUnit5.
 * Esta clase utiliza la librería Faker para generar fechas aleatorias
 * y probar la funcionalidad de validación de fechas correctas e incorrectas.
 *
 *
 * @author mayoa
 * @version 1.0
 * @since 2024
 */
package Test;

import Validaciones.ValidacionesRegex;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;  // Cambié la importación a Assertions de JUnit 5
import org.junit.jupiter.api.Test; // Cambié la importación para JUnit 5

/**
 * Clase de pruebas unitarias para verificar la validación de fechas en la
 * lógica de referencia, utilizando la biblioteca JUnit5. Esta clase utiliza la
 * librería Faker para generar fechas aleatorias y probar la funcionalidad de
 * validación de fechas correctas e incorrectas.
 *
 * @author mayoa
 * @version 1.0
 * @since 2024
 */
public class TestsDatas {

    @Test
    public void testValidDates() {
        List<String> incorrectes = Arrays.asList(
                "2024-1-1", // Incorrecto, debería ser "2000-01-01"
                "41-1-2024", // Año inválido
                "-1-13-2024", // Mes inválido
                "-1-1-2024", // Año inválido
                "+1-1-2024", // Año inválido
                "1-1/2024", // Formato incorrecto
                "1/1-2024", // Formato incorrecto
                "1//1/2024", // Formato incorrecto
                "1/1/-2024" // Formato incorrecto
        );

        System.out.println("Correctes:");
        // Fechas correctas
        for (int i = 1; i <= 12; i++) {
            // Aquí generamos las fechas correctamente formateadas
            String data = "2024-" + String.format("%02d", i) + "-31"; // Formato yyyy-MM-dd
            System.out.println(data);
            Assertions.assertTrue(ValidacionesRegex.FechaValida(data));
        }

        // Fecha bisiesta (29 de febrero de 2000)
        String dataBisiesta = "2024-02-29";  // Fecha bisiesta en un año bisiesto
        System.out.println(dataBisiesta);
        Assertions.assertTrue(ValidacionesRegex.FechaValida(dataBisiesta));

        System.out.println("Incorrectes:");
        // Fechas incorrectas
        for (String s : incorrectes) {
            System.out.println(s);
            Assertions.assertFalse(ValidacionesRegex.FechaValida(s));
        }
    }
}
