package Validaciones;

import dades.ReferenciaDAO;
import logica.Mensajes;
import logica.ReferenciaLogica;
import Validaciones.ValidacionesRegex;

/**
 * La clase {@code ValidarCamposInsertReferencia} es responsable de validar los datos necesarios
 * para la inserción de una referencia en el sistema. Verifica el formato de los campos proporcionados
 * y realiza consultas a la base de datos para asegurarse de que los datos relacionados con la familia
 * y el proveedor sean correctos.
 * 
 * Las validaciones incluyen formatos de fechas, precios, cantidades y unidades de medida,
 * así como la existencia de la familia y el proveedor en la base de datos.
 * 
 * @author mayoa
 * @since 2024
 */
public class ValidarCamposInsertReferencia extends Mensajes {

    /**
     * Valida los datos proporcionados para la inserción de una referencia.
     * 
     * Realiza las siguientes verificaciones:
     * <ul>
     * <li>Verifica si la unidad de medida es válida.</li>
     * <li>Valida las fechas de alta y de fabricación en formato {@code yyyy-MM-dd}.</li>
     * <li>Verifica que el precio sea válido (hasta dos decimales).</li>
     * <li>Comprueba que las cantidades de referencia y las unidades vendidas sean números positivos.</li>
     * <li>Verifica que la familia y el proveedor existan en la base de datos.</li>
     * </ul>
     * 
     * @param referenciaDAO Instancia de {@link ReferenciaDAO} utilizada para interactuar con la base de datos
     * y verificar la existencia de familias y proveedores.
     * @param unitatMida La unidad de medida de la referencia, en formato de cadena.
     * @param dataAlta La fecha de alta de la referencia, en formato de cadena {@code yyyy-MM-dd}.
     * @param dataFabricacio La fecha de fabricación de la referencia, en formato de cadena {@code yyyy-MM-dd}.
     * @param preu El precio de la referencia, en formato de cadena, permitido hasta dos decimales.
     * @param quantitat La cantidad de la referencia, en formato entero.
     * @param idFamilia El ID de la familia a la que pertenece la referencia, en formato entero.
     * @param unitatsVenudes Las unidades vendidas de la referencia, en formato entero.
     * @param idProveidor El ID del proveedor de la referencia, en formato entero.
     * 
     * @throws Exception Si alguna de las validaciones falla, se lanza una excepción con el mensaje correspondiente.
     */
    public static void validarDatos(ReferenciaDAO referenciaDAO, String unitatMida, String dataAlta,
            String dataFabricacio, String preu, int quantitat, int idFamilia, int idProveidor, int unitatsVenudes) throws Exception {
        
        // Validación de la unidad de medida
        if (!ValidacionesRegex.unitatMidaValid(unitatMida)) {
            throw new Exception("La unitat de mesura no és vàlida");
        }
        
        // Validación de la fecha de alta
        if (!ValidacionesRegex.FechaValida(dataAlta)) {
            throw new Exception("La data d'alta no és vàlida");
        }
        
        // Validación de la fecha de fabricación
        if (!ValidacionesRegex.FechaValida(dataFabricacio)) {
            throw new Exception("La data de fabricació no és vàlida");
        }
        
        // Validación del precio
        if (!ValidacionesRegex.PreuValid(preu)) {
            throw new Exception("El preu no és vàlid");
        }
        
        // Validación de la cantidad
        if (!ValidacionesRegex.NumerosPositivos(String.valueOf(quantitat))) {
            throw new Exception("La quantitat ha de ser un número positiu i no contenir lletres.");
        }
        
        // Validación de las unidades vendidas
        if (!ValidacionesRegex.NumerosPositivos(String.valueOf(unitatsVenudes))) {
            throw new Exception("Les unitats venudes han de ser un nombre positiu i no contenir lletres.");
        }
        
        // Verificación de la existencia de la familia
        if (!referenciaDAO.existeFamilia(idFamilia)) {
            throw new Exception("La família introduïda no existeix.");
        }
        
        // Verificación de la existencia del proveedor
        if (!referenciaDAO.existeProveedor(idProveidor)) {
            throw new Exception("El proveïdor introduït no existeix.");
        }
    }
}
