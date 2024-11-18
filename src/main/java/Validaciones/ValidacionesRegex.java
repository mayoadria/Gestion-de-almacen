package Validaciones;

import java.util.regex.Pattern;

/**
 * La clase {@code ValidacionesRegex} proporciona métodos estáticos para validar cadenas
 * de texto utilizando expresiones regulares. Los métodos permiten validar fechas, precios,
 * unidades de medida, números positivos, CIFs, correos electrónicos y valoraciones.
 * 
 * @author mayoa
 */
public class ValidacionesRegex {

    /**
     * Verifica si la fecha introducida cumple con el formato {@code yyyy-MM-dd}.
     * 
     * @param fecha La fecha en formato de cadena a validar.
     * @return {@code true} si la fecha es válida según el formato {@code yyyy-MM-dd}, 
     *         {@code false} en caso contrario.
     */
    public static boolean FechaValida(String fecha) {
        // Expresión regular para yyyy-MM-dd
        Boolean ret = false;
        Pattern patronFecha = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        // Crear patrón
        if (patronFecha.matcher(fecha).find()) {
            ret = true;
        }
        return ret;
    }

    /**
     * Verifica si el precio introducido es válido, permitiendo hasta dos decimales.
     *
     * @param preu El precio en formato de cadena a validar.
     * @return {@code true} si el precio es válido según el formato permitido, 
     *         {@code false} en caso contrario.
     */
    public static boolean PreuValid(String preu) {
        Boolean ret = false;
        Pattern patronPreu = Pattern.compile("^[1-9]\\d*(\\.\\d+)?$");
        // Crear patrón
        if (patronPreu.matcher(preu).find()) {
            ret = true;
        }
        return ret;
    }

    /**
     * Verifica si la unidad de medida introducida corresponde a {@code "unitats"}.
     *
     * @param unitat La unidad de medida en formato de cadena a validar.
     * @return {@code true} si la unidad es {@code "unitats"}, 
     *         {@code false} en caso contrario.
     */
    public static boolean unitatMidaValid(String unitat) {
        Boolean ret = false;
        Pattern patronPreu = Pattern.compile("^unitats$");
        // Crear patrón
        if (patronPreu.matcher(unitat).find()) {
            ret = true;
        }
        return ret;
    }

    /**
     * Verifica si el número introducido es un número positivo.
     *
     * @param unitat La cadena que representa el número a validar.
     * @return {@code true} si el número es positivo, {@code false} en caso contrario.
     */
    public static boolean NumerosPositivos(String unitat) {
        Pattern patronPreu = Pattern.compile("^[1-9]\\d*$");
        // Crear patrón
        return patronPreu.matcher(unitat).matches();
    }

    /**
     * Valida si el CIF cumple con el formato correcto.
     * El formato correcto es una letra, seguida de 7 dígitos y una letra final.
     *
     * @param cif El CIF a validar.
     * @return {@code true} si el CIF cumple con el formato, {@code false} en caso contrario.
     */
    public static boolean validarCif(String cif) {
        String regex = "^[A-Z][0-9]{7}[A-Z]$";
        return cif.matches(regex);
    }

    /**
     * Valida si el correo electrónico cumple con el formato correcto.
     * El formato esperado es: una secuencia de caracteres válidos para un correo, seguida de una 
     * arroba y un dominio válido.
     *
     * @param correu El correo electrónico a validar.
     * @return {@code true} si el correo electrónico cumple con el formato, {@code false} en caso contrario.
     */
    public static boolean validarCorreu(String correu) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return correu.matches(regex);
    }

    /**
     * Valida si la valoración es un número con hasta dos decimales.
     * 
     * @param valoracio La valoración a validar.
     * @return {@code true} si la valoración cumple con el formato numérico permitido, 
     *         {@code false} en caso contrario.
     */
    public static boolean validarValoracio(Float valoracio) {
        String valoracionString = Float.toString(valoracio);
        String regex = "^\\d+(\\.\\d{1,2})?$";
        return valoracionString.matches(regex);
    }
}