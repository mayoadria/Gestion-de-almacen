/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Clase MyDataSource para gestionar el acceso a la base de datos mediante un pool de conexiones
 * configurado con HikariCP. Esta clase implementa un patrón de diseño singleton, manteniendo 
 * un único pool de conexiones que se puede reutilizar. Proporciona métodos estáticos para 
 * obtener y cerrar conexiones de manera eficiente.
 * 
 * @author chris
 * @version 1.0
 * @since 2024
 */
public class MyDataSource {
    //té dues referències privades i estàtiques perquè només hi puguem accedir des de dins d'aquesta classe.
    private static final HikariConfig config = new HikariConfig(); // objecte que ens permetrà configurar el pool
    private static HikariDataSource dataSource; //és el pool de connexions

    //per poder inicialitzar els dos objectes privats ABANS que es cridi el únic mètode públic getConnection
    static {
        config.setJdbcUrl("jdbc:mysql://localhost/projecte1db?useUnicode=true&serverTimezone=Europe/Madrid&allowPublicKeyRetrieval=true&useSSL=false");
        config.setUsername("root");
        config.setPassword("Littlemix_4");
        config.addDataSourceProperty("maximumPoolSize", 1); //al pool només tindrem 1 connexió
        //altres propietats indicades a https://github.com/brettwooldridge/HikariCP#rocket-initialization
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config); //ja tenim el pool de connexions
    }

    /**
     * Constructor privado para evitar la creación de instancias de esta clase.
     * La configuración de conexión se maneja a través del bloque estático.
     */
    public MyDataSource(String projecte1db, String root, String jv) {
    }

    /**
     * Obtiene una conexión disponible del pool de conexiones configurado.
     * 
     * @return una instancia de Connection disponible en el pool.
     * @throws SQLException si ocurre un error al intentar obtener una conexión.
     */
    public static Connection getConnection() throws SQLException {
        //retornarà una connexió disponible del pool de connexions
        return dataSource.getConnection();
    }
    
    /**
     * Cierra el pool de conexiones si está activo, liberando los recursos utilizados.
     */
     public static void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
    
}
