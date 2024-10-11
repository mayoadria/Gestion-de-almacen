/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author mayoa
 */
public class Excepciones{
    

    // Excepción para validaciones generales
    public static void ValidacionException(String mensaje) throws Exception {
        throw new Exception("Error de validación: " + mensaje);
    }

    // Excepción para errores de base de datos
    public static void DatabaseException(String mensaje) throws Exception {
        throw new Exception("Error en la base de datos: " + mensaje);
    }
    // Excepción para errores al cargar las pantallas
    public static void CargaVistaException(String mensaje) throws Exception{
        throw new Exception("Error al cargar la pantalla " + mensaje);
    }
    // Excepción para errores al autenticarse
    public static void Autenticacion(String mensaje) throws Exception{
        throw new Exception("Error al cargar la pantalla " + mensaje);
    }

    
}
