/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 * Clase que representa una familia de productos en la base de datos.
 * Contiene información relevante sobre la familia, como el nombre, descripción,
 * fecha de alta, ID del proveedor y observaciones adicionales.
 * 
 * @autor oriol
 */
public class Familia {
    private int id_fam;
    private String nom_familia;
    private String descripcio;
    private String data_alta_fam;
    private int id_proveidor_fam;
    private String Observacions;
    
    /**
     * Constructor de la clase Familia.
     * 
     * @param id_fam ID único de la familia.
     * @param nom_familia Nombre de la familia.
     * @param descripcio Descripción de la familia.
     * @param data_alta_fam Fecha de alta de la familia.
     * @param id_proveidor_fam ID del proveedor asociado a la familia.
     * @param Observacions Observaciones adicionales sobre la familia.
     */
    public Familia(int id_fam, String nom_familia, String descripcio, String data_alta_fam, int id_proveidor_fam, String Observacions) {
        this.id_fam = id_fam;
        this.nom_familia = nom_familia;
        this.descripcio = descripcio;
        this.data_alta_fam = data_alta_fam;
        this.id_proveidor_fam = id_proveidor_fam;
        this.Observacions = Observacions;
    }

    public Familia() {
    }
    

    
    /**
     * Establece el ID de la familia.
     * 
     * @param id_fam ID único de la familia.
     */
    public void setId_fam(int id_fam) {
        this.id_fam = id_fam;
    }
    
    /**
     * Establece el nombre de la familia.
     * 
     * @param nom_familia Nombre de la familia.
     */
    public void setNom_familia(String nom_familia) {
        this.nom_familia = nom_familia;
    }
    
    /**
     * Establece la descripción de la familia.
     * 
     * @param descripcio Descripción de la familia.
     */
    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }
    
    /**
     * Establece la fecha de alta de la familia.
     * 
     * @param data_alta_fam Fecha de alta de la familia.
     */
    public void setData_alta_fam(String data_alta_fam) {
        this.data_alta_fam = data_alta_fam;
    }
    
    /**
     * Establece el ID del proveedor asociado a la familia.
     * 
     * @param id_proveidor_fam ID del proveedor de la familia.
     */
    public void setId_proveidor_fam(int id_proveidor_fam) {
        this.id_proveidor_fam = id_proveidor_fam;
    }
    
    /**
     * Establece las observaciones adicionales sobre la familia.
     * 
     * @param Observacions Observaciones adicionales sobre la familia.
     */
    public void setObservacions(String Observacions) {
        this.Observacions = Observacions;
    }
    
    /**
     * Obtiene el ID de la familia.
     * 
     * @return ID único de la familia.
     */
    public int getId_fam() {
        return id_fam;
    }
    
    /**
     * Obtiene el nombre de la familia.
     * 
     * @return Nombre de la familia.
     */
    public String getNom_familia() {
        return nom_familia;
    }
    
    /**
     * Obtiene la descripción de la familia.
     * 
     * @return Descripción de la familia.
     */
    public String getDescripcio() {
        return descripcio;
    }
    
    /**
     * Obtiene la fecha de alta de la familia.
     * 
     * @return Fecha de alta de la familia.
     */

    public String getData_alta_fam() {
        return data_alta_fam;
    }
    
    /**
     * Obtiene el ID del proveedor asociado a la familia.
     * 
     * @return ID del proveedor de la familia.
     */
    public int getId_proveidor_fam() {
        return id_proveidor_fam;
    }
    
    /**
     * Obtiene las observaciones adicionales sobre la familia.
     * 
     * @return Observaciones adicionales de la familia.
     */
    public String getObservacions() {
        return Observacions;
    }
    
    /**
     * Representación en forma de cadena de la instancia de Familia.
     * 
     * @return Cadena de texto con los valores de todos los atributos de la familia.
     */
    @Override
    public String toString() {
        return "Familia{" + "id_fam=" + id_fam + ", nom_familia=" + nom_familia 
                + ", descripcio=" + descripcio + ", data_alta_fam=" 
                + data_alta_fam + ", id_proveidor_fam=" + id_proveidor_fam 
                + ", Observacions=" + Observacions + '}';
    }
    
    
}
