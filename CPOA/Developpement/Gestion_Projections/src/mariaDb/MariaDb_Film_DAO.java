/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariaDb;

import dao.InterfaceFilm;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import metier.Film;

/**
 *
 * @author Jérémy Montrobert
 */
public class MariaDb_Film_DAO implements InterfaceFilm{
    private static DataSource ds;
    private static Connection connexionBD;
    @Override
    public void setDataSource(DataSource ds) {
        MariaDb_Film_DAO.ds = (DataSource) ds;
    }

    @Override
    public void setConnection(Connection c) {
       MariaDb_Film_DAO.connexionBD = c;
    }

    @Override
    public List<Film> getFilm() {
       ResultSet rset = null;
        Statement stmt = null;
        List<Film> listeFilm = null;
        try{
            
        }
        catch(){
            
        }finally{
            try{
                
            }
            catch(){
                
            }
        }
        return listeFilm;
    }
    
}
