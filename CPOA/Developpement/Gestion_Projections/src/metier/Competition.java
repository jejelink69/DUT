/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.sql.Date;

/**
 *
 * @author p1506068
 */
public class Competition {

    private String nom_competition;
    private Date date_debut;
    private Date date_fin;

    public String getNom_competition() {
        return nom_competition;
    }

    public void setNom_competition(String nom_competition) {
        this.nom_competition = nom_competition;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }
}
