/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariaDb;

import dao.InterfaceCompetition;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import metier.Competition;

/**
 *
 * @author Jérémy Montrobert
 */
public class MariaDb_Competition_DAO implements InterfaceCompetition {
    private static DataSource ds;
    private static Connection connexionBD;
    @Override
    public void setDataSource(DataSource ds) {
        MariaDb_Competition_DAO.ds = (DataSource) ds;
    }

    @Override
    public void setConnection(Connection c) {
       MariaDb_Competition_DAO.connexionBD = c;
    }

    @Override
    public List<Competition> getCompetition() {
     ResultSet rset = null;
        Statement stmt = null;
        List<Competition> listeCompetition = null;
        try{
            
        }
        catch(){
            
        }finally{
            try{
                
            }
            catch(){
                
            }
        }
        return listeCompetition;
    }
}