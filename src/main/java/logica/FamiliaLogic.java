package logica;

import dades.FamiliaDAO;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Familia;

/**
 * Clase que gestiona la lógica relacionada con las familias.
 * Proporciona métodos para cargar, agregar, eliminar, modificar y obtener familias.
 * Utiliza la capa de datos (FamiliaDAO) para interactuar con la base de datos.
 * 
 * @author mayoa
 */
public class FamiliaLogic {

    // Capa de acceso a datos para la entidad Familia
    FamiliaDAO dataLayer;

    // Lista observable que mantiene las familias para ser visualizadas en la UI
    ObservableList<Familia> llistaObservable;

    /**
     * Constructor de la clase FamiliaLogic.
     * Inicializa la capa de acceso a datos y carga las familias desde la base de datos.
     * 
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public FamiliaLogic() throws SQLException {
        this.dataLayer = new FamiliaDAO();
        this.llistaObservable = FXCollections.observableArrayList();
        carregarFamilia(); // Carga las familias desde la base de datos
    }

    /**
     * Carga las familias desde la base de datos y las almacena en la lista observable.
     * 
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public void carregarFamilia() throws SQLException {
        this.llistaObservable.setAll(dataLayer.getAll());
    }

    /**
     * Obtiene la lista observable de familias.
     * 
     * @return La lista observable de familias.
     */
    public ObservableList<Familia> getListObservableFamilla() {
        return llistaObservable;
    }

    /**
     * Agrega una nueva familia a la base de datos y a la lista observable.
     * 
     * @param f La familia a agregar.
     * @return El ID generado para la nueva familia.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public int afegirFamilia(Familia f) throws SQLException {
        // Llama al método insert en dataLayer que ahora retorna el ID generado
        int generatedId = dataLayer.insert(f);
        // Actualiza el ID de la familia con el ID generado
        f.setId_fam(generatedId);
        // Añade la familia con el ID a la lista observable
        llistaObservable.add(f);
        return generatedId; // Devuelve el ID generado
    }

    /**
     * Elimina una familia de la base de datos y de la lista observable.
     * 
     * @param fam La familia a eliminar.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public void eliminarFamilia(Familia fam) throws SQLException {
        dataLayer.delete(fam);
        llistaObservable.remove(fam);
    }

    /**
     * Verifica si una familia tiene referencias asociadas en la base de datos.
     * 
     * @param familia La familia a verificar.
     * @return true si la familia tiene referencias, false si no.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public boolean tieneReferencias(Familia familia) throws SQLException {
        return dataLayer.tieneReferencias(familia);
    }

    /**
     * Modifica los datos de una familia existente en la base de datos.
     * 
     * @param fam La familia con los datos actualizados.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public void modificarFamilia(Familia fam) throws SQLException {
        dataLayer.update(fam);
    }
}
