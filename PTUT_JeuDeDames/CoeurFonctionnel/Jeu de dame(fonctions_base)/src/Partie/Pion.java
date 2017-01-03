
package Partie;

/**
 *
 * @author Simon
 */
public class Pion {
  
    private boolean dame;
    private String couleur;
    private int posX;
    private int posY;
    private int statut;  //Statut : 1 si en jeu ; 0 si plus en jeu
    

    public Pion( String couleur, int posX, int posY) {
        
        this.dame = false;
        this.couleur = couleur;
        this.statut = 1;
        this.posX=posX;
        this.posY=posY;
    }
    
   
    
    
    //Getter and Setter
    
    public String getCouleur(){
        return this.couleur;
    }
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
    public void setPosX(int positionX) {
        this.posX = positionX;
    }
    public void setPosY(int positionY) {
        this.posY = positionY;
    }



   
 
    
    

}
