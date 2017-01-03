/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariaDb;

import dao.InterfaceJury;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import metier.Jury;

/**
 *
 * @author Jérémy Montrobert
 */
public class MariaDb_Jury_DAO implements InterfaceJury{
    private static DataSource ds;
    private static Connection connexionBD;
    
    @Override
    public void setDataSource(DataSource ds) {
        MariaDb_Jury_DAO.ds = (DataSource) ds;
    }

    @Override
    public void setConnection(Connection c) {
        MariaDb_Jury_DAO.connexionBD = c;
    }

    @Override
    public List<Jury> getJury() {
     ResultSet rset = null;
        Statement stmt = null;
        List<Jury> listeJury = null;
        try{
            
        }
        catch(){
            
        }finally{
            try{
                
            }
            catch(){
                
            }
        }
        return listeJury;
    }
}
