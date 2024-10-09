/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;


import dades.ReferenciaDAO;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author chris
 */
public class ReferenciaLogica {
    
    ReferenciaDAO dataLayer;
    
    
    ObservableList<Referencia> llistaObservable;


    public ReferenciaLogica() throws SQLException {
        this.dataLayer = new ReferenciaDAO();
        this.llistaObservable = FXCollections.observableArrayList();
        carregarReferencia();
    }
    
    public void carregarReferencia() throws SQLException{
        this.llistaObservable.setAll(dataLayer.getAll());
    }
    
    public void afegirReferencia(Referencia r) throws SQLException{
        dataLayer.insert(r);
        llistaObservable.add(r);
    }
    
    public void eliminarReferencia(Referencia ref) throws SQLException{
        dataLayer.delete(ref);
        llistaObservable.remove(ref);
    }
    
    public void modificarReferencia(Referencia ref) throws SQLException{
        dataLayer.update(ref);
    }
    
    public ObservableList<Referencia> getListObservableReferencia(){
        return llistaObservable;
    }
    
    
    public static boolean FechaValida(String fecha) {
        // Expresión regular para yyyy-MM-dd
        Boolean ret = false;
        Pattern patronFecha = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        // Crear patrón
        if (patronFecha.matcher(fecha).find()) {ret = true;}
       
        return ret;
    }
    
    public static boolean PreuValid(String preu) {
        // Expresión regular para yyyy-MM-dd
        Boolean ret = false;
        Pattern patronPreu = Pattern.compile("^\\d+(\\.\\d{1,2})?$");
        // Crear patrón
        if (patronPreu.matcher(preu).find()) {ret = true;}
       
        return ret;
    }
    public static boolean unitatMidaValid(String unitat) {
        // Expresión regular para yyyy-MM-dd
        Boolean ret = false;
        Pattern patronPreu = Pattern.compile("^unitats$");
        // Crear patrón
        if (patronPreu.matcher(unitat).find()) {ret = true;}
       
        return ret;
    }
    
}
