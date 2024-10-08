/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import dao.DAOInterface;
import aplicacio.model.Familia;
import dao.MyDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Familia t) throws SQLException {
        String query = "INSERT INTO familia (id_fam, nom_familia, descripcio, data_alta_fam, id_proveidor_fam, Observacions) VALUES (?, ?, ?, ?, ?, ?)";
    
 
        
        try (Connection conn = MyDataSource.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setInt(1, t.getId_fam());
        stmt.setString(2, t.getNom_familia());
        stmt.setString(3, t.getDescripcio());
        stmt.setDate(4, t.getData_alta_fam());
        stmt.setInt(5, t.getId_proveidor_fam());
        stmt.setString(6, t.getObservacions());
        
        stmt.executeUpdate();
    }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Familia t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Familia t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Familia get(Familia t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
