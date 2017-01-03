package Partie2_0;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Simon
 */
public class Partie {
    
    public static void main(String []args){
        Plateau plat=new Plateau();
        Partie test=new Partie();
        
        
    
        
       
      test.iaVsIA(plat);
    
    }
    
    public void iaVsIA(Plateau test3){
         
        Scanner entree=new Scanner(System.in);
          int choix;
      IA blanc=new IA(test3,0);
      IA noir=new IA(test3,1);
      
      while(test3.partieFinie()==0){
          blanc.jouerIA(5);
          test3.affichePlateau();
          noir.jouerIA(0);
          test3.affichePlateau();
         choix=entree.nextInt();
      }
    }
    
    public void jouerContreIA(){
         Plateau  test3=new Plateau();
      List<Coup> suiv=new ArrayList<Coup>();
      Scanner entree=new Scanner(System.in);
      int choix;
      IA ia=new IA(test3,0);
      while(test3.partieFinie()==0){   
      ia.jouerIA(4);
       test3.affichePlateau();
      suiv=test3.coupPossible(1);
      for(int j=0;j<suiv.size();j++){
          Coup c=suiv.get(j);
          System.out.println("Coup "+j+" : x="+c.getPion().getPosX()+"; y= "+c.getPion().getPosY()+" Type :"+c.getType()+" Dir :"+c.getDir());
         
      }
      
      choix=entree.nextInt();
      test3.jouerCoup(suiv.get(choix));
    }
    }
    
    public void joueurVsJoueur(){
             Plateau  test3=new Plateau();
      List<Coup> suiv=new ArrayList<Coup>();
      Scanner entree=new Scanner(System.in);
      int choix;
      for (int i=0;i<20;i++){ 
          test3.affichePlateau();
          suiv=test3.coupPossible(0);
      
       for(int j=0;j<suiv.size();j++){
          Coup c=suiv.get(j);
          System.out.println("Coup "+j+" : x="+c.getPion().getPosX()+"; y= "+c.getPion().getPosY()+" Type :"+c.getType()+" Dir :"+c.getDir());
         
      }
       choix=entree.nextInt();
      test3.jouerCoup(suiv.get(choix));
      test3.affichePlateau();
         suiv=test3.coupPossible(1);
      
       for(int j=0;j<suiv.size();j++){
          Coup c=suiv.get(j);
          System.out.println("Coup "+j+" : x="+c.getPion().getPosX()+"; y= "+c.getPion().getPosY()+" Type :"+c.getType()+" Dir :"+c.getDir());
         
      }
      
      choix=entree.nextInt();
      test3.jouerCoup(suiv.get(choix));
    }
    }
    
    public static void testIa(){
        Plateau plat=new Plateau();
        
        plat.viderPlateau();
        plat.placerPion(0,4,7);
        plat.placerPion(1,5,6);
        plat.placerPion(1,7,6);
        plat.placerPion(1,8,7);
        plat.placerPion(1,7,4);
        plat.placerPion(1, 8,3);
        
            Scanner entree=new Scanner(System.in);
          int choix;
      IA blanc=new IA(plat,0);
      IA noir=new IA(plat,1);
      
      for(int i=0;i<30;i++){
          blanc.jouerIA(0);
          plat.affichePlateau();
          noir.jouerIA(0);
          plat.affichePlateau();
         choix=entree.nextInt();
      }
    }
}
