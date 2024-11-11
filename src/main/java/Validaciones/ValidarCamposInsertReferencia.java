/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import dades.ReferenciaDAO;
import logica.Mensajes;
import logica.ReferenciaLogica;
import Validaciones.ValidacionesRegex;

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
        if (!ValidacionesRegex.unitatMidaValid(unitatMida)) {
            throw new Exception("La unitat de mesura no és vàlida");
        }
        if (!ValidacionesRegex.FechaValida(dataAlta)) {
            throw new Exception("La data d'alta no és vàlida");
        }
        if (!ValidacionesRegex.FechaValida(dataFabricacio)) {
            throw new Exception("La data de fabricació no és vàlida");
        }
        if (!ValidacionesRegex.PreuValid(preu)) {
            throw new Exception("El preu no és vàlid");
        }
        if (!ValidacionesRegex.NumerosPositivos(String.valueOf(quantitat))) {
            throw new Exception("La quantitat ha de ser un número positiu i no contenir lletres.");
        }
        if (!ValidacionesRegex.NumerosPositivos(String.valueOf(unitatsVenudes))) {
            throw new Exception("Les unitats venudes han de ser un nombre positiu i no contenir lletres.");
        }
        if (!referenciaDAO.existeFamilia(idFamilia)) {
            throw new Exception("La família introduïda no existeix.");
        }
        if (!referenciaDAO.existeProveedor(idProveidor)) {
            throw new Exception("El proveïdor introduït no existeix.");
        }
    }
}
