/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import dades.MyDataSource;
import dades.ReferenciaDAO;
import java.sql.SQLException;
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
     * @param r La referencia que se desea agregar.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public void afegirReferencia(Referencia r) throws SQLException {
        Referencia ref = new Referencia();

        dataLayer.insert(ref);
        llistaObservable.add(ref);
    }
    
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

}
