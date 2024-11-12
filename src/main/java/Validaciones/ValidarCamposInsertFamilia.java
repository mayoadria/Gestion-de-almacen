/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import dades.FamiliaDAO;
import dades.ReferenciaDAO;
import logica.Mensajes;

/**
 *
 * @author mayoa
 */
public class ValidarCamposInsertFamilia extends Mensajes {

    private FamiliaDAO familiaDao;

    public static void validarDatos(FamiliaDAO familiaDao, String data_alta, int id_proveidor) throws Exception {
        if (!ValidacionesRegex.FechaValida(data_alta)) {
            throw new Exception("La data d'alta no és vàlida");
        }
        if (!familiaDao.existeProveedor(id_proveidor)) {
            throw new Exception("El Id de proveidor ha de ser un nombre positiu i no contenir lletres, a part ha d'existir a la base de dades.");
        }
    }
}
