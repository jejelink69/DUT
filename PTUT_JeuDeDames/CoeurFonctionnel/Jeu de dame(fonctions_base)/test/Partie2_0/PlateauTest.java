/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Partie2_0;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vincent
 */
public class PlateauTest {
    
   @Test
    public void testinitPlateau() {
    Plateau test = new Plateau();
    
    assertTrue ("pions blancs mal placés",test.getPion(0, 0).getCouleur().equals("Blanc")); // on verifie le placement d'un blanc
    assertTrue ("pions noirs mal placés",test.getPion(6, 0).getCouleur().equals("Noir"));   // on verifie le placement d'un noir
    
    assertNull ("pions la ou il ne devrait pas il y en avoir",test.getPion(0, 1));
    assertNull ("pions la ou il ne devrait pas il y en avoir",test.getPion(6, 1));
    
                                  }
    
    @Test
    public void testjouerCoup() {

    Plateau test = new Plateau();
    
    Coup coup1= new Coup (test.getPion(3, 9),0,1,0);  
    test.jouerCoup(coup1);
    assertTrue ("pion blanc que l'on ne peut pas déplacer s'est déplacé",test.getPion(3, 9).getCouleur().equals("Blanc"));
    
    Coup coup2= new Coup (test.getPion(6, 0),0,0,0);  
    test.jouerCoup(coup2);
    assertTrue ("pion noir que l'on ne peut pas déplacer s'est déplacé",test.getPion(6, 0).getCouleur().equals("Noir"));
    
    Coup coup3= new Coup (test.getPion(3, 1),0,0,0);  
    test.jouerCoup(coup3);
    assertTrue ("probleme deplacement pion blanc vers la gauche",test.getPion(4,0).getCouleur().equals("Blanc"));
    assertNull ("pion blanc est résté dans sa case ",test.getPion(3,1));
    
    Coup coup4= new Coup (test.getPion(3,3),0,1,0);  
    test.jouerCoup(coup4);
    assertTrue ("probleme deplacement pionc blanc vers la droite",test.getPion(4,4).getCouleur().equals("Blanc"));
    assertNull ("pion blanc est résté dans sa case ",test.getPion(3,3));
    
    Coup coup5= new Coup (test.getPion(6,2),0,0,0);  
    test.jouerCoup(coup5);
    assertTrue ("probleme deplacement pionc noir vers la gauche",test.getPion(5,1).getCouleur().equals("Noir"));
    assertNull ("pion noir est résté dans sa case ",test.getPion(6,2));
    
    Coup coup6= new Coup (test.getPion(6,4),0,1,0);  
    test.jouerCoup(coup6);
    assertTrue ("probleme deplacement pionc noir vers la droite",test.getPion(5,5).getCouleur().equals("Noir"));
    assertNull ("pion noir est résté dans sa case ",test.getPion(6,4)); 

    
    //on test que la fonction coup possible renvoit bien les coup obligatoires et que les coups sont joués correctement 
   Plateau test2 = new Plateau();
    test2.viderPlateau();
    test2.placerPion(0,4,4);
    test2.placerPion(1,5,5);
    test2.placerPion(1,7,7);
    List<Coup> testliste=new ArrayList<Coup>();
    testliste = test2.coupPossible(0);
    boolean vide = testliste.isEmpty();
    assertTrue ("arrayliste pb",vide==false);
    
    Coup c1 = testliste.get(0);   
    test2.jouerCoup(c1);
    assertTrue ("Pion n'est pas au bon endroit apres avoir mangé (vers l'avant)",test2.getPion(8,8).getCouleur().equals("Blanc")); 
    assertNull ("premier pion n'a pas été mangé  ",test2.getPion(5,5));
    assertNull ("deuxieme pion n'a pas été mangé  ",test2.getPion(7,7)); 

    Plateau test3 = new Plateau();
    test3.viderPlateau();
    test3.placerPion(0,3,2);
    test3.placerPion(1,2,1);
    
    List<Coup> testliste3=new ArrayList<Coup>();
    testliste3 = test3.coupPossible(0);
    boolean vide3 = testliste3.isEmpty();
    assertTrue ("arrayliste pb",vide3==false);
    
    Coup c3 = testliste3.get(0);
    test3.jouerCoup(c3);
    assertTrue ("Pion n'est pas au bon endroit apres avoir mangé (vers l'arriere)",test3.getPion(1,0).getCouleur().equals("Blanc"));
    assertNull ("pion n'a pas été mangé",test3.getPion(2,1));
    
    //On teste que coup possible prend en priorité le combo le plus long 
    
    Plateau test4 = new Plateau();
    test4.viderPlateau();
    test4.placerPion(0,3,5);
    test4.placerPion(1,2,4);
    test4.placerPion(1,4,6);
    test4.placerPion(1,6,8);
    
    List<Coup> testliste4=new ArrayList<Coup>();
    testliste4 = test4.coupPossible(0);
    boolean vide4 = testliste4.isEmpty();
    assertTrue ("arrayliste pb",vide4==false);
    
    Coup c4 = testliste4.get(0);
    test4.jouerCoup(c4);
    assertTrue ("Pion n'a pas choisit le bon combo",test4.getPion(7,9).getCouleur().equals("Blanc"));
    assertNull ("pion n'a pas été mangé",test4.getPion(6,8));
    assertNull ("pion n'a pas été mangé",test4.getPion(4,6));
    assertNull ("pion n'a pas choisit le bon combo",test4.getPion(1,3));
} 


public void testevaluation() { // on teste si la fonction renvoie les bonnes valeures en fonction des cas 
    Plateau test = new Plateau();
    test.viderPlateau();
    test.placerPion(0,8,3);
    test.placerPion(0,7,1);
    test.placerPion(0,5,5);
    test.placerPion(1,6,8);
    
    
    Coup coup1= new Coup (test.getPion(8,3),0,1,0);  
    int a = test.evaluation(coup1);
    
    Coup coup2= new Coup (test.getPion(7,1),0,0,0);  
    int b = test.evaluation(coup2);
    
    Coup coup3= new Coup (test.getPion(5,5),0,1,0);  
    int c = test.evaluation(coup3);
    
   assertTrue ("Probleme valuation dame",a==10);
   assertTrue ("Probleme valuation en extremité",b==2);
   assertTrue ("Probleme valuation attaque coup",c==5);
}

}