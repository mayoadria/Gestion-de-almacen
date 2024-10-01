/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author mayoa
 */
public abstract class DataLayer {

    Connection con;

    public DataLayer() throws SQLException {

        //intentem connectar
        this.con = MysqlConnection.connection();
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection _con) {
        con = _con;
    }

}
