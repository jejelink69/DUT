/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariaDb;

/**
 *
 * @author Jérémy Montrobert
 */
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbDataSource;

public class MariaDb_DataSource_DAO {
    private static MariaDb_DataSource_DAO instance = null;
    private static Statement stmt;
    private static Connection connec;
    private static ResultSet rs;
    
    private MariaDb_DataSource_DAO(){};
    
        public static MariaDb_DataSource_DAO getInstance() throws SQLException, IOException{
        if(instance == null){
            instance = new MariaDb_DataSource_DAO();
        }
        return instance;
    }
        
    public static Connection creerConnexion() throws SQLException{
        Properties props = new Properties();
        FileInputStream fichier = null;
        try {
            fichier = new FileInputStream("./src/mariaDb/connexion.properties");
            props.load(fichier);
        }
        catch (FileNotFoundException e) {
            System.out.println("**** Fichier décrivant les propriétés d'accès à la BD non trouvé !"+e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger( MariaDbConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
            fichier.close();
            } catch (IOException ex) {
            Logger.getLogger( MariaDbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        try {
             MariaDbDataSource mdbds = new  MariaDbDataSource();
           
            mdbds.setPortNumber(new Integer(props.getProperty("port")).intValue());
            mdbds.setDatabaseName(props.getProperty("service"));
            mdbds.setUser(props.getProperty("user"));
            mdbds.setPassword(props.getProperty("pwd"));
            mdbds.setServerName(props.getProperty("serveur"));

            System.out.println("==> Connexion MySql établie...");
            return ( mdbds.getConnection() );
        } catch (Exception e) {
            System.err.println("Erreur de connexion au serveur MySql...");
            return null;
        }
     }
    
    public void fermer() throws SQLException {
        connec.close();
        System.out.println("deconnection ok");
    }
    
}