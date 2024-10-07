/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import aplicacio.model.Proveidor;
import dao.DAOInterface;
import aplicacio.model.Proveidor.EstatProveidor;
import dao.MyDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import cat.copernic.project1_grup2.pantallaProveidorController;

/**
 * *
 *
 * @author Anna
 */
public class ProveidorDAO implements DAOInterface<Proveidor> {

    ObservableList<Proveidor> llistaObservableProveidor;

    //Creem una llista de proveïdors per a poder treballar el CRUD.
    /**
     * *
     *
     * @throws SQLException
     */
    public ProveidorDAO() throws SQLException {
        //Aquest mètode recull els errors de connexió que es puguin donar en la connexió amb la BBDD.

        super();
    }

    /**
     * *
     * @return @throws SQLException
     * @throws java.sql.SQLException
     */
    @Override
    public List<Proveidor> getAll() throws SQLException {
        //Aquest mètode ens retorna una llista amb tots els proveïdors.

        List<Proveidor> ret = new ArrayList<>();

        //Comanda en SQL per al select de tots els proveïdors.
        String consulta = "select * from proveidors";
        try (Connection conn = MyDataSource.getConnection(); PreparedStatement pstm = conn.prepareStatement(consulta)) {
            ResultSet res = pstm.executeQuery();

            //Bucle per a recollir les dades dels diferents proveïdors
            while (res.next()) {

                Proveidor p = new Proveidor();
                p.setId_proveidor(res.getInt("id_proveidor"));
                p.setNom_proveidor(res.getString("nom_proveidor"));
                p.setCif(res.getString("cif"));

                int estat = res.getInt("actiu");

                if (estat == 1) {
                    p.setEstat(EstatProveidor.ACTIU);
                } else {
                    p.setEstat(EstatProveidor.INACTIU);
                }

                p.setMotiu_inactiu(res.getString("motiu_inactiu"));
                p.setData_creacio(res.getDate("data_creacio"));
                p.setCorreu_electronic(res.getString("correu_electronic"));
                p.setRating_proveidor(res.getFloat("rating_proveidor"));
                p.setMesos_de_colaboracio(res.getInt("mesos_de_colaboracio"));

                ret.add(p);
            }

            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        return ret;
    }

    @Override
    public void insert(Proveidor t) throws SQLException {
        //Aquest mètode crea un nou proveïdor.
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        String consultaInsert = "INSERT INTO proveidors(id_proveidor,nom_proveidor,cif,actiu"
                + ",motiu_inactiu,data_creacio,correu_electronic,rating_proveidor,mesos_de_colaboracio) VALUES (?,?,?,?,?,?,?,?,?)";

        try (Connection conn = MyDataSource.getConnection(); PreparedStatement pstm = conn.prepareStatement(consultaInsert)) {

            //Bucle per a recollir les dades dels diferents proveïdors
            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    /*Primer recollir dades i inserir-les a les variables.*/
    @Override
    public void update(Proveidor t) throws SQLException {
        //Aquest mètode ens permet modificar un proveïdor.

        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Proveidor t) throws SQLException {
        //Aquest mètode esborra un proveïdor.

        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Proveidor get(Proveidor t) throws SQLException {
        //Aquest mètode ens permet seleccionar un proveïdor.

        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

/* 
    VARIABLES JAVA FX PROVEÏDOR 
    - ID       : tf_idProv 
    - Nom      : tf_nomProv
    - cif      : tf_cifProv
    - estat    : chb_estatProv
    - motiu    : tf_motiuInaProv
    - dataCrea : dp_creacioProv
    - correu   : tf_correuProv 
 */
