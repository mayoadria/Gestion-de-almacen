/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import logica.Familia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        
        List<Familia> famlist = new ArrayList<>();
        
        String sentenciaSql= "select * from Families ";
        PreparedStatement pstmt = MyDataSource.getConnection().prepareStatement(sentenciaSql);
        ResultSet rs = pstmt.executeQuery();
        //Mostrar dades
        while (rs.next()) {
            Familia f = new Familia();
            f.setId_fam(rs.getInt("id_familia"));
            f.setNom_familia(rs.getString("nom_familia"));
            f.setDescripcio(rs.getString("descripcio"));
            f.setData_alta_fam(rs.getDate("data_alta"));
            f.setId_proveidor_fam(rs.getInt("id_proveidor_defecte"));
            f.setObservacions(rs.getString("observacions"));


            famlist.add(f);

        }
        return famlist;
    }
    
     public boolean proveidorExiste(int idProveidor) {
        String sql = "SELECT COUNT(*) FROM proveidors WHERE id_proveidor = ?";
        
        try (PreparedStatement preparedStatement = MyDataSource.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, idProveidor);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Retorna true si hay al menos un proveedor con ese ID
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores
        }
        
        return false; // Retorna false si hay algún problema o el proveedor no existe
    }
    
    @Override
    public void insert(Familia t) throws SQLException {
        String insert = "INSERT INTO Families (nom_familia, descripcio, data_alta, "
                + "id_proveidor_defecte, observacions) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = MyDataSource.getConnection().prepareStatement(insert)) {
              pstmt.setString(1, t.getNom_familia());
              pstmt.setString(2, t.getDescripcio());
              pstmt.setDate(3, t.getData_alta_fam());
              pstmt.setInt(4, t.getId_proveidor_fam());
              pstmt.setString(5, t.getObservacions());


            //executa el insert
            pstmt.executeUpdate();  

        } catch (SQLException e) {
            System.out.println("Error al insertar la familia: "+ e.getMessage());
            
            throw e;
        }
    }

    @Override
    public void update(Familia t) throws SQLException {
        
        String update = "UPDATE Families SET nom_familia = ?, descripcio = ?, data_alta = ?, "
                + "id_proveidor_defecte = ?, observacions = ? WHERE id_familia = ?";
        try(PreparedStatement pstmt = MyDataSource.getConnection().prepareStatement(update)){
            pstmt.setString(1, t.getNom_familia());
              pstmt.setString(2, t.getDescripcio());
              pstmt.setDate(3, t.getData_alta_fam());
              pstmt.setInt(4, t.getId_proveidor_fam());
              pstmt.setString(5, t.getObservacions());
              pstmt.setInt(6, t.getId_fam());
              
              int rowsUpdated = pstmt.executeUpdate();
              if (rowsUpdated > 0) {
                  System.out.println("Familia actualitzada");
              }else{
                  System.out.println("no sha trobat cap familia amb el id obtingut");
              }
        } catch (SQLException e) {
            System.out.println("Error al borrar la familia: "+ e.getMessage());
            
            throw e;
        }
    }

    @Override
    public void delete(Familia t) throws SQLException {
        String delete = "DELETE FROM Families WHERE id_familia = ?";
        try(PreparedStatement pstmt = MyDataSource.getConnection().prepareStatement(delete)){
            pstmt.setInt(1, t.getId_fam());
            
            int columnesEliminades = pstmt.executeUpdate();
            if (columnesEliminades > 0 ) {
                System.out.println("La familia s'ha eliminat correctament");
            }else{
                System.out.println("no s'ha trobat cap familia amb aquest ID");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al borrar la familia: "+ e.getMessage());
            
            throw e;
        }

    }

    @Override
    public Familia get(Familia t) throws SQLException {
        String select = "SELECT * FROM Families WHERE id_familia = ?";
        Familia f = null;
        try(PreparedStatement pstmt = MyDataSource.getConnection().prepareStatement(select)){
           
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    f = new Familia();
                    f.setId_fam(rs.getInt("id_familia"));
                    f.setNom_familia(rs.getNString("nom_familia"));
                    f.setDescripcio(rs.getString("descripcio"));
                    f.setData_alta_fam(rs.getDate("data_alta"));
                    f.setId_proveidor_fam(rs.getInt("id_proveidor_defecte"));
                    f.setObservacions(rs.getString("observacions"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtenir la familia: "+ e.getMessage());
            
            throw e;
        }
        return f;
    }
    
}
