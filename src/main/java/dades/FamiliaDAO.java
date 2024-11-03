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
            r.setData_alta_fam(rs.getDate("data_alta"));
            r.setId_proveidor_fam(rs.getInt("id_proveidor_defecte"));
            r.setObservacions(rs.getString("observacions"));      

            ret.add(r);

        }
        return ret;
        
       
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
