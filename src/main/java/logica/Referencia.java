/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.sql.Date;

/**
 * Clase que representa una referencia de producto en el sistema. Contiene
 * información detallada sobre el producto, como su nombre, cantidad, unidad de
 * medida, fechas de alta y fabricación, descripción, precio, unidades vendidas,
 * y asociaciones con una familia y proveedor específicos.
 *
 * @autor mayoa
 */
public class Referencia {

    private int id;
    private String nom;
    private int quantitat;
    private String unitat_mida;
    private Date data_alta;
    private Date data_fabricacio;
    private String descripcio;
    private float preu;
    private int unitats_venudes;
    private int id_fam;
    private int id_proveidor;

    /**
     * Constructor vacío de la clase Referencia. Inicializa una referencia de
     * producto sin datos específicos.
     */
    public Referencia() {
    }

    /**
     * Obtiene el ID único de la referencia.
     *
     * @return ID de la referencia.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID único de la referencia.
     *
     * @param id ID de la referencia.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la referencia.
     *
     * @return Nombre de la referencia.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Establece el nombre de la referencia.
     *
     * @param nom Nombre de la referencia.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtiene la cantidad disponible de la referencia.
     *
     * @return Cantidad de la referencia.
     */
    public int getQuantitat() {
        return quantitat;
    }

    /**
     * Establece la cantidad disponible de la referencia.
     *
     * @param quantitat Cantidad de la referencia.
     */
    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    /**
     * Obtiene la unidad de medida de la referencia.
     *
     * @return Unidad de medida de la referencia.
     */
    public String getUnitat_mida() {
        return unitat_mida;
    }

    /**
     * Establece la unidad de medida de la referencia.
     *
     * @param unitat_mida Unidad de medida de la referencia.
     */
    public void setUnitat_mida(String unitat_mida) {
        this.unitat_mida = unitat_mida;
    }

    /**
     * Obtiene la fecha de alta de la referencia.
     *
     * @return Fecha de alta de la referencia.
     */
    public Date getData_alta() {
        return data_alta;
    }

    /**
     * Establece la fecha de alta de la referencia.
     *
     * @param data_alta Fecha de alta de la referencia.
     */
    public void setData_alta(Date data_alta) {
        this.data_alta = data_alta;
    }

    /**
     * Obtiene la fecha de fabricación de la referencia.
     *
     * @return Fecha de fabricación de la referencia.
     */
    public Date getData_fabricacio() {
        return data_fabricacio;
    }

    /**
     * Establece la fecha de fabricación de la referencia.
     *
     * @param data_fabricacio Fecha de fabricación de la referencia.
     */
    public void setData_fabricacio(Date data_fabricacio) {
        this.data_fabricacio = data_fabricacio;
    }

    /**
     * Obtiene la descripción de la referencia.
     *
     * @return Descripción de la referencia.
     */
    public String getDescripcio() {
        return descripcio;
    }

    /**
     * Establece la descripción de la referencia.
     *
     * @param descripcio Descripción de la referencia.
     */
    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    /**
     * Obtiene el precio de la referencia.
     *
     * @return Precio de la referencia.
     */
    public float getPreu() {
        return preu;
    }

    /**
     * Establece el precio de la referencia.
     *
     * @param preu Precio de la referencia.
     */
    public void setPreu(float preu) {
        this.preu = preu;
    }

    /**
     * Obtiene el número de unidades vendidas de la referencia.
     *
     * @return Unidades vendidas de la referencia.
     */
    public int getUnitats_venudes() {
        return unitats_venudes;
    }

    /**
     * Establece el número de unidades vendidas de la referencia.
     *
     * @param unitats_venudes Unidades vendidas de la referencia.
     */
    public void setUnitats_venudes(int unitats_venudes) {
        this.unitats_venudes = unitats_venudes;
    }

    /**
     * Obtiene el ID de la familia a la que pertenece la referencia.
     *
     * @return ID de la familia de la referencia.
     */
    public int getId_fam() {
        return id_fam;
    }

    /**
     * Establece el ID de la familia a la que pertenece la referencia.
     *
     * @param id_fam ID de la familia de la referencia.
     */
    public void setId_fam(int id_fam) {
        this.id_fam = id_fam;
    }

    /**
     * Obtiene el ID del proveedor asociado a la referencia.
     *
     * @return ID del proveedor de la referencia.
     */
    public int getId_proveidor() {
        return id_proveidor;
    }

    /**
     * Establece el ID del proveedor asociado a la referencia.
     *
     * @param id_proveidor ID del proveedor de la referencia.
     */
    public void setId_proveidor(int id_proveidor) {
        this.id_proveidor = id_proveidor;
    }

    /**
     * Representación en forma de cadena de la instancia de Referencia.
     *
     * @return Cadena con la información completa de la referencia.
     */
    @Override
    public String toString() {
        return "Id: " + id + " ; nom: " + nom + " ; Id familia: " + id_fam + " ; ID proveïdor: " + id_proveidor
                + "; data de fabricacio: " + data_fabricacio + " ; data d'alta: " + data_alta
                + " ; preu: " + preu + " ; quantitat: " + quantitat + " ; unitats venudes: " + unitats_venudes
                + " ; descripcio: " + descripcio + " ; unitat de mida: " + unitat_mida;
    }
}
