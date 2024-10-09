/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import logica.Referencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.util.ArrayList;
import javafx.scene.control.Alert;
import logica.Mensajes;

/**
 *
 * @author mayoa
 */
public class ReferenciaDAO extends Mensajes implements DAOInterface<Referencia> {

    public ReferenciaDAO() throws SQLException {
        super();
    }

    @Override
    public List<Referencia> getAll() throws SQLException {
        //Crear una llista per poder obtenir totes les referencies existents a la base de dades
        List<Referencia> ret = new ArrayList<>();
        //Fer la consulta
        String select = "select * from Referencies ";
        PreparedStatement sentencia = MyDataSource.getConnection().prepareStatement(select);
        ResultSet rs = sentencia.executeQuery();
        //Mostrar los datos
        while (rs.next()) {
            Referencia r = new Referencia();
            r.setId(rs.getInt("id_referencia"));
            r.setNom(rs.getString("nom_producte"));
            r.setQuantitat((int) rs.getDouble("quantitat"));
            r.setUnitat_mida(rs.getString("unitat_mida"));
            r.setData_alta(rs.getDate("data_alta"));
            r.setData_fabricacio(rs.getDate("data_fabricacio"));
            r.setDescripcio(rs.getString("descripcio_producte"));
            r.setPreu(rs.getFloat("preu"));
            r.setUnitats_venudes(rs.getInt("unitats_venudes"));
            r.setId_fam(rs.getInt("id_familia"));
            r.setId_proveidor(rs.getInt("id_proveidor"));

            ret.add(r);

        }
        return ret;
    }

    @Override
    public void insert(Referencia t) throws SQLException {
        //Hacer la sentencia del insert
        String insert = "INSERT INTO Referencies (nom_producte, quantitat, unitat_mida, data_alta, data_fabricacio, descripcio_producte, preu, unitats_venudes, id_familia, id_proveidor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        //Hacer la conexion con la base y marcar de donde tienen que obtener los datos
        try (PreparedStatement sentencia = MyDataSource.getConnection().prepareStatement(insert)) {
            sentencia.setString(1, t.getNom());
            sentencia.setInt(2, (int) t.getQuantitat());
            sentencia.setString(3, t.getUnitat_mida());
            sentencia.setDate(4, t.getData_alta());
            sentencia.setDate(5, t.getData_fabricacio());
            sentencia.setString(6, t.getDescripcio());
            sentencia.setFloat(7, t.getPreu());
            sentencia.setInt(8, t.getUnitats_venudes());
            sentencia.setInt(9, t.getId_fam());
            sentencia.setInt(10, t.getId_proveidor());

            //Ejecutar el insert
            sentencia.executeUpdate();  // Ejecutar la consulta

        } catch (SQLException e) {
            mostrarMensajeError("Error al insertar la referencia: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void update(Referencia t) throws SQLException {
        //Hacer la sentencia del update
        String update = "UPDATE Referencies SET nom_producte = ?, quantitat = ?, unitat_mida = ?, data_alta = ?, data_fabricacio = ?, descripcio_producte = ?, preu = ?, unitats_venudes = ?, id_familia = ?, id_proveidor = ? WHERE id_referencia = ?";

        try (PreparedStatement sentencia = MyDataSource.getConnection().prepareStatement(update)) {
            //Hacer la conexion con la base y marcar de donde tienen que obtener los datos        
            sentencia.setString(1, t.getNom());
            sentencia.setInt(2, (int) t.getQuantitat());
            sentencia.setString(3, t.getUnitat_mida());
            sentencia.setDate(4, t.getData_alta());
            sentencia.setDate(5, t.getData_fabricacio());
            sentencia.setString(6, t.getDescripcio());
            sentencia.setFloat(7, t.getPreu());
            sentencia.setInt(8, t.getUnitats_venudes());
            sentencia.setInt(9, t.getId_fam());
            sentencia.setInt(10, t.getId_proveidor());
            sentencia.setInt(11, t.getId());  // Condición para encontrar el registro a actualizar (id_referencia)

            // Ejecutamos la sentencia
            int rowsUpdated = sentencia.executeUpdate();
            if (rowsUpdated > 0) {
                mostrarMensaje("La referencia ha sido actualizada exitosamente.");
            } else {
                mostrarMensajeError("No se encontró ninguna referencia con el ID proporcionado.");
            }
        } catch (SQLException e) {
            mostrarMensajeError("Error al actualizar la referencia: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(Referencia t) throws SQLException {
        //Hacer la conexion con la base y marcar de donde tienen que obtener los datos        
        String delete = "DELETE FROM Referencies WHERE id_referencia = ?";

        try (PreparedStatement sentencia = MyDataSource.getConnection().prepareStatement(delete)) {
            // Configuramos el valor del id_referencia para eliminar
            sentencia.setInt(1, t.getId());

            // Ejecutamos la eliminación
            int rowsDeleted = sentencia.executeUpdate();
            if (rowsDeleted > 0) {
                mostrarMensaje("La referencia ha sido eliminada exitosamente.");
            } else {
                mostrarMensajeError("No se encontró ninguna referencia con el ID proporcionado.");
            }
        } catch (SQLException e) {
            mostrarMensajeError("Error al eliminar la referencia: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Referencia get(Referencia t) throws SQLException {
        //Metodo el cual, según la fila que este seleccionada muestra en los textFields la informacion de la base de datos
        String select = "SELECT * FROM Referencies WHERE id_referencia = ?";
        Referencia r = null; // Para almacenar la referencia encontrada

        try (PreparedStatement sentencia = MyDataSource.getConnection().prepareStatement(select)) {

            try (ResultSet rs = sentencia.executeQuery()) {
                if (rs.next()) {
                    // Creamos el objeto Referencia y llenamos sus datos
                    r = new Referencia();
                    r.setId(rs.getInt("id_referencia"));
                    r.setNom(rs.getString("nom_producte"));
                    sentencia.setInt(2, (int) t.getQuantitat());
                    r.setUnitat_mida(rs.getString("unitat_mida"));
                    r.setData_alta(rs.getDate("data_alta"));
                    r.setData_fabricacio(rs.getDate("data_fabricacio"));
                    r.setDescripcio(rs.getString("descripcio_producte"));
                    r.setPreu(rs.getFloat("preu"));
                    r.setUnitats_venudes(rs.getInt("unitats_venudes"));
                    r.setId_fam(rs.getInt("id_familia"));
                    r.setId_proveidor(rs.getInt("id_proveidor"));
                }
            }
        } catch (SQLException e) {
            mostrarMensajeError("Error al obtener la referencia: " + e.getMessage());
            throw e;
        }

        return r; // Devolver la referencia encontrada o null si no existe    }

    }

    public boolean existeFamilia(int idFamilia) throws SQLException {
        String selectFamilia = "SELECT COUNT(*) FROM Families WHERE id_familia = ?";
        try (PreparedStatement stmt = MyDataSource.getConnection().prepareStatement(selectFamilia)) {
            stmt.setInt(1, idFamilia);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Devuelve true si existe, false si no
            }
        }
        return false;
    }

    public boolean existeProveedor(int idProveedor) throws SQLException {
        String selectProveidor = "SELECT COUNT(*) FROM Proveidors WHERE id_proveidor = ?";
        try (PreparedStatement stmt = MyDataSource.getConnection().prepareStatement(selectProveidor)) {
            stmt.setInt(1, idProveedor);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Devuelve true si existe, false si no
            }
        }
        return false;
    }
}
