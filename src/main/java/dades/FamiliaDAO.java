/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Familia;
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
        
       
        return null;
        
       
    }
    
     public boolean proveidorExiste(int idProveidor) {
    
        return false;
    
    }
    
    @Override
    public void insert(Familia t) throws SQLException {
        
    }

    @Override
    public void update(Familia t) throws SQLException {
        
        
    }

    @Override
    public void delete(Familia t) throws SQLException {
    

    }

    @Override
    public Familia get(Familia t) throws SQLException {
       return null;
    }
    
}
