/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import aplicacio.model.Referencia;
import dades.DAOInteerface;
import dades.DataLayer;
import dades.MysqlConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.util.ArrayList;

/**
 *
 * @author mayoa
 */
public class ReferenciaDAO extends DataLayer implements DAOInteerface<Referencia> {

    public ReferenciaDAO() throws SQLException {
        super();
    }

    @Override
    public List<Referencia> getAll() throws SQLException {
        /*
        CODIGO DE PRUEBA
         */
        List<Referencia> ret = new ArrayList<>();
        String selectFam = "select count(id_familia) from families where id_familia = ?";
        String selectPro = "select count(id_proveidor) from proveidor where id_proveidor = ?";
        if (Integer.parseInt(selectFam) == 1 && Integer.parseInt(selectPro) == 1) {

            String select = "select * from Referencies where id_familia = ?";
            PreparedStatement sentencia = this.getCon().prepareStatement(select);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                Referencia r = new Referencia();
                r.setId(rs.getInt("id_referencia"));
                r.setNom(rs.getString("nom_producte"));
                r.setQuantitat(rs.getDouble("quantitat"));
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

        }
        return ret;
    }

    @Override
    public void insert(Referencia t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Referencia t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Referencia t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Referencia get(Referencia t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
