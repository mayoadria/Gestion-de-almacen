/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import java.sql.Connection;
import model.Referencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import logica.Mensajes;

/**
 * Clase para gestionar el acceso a datos de Referencia en la base de datos.
 * Implementa operaciones CRUD para la entidad Referencia.
 *
 * @autor mayoa
 */
public class ReferenciaDAO extends Mensajes implements DAOInterface<Referencia> {

    /**
     * Constructor de ReferenciaDAO. Establece la conexión inicial a la base de
     * datos.
     *
     * @throws SQLException en caso de errores de conexión.
     */
    public ReferenciaDAO() throws SQLException {
        super();
    }

    /**
     * Obtiene todas las referencias de la base de datos.
     *
     * @return una lista de todas las referencias almacenadas.
     * @throws SQLException en caso de error al ejecutar la consulta.
     */
    @Override
    public List<Referencia> getAll() throws SQLException {
        //Crear una llista per poder obtenir totes les referencies existents a la base de dades
        List<Referencia> ret = new ArrayList<>();
        //Fer la consulta
        String select = "select * from Referencies ";
        try (Connection conn = MyDataSource.getConnection(); PreparedStatement sentencia = conn.prepareStatement(select);) {
            ResultSet rs = sentencia.executeQuery();
            //Mostrar los datos
            while (rs.next()) {
                Referencia r = new Referencia();
                r.setId(rs.getInt("id_referencia"));
                r.setNom(rs.getString("nom_producte"));
                r.setQuantitat((int) rs.getDouble("quantitat"));
                r.setUnitat_mida(rs.getString("unitat_mida"));
                r.setData_alta(rs.getString("data_alta"));
                r.setData_fabricacio(rs.getString("data_fabricacio"));
                r.setDescripcio(rs.getString("descripcio_producte"));
                r.setPreu(rs.getString("preu"));
                r.setUnitats_venudes(rs.getInt("unitats_venudes"));
                r.setId_fam(rs.getInt("id_familia"));
                r.setId_proveidor(rs.getInt("id_proveidor"));

                ret.add(r);
            }
        } catch (SQLException e) {
            throw e;
        }
        return ret;
    }

    /**
     * Inserta una nueva referencia en la base de datos.
     *
     * @param t referencia a insertar en la base de datos.
     * @throws SQLException en caso de error al ejecutar la sentencia SQL.
     */
    @Override
    public int insert(Referencia t) throws SQLException {
        //Hacer la sentencia del insert
        String insert = "INSERT INTO Referencies (nom_producte, quantitat, unitat_mida, data_alta, data_fabricacio, descripcio_producte, preu, unitats_venudes, id_familia, id_proveidor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        //Hacer la conexion con la base y marcar de donde tienen que obtener los datos
        try (Connection conn = MyDataSource.getConnection();PreparedStatement sentencia =conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            sentencia.setString(1, t.getNom());
            sentencia.setInt(2, (int) t.getQuantitat());
            sentencia.setString(3, t.getUnitat_mida());
            sentencia.setString(4, t.getData_alta());
            sentencia.setString(5, t.getData_fabricacio());
            sentencia.setString(6, t.getDescripcio());
            sentencia.setString(7, t.getPreu());
            sentencia.setInt(8, t.getUnitats_venudes());
            sentencia.setInt(9, t.getId_fam());
            sentencia.setInt(10, t.getId_proveidor());

            // Ejecutar la inserción
            int affectedRows = sentencia.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Error en inserir la referència, no es va crear cap registre.");
            }
            // Obtener el ID generado
            try (ResultSet generatedKeys = sentencia.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Retorna el ID generado
                } else {
                    throw new SQLException("Error al insertar la referencia, no se obtuvo el ID.");
                }
            }
        } catch (SQLException e) {
            mostrarMensajeError("Error en inserir la referència: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Actualiza los datos de una referencia existente en la base de datos.
     *
     * @param t referencia con los datos actualizados.
     * @throws SQLException en caso de error al ejecutar la sentencia SQL.
     */
    @Override
    public void update(Referencia t) throws SQLException {
        //Hacer la sentencia del update
        String update = "UPDATE Referencies SET nom_producte = ?, quantitat = ?, unitat_mida = ?, data_alta = ?, data_fabricacio = ?, descripcio_producte = ?, preu = ?, unitats_venudes = ?, id_familia = ?, id_proveidor = ? WHERE id_referencia = ?";

        try (Connection conn = MyDataSource.getConnection();PreparedStatement sentencia =conn.prepareStatement(update)) {
            //Hacer la conexion con la base y marcar de donde tienen que obtener los datos        
            sentencia.setString(1, t.getNom());
            sentencia.setInt(2, (int) t.getQuantitat());
            sentencia.setString(3, t.getUnitat_mida());
            sentencia.setString(4, t.getData_alta());
            sentencia.setString(5, t.getData_fabricacio());
            sentencia.setString(6, t.getDescripcio());
            sentencia.setString(7, t.getPreu());
            sentencia.setInt(8, t.getUnitats_venudes());
            sentencia.setInt(9, t.getId_fam());
            sentencia.setInt(10, t.getId_proveidor());
            sentencia.setInt(11, t.getId());  // Condición para encontrar el registro a actualizar (id_referencia)

            // Ejecutamos la sentencia
            int rowsUpdated = sentencia.executeUpdate();
            if (rowsUpdated < 0) {
                mostrarMensajeError("No s'ha trobat cap referència a l'ID proporcionat.");
            }
        } catch (SQLException e) {
            mostrarMensajeError("Error en actualitzar la referència:" + e.getMessage());
            throw e;
        }
    }

    /**
     * Elimina una referencia de la base de datos.
     *
     * @param t referencia a eliminar.
     * @throws SQLException en caso de error al ejecutar la sentencia SQL.
     */
    @Override
    public void delete(Referencia t) throws SQLException {
        //Hacer la conexion con la base y marcar de donde tienen que obtener los datos        
        String delete = "DELETE FROM Referencies WHERE id_referencia = ?";

        try (Connection conn = MyDataSource.getConnection();PreparedStatement sentencia =conn.prepareStatement(delete)) {
            // Configuramos el valor del id_referencia para eliminar
            sentencia.setInt(1, t.getId());

            // Ejecutamos la eliminación
            int rowsDeleted = sentencia.executeUpdate();
            if (rowsDeleted > 0) {
                mostrarMensaje("La referència ha estat eliminada amb èxit.");
            } else {
                mostrarMensajeError("No s'ha trobat cap referència a l'ID proporcionat.");
            }
        } catch (SQLException e) {
            mostrarMensajeError("Error en eliminar la referència: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene una referencia específica de la base de datos según su ID.
     *
     * @param t referencia con el ID a buscar.
     * @return la referencia encontrada, o null si no existe.
     * @throws SQLException en caso de error al ejecutar la consulta.
     */
    @Override
    public Referencia get(Referencia t) throws SQLException {
        String select = "SELECT * FROM Referencies WHERE id_referencia = ?";
        Referencia r = null;

        try (Connection conn = MyDataSource.getConnection();PreparedStatement sentencia =conn.prepareStatement(select)) {
            // Configurar el parámetro antes de ejecutar la consulta
            sentencia.setInt(1, t.getId());

            try (ResultSet rs = sentencia.executeQuery()) {
                if (rs.next()) {
                    r = new Referencia();
                    r.setId(rs.getInt("id_referencia"));
                    r.setNom(rs.getString("nom_producte"));
                    r.setUnitat_mida(rs.getString("unitat_mida"));
                    r.setData_alta(rs.getString("data_alta"));
                    r.setData_fabricacio(rs.getString("data_fabricacio"));
                    r.setDescripcio(rs.getString("descripcio_producte"));
                    r.setPreu(rs.getString("preu"));
                    r.setUnitats_venudes(rs.getInt("unitats_venudes"));
                    r.setId_fam(rs.getInt("id_familia"));
                    r.setId_proveidor(rs.getInt("id_proveidor"));
                }
            }
        } catch (SQLException e) {
            mostrarMensajeError("Error en obtener la referencia: " + e.getMessage());
            throw e;
        }
        return r;
    }

    /**
     * Comprueba si existe una familia con el ID especificado en la base de
     * datos.
     *
     * @param idFamilia ID de la familia a verificar.
     * @return true si existe la familia, false si no.
     * @throws SQLException en caso de error al ejecutar la consulta.
     */
    public boolean existeFamilia(int idFamilia) throws SQLException {
        String selectFamilia = "SELECT COUNT(*) FROM Families WHERE id_familia = ?";
        try (Connection conn = MyDataSource.getConnection();PreparedStatement stmt =conn.prepareStatement(selectFamilia)) {
            stmt.setInt(1, idFamilia);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Devuelve true si existe, false si no
            }
        }
        return false;
    }

    /**
     * Comprueba si existe un proveedor con el ID especificado en la base de
     * datos.
     *
     * @param idProveedor ID del proveedor a verificar.
     * @return true si existe el proveedor, false si no.
     * @throws SQLException en caso de error al ejecutar la consulta.
     */
    public boolean existeProveedor(int idProveedor) throws SQLException {
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
