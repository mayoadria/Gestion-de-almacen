/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;


import model.Referencia;
import dades.ReferenciaDAO;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Clase que maneja la lógica de negocio relacionada con las referencias de
 * producto. Esta clase actúa como intermediaria entre la capa de datos
 * (ReferenciaDAO) y la interfaz de usuario, permitiendo realizar operaciones
 * sobre las referencias y gestionando una lista observable de ellas.
 *
 * @author chris
 */
public class ReferenciaLogica {

    ReferenciaDAO dataLayer;

    ObservableList<Referencia> llistaObservable;


    /**
     * Constructor por defecto de la clase ReferenciaLogica.
     * Inicializa la capa de datos y la lista observable de referencias,
     * y carga las referencias desde la base de datos.
     * 
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public ReferenciaLogica() throws SQLException {
        this.dataLayer = new ReferenciaDAO();
        this.llistaObservable = FXCollections.observableArrayList();
        carregarReferencia();
    }
    
    /**
     * Carga todas las referencias desde la capa de datos y las añade a 
     * la lista observable.
     * 
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public void carregarReferencia() throws SQLException {
        this.llistaObservable.setAll(dataLayer.getAll());
    }
    
    /**
     * Agrega una nueva referencia a la base de datos y a la lista observable.
     * 
     * @param t La referencia que se desea agregar.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public void afegirReferencia(Referencia ref) throws SQLException {
        dataLayer.insert(ref);
        llistaObservable.add(ref);
    }
    /**
     * Agrega una nueva referencia a la base de datos y a la lista observable.
     * 
     * @param r La referencia que se desea agregar.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    
    
    /**
     * Elimina una referencia de la base de datos y de la lista observable.
     * 
     * @param ref La referencia que se desea eliminar.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public void eliminarReferencia(Referencia ref) throws SQLException {
        dataLayer.delete(ref);
        llistaObservable.remove(ref);
    }
    
    /**
     * Modifica una referencia existente en la base de datos.
     * 
     * @param ref La referencia que se desea modificar.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public void modificarReferencia(Referencia ref) throws SQLException {
        dataLayer.update(ref);
    }
    
    /**
     * Obtiene la lista observable de referencias.
     * 
     * @return Lista observable de referencias.
     */
    public ObservableList<Referencia> getListObservableReferencia() {
        return llistaObservable;
    }
    
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
    

}
