/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import dades.ProveidorDAO;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import model.Proveidor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe de la lògica de negoci per gestionar proveïdors. S'encarrega de
 * manipular la llista de proveïdors i interactuar amb el DAO per dur a terme
 * operacions de consulta, inserció, modificació i eliminació.
 *
 * @author Anna
 */
public class ProveidorLogic {

    ProveidorDAO proveidorDAO;
    ObservableList<Proveidor> llistaObservable;

    /**
     * Constructor que inicialitza el DAO de proveïdors i una llista observable.
     *
     * @throws SQLException si es produeix un error de base de dades en
     * inicialitzar el DAO.
     */
    public ProveidorLogic() throws SQLException {
        this.proveidorDAO = new ProveidorDAO();
        this.llistaObservable = FXCollections.observableArrayList();
        carregarProveidor();
    }

    
    public void carregarProveidor() throws SQLException {
        this.llistaObservable.setAll(proveidorDAO.getAll());
    }
    /**
     * Obtiene la lista observable de referencias.
     * 
     * @return Lista observable de referencias.
     */
    public ObservableList<Proveidor> getListObservableProveidor() {
        return llistaObservable;
    }
    /**
     * Esborra un proveïdor proporcionat de la base de dades.
     *
     * @param proveidor El proveïdor que es vol esborrar.
     * @throws Exception si el proveïdor proporcionat és null o si es produeix
     * un error durant l'operació.
     */
    public void esborrarProveidor(Proveidor proveidor) throws Exception {
        //Cridem al DAO per esborrar el proveïdor.
        proveidorDAO.delete(proveidor);
        llistaObservable.remove(proveidor);
    }

    /**
     * Modifica un proveïdor existent a la base de dades després de validar les
     * seves dades.
     *
     * @param proveidor El proveïdor que es vol modificar amb les noves dades.
     * @throws Exception Si qualsevol de les validacions falla o si es produeix
     * un error en l'actualització.
     */
    public void modificarProveidor(Proveidor proveidor) throws Exception {
        proveidorDAO.update(proveidor);
    }

    /**
     * Afegeix un nou proveïdor a la llista observable i a la base de dades
     * després de validar les seves dades.
     *
     * @param proveidor El proveïdor que es vol afegir.
     * @throws Exception Si qualsevol de les validacions falla o si es produeix
     * un error durant l'operació.
     */
    public void afegirProveidor(Proveidor proveidor) throws Exception {
        // Inserir el proveïdor al DAO
        proveidorDAO.insert(proveidor);
        llistaObservable.add(proveidor);
        
        
    }
    /**
     * Retorna una llista amb tots els proveïdors emmagatzemats a la base de
     * dades.
     *
     * @return Llista de tots els proveïdors.
     * @throws SQLException Si es produeix un error durant la consulta a la base
     * de dades.
     */
    public List<Proveidor> getAllProveidors() throws SQLException {
        return proveidorDAO.getAll();
    }
    /**
     * Retorna un conjunt de tots els identificadors (IDs) dels proveïdors
     * emmagatzemats a la base de dades.
     *
     * @return Un conjunt (HashSet) amb els IDs de tots els proveïdors.
     * @throws SQLException Si es produeix un error durant la consulta a la base
     * de dades.
     */
    public HashSet<Integer> obtenerTodosIdsProveidors() throws SQLException {
        return new HashSet<>(proveidorDAO.getAllIds());
    }

}
