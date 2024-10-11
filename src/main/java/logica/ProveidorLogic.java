
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import model.Proveidor;
import dades.ProveidorDAO;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Clase lógica para la gestión de operaciones relacionadas con los proveedores.
 * 
 * Proporciona métodos para añadir, eliminar, modificar y obtener proveedores, 
 * interactuando con la capa de acceso a datos (DAO) y manteniendo una lista observable 
 * para facilitar la actualización de la interfaz gráfica.
 * 
 * @see ProveidorDAO
 * @see Proveidor
 * @see ObservableList
 * 
 * 
 * @throws SQLException si ocurre un error en la conexión a la base de datos.
 * @throws Exception si se intenta realizar una operación con un proveedor nulo.
 * 
 * 
 * @autor Anna
 */
public class ProveidorLogic {

    private ProveidorDAO proveidorDAO;
    ObservableList<Proveidor> llistaObservable;
    
    /**
     * Constructor que inicializa el DAO de proveedores y la lista observable de proveedores.
     *
     * @throws SQLException si hay un problema al conectar con la base de datos.
     */
    public ProveidorLogic() throws SQLException {
        this.proveidorDAO = new ProveidorDAO();
        this.llistaObservable = FXCollections.observableArrayList();
    }
    
    /**
     * Elimina un proveedor especificado.
     *
     * @param proveidor El proveedor a eliminar.
     * @throws Exception si el proveedor es nulo o no existe.
     */
    public void esborrarProveidor(Proveidor proveidor) throws Exception {
        if (proveidor == null) {
            throw new Exception("El proveïdor no existeix");
        }
        //Cridem al DAO per esborrar el proveïdor.
        proveidorDAO.delete(proveidor);  
    }
    
    /**
     * Modifica la información de un proveedor existente.
     *
     * @param proveidor El proveedor con los datos actualizados.
     * @throws Exception si ocurre un error al actualizar el proveedor.
     */
    public void modificarProveidor(Proveidor proveidor) throws Exception {
      
        proveidorDAO.update(proveidor);  
    }
    
    /**
     * Añade un proveedor a la lista observable y a la base de datos.
     *
     * @param proveidor El proveedor a añadir.
     * @throws Exception si ocurre un error al insertar el proveedor.
     */
    public void afegirProveidor(Proveidor proveidor) throws Exception {
      
        
        llistaObservable.add(proveidor);
        // Inserir el proveïdor al DAO
        proveidorDAO.insert(proveidor);
    }
    
    /**
     * Obtiene una lista de todos los proveedores de la base de datos.
     *
     * @return Lista de proveedores obtenidos de la base de datos.
     * @throws SQLException si ocurre un error al obtener los proveedores.
     */
    public List<Proveidor> getAllProveidors() throws SQLException {
        return proveidorDAO.getAll();
    }
    
    /**
     * Obtiene un conjunto de todos los IDs de proveedores existentes en la base de datos.
     *
     * @return Un conjunto con todos los IDs de proveedores.
     * @throws SQLException si ocurre un error al obtener los IDs.
     */
    public HashSet<Integer> obtenerTodosIdsProveidors() throws SQLException {
        return new HashSet<>(proveidorDAO.getAllIds());
    }
    
}
