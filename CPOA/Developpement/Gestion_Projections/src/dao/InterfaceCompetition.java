/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.List;
import javax.sql.DataSource;
import metier.Competition;

/**
 *
 * @author Jérémy Montrobert
 */
public interface InterfaceCompetition {
    public void setDataSource(DataSource ds);
    public void setConnection(Connection c);
    public List<Competition>getCompetition();
}
