/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

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
 *
 * @author oriol
 */
public class FamiliaDAO implements DAOInterface<Familia>{

    public FamiliaDAO() throws SQLException {
        super();
    }

    @Override
    public List<Familia> getAll() throws SQLException {
        //Crear una llista per poder obtenir totes les referencies existents a la base de dades
        List<Familia> ret = new ArrayList<>();
        //Fer la consulta
        String select = "select * from families ";
        PreparedStatement sentencia = MyDataSource.getConnection().prepareStatement(select);
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
        return ret;
        
       
    }
    
    @Override
    public int insert(Familia t) throws SQLException {
        String insert = "INSERT INTO families (nom_familia, descripcio, data_alta, id_proveidor_defecte, observacions) VALUES (?, ?, ?, ?, ?)";
        //Hacer la conexion con la base y marcar de donde tienen que obtener los datos
        try (PreparedStatement sentencia = MyDataSource.getConnection().prepareStatement(insert,Statement.RETURN_GENERATED_KEYS)) {
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

    @Override
    public void update(Familia t) throws SQLException {
        //Hacer la sentencia del update
        String update = "UPDATE Families SET nom_familia = ?, descripcio = ?, data_alta = ?, id_proveidor_defecte = ?, observacions = ? WHERE id_familia = ?";

        try (PreparedStatement sentencia = MyDataSource.getConnection().prepareStatement(update)) {
            //Hacer la conexion con la base y marcar de donde tienen que obtener los datos        
            sentencia.setString(1, t.getNom_familia());
            sentencia.setString(2, t.getDescripcio());
            sentencia.setString(3, t.getData_alta_fam());
            sentencia.setInt(4, (int)t.getId_proveidor_fam());
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

    @Override
    public void delete(Familia t) throws SQLException {
        String delete = "DELETE FROM Families WHERE id_familia = ?";

        try (PreparedStatement sentencia = MyDataSource.getConnection().prepareStatement(delete)) {
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

    @Override
    public Familia get(Familia t) throws SQLException {
       //Metodo el cual, según la fila que este seleccionada muestra en los textFields la informacion de la base de datos
        String select = "SELECT * FROM Families WHERE id_familia = ?";
        Familia f = null; // Para almacenar la referencia encontrada

        try (PreparedStatement sentencia = MyDataSource.getConnection().prepareStatement(select)) {

            try (ResultSet rs = sentencia.executeQuery()) {
                if (rs.next()) {
                    // Creamos el objeto Referencia y llenamos sus datos
                    f = new Familia();
                    f.setId_fam(rs.getInt("id_referencia"));
                    f.setNom_familia(rs.getString("nom_producte"));
                    f.setDescripcio(rs.getString("descripcio"));
                    f.setData_alta_fam(rs.getString("data_alta"));
                    f.setId_proveidor_fam(rs.getInt("id_proveidor_defecte"));
                    f.setObservacions(rs.getString("observacions"));
                }
            }
        } catch (SQLException e) {
            mostrarMensajeError("Error en eliminar la família:" + e.getMessage());
            throw e;
        }

        return f; // Devolver la referencia encontrada o null si no existe    }
    }
    
    /**
     * Comprueba si existe un proveedor con el ID especificado en la base de datos.
     * 
     * @param idProveedor ID del proveedor a verificar.
     * @return true si existe el proveedor, false si no.
     * @throws SQLException en caso de error al ejecutar la consulta.
     */
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
