/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 * Clase utilitaria para gestionar excepciones específicas de la aplicación.
 * 
 * Proporciona métodos estáticos que lanzan excepciones con mensajes personalizados
 * para distintos contextos: validaciones generales, errores en la base de datos, 
 * carga de vistas y autenticación.
 * 
 * @author mayoa
 */
public class Excepciones{
    

    /**
     * Excepción para validaciones generales
     * 
     * @param mensaje El mensaje de error que describe la causa de la excepción de base de datos.
     * @throws Exception Indica un error al interactuar con la base de datos.
     */
    public static void ValidacionException(String mensaje) throws Exception {
        throw new Exception("Error de validación: " + mensaje);
    }

    /**
     * Excepción para errores de base de datos
     * 
     * @param mensaje El mensaje de error que describe la causa de la excepción de base de datos.
     * @throws Exception Indica un error al interactuar con la base de datos.
     */
    public static void DatabaseException(String mensaje) throws Exception {
        throw new Exception("Error en la base de datos: " + mensaje);
    }
    
    /**
     * Excepción para errores al cargar las pantallas
     * 
     * @param mensaje El mensaje de error que describe la causa de la excepción de base de datos.
     * @throws Exception Indica un error al interactuar con la base de datos.
     */
    public static void CargaVistaException(String mensaje) throws Exception{
        throw new Exception("Error al cargar la pantalla " + mensaje);
    }
    
    /**
     * Excepción para errores al autenticarse
     * 
     * @param mensaje El mensaje de error que describe la causa de la excepción de base de datos.
     * @throws Exception Indica un error al interactuar con la base de datos.
     */
    public static void Autenticacion(String mensaje) throws Exception{
        throw new Exception("Error al cargar la pantalla " + mensaje);
    }

    
}
