/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TestReferencia;

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
public class TestCorreusElectronics {
    
    @Test
    public void testCorreos(){
    List<String> incorrectes = Arrays.asList(
                "pepe", // Incorrecto, debería ser "2000-01-01"
                "pepe.com", // Año inválido
                "pepe@gmail", // Mes inválido
                "pepe@gmail..com", // Año inválido
                "pepe@gmail,com", // Año inválido
                "pepe@gmail#com" // Formato incorrecto
        );
    
    Faker faker = new Faker();

        // Número de correos electrónicos a generar
        int cantidadCorreos = 10;
        System.out.println("Correctos:");
        // Bucle para generar correos electrónicos
        for (int i = 0; i < cantidadCorreos; i++) {
            // Generar un correo electrónico aleatorio usando Faker
            String correo = faker.internet().emailAddress();
            
            // Imprimir el correo generado
            System.out.println(correo);
            Assertions.assertTrue(ValidacionesRegex.validarCorreu(correo));
        }
        System.out.println("Incorrectos");
        for (String s : incorrectes) {
            
            System.out.println(s);
            Assertions.assertFalse(ValidacionesRegex.validarCorreu(s));
        }
}
}
