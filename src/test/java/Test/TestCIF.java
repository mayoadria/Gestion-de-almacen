/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import Validaciones.ValidacionesRegex;
import com.github.javafaker.Faker;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 *
 * @author mayoa
 */
public class TestCIF {
    
    @Test
    public void testCIFS(){
    List<String> incorrectes = Arrays.asList(
                "abc", // Incorrecto, debería ser "2000-01-01"
                "123456789",
                "B1234567", 
                "1B2345678", 
                "1B2345678" 
        );
    
    Faker faker = new Faker();

        // Número de correos electrónicos a generar
        int cantidadCifs = 10;
        System.out.println("Correctos:");
        // Bucle para generar correos electrónicos
        for (int i = 0; i < cantidadCifs; i++) {
            // Generar un correo electrónico aleatorio usando Faker
            char letraInicial = faker.letterify("A").charAt(0); // Genera una letra entre A-Z
            char letraFinal = faker.letterify("Z").charAt(0); // Genera otra letra entre A-Z
            String numeros = faker.numerify("#######"); // Genera 7 números

            String cif = letraInicial + numeros + letraFinal;
            // Imprimir el correo generado
            System.out.println(cif);
            Assertions.assertTrue(ValidacionesRegex.validarCif(cif));
        }
        System.out.println("Incorrectos");
        for (String s : incorrectes) {
            
            System.out.println(s);
            Assertions.assertFalse(ValidacionesRegex.validarCif(s));
        }
}
}
