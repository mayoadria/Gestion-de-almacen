package Validaciones;

import dades.ProveidorDAO;
import logica.Mensajes;
import Validaciones.ValidacionesRegex;

/**
 * La clase {@code ValidarCamposInsertProveidor} se encarga de validar los datos de entrada
 * relacionados con la inserción de un nuevo proveedor en el sistema.
 * 
 * Se asegura de que el CIF, la fecha de creación, el correo electrónico, la valoración y la cantidad de meses
 * cumplan con los formatos y requisitos establecidos antes de permitir la inserción de un nuevo proveedor.
 * 
 * @author mayoa
 */
public class ValidarCamposInsertProveidor extends Mensajes {
    
    /**
     * Valida los datos de un nuevo proveedor antes de realizar la inserción.
     * 
     * @param proveidorDAO La instancia de {@code ProveidorDAO} para interactuar con la base de datos.
     * @param cif El CIF del proveedor a validar. Debe seguir el formato correcto para un CIF español.
     * @param data_creacio La fecha de creación del proveedor, en formato de cadena. Debe ser válida y seguir el formato {@code yyyy-MM-dd}.
     * @param correu_electronic El correo electrónico del proveedor. Debe ser una dirección válida.
     * @param rating La valoración del proveedor, que debe ser un número con hasta dos decimales.
     * @param mesos La cantidad de meses del proveedor. Debe ser un número positivo y no contener letras.
     * @throws Exception Si alguno de los datos no es válido, se lanza una excepción con el mensaje correspondiente.
     */
    public static void validarDatos(ProveidorDAO proveidorDAO, String cif,
            String data_creacio, String correu_electronic, Float rating, int mesos) throws Exception {
        // Validación del CIF
        if (!ValidacionesRegex.validarCif(cif)) {
            throw new Exception("El cif no és vàlid");
        }
        
        // Validación de la fecha de creación
        if (!ValidacionesRegex.FechaValida(data_creacio)) {
            throw new Exception("La data de creació no és vàlida");
        }
        
        // Validación del correo electrónico
        if (!ValidacionesRegex.validarCorreu(correu_electronic)) {
            throw new Exception("El correu electrònic no és vàlida");
        }
        
        // Validación de la valoración
        if (!ValidacionesRegex.validarValoracio(rating)) {
            throw new Exception("La valoració no és vàlida");
        }
        
        // Validación de los meses
        if (!ValidacionesRegex.NumerosPositivos(String.valueOf(mesos))) {
            throw new Exception("La quantitat ha de ser un nombre positiu i no contenir lletres.");
        }
    }
}
