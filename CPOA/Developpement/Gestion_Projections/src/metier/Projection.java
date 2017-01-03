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
public class Projection {

    private String type;
    private Date date;
    private String horaire;
    private int num_proj;
    private int num_jury;
    private String titre_film;
    private String nom_salle;

    public Projection(String type, Date date, String horaire, int num_proj, int num_jury, String titre_film, String nom_salle) {
        this.type = type;
        this.date = date;
        this.horaire = horaire;
        this.num_proj = num_proj;
        this.num_jury = num_jury;
        this.titre_film = titre_film;
        this.nom_salle = nom_salle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public int getNum_proj() {
        return num_proj;
    }

    public void setNum_proj(int num_proj) {
        this.num_proj = num_proj;
    }
}
