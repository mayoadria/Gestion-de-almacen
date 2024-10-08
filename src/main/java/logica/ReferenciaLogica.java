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
 *
 * @author chris
 */
public class ReferenciaLogica {
    
    ReferenciaDAO dataLayer;
    
    
    ObservableList<Referencia> llistaObservable;

//    public ReferenciaLogica(ReferenciaDAO dataLayer, ObservableList<Referencia> llistaObservable) {
//    }

    public ReferenciaLogica() throws SQLException {
        this.dataLayer = new ReferenciaDAO();
        this.llistaObservable = FXCollections.observableArrayList();
        carregarReferencia();
    }
    
    public void carregarReferencia() throws SQLException{
        this.llistaObservable.setAll(dataLayer.getAll());
    }
    
    public void afegirReferencia(Referencia r) throws SQLException{
        Referencia ref = new Referencia();
        
        dataLayer.insert(ref);
        llistaObservable.add(ref);
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
    
    
    
}
