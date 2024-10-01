/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author chris
 */
public class MysqlConnection {
    public static Connection connection() {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Introdueix contrasenya de base de dades:");
        String contrasenya = sc.nextLine();
                
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", contrasenya);
        props.setProperty("useSSL", "true");
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projecte1db", props);) {
            System.out.println("Connexió feta a la base de dades database");
            // codi per fer operacions a la base de dades

    
    } catch (SQLException e) {
            e.printStackTrace();
        } // ja no cal el bloc finally per tancar recursos   
        return null;
    }
    
}
