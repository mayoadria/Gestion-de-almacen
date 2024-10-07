/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author chris
 */
public class Autenticacio {
    // Método para verificar si un usuario y rol coinciden con un registro en el archivo
    public boolean verificarUsuario(String usuario, String rol) {
        String archivo = "usuaris.txt"; // Ubicación del archivo
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            
            // Leer cada línea del archivo
            while ((linea = br.readLine()) != null) {
                // Separar el nombre del rol usando espacio como separador
                String[] partes = linea.split(" ", 2); // Doble espacio para asegurar separación correcta
                if (partes.length == 2) {
                    String usuarioArchivo = partes[0].trim();
                    String rolArchivo = partes[1].trim();

                    // Comparar usuario y rol
                    if (usuarioArchivo.equals(usuario) && rolArchivo.equals(rol)) {
                        encontrado = true;
                        break; // Si encuentra coincidencia, termina la búsqueda
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return encontrado;
    }
}

