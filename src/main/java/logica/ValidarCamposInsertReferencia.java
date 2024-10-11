/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;
import dades.ReferenciaDAO;
import logica.Excepciones;
/**
 *
 * @author mayoa
 */
public class ValidarCamposInsertReferencia extends Mensajes{
    
    public static void validarDatos(ReferenciaLogica referenciaLogica, ReferenciaDAO referenciaDAO, String unitatMida, String dataAlta, 
                                     String dataFabricacio, String preu, int quantitat, int idFamilia, int idProveidor) throws Exception {
        // Validaciones
        if (!referenciaLogica.unitatMidaValid(unitatMida)) {
            mostrarMensajeError("La unitat de mida no es valida");
            Excepciones.ValidacionException("La unitat de mida no es valida");
            return;
        }
        if (!referenciaLogica.FechaValida(dataAlta)) {
            Excepciones.ValidacionException("La data d'alta no es valida");
            mostrarMensajeError("La data d'alta no es valida");
            return;
        }
        if (!referenciaLogica.FechaValida(dataFabricacio)) {
            Excepciones.ValidacionException("La data de fabricació no es valida");
            mostrarMensajeError("La data de fabricació no es valida");
                return;
        }
        if (!referenciaLogica.PreuValid(String.valueOf(preu))) {
            Excepciones.ValidacionException("El preu no es valid");
            mostrarMensajeError("La preu no es valid");
                return;
        }
        if (!referenciaLogica.PreuValid(String.valueOf(quantitat))) {
            Excepciones.ValidacionException("La quantitat no es valida");
            mostrarMensajeError("La quantitat no es valida");
                return;
        }

        // Validar que la familia existe en la base de datos
        if (!referenciaDAO.existeFamilia(idFamilia)) {
            Excepciones.DatabaseException("La familia introducida no existe.");mostrarMensajeError("La familia introducida no existe. Por favor, introduce un ID de familia válido.");
                return; // Detener el flujo si la familia no existe
        }

        // Validar que el proveedor existe en la base de datos
        if (!referenciaDAO.existeProveedor(idProveidor)) {
            Excepciones.DatabaseException("El proveedor introducido no existe.");
            mostrarMensajeError("El proveedor introducido no existe. Por favor, introduce un ID de proveedor válido.");
                return; // Detener el flujo si el proveedor no existe
        }
    }
}
