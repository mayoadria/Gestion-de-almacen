/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import dades.FamiliaDAO;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Familia;

/**
 *
 * @author mayoa
 */
public class FamiliaLogic {
    FamiliaDAO dataLayer;

    ObservableList<Familia> llistaObservable;
    
    public FamiliaLogic() throws SQLException {
        this.dataLayer = new FamiliaDAO();
        this.llistaObservable = FXCollections.observableArrayList();
        carregarFamilia();
    }
    
    public void carregarFamilia() throws SQLException {
        this.llistaObservable.setAll(dataLayer.getAll());
    }
    
    public ObservableList<Familia> getListObservableFamilla() {
        return llistaObservable;
    }
    
    public void afegirFamilia(Familia fam) throws SQLException {
        dataLayer.insert(fam);
        llistaObservable.add(fam);
    }
}
