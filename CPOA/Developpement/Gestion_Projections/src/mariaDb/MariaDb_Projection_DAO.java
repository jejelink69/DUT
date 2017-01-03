/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariaDb;

import dao.InterfaceProjection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.activation.DataSource;
import metier.Projection;

/**
 *
 * @author p1506068
 */
public class MariaDb_Projection_DAO implements InterfaceProjection {
    private static DataSource ds;
    private static Connection connexionBD;
    
    @Override
    public void setDataSource(javax.sql.DataSource ds) {
       MariaDb_Projection_DAO.ds = (DataSource) ds;
    }

    @Override
    public void setConnection(Connection c) {
       MariaDb_Projection_DAO.connexionBD = c;
    }

    @Override
    public List<Projection> getProjections() {
        ResultSet rset = null;
        Statement stmt = null;
        List<Projection> listeProjection = null;
        try{
            stmt = connexionBD.createStatement();
            listeProjection = new ArrayList<>();
            rset = stmt.executeQuery("SELECT * FROM Projection");
            while (rset.next()){
                String type = rset.getString("Type");
                Date date = rset.getDate("Date");
                String horaire = rset.getString("Horaire");
                int num_proj = rset.getInt("num_Projection");
                int num_jury = rset.getInt("num_Jury");
                String titre_film = rset.getString("titre_Film");
                String nom_salle = rset.getString("nomSalle");
                
                listeProjection.add(new Projection(type,date,horaire,num_proj,num_jury,titre_film,nom_salle));
                
            }
            
        }
        catch(SQLException exc){
            System.err.println("Erreur:"+ exc.getMessage());
            
        }finally{
            try{
                if(stmt != null)stmt.close();
                if(stmt != null)rset.close();
            }
            catch(SQLException ex){
                System.err.println("Erreur:"+ ex.getMessage());
            }
        }
        return listeProjection;
    }
}
