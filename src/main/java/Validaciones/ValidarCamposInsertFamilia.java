package Validaciones;

import dades.FamiliaDAO;
import logica.Mensajes;

/**
 * La clase {@code ValidarCamposInsertFamilia} contiene métodos para validar los datos de entrada
 * relacionados con la inserción de una nueva familia en el sistema. 
 * Esta clase extiende la clase {@code Mensajes} para heredar funcionalidades de manejo de mensajes.
 * 
 * Se encargará de validar los datos antes de realizar la inserción en la base de datos, como
 * la validez de la fecha y la existencia de un proveedor.
 * 
 * @author mayoa
 */
public class ValidarCamposInsertFamilia extends Mensajes {

    private FamiliaDAO familiaDao;

    /**
     * Valida los datos de una nueva familia antes de realizar la inserción.
     * 
     * @param familiaDao La instancia de {@code FamiliaDAO} para interactuar con la base de datos
     *                   y verificar la existencia de un proveedor.
     * @param data_alta La fecha de alta de la nueva familia en formato de cadena. Esta fecha debe ser válida
     *                  y seguir el formato {@code yyyy-MM-dd}.
     * @param id_proveidor El ID del proveedor asociado con la nueva familia. Debe ser un número positivo
     *                     y el proveedor debe existir en la base de datos.
     * @throws Exception Si alguno de los datos no es válido, se lanzará una excepción con el mensaje correspondiente.
     */
    public static void validarDatos(FamiliaDAO familiaDao, String data_alta, int id_proveidor) throws Exception {
        // Validación de la fecha de alta
        if (!ValidacionesRegex.FechaValida(data_alta)) {
            throw new Exception("La data d'alta no és vàlida");
        }

        // Validación del ID de proveedor
        if (id_proveidor != 0 && !familiaDao.existeProveidor(id_proveidor)) {
            throw new Exception("El Id de proveidor ha de ser un nombre positiu i no contenir lletres, a part ha d'existir a la base de dades.");
        }
    }
}
