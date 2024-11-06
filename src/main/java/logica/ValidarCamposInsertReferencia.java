/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import dades.ReferenciaDAO;

/**
 * Clase encargada de validar los campos necesarios para la inserción de
 * referencias. Realiza verificaciones de formato y existencia en la base de
 * datos para asegurar la validez de los datos ingresados.
 *
 * @author mayoa
 * @since 2024
 */
public class ValidarCamposInsertReferencia extends Mensajes {

    /**
     * Valida los datos proporcionados para la inserción de una referencia.
     * Verifica el formato de los campos, así como la existencia de familia y
     * proveedor en la base de datos.
     *
     * @param referenciaLogica Instancia de {@link ReferenciaLogica} para
     * realizar validaciones de lógica de negocio.
     * @param referenciaDAO Instancia de {@link ReferenciaDAO} para realizar
     * validaciones en la base de datos.
     * @param unitatMida Unidad de medida en formato de cadena.
     * @param dataAlta Fecha de alta en formato de cadena.
     * @param dataFabricacio Fecha de fabricación en formato de cadena.
     * @param preu Precio en formato de cadena.
     * @param quantitat Cantidad en formato entero.
     * @param idFamilia ID de la familia en formato entero.
     * @param unitatsVenudes
     * @param idProveidor ID del proveedor en formato entero.
     *
     * @throws Exception Si alguna validación falla, lanza una excepción con el
     * mensaje correspondiente.
     */
    public static void validarDatos(ReferenciaDAO referenciaDAO, String unitatMida, String dataAlta,
            String dataFabricacio, String preu, int quantitat, int idFamilia, int idProveidor, int unitatsVenudes) throws Exception {
        if (!ReferenciaLogica.unitatMidaValid(unitatMida)) {
            throw new Exception("La unitat de mida no es válida");
        }
        if (!ReferenciaLogica.FechaValida(dataAlta)) {
            throw new Exception("La data d'alta no es válida");
        }
        if (!ReferenciaLogica.FechaValida(dataFabricacio)) {
            throw new Exception("La data de fabricación no es válida");
        }
        if (!ReferenciaLogica.PreuValid(preu)) {
            throw new Exception("El precio no es válido");
        }
        if (!ReferenciaLogica.NumerosPositivos(String.valueOf(quantitat))) {
            throw new Exception("La cantidad debe ser un número positivo y no contener letras.");
        }
        if (!ReferenciaLogica.NumerosPositivos(String.valueOf(unitatsVenudes))) {
            throw new Exception("Las unidades vendidas deben ser un número positivo y no contener letras.");
        }
        if (!referenciaDAO.existeFamilia(idFamilia)) {
            throw new Exception("La familia introducida no existe.");
        }
        if (!referenciaDAO.existeProveedor(idProveidor)) {
            throw new Exception("El proveedor introducido no existe.");
        }
    }
}
