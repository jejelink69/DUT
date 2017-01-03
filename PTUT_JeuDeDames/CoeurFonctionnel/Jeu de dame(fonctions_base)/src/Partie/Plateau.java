
package Partie;

import java.util.ArrayList;

/**
 *
 * @author Simon
 */
public class Plateau {
    private Pion plateau[][];
    private int nbPionEnJeu;

    public Plateau() {
        plateau=new Pion[10][10];
        this.initPlateau();
        this.nbPionEnJeu = 40;
        
    }

    private void initPlateau() {
        //Initialisation des pions blanc
        
       for(int i=0;i<4;i++){
           
           for(int j=0;j<10;j+=2){
               if(i%2==0)plateau[i][j]=new Pion("Blanc",i,j);
               else plateau[i][j+1]=new Pion("Blanc",i,j+1);
               }
        }
       
       for(int i=6;i<10;i++){
           
           for(int j=0;j<10;j+=2){
               
               if(i%2==0)plateau[i][j]=new Pion("Noir",i,j);
               else plateau[i][j+1]=new Pion("Noir",i,j+1);
               }
        }
    }
    
    
    public void affichePlateau(){
        for (int i=9;i>=0;i--){
            for(int j=0;j<10;j++){
                if(plateau[i][j]!=null)System.out.print("|"+plateau[i][j].getCouleur());
                else System.out.print("|Vide");
            }
            System.out.println(" ");
            System.out.println(" ");
        }
        
       
    }

    public ArrayList<Pion> deplacementPossibleBlanc(){     
       
        ArrayList<Pion> dep=new ArrayList<Pion>();              //Liste des pions  qui peuvent se déplacer
        ArrayList<Pion> obl=new ArrayList<Pion>();              //Liste des pions qui peuvent potentiellement manger un pion
        
     
        
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(plateau[i][j]!=null){
                    Pion temp=plateau[i][j];                
                    if(temp.getCouleur().equals("Blanc")){ 
                        if(j==0){
                            if( plateau[i+1][j+1]==null)dep.add(temp);
                            else if(plateau[i+1][j+1].getCouleur()=="Noir")obl.add(temp);
                            
                        }
                        else{
                            if(j==9){
                                if(plateau[i+1][j-1]==null)dep.add(temp);
                                else if(plateau[i+1][j-1].getCouleur()=="Noir")obl.add(temp);
                                
                            }
                            else {
                                if(plateau[i+1][j-1]==null|| plateau[i+1][j+1]==null)dep.add(temp);
                                else if(plateau[i+1][j-1].getCouleur()=="Noir")obl.add(temp);
                                else if(plateau[i+1][j+1].getCouleur()=="Noir")obl.add(temp);
                            }
                        }
                    }
                }
            }     
        }
      for (Pion p : obl){
          System.out.println("1");
      }
       obl=coupsObligatoiresBlanc(obl);
        if (obl.isEmpty())return dep;
        else  return obl;
        
        
        
    }
    
    public ArrayList<Pion> deplacementPossibleNoir(){     
       
        ArrayList<Pion> dep=new ArrayList<Pion>();              //Liste des pions  qui peuvent se déplacer
        ArrayList<Pion> obl=new ArrayList<Pion>();              //Liste des pions qui peuvent potentiellement manger un pion
        
     
        
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(plateau[i][j]!=null){
                    Pion temp=plateau[i][j];                
                    if(temp.getCouleur().equals("Noir")){ 
                        if(j==0){
                            if( plateau[i-1][j+1]==null)dep.add(temp);
                            else if(plateau[i-1][j+1].getCouleur()=="Blanc")obl.add(temp);
                        }
                        else{
                            if(j==9){
                                if(plateau[i-1][j-1]==null)dep.add(temp);
                                else if(plateau[i-1][j-1].getCouleur()=="Blanc")obl.add(temp);
                            }
                            else {
                                if(plateau[i-1][j-1]==null|| plateau[i-1][j+1]==null)dep.add(temp);
                                else if(plateau[i-1][j-1].getCouleur()=="Blanc" || plateau[i-1][j+1].getCouleur()=="Blanc")dep.add(temp);
                            }
                        }
                    }
                }
            }     
        }
        
        obl=coupsObligatoiresNoir(obl);
        if (obl.isEmpty())return dep;
        else return obl;
        
        
        
    }
    
    public void deplacerPion(Pion p,int  dir){  //On déplace le pion dans le tableau et on change ses coordonnées 
        int x;                                  // dir=0 --> déplacement à gauche ; dir=1 --> déplacement à droite
        int y;
        
        if (p.getCouleur()=="Blanc") x=p.getPosX()+1;        // x et y représentent les coordonnées post-déplacement
        else x=p.getPosX()-1;
        
        if (dir==0)y=p.getPosY()-1;
        else y=p.getPosY()+1;
                    
        if(x<10 && x>=0 && y>=0 && y<10){                    // On vérifie que le déplacement reste dans le plateau
            if(plateau[x][y]==null){                        // On vérifie que l'on n'écrase pas un pion
                plateau[p.getPosX()][p.getPosY()]=null;   
                plateau[x][y]=p;

                p.setPosX(x);                             
                p.setPosY(y);   
            }
        } 
    }
    
    public Pion getPion(int x, int y){ //Récuperer un pion à une certaine position
        return plateau[x][y];
    }

    
    
    
    
    private ArrayList<Pion> coupsObligatoiresBlanc(ArrayList<Pion> obl){
        ArrayList<Pion> res=new ArrayList<Pion>();
        if(!obl.isEmpty()){
            for (Pion p : obl){
                int x=p.getPosX()+1;
                int yg=p.getPosY()-1;
                int yd= p.getPosY()+1;
                if(plateau[x][yg]!=null){
                    if(plateau[x][yg].getCouleur()=="Noir"){            //S'il y a un pion adverse à gauche on regarde si on peut le manger
                        if(plateau[x+1][yg-1]==null)res.add(p);
                    }
                    }
                else if(plateau[x+1][yd+1]==null)res.add(p);        // Pareil pour droite
            }
    }
        return res;
    }

    private ArrayList<Pion> coupsObligatoiresNoir(ArrayList<Pion> obl) {
         ArrayList<Pion> res=new ArrayList<Pion>();
       if(!obl.isEmpty()){
            for (Pion p : obl){
                int x=p.getPosX()-1;
                int yg=p.getPosY()-1;
                int yd= p.getPosY()+1;
                if(plateau[x][yg]!=null){
                    if(plateau[x][yg].getCouleur()=="Blanc"){            //S'il y a un pion adverse à gauche on regarde si on peut le manger
                        if(plateau[x-1][yg-1]==null)res.add(p);
                    }
                    }
                else if(plateau[x-1][yd+1]==null)res.add(p);        // Pareil pour droite
            }
       }
        return res;
    }
}
    
    
    
    

