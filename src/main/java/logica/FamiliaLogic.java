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

    public int afegirFamilia(Familia f) throws SQLException {
        // Llama al método insert en dataLayer que ahora retorna el ID generado
        int generatedId = dataLayer.insert(f);
        // Actualiza el ID de la referencia con el ID generado
        f.setId_fam(generatedId);
        // Añade la referencia con el ID a la lista observable
        llistaObservable.add(f);
        return generatedId; // Devuelve el ID generado
    }

    public void eliminarFamilia(Familia fam) throws SQLException {
        dataLayer.delete(fam);
        llistaObservable.remove(fam);
    }
    
    public boolean tieneReferencias(Familia familia) throws SQLException {
        return dataLayer.tieneReferencias(familia);
    }

    public void modificarFamilia(Familia fam) throws SQLException {
        dataLayer.update(fam);
    }
}
