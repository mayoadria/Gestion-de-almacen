/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import dades.ProveidorDAO;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Anna
 */
public class ProveidorLogic {

    private ProveidorDAO proveidorDAO;
    ObservableList<Proveidor> llistaObservable;

    public ProveidorLogic() throws SQLException {
        this.proveidorDAO = new ProveidorDAO();
        this.llistaObservable = FXCollections.observableArrayList();
    }

    public void esborrarProveidor(Proveidor proveidor) throws Exception {
        if (proveidor == null) {
            throw new Exception("El proveïdor no existeix");
        }
        //Cridem al DAO per esborrar el proveïdor.
        proveidorDAO.delete(proveidor);  
    }
    
    public void modificarProveidor(Proveidor proveidor) throws Exception {
      
        proveidorDAO.update(proveidor);  
    }
    

    public void afegirProveidor(Proveidor proveidor) throws Exception {
      
        
        llistaObservable.add(proveidor);
        // Inserir el proveïdor al DAO
        proveidorDAO.insert(proveidor);
    }
}
