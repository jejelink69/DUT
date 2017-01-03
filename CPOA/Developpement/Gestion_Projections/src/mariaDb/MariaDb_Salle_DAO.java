/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariaDb;

import dao.InterfaceSalle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import metier.Salle;

/**
 *
 * @author Jérémy Montrobert
 */
public class MariaDb_Salle_DAO implements InterfaceSalle{
    private static DataSource ds;
    private static Connection connexionBD;
    
    @Override
    public void setDataSource(DataSource ds) {
         MariaDb_Salle_DAO.ds = (DataSource) ds;
    }

    @Override
    public void setConnection(Connection c) {
        MariaDb_Salle_DAO.connexionBD = c;
    }

    @Override
    public List<Salle> getSalle() {
        ResultSet rset = null;
        Statement stmt = null;
        List<Salle> listeSalle = null;
        try{
            
        }
        catch(){
            
        }finally{
            try{
                
            }
            catch(){
                
            }
        }
        return listeSalle;
    }
}

