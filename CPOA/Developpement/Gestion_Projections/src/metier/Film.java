/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

/**
 *
 * @author p1506068
 */
public class Film {

    private String titre;
    private String realisateur;
    private int durée;
    private int année;
    private String nom_competiton;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public int getDurée() {
        return durée;
    }

    public void setDurée(int durée) {
        this.durée = durée;
    }

    public int getAnnée() {
        return année;
    }

    public void setAnnée(int année) {
        this.année = année;
    }
}
