package dades;

import model.Proveidor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.collections.ObservableList;
import static logica.Mensajes.mostrarMensaje;
import static logica.Mensajes.mostrarMensajeError;

/**
 * Classe per gestionar les operacions CRUD (Crear, Llegir, Actualitzar, Esborrar)
 * dels proveïdors a la base de dades. Implementa la interfície DAOInterface
 * per aplicar el patró DAO.
 * 
 * @author Anna
 */
public class ProveidorDAO implements DAOInterface<Proveidor> {

    ObservableList<Proveidor> llistaObservableProveidor;

    /**
     * Constructor de la classe {@code ProveidorDAO}.
     * Aquest mètode recull els errors de connexió que es poden produir
     * en establir la connexió amb la base de dades.
     * 
     * @throws SQLException Si hi ha un error al connectar amb la base de dades.
     */
    public ProveidorDAO() throws SQLException {
        super();
    }

    /**
     * Obté tots els proveïdors de la base de dades.
     * 
     * @return Una llista amb tots els proveïdors de la base de dades.
     * @throws SQLException Si hi ha un error al recuperar les dades de la base de dades.
     */
    @Override
    public List<Proveidor> getAll() throws SQLException {
        List<Proveidor> ret = new ArrayList<>();

        // Comanda en SQL per obtenir tots els proveïdors
        String consulta = "select * from Proveidors";
        try (Connection conn = MyDataSource.getConnection(); PreparedStatement pstm = conn.prepareStatement(consulta)) {
            ResultSet res = pstm.executeQuery();

            // Bucle per recollir les dades dels diferents proveïdors
            while (res.next()) {
                Proveidor p = new Proveidor();
                p.setId_proveidor(res.getInt("id_proveidor"));
                p.setNom_proveidor(res.getString("nom_proveidor"));
                p.setCif(res.getString("cif"));

                boolean estat = res.getBoolean("actiu"); // Agafem el valor de la BBDD en boolean
                p.setActiu(estat);

                p.setMotiu_inactiu(res.getString("motiu_inactiu"));
                p.setData_creacio(res.getString("data_creacio"));
                p.setCorreu_electronic(res.getString("correu_electronic"));
                p.setRating_proveidor(res.getFloat("rating_proveidor"));
                p.setMesos_de_colaboracio(res.getInt("mesos_de_colaboracio"));

                ret.add(p);
            }
        } catch (SQLException e) {
            throw e;
        }
        return ret;
    }

    /**
     * Insereix un nou proveïdor a la base de dades.
     * 
     * @param t L'objecte Proveidor que es vol inserir.
     * @return L'ID del nou proveïdor inserit.
     * @throws SQLException Si hi ha un error en inserir el proveïdor a la base de dades.
     */
    @Override
    public int insert(Proveidor t) throws SQLException {
        // Consulta SQL per inserir nous proveïdors
        String consultaInsert = "INSERT INTO Proveidors(nom_proveidor, cif, actiu"
                + ", motiu_inactiu, data_creacio, correu_electronic, rating_proveidor, mesos_de_colaboracio) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection conn = MyDataSource.getConnection(); PreparedStatement sentencia = conn.prepareStatement(consultaInsert, Statement.RETURN_GENERATED_KEYS)) {

            sentencia.setString(1, t.getNom_proveidor());
            sentencia.setString(2, t.getCif());
            sentencia.setBoolean(3, t.isActiu());  // Camp booleà 'actiu'
            sentencia.setString(4, t.getMotiu_inactiu());
            sentencia.setString(5, t.getData_creacio());
            sentencia.setString(6, t.getCorreu_electronic());
            sentencia.setFloat(7, t.getRating_proveidor());
            sentencia.setInt(8, t.getMesos_de_colaboracio());

            // Executar la inserció
            int affectedRows = sentencia.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Error en inserir el proveïdor, no es va crear cap registre.");
            }
            // Obtenir l'ID generat
            try (ResultSet generatedKeys = sentencia.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Retorna l'ID generat
                } else {
                    throw new SQLException("Error en inserir el proveïdor, no s'ha obtingut l'ID.");
                }
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Actualitza un proveïdor existent a la base de dades.
     * 
     * @param t L'objecte Proveidor amb les noves dades a actualitzar.
     * @throws SQLException Si hi ha un error al modificar el proveïdor a la base de dades.
     */
    @Override
    public void update(Proveidor t) throws SQLException {
        // Consulta SQL per actualitzar un proveïdor existent
        String consultaUpdate = "UPDATE Proveidors SET  nom_proveidor = ?, cif = ?, actiu = ?"
                + ", motiu_inactiu = ?, data_creacio = ?, correu_electronic = ?, rating_proveidor = ?, mesos_de_colaboracio = ? WHERE id_proveidor = ?";

        try (Connection conn = MyDataSource.getConnection(); PreparedStatement sentencia = conn.prepareStatement(consultaUpdate)) {

            sentencia.setString(1, t.getNom_proveidor());
            sentencia.setString(2, t.getCif());
            sentencia.setBoolean(3, t.isActiu());
            sentencia.setString(4, t.getMotiu_inactiu());
            sentencia.setString(5, t.getData_creacio());
            sentencia.setString(6, t.getCorreu_electronic());
            sentencia.setFloat(7, t.getRating_proveidor());
            sentencia.setInt(8, t.getMesos_de_colaboracio());
            sentencia.setInt(9, t.getId_proveidor());

            // Executem l'actualització del proveïdor
            int rowsUpdated = sentencia.executeUpdate();
            if (rowsUpdated > 0) {
                mostrarMensaje("S'ha actualitzat el proveïdor exitosament.");
            } else {
                mostrarMensajeError("No s'ha trobat cap proveïdor amb l'ID proporcionat.");
            }

        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Elimina un proveïdor de la base de dades.
     * 
     * @param t L'objecte Proveidor que es vol eliminar.
     * @throws SQLException Si hi ha un error al eliminar el proveïdor a la base de dades.
     */
    @Override
    public void delete(Proveidor t) throws SQLException {
        // Consulta SQL per eliminar un proveïdor existent
        String consultaDelete = "Delete FROM Proveidors WHERE id_proveidor = ?";

        try (Connection conn = MyDataSource.getConnection(); PreparedStatement sentencia = conn.prepareStatement(consultaDelete)) {

            sentencia.setInt(1, t.getId_proveidor());

            int rowsDeleted = sentencia.executeUpdate();
            if (rowsDeleted > 0) {
                mostrarMensaje("El proveïdor ha estat eliminat correctament.");
            } else {
                mostrarMensajeError("No s'ha trobat cap proveïdor amb l'ID proporcionat.");
            }
        } catch (SQLException e) {
            mostrarMensajeError("Error en eliminar el proveïdor: " + e.getMessage());
        }
    }

    /**
     * Comprova si un proveïdor té referències associades a la base de dades.
     * 
     * @param proveidor L'objecte Proveidor a verificar.
     * @return {@code true} si el proveïdor té referències associades, {@code false} si no.
     * @throws SQLException Si hi ha un error al consultar la base de dades.
     */
    public boolean tieneReferencias(Proveidor proveidor) throws SQLException {
        String query = "SELECT COUNT(*) FROM Referencies WHERE id_proveidor = ?";

        try (Connection conn = MyDataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, proveidor.getId_proveidor());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Retorna true si hi ha referències associades
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al verificar les referències associades: " + e.getMessage(), e);
        }
        return false;
    }

    /**
     * Obté un proveïdor per ID.
     * 
     * @param t L'objecte Proveidor amb l'ID a buscar.
     * @return El proveïdor amb l'ID indicat, o {@code null} si no s'ha trobat cap registre.
     * @throws SQLException Si hi ha un error al consultar la base de dades.
     */
    @Override
    public Proveidor get(Proveidor t) throws SQLException {
        String consultaSeleccionar = "SELECT * FROM Proveidors WHERE id_proveidor = ?";
        Proveidor p = null;

        // Usant try-with-resources per ResultSet
        try (Connection conn = MyDataSource.getConnection(); PreparedStatement sentencia = conn.prepareStatement(consultaSeleccionar); ResultSet res = sentencia.executeQuery()) {

            if (res.next()) {
                p = new Proveidor();
                p.setId_proveidor(res.getInt("id_proveidor"));
                p.setNom_proveidor(res.getString("nom_proveidor"));
                p.setCif(res.getString("cif"));
                p.setActiu(res.getBoolean("actiu"));
                p.setMotiu_inactiu(res.getString("motiu_inactiu"));
                p.setData_creacio(res.getString("data_creacio"));
                p.setCorreu_electronic(res.getString("correu_electronic"));
                p.setRating_proveidor(res.getFloat("rating_proveidor"));
                p.setMesos_de_colaboracio(res.getInt("mesos_de_colaboracio"));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtenir el proveïdor", e);
        }
        return p;
    }

    /**
     * Obté tots els IDs dels proveïdors existents a la base de dades.
     * 
     * @return Un conjunt {@code Set<Integer>} amb tots els IDs dels proveïdors.
     * @throws SQLException Si hi ha un error al consultar la base de dades.
     */
    public Set<Integer> getAllIds() throws SQLException {
        Set<Integer> idsExistents = new HashSet<>();
        String sql = "SELECT id_proveidor FROM proveidors";

        try (Connection conn = MyDataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            // Itera sobre els resultats i afegeix els IDs.
            while (rs.next()) {
                idsExistents.add(rs.getInt("id_proveidor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error en obtenir els ID dels proveïdors.");
        }

        return idsExistents;
    }

}
