/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import logica.Proveidor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.collections.ObservableList;

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

                boolean estat = res.getBoolean("actiu"); //Agafem el valor de la BBDD en boolean.

                if (estat == true) {
                    p.setActiu(estat);
                }

                p.setMotiu_inactiu(res.getString("motiu_inactiu"));
                p.setData_creacio(res.getDate("data_creacio"));
                p.setCorreu_electronic(res.getString("correu_electronic"));
                p.setRating_proveidor(res.getFloat("rating_proveidor"));
                p.setMesos_de_colaboracio(res.getInt("mesos_de_colaboracio"));

                ret.add(p);
            }
        }
        return ret;
    }

    @Override
    public void insert(Proveidor t) throws SQLException {
        //Aquest mètode crea un nou proveïdor.

        //Fem el mòdel de consulta per inserir nous proveïdors.
        String consultaInsert = "INSERT INTO proveidors(nom_proveidor,cif,actiu"
                + ",motiu_inactiu,data_creacio,correu_electronic,rating_proveidor,mesos_de_colaboracio) VALUES (?,?,?,?,?,?,?,?)";

        //Fem la connexió amb la BBDD.
        try (PreparedStatement sentencia = MyDataSource.getConnection().prepareStatement(consultaInsert)) {

            sentencia.setString(1, t.getNom_proveidor());
            sentencia.setString(2, t.getCif());
            sentencia.setBoolean(3, t.isActiu());  // Camp booleà 'actiu'
            sentencia.setString(4, t.getMotiu_inactiu());
            sentencia.setDate(5, t.getData_creacio());
            sentencia.setString(6, t.getCorreu_electronic());
            sentencia.setFloat(7, t.getRating_proveidor());
            sentencia.setInt(8, t.getMesos_de_colaboracio());

            //Executem l'insert, modificant les dades del nou proveïdor.
            sentencia.executeUpdate();

        } catch (SQLException e) {
            throw e;
        }
    }

    /*Primer recollir dades i inserir-les a les variables.*/
    @Override
    public void update(Proveidor t) throws SQLException {
        //Aquest mètode ens permet modificar un proveïdor.

        //Fem el mòdel de consulta general per a modificar un proveïdor ja existent.
        String consultaUpdate = "UPDATE Proveidors SET  nom_proveidor = ?,cif = ?,actiu = ?"
                + ",motiu_inactiu = ?,data_creacio = ?,correu_electronic = ?,rating_proveidor = ?,mesos_de_colaboracio = ? WHERE id_proveidor = ?";

        //Fem la connexió amb la BBDD.
        try (PreparedStatement sentencia = MyDataSource.getConnection().prepareStatement(consultaUpdate)) {

            sentencia.setString(1, t.getNom_proveidor());
            sentencia.setString(2, t.getCif());
            sentencia.setBoolean(3, t.isActiu());
            sentencia.setString(4, t.getMotiu_inactiu());
            sentencia.setDate(5, t.getData_creacio());
            sentencia.setString(6, t.getCorreu_electronic());
            sentencia.setFloat(7, t.getRating_proveidor());
            sentencia.setInt(8, t.getMesos_de_colaboracio());
            sentencia.setInt(9, t.getId_proveidor());

            //Executem l'insert, modificant les dades del nou proveïdor.
            int rowsUpdated = sentencia.executeUpdate();
            if (rowsUpdated > 0) {
                //missatgeExcepcio("El proveïdor ha estat actualitzat exitosament.");
            } else {
                //missatgeExcepcio("No s'ha trobat cap proveïdor amb l'ID proporcionat.");
            }

        } catch (SQLException e) {
            //missatgeExcepcio("Error en insertar el proveïdor: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(Proveidor t) throws SQLException {
        //Aquest mètode esborra un proveïdor.

        //Fem el mòdel de consulta general per a esborrar un proveïdor ja existent.
        String consultaDelete = "Delete FROM Proveidors WHERE id_proveidor = ?";

        try (PreparedStatement sentencia = MyDataSource.getConnection().prepareStatement(consultaDelete)) {

            sentencia.setInt(1, t.getId_proveidor());

            int rowsDeleted = sentencia.executeUpdate();
            if (rowsDeleted > 0) {
                //missatgeExepcio("El proveïdor ha estat esborrat exitosament.");
            } else {
                //missatgeExepcio("No s'ha trobat cap proveïdor amb l'ID proporcionat.");
            }
        } catch (SQLException e) {
            //missatgeExcepcio("Error en insertar el proveïdor: " + e.getMessage());
            throw e;
        }

        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Proveidor get(Proveidor t) throws SQLException {
        //Aquest mètode ens permet seleccionar un proveïdor.

        //Fem la consulta que ens permet seleccionar un proveïdor a partir d'un ID.
        String consultaSeleccionar = "SELECT * FROM Proveidors WHERE id_proveidor = ?";

        // Inicialitzem un Proveidor p en null per tal de poder emmagatzemar les dades del proveïdor que volem seleccionar.
        Proveidor p = null;

        try (PreparedStatement sentencia = MyDataSource.getConnection().prepareStatement(consultaSeleccionar)) {

            try (ResultSet res = sentencia.executeQuery()) {
                if (res.next()) {
                    //Creem el proveidor i les seves dades.
                    p = new Proveidor();

                    p.setId_proveidor(res.getInt("id_proveidor"));
                    p.setNom_proveidor(res.getString("nom_proveidor"));
                    p.setCif(res.getString("cif"));

                    boolean estat = res.getBoolean("actiu"); //Agafem el valor de la BBDD en boolean.

                    if (estat == true) {
                        p.setActiu(estat);
                    }

                    p.setMotiu_inactiu(res.getString("motiu_inactiu"));
                    p.setData_creacio(res.getDate("data_creacio"));
                    p.setCorreu_electronic(res.getString("correu_electronic"));
                    p.setRating_proveidor(res.getFloat("rating_proveidor"));
                    p.setMesos_de_colaboracio(res.getInt("mesos_de_colaboracio"));
                }
            }
        } catch (SQLException e) {
            //missatgeExcepcio("Error en obtenir el proveïdor: " + e.getMessage());
        }
        return p;
    }

    public Set<Integer> getAllIds() throws SQLException {
        Set<Integer> idsExistents = new HashSet<>();
        String sql = "SELECT id_proveidor FROM proveidors"; // Cambia 'proveidors' por el nombre real de tu tabla

        // Obtiene la conexión desde MyDataSource
        try (Connection conn = MyDataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            // Itera sobre los resultados y agrega los IDs al conjunto
            while (rs.next()) {
                idsExistents.add(rs.getInt("id_proveidor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al obtener los IDs de los proveïdors.");
        }

        return idsExistents;
    }
}
