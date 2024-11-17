/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import java.util.regex.Pattern;

/**
 *
 * @author mayoa
 */
public class ValidacionesRegex {
     /**
     * Verifica si la fecha introducida cumple con el formato yyyy-MM-dd.
     *
     * @param fecha La fecha en formato de cadena a validar.
     * @return {@code true} si la fecha es válida; {@code false} en caso contrario.
     */
    public static boolean FechaValida(String fecha) {
        // Expresión regular para yyyy-MM-dd
        Boolean ret = false;
        Pattern patronFecha = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        // Crear patrón
        if (patronFecha.matcher(fecha).find()) {ret = true;}
       
        return ret;
    }
    
    /**
     * Verifica si el precio introducido es válido, permitiendo hasta dos decimales.
     *
     * @param preu El precio en formato de cadena a validar.
     * @return {@code true} si el precio es válido; {@code false} en caso contrario.
     */
    public static boolean PreuValid(String preu) {
        Boolean ret = false;
        Pattern patronPreu = Pattern.compile("^[1-9]\\d*(\\.\\d+)?$");
        // Crear patrón
        if (patronPreu.matcher(preu).find()) {ret = true;}
       
        return ret;
    }
    
    /**
     * Verifica si la unidad de medida introducida corresponde a "unitats".
     *
     * @param unitat La unidad de medida en formato de cadena a validar.
     * @return {@code true} si la unidad es "unitats"; {@code false} en caso contrario.
     */
    public static boolean unitatMidaValid(String unitat) {
        Boolean ret = false;
        Pattern patronPreu = Pattern.compile("^unitats$");
        // Crear patrón
        if (patronPreu.matcher(unitat).find()) {ret = true;}
       
        return ret;
    }
    
    public static boolean NumerosPositivos(String unitat) {
        Pattern patronPreu = Pattern.compile("^[1-9]\\d*$");
        // Crear patrón
        return patronPreu.matcher(unitat).matches();
    }
    /**
     * Valida si el CIF compleix el format correcte.
     *
     * @param cif El CIF a validar.
     * @return true si el CIF compleix el format, false altrament.
     */
    public static boolean validarCif(String cif) {
        String regex = "^[A-Z][0-9]{7}[A-Z]$";
        return cif.matches(regex);
    }

    /**
     * Valida si el correu electrònic compleix el format correcte.
     *
     * @param correu El correu electrònic a validar.
     * @return true si el correu electrònic compleix el format, false altrament.
     */
    public static boolean validarCorreu(String correu) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return correu.matches(regex);
    }

    /**
     * Valida si la valoració és un nombre amb fins a dues decimals.
     *
     * @param valoracio La valoració a validar.
     * @return true si la valoració compleix el format, false altrament.
     */
    public static boolean validarValoracio(Float valoracio) {
        String valoracionString = Float.toString(valoracio);
        String regex = "^\\d+(\\.\\d{1,2})?$";
        return valoracionString.matches(regex);
    }
}
