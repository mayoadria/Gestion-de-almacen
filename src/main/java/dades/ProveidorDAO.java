/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import java.sql.SQLException;


/**
 *
 * @author Anna
 */
public class ProveidorDAO extends DataLayer implements DAOInteerface<Proveidor>{
    
    public ProveidorDAO() throws SQLException() {
        //Aquest mètode recull els errors de connexió que es puguin donar en la connexió amb la BBDD.
        
        super();
    }
}
