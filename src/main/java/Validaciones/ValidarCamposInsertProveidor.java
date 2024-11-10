/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import dades.ProveidorDAO;
import logica.Mensajes;
import Validaciones.ValidacionesRegex;

/**
 *
 * @author mayoa
 */
public class ValidarCamposInsertProveidor extends Mensajes{
    
     public static void validarDatos(ProveidorDAO proveidorDAO, String cif,
            String data_creacio, String correu_electronic, Float rating,int mesos) throws Exception {
        if (!ValidacionesRegex.validarCif(cif)) {
            throw new Exception("El cif no es vàlid");
        }
        if (!ValidacionesRegex.FechaValida(data_creacio)) {
            throw new Exception("La data de creació no es vàlida");
        }
        if (!ValidacionesRegex.validarCorreu(correu_electronic)) {
            throw new Exception("El correu electronic no es vàlid");
        }
        if (!ValidacionesRegex.validarValoracio(rating)) {
            throw new Exception("La valoracio no es vàlida");
        }
        if (!ValidacionesRegex.NumerosPositivos(String.valueOf(mesos))) {
            throw new Exception("La quantitat ha de ser un número positiu i no contenir lletres.");
        }
    }
}
