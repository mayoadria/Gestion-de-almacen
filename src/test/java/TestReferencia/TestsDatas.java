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
package TestReferencia;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.github.javafaker.Faker;
import logica.ReferenciaLogica;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author mayoa
 */
public class TestsDatas {

    Faker faker = new Faker();

    @Test
    public void testValidDates() {
        List<String> incorrectes = Arrays.asList(
                "2000-1-1", // Incorrecto, debería ser "2000-01-01"
                "41-1-2010", // Año inválido
                "-1-13-2010", // Mes inválido
                "-1-1-2010", // Año inválido
                "+1-1-2010", // Año inválido
                "1-1/2010", // Formato incorrecto
                "1/1-2010", // Formato incorrecto
                "1//1/2010", // Formato incorrecto
                "1/1/-2010" // Formato incorrecto
        );
        String date;
        // Fechas correctas
        for (int i = 1; i <= 12; i++) {
            Date ok = faker.date().birthday();
            if (i == 2) {
                String data = date = "2000-" + String.format("%02d", i) + "-29"; // Formato yyyy-MM-dd);
                System.out.println(data);
                //Assert.assertTrue(ReferenciaLogica.FechaValida(data));
            }
            String data = date = "2000-" + String.format("%02d", i) + "-31"; // Formato yyyy-MM-dd);
            System.out.println(data);
            Assert.assertTrue(ReferenciaLogica.FechaValida(data));

        }

        // Fechas incorrectas
        for (String s : incorrectes) {
            Assert.assertFalse(ReferenciaLogica.FechaValida(s));
        }
    }
}
