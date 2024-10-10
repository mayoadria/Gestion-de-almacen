/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * La clase Autenticacio se utiliza para verificar si un usuario con un rol específico
 * coincide con un registro en un archivo de texto que almacena los datos de usuarios.
 * Extiende la clase Mensajes, que contiene métodos para mostrar mensajes de error u otros.
 * 
 * @autor chris
 * @version 1.0
 * @since 2024
 */
public class Autenticacio extends Mensajes{
    
    /**
     * Verifica si un usuario y su rol coinciden con un registro en el archivo "usuaris.txt".
     * Lee el archivo línea por línea, donde cada línea contiene un usuario y su rol separados
     * por un espacio. Si encuentra una coincidencia exacta de usuario y rol, devuelve true.
     * En caso de error de lectura, muestra un mensaje de error.
     * 
     * @param usuario el nombre de usuario a verificar.
     * @param rol el rol del usuario a verificar.
     * @return true si el usuario y el rol coinciden con un registro en el archivo; false en caso contrario.
     */
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
                    if (usuarioArchivo.equalsIgnoreCase(usuario) && rolArchivo.equalsIgnoreCase(rol)) {
                        encontrado = true;
                        break; // Si encuentra coincidencia, termina la búsqueda
                    }
                }
            }
        } catch (IOException e) {
           mostrarMensajeError("Error al leer el archivo: " + e.getMessage());
        }
        
        return encontrado;
    }
}

