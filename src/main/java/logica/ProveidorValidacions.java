/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 * Classe que proporciona diferents mètodes per validar dades relacionades amb
 * els proveïdors.
 *
 * @author Anna
 */
public class ProveidorValidacions {

    /**
     * Valida si el CIF compleix el format correcte.
     *
     * @param cif El CIF a validar.
     * @return true si el CIF compleix el format, false altrament.
     */
    public static boolean validarCif(String cif) {
        String regex = "^[ABCDEFGHJKLMNPQRSUVW]\\d{7}[0-9A-J]$";
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
     * Valida si la data compleix el format correcte (YYYY-MM-DD).
     *
     * @param data La data a validar.
     * @return true si la data compleix el format, false altrament.
     */
    public static boolean validarData(String data) {
        String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
        return data.matches(regex);
    }

    /**
     * Valida si la valoració és un nombre amb fins a dues decimals.
     *
     * @param valoracio La valoració a validar.
     * @return true si la valoració compleix el format, false altrament.
     */
    public static boolean validarValoracio(String valoracio) {
        String regex = "^\\d+(\\.\\d{1,2})?$";
        return valoracio.matches(regex);
    }

    /**
     * Valida si els mesos de col·laboració són un nombre enter positiu.
     *
     * @param mesos Els mesos de col·laboració a validar.
     * @return true si els mesos compleixen el format, false altrament.
     */
    public static boolean validarMesos(String mesos) {
        String regex = "^\\d+$";
        return mesos.matches(regex);
    }

}
