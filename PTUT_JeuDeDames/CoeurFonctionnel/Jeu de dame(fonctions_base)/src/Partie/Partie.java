
package Partie;

import java.util.ArrayList;

/**
 *
 * @author Simon
 */
public class Partie {
    
    public static void main(String []args){
      Plateau  plat=new Plateau();
      
      
      Pion p1= plat.getPion(6,0);
      plat.deplacerPion(p1,1);
     p1= plat.getPion(5,1);
            plat.deplacerPion(p1,1);
            Pion p2=plat.getPion(6,4);
            plat.deplacerPion(p2,0);
            
            plat.affichePlateau();
            
     ArrayList<Pion> res=plat.deplacementPossibleNoir();
        for(Pion p : res){
        System.out.println(p.getPosX());
       
    }
    }
}
