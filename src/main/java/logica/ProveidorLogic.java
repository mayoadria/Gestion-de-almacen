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
 * Classe de la lògica de negoci per gestionar proveïdors. S'encarrega de
 * manipular la llista de proveïdors i interactuar amb el DAO per dur a terme
 * operacions de consulta, inserció, modificació i eliminació.
 *
 * @author Anna
 */
public class ProveidorLogic {

    private ProveidorDAO proveidorDAO;
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
    }

    /**
     * Esborra un proveïdor proporcionat de la base de dades.
     *
     * @param proveidor El proveïdor que es vol esborrar.
     * @throws Exception si el proveïdor proporcionat és null o si es produeix
     * un error durant l'operació.
     */
    public void esborrarProveidor(Proveidor proveidor) throws Exception {
        if (proveidor == null) {
            throw new Exception("El proveïdor no existeix");
        }
        //Cridem al DAO per esborrar el proveïdor.
        proveidorDAO.delete(proveidor);
    }

    /**
     * Modifica la informació d'un proveïdor existent a la base de dades.
     *
     * @param proveidor El proveïdor amb la informació modificada.
     * @throws Exception si es produeix un error durant l'operació.
     */
    public void modificarProveidor(Proveidor proveidor) throws Exception {

        proveidorDAO.update(proveidor);
    }

    /**
     * Afegeix un nou proveïdor a la llista observable i a la base de dades.
     *
     * @param proveidor El proveïdor que es vol afegir.
     * @throws Exception si es produeix un error durant l'operació.
     */
    public void afegirProveidor(Proveidor proveidor) throws Exception {

        llistaObservable.add(proveidor);
        // Inserir el proveïdor al DAO
        proveidorDAO.insert(proveidor);
    }
}
