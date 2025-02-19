/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Familia;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static logica.Mensajes.mostrarMensaje;
import static logica.Mensajes.mostrarMensajeError;

/**
 * La classe {@code FamiliaDAO} gestiona les operacions d'accés a les dades
 * relacionades amb les famílies a la base de dades. Implementa les operacions
 * bàsiques de CRUD (crear, llegir, actualitzar i eliminar) per a l'entitat Família.
 * 
 * @author oriol
 */

public class FamiliaDAO implements DAOInterface<Familia> {

    public FamiliaDAO() throws SQLException {
        super();
    }
    /**
     * Obté totes les famílies de la base de dades.
     * 
     * @return Una llista amb totes les famílies de la base de dades.
     * @throws SQLException Si hi ha un error en obtenir les dades de la base de dades.
     */

    @Override
    public List<Familia> getAll() throws SQLException {
        //Crear una llista per poder obtenir totes les referencies existents a la base de dades
        List<Familia> ret = new ArrayList<>();
        //Fer la consulta
        String select = "select * from Families ";
        try (Connection conn = MyDataSource.getConnection(); PreparedStatement sentencia = conn.prepareStatement(select)) {

            ResultSet rs = sentencia.executeQuery();
            //Mostrar los datos
            while (rs.next()) {
                Familia r = new Familia();
                r.setId_fam(rs.getInt("id_familia"));
                r.setNom_familia(rs.getString("nom_familia"));
                r.setDescripcio(rs.getString("descripcio"));
                r.setData_alta_fam(rs.getString("data_alta"));
                r.setId_proveidor_fam(rs.getInt("id_proveidor_defecte"));
                r.setObservacions(rs.getString("observacions"));

                ret.add(r);

            }

        } catch (SQLException e) {
            throw e;
        }
        return ret;
    }
    /**
     * Insereix una nova família a la base de dades.
     * 
     * @param t L'objecte Família que es vol inserir.
     * @return L'ID de la nova família inserida.
     * @throws SQLException Si hi ha un error en inserir la família a la base de dades.
     */

    @Override
    public int insert(Familia t) throws SQLException {
        String insert = "INSERT INTO Families (nom_familia, descripcio, data_alta, id_proveidor_defecte, observacions) VALUES (?, ?, ?, ?, ?)";
        //Hacer la conexion con la base y marcar de donde tienen que obtener los datos
        try (Connection conn = MyDataSource.getConnection();PreparedStatement sentencia = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            sentencia.setString(1, t.getNom_familia());
            sentencia.setString(2, t.getDescripcio());
            sentencia.setString(3, String.valueOf(t.getData_alta_fam()));
            sentencia.setInt(4, (int) t.getId_proveidor_fam());
            sentencia.setString(5, t.getObservacions());

            // Ejecutar la inserción
            int affectedRows = sentencia.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Error en inserir la família, no es va crear cap registre.");
            }
            // Obtener el ID generado
            try (ResultSet generatedKeys = sentencia.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Retorna el ID generado
                } else {
                    throw new SQLException("Error en inserir la família, no s'ha obtingut l'ID.");
                }
            }
        } catch (SQLException e) {
            mostrarMensajeError("Error en inserir la família:" + e.getMessage());
            throw e;
        }
    }
    /**
     * Actualitza una família existent a la base de dades.
     * 
     * @param t L'objecte Família amb les noves dades a actualitzar.
     * @throws SQLException Si hi ha un error en actualitzar la família a la base de dades.
     */

    @Override
    public void update(Familia t) throws SQLException {
        //Hacer la sentencia del update
        String update = "UPDATE Families SET nom_familia = ?, descripcio = ?, data_alta = ?, id_proveidor_defecte = ?, observacions = ? WHERE id_familia = ?";

        try (Connection conn = MyDataSource.getConnection();PreparedStatement sentencia =conn.prepareStatement(update)) {
            //Hacer la conexion con la base y marcar de donde tienen que obtener los datos        
            sentencia.setString(1, t.getNom_familia());
            sentencia.setString(2, t.getDescripcio());
            sentencia.setString(3, t.getData_alta_fam());
            sentencia.setInt(4, (int) t.getId_proveidor_fam());
            sentencia.setString(5, t.getObservacions());
            sentencia.setInt(6, t.getId_fam());  // Condición para encontrar el registro a actualizar (id_referencia)

            // Ejecutamos la sentencia
            int rowsUpdated = sentencia.executeUpdate();
            if (rowsUpdated > 0) {
                mostrarMensaje("La família ha estat actualitzada amb èxit.");
            } else {
                mostrarMensajeError("No s'ha trobat cap família amb ID proporcionat.");
            }
        } catch (SQLException e) {
            mostrarMensajeError("Error en actualitzar la família:" + e.getMessage());
            throw e;
        }
    }

    /**
     * Elimina una família de la base de dades.
     * 
     * @param t L'objecte Família que es vol eliminar.
     * @throws SQLException Si hi ha un error en eliminar la família a la base de dades.
     */

    @Override
    public void delete(Familia t) throws SQLException {
        String delete = "DELETE FROM Families WHERE id_familia = ?";

        try (Connection conn = MyDataSource.getConnection();PreparedStatement sentencia =conn.prepareStatement(delete)) {
            // Configuramos el valor del id_referencia para eliminar
            sentencia.setInt(1, t.getId_fam());

            // Ejecutamos la eliminación
            int rowsDeleted = sentencia.executeUpdate();
            if (rowsDeleted > 0) {
                mostrarMensaje("La Família ha estat eliminada amb èxit.");
            } else {
                mostrarMensajeError("No s'ha trobat cap família amb ID proporcionat.");
            }
        } catch (SQLException e) {
            mostrarMensajeError("Error en eliminar la família:" + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Comprova si una família té referències associades.
     * 
     * @param familia La família per verificar.
     * @return {@code true} si la família té referències associades, {@code false} si no.
     * @throws SQLException Si hi ha un error en consultar la base de dades.
     */

     public boolean tieneReferencias(Familia familia) throws SQLException {
        String query = "SELECT COUNT(*) FROM Referencies WHERE id_familia = ?";

        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, familia.getId_fam());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Retorna true si hay referencias asociadas
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al verificar referencias asociadas: " + e.getMessage(), e);
        }
        return false;
    }
    /**
     * Obté una família per ID.
     * 
     * @param t L'objecte Família amb l'ID a buscar.
     * @return L'objecte Família corresponent a l'ID especificat, o {@code null} si no existeix.
     * @throws SQLException Si hi ha un error en obtenir la família a la base de dades.
     */

    @Override
    public Familia get(Familia t) throws SQLException {
        String select = "SELECT * FROM Families WHERE id_familia = ?";
        Familia f = null;

        // Uso correcto de try-with-resources para PreparedStatement y ResultSet
        try (Connection conn = MyDataSource.getConnection();PreparedStatement sentencia =conn.prepareStatement(select)) {
            sentencia.setInt(1, t.getId_fam());
            try (ResultSet rs = sentencia.executeQuery()) {
                if (rs.next()) {
                    f = new Familia();
                    f.setId_fam(rs.getInt("id_familia"));
                    f.setNom_familia(rs.getString("nom_familia"));
                    f.setDescripcio(rs.getString("descripcio"));
                    f.setData_alta_fam(rs.getString("data_alta"));
                    f.setId_proveidor_fam(rs.getInt("id_proveidor_defecte"));
                    f.setObservacions(rs.getString("observacions"));
                }
            } catch (SQLException e) {
                mostrarMensajeError("Error al obtener la familia:" + e.getMessage());
                throw e;
            }
        } catch (SQLException e) {
            mostrarMensajeError("Error al obtener la familia:" + e.getMessage());
            throw e;
        }

        return f;
    }


    /**
     * Comprova si existeix un proveïdor associat a una família.
     * 
     * @param idFamilia L'ID de la família a verificar.
     * @return {@code true} si existeix un proveïdor, {@code false} si no.
     * @throws SQLException Si hi ha un error en consultar la base de dades.
     */

    public boolean existeProveedor(int idProveedor) throws SQLException {
        if (idProveedor == 0) {
            return true; // 0 es válido como valor predeterminado
        }
        String selectProveidor = "SELECT COUNT(*) FROM Proveidors WHERE id_proveidor = ?";
        try (Connection conn = MyDataSource.getConnection();PreparedStatement stmt =conn.prepareStatement(selectProveidor)) {
            stmt.setInt(1, idProveedor);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Devuelve true si existe, false si no
            }
        }
        return false;
    }
}
