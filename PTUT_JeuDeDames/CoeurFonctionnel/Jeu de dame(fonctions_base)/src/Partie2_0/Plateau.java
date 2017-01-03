package Partie2_0;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Simon
 */
public class Plateau {
    private  Pion plateau[][];
    
    public Plateau() {
        plateau=new Pion[10][10];
        this.initPlateau();
        
    }


    
    public void affichePlateau(){
        for (int i=9;i>=0;i--){
            for(int j=0;j<10;j++){
                if(plateau[i][j]!=null){
                    if(plateau[i][j].getCouleur().equals("Blanc")) System.out.print("| O ");
                    else System.out.print("| X ");
                }
                else System.out.print("|   ");
            }
             System.out.println("|");
            System.out.println("");
        }
        
        System.out.println("--------------------------------------------");
        
       
    }
    
    public void jouerCoup(Coup c){
        
        int dir=c.getDir();
        int type=c.getType();
        int sens=c.getSens();
        Pion p=c.getPion();
        String coul=p.getCouleur();
        int x;        
        int y;
        int xInte=99;  //Positions du pion qui va être mangé
        int yInte=99;
        //Deplacement d'un pion
        if(type==0){
            if(sens==0){
            if(coul.equals("Blanc"))x=p.getPosX()+1; //Future position X
            else x=p.getPosX()-1;

            if(dir==0) y=p.getPosY()-1; //Future position Y
            else y=p.getPosY()+1;
            }else{
                if(coul.equals("Blanc"))x=p.getPosX()-1; //Future position X
            else x=p.getPosX()+1;

            if(dir==0) y=p.getPosY()-1; //Future position Y
            else y=p.getPosY()+1;
            }      
        }
        //On mange un Pion
        else{
            
            if(sens==0){
                if(coul.equals("Blanc")){x=p.getPosX()+2; xInte=x-1;} //Future position X
                else { x=p.getPosX()-2; xInte=x+1;}
            }
            else{
                if(coul.equals("Blanc")){x=p.getPosX()-2; xInte=x+1;} //Future position X
                else { x=p.getPosX()+2; xInte=x-1;}
                
            }
            if(dir==0){ y=p.getPosY()-2; yInte=y+1;}//Future position Y
                else{ y=p.getPosY()+2; yInte=y-1;}
            
        }
        
        
        if(x<10 && x>=0 && y>=0 && y<10){                    // On vérifie que le déplacement reste dans le plateau
            if(plateau[x][y]==null){                        // On vérifie que l'on n'écrase pas un pion
                plateau[p.getPosX()][p.getPosY()]=null;   //On bouge le pion dans le tableau
                if((coul.equals("Blanc")&& x==9)||(coul.equals("Noir")&& x==0)){ //Si l'on crée un dame
                   p.setDame(true);
                }
                plateau[x][y]=p;
                p.setPosX(x);                 //On modifie ses attributs de positions            
                p.setPosY(y);
                
                
                if(xInte!=99 && yInte!=99)plateau[xInte][yInte]=null;  //On supprime le Pion une fois que le coup est effectué
            }
            
            c.setAdef(true);
        }//else{System.out.println("Erreur 1"+ c.getPion().getPosX()+" : "+c.getPion().getPosY()+ " ->"+c.getType()+ "&&"+c.getSens()+ " ! "+coul+"="+p.getCouleur());}
        
        if(c.getType()==1 && c.getNext()!=null){        // Si le joueur peut/doit manger un autre Pion et qu'il n'a qu'une seule possibilité alors le système joue pour lui 
            if(c.getNext().size()==1){
                jouerCoup(c.getNext().get(0));
            }
        }
       
    }
    
    public void jouerCoup(Coup c, int dist){
        int distMax=c.getDistance();
        
        if(dist<=distMax){
            int i=0;
            while(i<=dist){
                jouerCoup(c);
                i++;
            }
        }
    } //Pour les dames qui peuvent se déplacer de plusieurs cases
    
    public void dejouerCoup(Coup c){
        int coul=0;
        
        if(c.getAdef()==true){
        if(c.getNext()!=null)dejouerCoup(c.getNext().get(0));
        if (c.getSens() == 0) {
            if (c.getType() == 0) {
                if (c.getDir() == 0) {
                    jouerCoup(new Coup(c.getPion(), 0, 1, 1));
                } else {
                    jouerCoup(new Coup(c.getPion(), 0, 0, 1));
                }
            } else if (c.getDir() == 0) {
                jouerCoup(new Coup(c.getPion(), 0, 1, 1));
                jouerCoup(new Coup(c.getPion(), 0, 1, 1));
                if (c.getPion().getCouleur().equals("Blanc")) {
                    coul = 1;
                    placerPion(coul, c.getPion().getPosX() + 1, c.getPion().getPosY() - 1);
                } else {
                    placerPion(coul, c.getPion().getPosX() - 1, c.getPion().getPosY() - 1);
                }
            } else {
                jouerCoup(new Coup(c.getPion(), 0, 0, 1));
                jouerCoup(new Coup(c.getPion(), 0, 0, 1));
                if (c.getPion().getCouleur().equals("Blanc")) {
                    coul = 1;
                    placerPion(coul, c.getPion().getPosX() + 1, c.getPion().getPosY() + 1);
                } else {
                    placerPion(coul, c.getPion().getPosX() - 1, c.getPion().getPosY() + 1);
                }
            }
        }else{
            if (c.getType() == 0) {
                if (c.getDir() == 0) {
                    jouerCoup(new Coup(c.getPion(), 0, 1, 0));
                } else {
                    jouerCoup(new Coup(c.getPion(), 0, 0, 0));
                }
            } else if (c.getDir() == 0) {
                jouerCoup(new Coup(c.getPion(), 0, 1, 0));
                jouerCoup(new Coup(c.getPion(), 0, 1, 0));
                if (c.getPion().getCouleur().equals("Blanc")) {
                    coul = 1;
                    placerPion(coul, c.getPion().getPosX() - 1, c.getPion().getPosY() - 1);
                } else {
                    placerPion(coul, c.getPion().getPosX() + 1, c.getPion().getPosY() - 1);
                }
            } else {
                jouerCoup(new Coup(c.getPion(), 0, 0, 0));
                jouerCoup(new Coup(c.getPion(), 0, 0, 0));
                if (c.getPion().getCouleur().equals("Blanc")) {
                    coul = 1;
                    placerPion(coul, c.getPion().getPosX() - 1, c.getPion().getPosY() + 1);
                } else {
                    placerPion(coul, c.getPion().getPosX() +1, c.getPion().getPosY() + 1);
                }
            }
        }
        }
        
        c.setAdef(false);
    }
    
    public void dejouerCoup(Coup c, int dist){
         int distMax=c.getDistance();
        
        if(dist<=distMax){
            int i=0;
            while(i<=dist){
                dejouerCoup(c);
                i++;
            }
        }
    } //Pour les dames qui peuvent se déplacer de plusieurs cases
    
    
    
    public List<Coup> coupPossible(int coul){ // 0-> Blanc et 1-> Noir
               
        if (coul==0){
           List<Coup> attaque= attaquePossibleBlanc();
           if(attaque==null){
               List<Coup> deplacement= deplacementPossibleBlanc();
               return deplacement;
           }
           return attaque;
        }
        else{
            List<Coup> attaque= attaquePossibleNoir();
           if(attaque==null){
               List<Coup> deplacement= deplacementPossibleNoir();
               return deplacement;
           }
           return attaque;
        }
    }
     
    private void initPlateau(){
        //Initialisation des pions blancs
        
       for(int i=0;i<4;i++){
           for(int j=0;j<10;j+=2){
               if(i%2==0)plateau[i][j]=new Pion("Blanc",i,j);
               else plateau[i][j+1]=new Pion("Blanc",i,j+1);
            }
        }
       //Initialisation des pions noirs
       for(int i=6;i<10;i++){
           for(int j=0;j<10;j+=2){
               if(i%2==0)plateau[i][j]=new Pion("Noir",i,j);
               else plateau[i][j+1]=new Pion("Noir",i,j+1);
            }
        }
    }
    
    public int partieFinie(){       //0-> Pas finie 1-> blanc a gagné 2->Noir a gagné
        int cptNoir=0;
        int cptBlanc=0;
        for (int i=0;i<=9;i++){
            for (int j=0;j<9;j++){
                if(plateau[i][j]!=null){
                if(plateau[i][j].getCouleur().equals("Blanc"))cptBlanc+=1;
                else cptNoir+=1;
        }
        }
        }
        if(cptNoir==0)return 1;
        else if(cptBlanc==0)return 2;
        else return 0;
    }
    
   
    //Méthodes nécessaires pour CoupPossible
    public List<Coup> deplacementPossibleBlanc(){
        List<Coup> moves = new ArrayList<Coup>();

        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                if (plateau[i][j] != null && !plateau[i][j].getDame()) {
                    Pion temp = plateau[i][j];
                    if (temp.getCouleur().equals("Blanc")) {
                        if (i < 9) {
                            if (j == 0) {
                                if (plateau[i + 1][j + 1] == null) {
                                    moves.add(new Coup(temp, 0, 1, 0));
                                }
                            } else if (j == 9) {
                                if (plateau[i + 1][j - 1] == null) {
                                    moves.add(new Coup(temp, 0, 0, 0));
                                }
                            } else if (j > 0 && j < 9) {
                                if (plateau[i + 1][j - 1] == null) {
                                    moves.add(new Coup(temp, 0, 0, 0));
                                }
                                if (plateau[i + 1][j + 1] == null) {
                                    moves.add(new Coup(temp, 0, 1, 0));
                                }
                            }
                        }
                    }
                }
            }
        }
        return moves;
    }
    
    private List<Coup> deplacementPossibleNoir(){
        List<Coup> moves = new ArrayList<Coup>();

        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                if (plateau[i][j] != null) {
                    Pion temp = plateau[i][j];
                    if (temp.getCouleur().equals("Noir")) {
                        if (i > 0) {
                            if (j == 0) {
                                if (plateau[i - 1][j + 1] == null) {
                                    moves.add(new Coup(temp, 0, 1, 0));  //Deplacement à droite quand on est sur le bord gauche
                                }
                            } else if (j == 9) {
                                if (plateau[i - 1][j - 1] == null) {
                                    moves.add(new Coup(temp, 0, 0, 0));   //Deplacement à gauche quand on est sur le bord droit
                                }
                            } else if (j > 0 && j < 9) {
                                if (plateau[i - 1][j - 1] == null) {
                                    moves.add(new Coup(temp, 0, 0, 0)); //Deplacement à gauche
                                }
                                if (plateau[i - 1][j + 1] == null) {
                                    moves.add(new Coup(temp, 0, 1, 0)); //Deplacement à droite
                                }
                            }
                        }
                    }
                }
            }
        }
        return moves;
    }
    
    private List<Coup> attaquePossibleBlanc(){
        List<Coup> moves=new ArrayList<Coup>();
        
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                if (plateau[i][j] != null) {
                    Pion temp = plateau[i][j];
                    if (temp.getCouleur().equals("Blanc")) {
                        if (j == 0 || j == 1) {
                            if (i < 8 && i > 1) {     // Si le pion est sur le bord de gauche alors uniquement déplacement sur la droite
                                if (plateau[i + 1][j + 1] != null && plateau[i + 1][j + 1].getCouleur().equals("Noir")) {
                                    List<Coup> tmp = attaquePionBlanc(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                } else if (plateau[i - 1][j + 1] != null && plateau[i - 1][j + 1].getCouleur().equals("Noir")) {
                                    List<Coup> tmp = attaquePionBlanc(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                }
                            } else if (i==8 || i==9) {  // Uniquement manger vers le bas
                                if (plateau[i - 1][j + 1] != null && plateau[i - 1][j + 1].getCouleur().equals("Noir")) {
                                    List<Coup> tmp = attaquePionBlanc(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                }
                            } else if (i==1 || i==0) {  // Uniquement vers le haut
                                if (plateau[i + 1][j + 1] != null && plateau[i + 1][j + 1].getCouleur().equals("Noir")) {
                                    List<Coup> tmp = attaquePionBlanc(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                }
                            }
                        } else if (j == 8 || j == 9) {   // Si le pion est sur le bord de droite alors uniquement déplacement sur la gauche
                            if (i < 8 && i > 1) {
                                if (plateau[i + 1][j - 1] != null && plateau[i + 1][j - 1].getCouleur().equals("Noir")) {
                                    List<Coup> tmp = attaquePionBlanc(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                } else if (plateau[i - 1][j - 1] != null && plateau[i - 1][j - 1].getCouleur().equals("Noir")) {
                                    List<Coup> tmp = attaquePionBlanc(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                }
                            } else if (i==8 || i==9) {  // Uniquement manger vers le bas
                                if (plateau[i - 1][j - 1] != null && plateau[i - 1][j - 1].getCouleur().equals("Noir")) {
                                    List<Coup> tmp = attaquePionBlanc(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                }
                            } else if (i==0||i==1) {  // Uniquement vers le haut
                                if (plateau[i + 1][j - 1] != null && plateau[i + 1][j - 1].getCouleur().equals("Noir")) {
                                    List<Coup> tmp = attaquePionBlanc(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                }
                            }
                        } else if (j >1 && j < 8) { //Si le pion n'est pas sur les bords
                            if (i > 1 && i < 8) {
                                if (plateau[i + 1][j - 1] != null && plateau[i + 1][j - 1].getCouleur().equals("Noir")) {
                                    List<Coup> tmp = attaquePionBlanc(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                } else if (plateau[i + 1][j + 1] != null && plateau[i + 1][j + 1].getCouleur().equals("Noir")) {
                                    List<Coup> tmp = attaquePionBlanc(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                } else if (plateau[i - 1][j + 1] != null && plateau[i - 1][j + 1].getCouleur().equals("Noir")) {
                                    List<Coup> tmp = attaquePionBlanc(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                } else if (plateau[i - 1][j - 1] != null && plateau[i - 1][j - 1].getCouleur().equals("Noir")) {
                                    List<Coup> tmp = attaquePionBlanc(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                }
                            } else if (i == 0 || i == 1) {
                                if (plateau[i + 1][j - 1] != null && plateau[i + 1][j - 1].getCouleur().equals("Noir")) {
                                    List<Coup> tmp = attaquePionBlanc(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                } else if (plateau[i + 1][j + 1] != null && plateau[i + 1][j + 1].getCouleur().equals("Noir")) {
                                    List<Coup> tmp = attaquePionBlanc(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                }
                            } else if (i == 8 || i == 9) {
                                if (plateau[i - 1][j + 1] != null && plateau[i - 1][j + 1].getCouleur().equals("Noir")) {
                                    List<Coup> tmp = attaquePionBlanc(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                } else if (plateau[i - 1][j - 1] != null && plateau[i - 1][j - 1].getCouleur().equals("Noir")) {
                                    List<Coup> tmp = attaquePionBlanc(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        List<Coup> resultat = gardeLesMax(moves);
        if (resultat != null) {
            return resultat;
        } else {
            return null;
        }
    }
    
    private List<Coup> attaquePossibleNoir(){
        List<Coup> moves=new ArrayList<Coup>();
    
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                if (plateau[i][j] != null) {
                    Pion temp = plateau[i][j];
                    if (temp.getCouleur().equals("Noir")) {
                        if (j == 0 || j == 1) {
                            if (i < 8 && i>1) {     // Si le pion est sur le bord de gauche alors uniquement déplacement sur la droite
                                if (plateau[i + 1][j + 1] != null && plateau[i + 1][j + 1].getCouleur().equals("Blanc")) {
                                    List<Coup> tmp = attaquePionNoir(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                } else if (plateau[i - 1][j + 1] != null && plateau[i - 1][j + 1].getCouleur().equals("Blanc")) {
                                    List<Coup> tmp = attaquePionNoir(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                }
                            } else if (i > 7) {  // Uniquement manger vers le bas
                                if (plateau[i - 1][j + 1] != null && plateau[i - 1][j + 1].getCouleur().equals("Blanc")) {
                                    List<Coup> tmp = attaquePionNoir(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                }
                            } else if (i < 2) {  // Uniquement vers le haut
                                if (plateau[i + 1][j + 1] != null && plateau[i + 1][j + 1].getCouleur().equals("Blanc")) {
                                    List<Coup> tmp = attaquePionNoir(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                }
                            }
                        } else if (j == 8 || j == 9) {   // Si le pion est sur le bord de droite alors uniquement déplacement sur la gauche
                            if (i < 8 && i > 1) {
                                if (plateau[i + 1][j - 1] != null && plateau[i + 1][j - 1].getCouleur().equals("Blanc")) {
                                    List<Coup> tmp = attaquePionNoir(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                } else if (plateau[i - 1][j - 1] != null && plateau[i - 1][j - 1].getCouleur().equals("Blanc")) {
                                    List<Coup> tmp = attaquePionNoir(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                }
                            } else if (i > 7) {  // Uniquement manger vers le bas
                                if (plateau[i - 1][j - 1] != null && plateau[i - 1][j - 1].getCouleur().equals("Blanc")) {
                                    List<Coup> tmp = attaquePionNoir(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                }
                            } else if (i < 2) {  // Uniquement vers le haut
                                if (plateau[i + 1][j - 1] != null && plateau[i + 1][j - 1].getCouleur().equals("Blanc")) {
                                    List<Coup> tmp = attaquePionNoir(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                }
                            }
                        } else if (j > 1&& j < 8) { //Si le pion n'est pas sur les bords
                            if (i > 1 && i < 8) {
                                if (plateau[i + 1][j - 1] != null && plateau[i + 1][j - 1].getCouleur().equals("Blanc")) {
                                    List<Coup> tmp = attaquePionNoir(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                } else if (plateau[i + 1][j + 1] != null && plateau[i + 1][j + 1].getCouleur().equals("Blanc")) {
                                    List<Coup> tmp = attaquePionNoir(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                } else if (plateau[i - 1][j + 1] != null && plateau[i - 1][j + 1].getCouleur().equals("Blanc")) {
                                    List<Coup> tmp = attaquePionNoir(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                } else if (plateau[i - 1][j - 1] != null && plateau[i - 1][j - 1].getCouleur().equals("Blanc")) {
                                    List<Coup> tmp = attaquePionNoir(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                }
                            } else if (i == 0 || i == 1) {
                                if (plateau[i + 1][j - 1] != null && plateau[i + 1][j - 1].getCouleur().equals("Blanc")) {
                                    List<Coup> tmp = attaquePionNoir(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                } else if (plateau[i + 1][j + 1] != null && plateau[i + 1][j + 1].getCouleur().equals("Blanc")) {
                                    List<Coup> tmp = attaquePionNoir(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                }
                            } else if (i == 8 || i == 9) {
                                if (plateau[i - 1][j + 1] != null && plateau[i - 1][j + 1].getCouleur().equals("Blanc")) {
                                    List<Coup> tmp = attaquePionNoir(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                } else if (plateau[i - 1][j - 1] != null && plateau[i - 1][j - 1].getCouleur().equals("Blanc")) {
                                    List<Coup> tmp = attaquePionNoir(temp);
                                    if (tmp != null) {
                                        for (Coup c : tmp) {
                                            moves.add(c);
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        List<Coup> resultat = gardeLesMax(moves);
        if (resultat != null) {
            return resultat;
        } else {
            return null;
        }

    }
    // refait
    private List<Coup> attaquePionBlanc(Pion temp){
        int x=temp.getPosX();
        int y=temp.getPosY();
       
        List<Coup> res= new ArrayList<Coup>();
        
       
          if(y==0|| y==1){
            if(x<8 && x>1){     // Si le pion est sur le bord de gauche alors uniquement déplacement sur la droite
              if(plateau[x+1][y+1]!=null && plateau[x+1][y+1].getCouleur().equals("Noir")){
                if(plateau[x+2][y+2]==null){
                       Coup cad=new Coup(temp,1,1,0);
                       jouerCoup(cad);
                       cad.setNext(attaquePionBlanc(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
              if(plateau[x-1][y+1]!=null && plateau[x-1][y+1].getCouleur().equals("Noir")){
                if(plateau[x-2][y+2]==null){
                       Coup cad=new Coup(temp,1,1,1);
                       jouerCoup(cad);
                       cad.setNext(attaquePionBlanc(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
            }else if(x>7){  // Uniquement manger vers le bas
              if(plateau[x-1][y+1]!=null && plateau[x-1][y+1].getCouleur().equals("Noir")){
                if(plateau[x-2][y+2]==null){
                       Coup cad=new Coup(temp,1,1,1);
                       jouerCoup(cad);
                       cad.setNext(attaquePionBlanc(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
            }else if(x<2){  // Uniquement vers le haut
              if(plateau[x+1][y+1]!=null && plateau[x+1][y+1].getCouleur().equals("Noir")){
                if(plateau[x+2][y+2]==null){
                       Coup cad=new Coup(temp,1,1,0);
                       jouerCoup(cad);
                       cad.setNext(attaquePionBlanc(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
            }
          }else if(y==8 || y==9){   // Si le pion est sur le bord de droite alors uniquement déplacement sur la gauche
            if(x<8 && x>1){
              if(plateau[x+1][y-1]!=null && plateau[x+1][y-1].getCouleur().equals("Noir")){
                if(plateau[x+2][y-2]==null){
                       Coup cad=new Coup(temp,1,0,0);
                       jouerCoup(cad);
                       cad.setNext(attaquePionBlanc(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
              if(plateau[x-1][y-1]!=null && plateau[x-1][y-1].getCouleur().equals("Noir")){
                if(plateau[x-2][y-2]==null){
                       Coup cad=new Coup(temp,1,0,1);
                       jouerCoup(cad);
                       cad.setNext(attaquePionBlanc(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
            }else if(x>7){  // Uniquement manger vers le bas
              if(plateau[x-1][y-1]!=null && plateau[x-1][y-1].getCouleur().equals("Noir")){
                if(plateau[x-2][y-2]==null){
                       Coup cad=new Coup(temp,1,0,1);
                       jouerCoup(cad);
                       cad.setNext(attaquePionBlanc(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
            }else if(x<2){  // Uniquement vers le haut
              if(plateau[x+1][y-1]!=null && plateau[x+1][y-1].getCouleur().equals("Noir")){
                if(plateau[x+2][y-2]==null){
                       Coup cad=new Coup(temp,1,0,0);
                       jouerCoup(cad);
                       cad.setNext(attaquePionBlanc(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
            }
          }else if(y>1 && y<8){ //Si le pion n'est pas sur les bords
            if(x>1 && x<8){
              if(plateau[x+1][y-1]!=null && plateau[x+1][y-1].getCouleur().equals("Noir")){
                if(plateau[x+2][y-2]==null){
                       Coup cad=new Coup(temp,1,0,0);
                       jouerCoup(cad);
                       cad.setNext(attaquePionBlanc(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
              if(plateau[x+1][y+1]!=null && plateau[x+1][y+1].getCouleur().equals("Noir")){
                if(plateau[x+2][y+2]==null){
                       Coup cad=new Coup(temp,1,1,0);
                       jouerCoup(cad);
                       cad.setNext(attaquePionBlanc(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
              if(plateau[x-1][y+1]!=null && plateau[x-1][y+1].getCouleur().equals("Noir")){
                if(plateau[x-2][y+2]==null){
                       Coup cad=new Coup(temp,1,1,1);
                       jouerCoup(cad);
                       cad.setNext(attaquePionBlanc(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
              if(plateau[x-1][y-1]!=null && plateau[x-1][y-1].getCouleur().equals("Noir")){
                if(plateau[x-2][y-2]==null){
                       Coup cad=new Coup(temp,1,0,1);
                       jouerCoup(cad);
                       cad.setNext(attaquePionBlanc(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
            }else if(x==0|| x==1){
              if(plateau[x+1][y-1]!=null && plateau[x+1][y-1].getCouleur().equals("Noir")){
                if(plateau[x+2][y-2]==null){
                       Coup cad=new Coup(temp,1,0,0);
                       jouerCoup(cad);
                       cad.setNext(attaquePionBlanc(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
              if(plateau[x+1][y+1]!=null && plateau[x+1][y+1].getCouleur().equals("Noir")){
                if(plateau[x+2][y+2]==null){
                       Coup cad=new Coup(temp,1,1,0);
                       jouerCoup(cad);
                       cad.setNext(attaquePionBlanc(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
            }else if(x==8||x==9){
              if(plateau[x-1][y+1]!=null && plateau[x-1][y+1].getCouleur().equals("Noir")){
                if(plateau[x-2][y+2]==null){
                       Coup cad=new Coup(temp,1,1,1);
                       jouerCoup(cad);
                       cad.setNext(attaquePionBlanc(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
              if(plateau[x-1][y-1]!=null && plateau[x-1][y-1].getCouleur().equals("Noir")){
                if(plateau[x-2][y-2]==null){
                       Coup cad=new Coup(temp,1,0,1);
                       jouerCoup(cad);
                       cad.setNext(attaquePionBlanc(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
                }
            }
        }
    List<Coup> resultat= gardeLesMax(res);
    if(resultat!=null)return resultat;
    else return null;
    }
    
    private List<Coup> attaquePionNoir(Pion temp){
        int x=temp.getPosX();
        int y=temp.getPosY();
        List<Coup> res= new ArrayList<Coup>();
               
       
          if(y==0 || y==1){
            if(x<8 && x>1){     // Si le pion est sur le bord de gauche alors uniquement déplacement sur la droite
              if(plateau[x+1][y+1]!=null && plateau[x+1][y+1].getCouleur().equals("Blanc")){
                if(plateau[x+2][y+2]==null){
                       Coup cad=new Coup(temp,1,1,1);
                       jouerCoup(cad);
                       cad.setNext(attaquePionNoir(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
              if(plateau[x-1][y+1]!=null && plateau[x-1][y+1].getCouleur().equals("Blanc")){
                if(plateau[x-2][y+2]==null){
                       Coup cad=new Coup(temp,1,1,0);
                       jouerCoup(cad);
                       cad.setNext(attaquePionNoir(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
            }else if(x>7){  // Uniquement manger vers le bas
              if(plateau[x-1][y+1]!=null && plateau[x-1][y+1].getCouleur().equals("Blanc")){
                if(plateau[x-2][y+2]==null){
                       Coup cad=new Coup(temp,1,1,0);
                       jouerCoup(cad);
                       cad.setNext(attaquePionNoir(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
            }else if(x<2){  // Uniquement vers le haut
              if(plateau[x+1][y+1]!=null && plateau[x+1][y+1].getCouleur().equals("Blanc")){
                if(plateau[x+2][y+2]==null){
                       Coup cad=new Coup(temp,1,1,1);
                       jouerCoup(cad);
                       cad.setNext(attaquePionNoir(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
            }
          }else if(y==8 || y==9){   // Si le pion est sur le bord de droite alors uniquement déplacement sur la gauche
            if(x<8 && x>1){
              if(plateau[x+1][y-1]!=null && plateau[x+1][y-1].getCouleur().equals("Blanc")){
                if(plateau[x+2][y-2]==null){
                       Coup cad=new Coup(temp,1,0,1);
                       jouerCoup(cad);
                       cad.setNext(attaquePionNoir(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
              if(plateau[x-1][y-1]!=null && plateau[x-1][y-1].getCouleur().equals("Blanc")){
                if(plateau[x-2][y-2]==null){
                       Coup cad=new Coup(temp,1,0,0);
                       jouerCoup(cad);
                       cad.setNext(attaquePionNoir(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
            }else if(x>7){  // Uniquement manger vers le bas
              if(plateau[x-1][y-1]!=null && plateau[x-1][y-1].getCouleur().equals("Blanc")){
                if(plateau[x-2][y-2]==null){
                       Coup cad=new Coup(temp,1,0,0);
                       jouerCoup(cad);
                       cad.setNext(attaquePionNoir(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
            }else if(x<2){  // Uniquement vers le haut
              if(plateau[x+1][y-1]!=null && plateau[x+1][y-1].getCouleur().equals("Blanc")){
                if(plateau[x+2][y-2]==null){
                       Coup cad=new Coup(temp,1,0,1);
                       jouerCoup(cad);
                       cad.setNext(attaquePionNoir(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
            }
          }else if(y>1 && y<8){ //Si le pion n'est pas sur les bords
            if(x>1 && x<8){
              if(plateau[x+1][y-1]!=null && plateau[x+1][y-1].getCouleur().equals("Blanc")){
                if(plateau[x+2][y-2]==null){
                       Coup cad=new Coup(temp,1,0,1);
                       jouerCoup(cad);
                       cad.setNext(attaquePionNoir(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
              if(plateau[x+1][y+1]!=null && plateau[x+1][y+1].getCouleur().equals("Blanc")){
                if(plateau[x+2][y+2]==null){
                       Coup cad=new Coup(temp,1,1,1);
                       jouerCoup(cad);
                       cad.setNext(attaquePionNoir(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
              if(plateau[x-1][y+1]!=null && plateau[x-1][y+1].getCouleur().equals("Blanc")){
                if(plateau[x-2][y+2]==null){
                       Coup cad=new Coup(temp,1,1,0);
                       jouerCoup(cad);
                       cad.setNext(attaquePionNoir(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
              if(plateau[x-1][y-1]!=null && plateau[x-1][y-1].getCouleur().equals("Blanc")){
                if(plateau[x-2][y-2]==null){
                       Coup cad=new Coup(temp,1,0,0);
                       jouerCoup(cad);
                       cad.setNext(attaquePionNoir(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
            }else if(x==0|| x==1){
              if(plateau[x+1][y-1]!=null && plateau[x+1][y-1].getCouleur().equals("Blanc")){
                if(plateau[x+2][y-2]==null){
                       Coup cad=new Coup(temp,1,0,1);
                       jouerCoup(cad);
                       cad.setNext(attaquePionNoir(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
              if(plateau[x+1][y+1]!=null && plateau[x+1][y+1].getCouleur().equals("Blanc")){
                if(plateau[x+2][y+2]==null){
                       Coup cad=new Coup(temp,1,1,1);
                       jouerCoup(cad);
                       cad.setNext(attaquePionNoir(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
            }else if(x==8||x==9){
              if(plateau[x-1][y+1]!=null && plateau[x-1][y+1].getCouleur().equals("Blanc")){
                if(plateau[x-2][y+2]==null){
                       Coup cad=new Coup(temp,1,1,0);
                       jouerCoup(cad);
                       cad.setNext(attaquePionNoir(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                   }
              }
              if(plateau[x-1][y-1]!=null && plateau[x-1][y-1].getCouleur().equals("Blanc")){
                if(plateau[x-2][y-2]==null){
                       Coup cad=new Coup(temp,1,0,0);
                       jouerCoup(cad);
                       cad.setNext(attaquePionNoir(cad.getPion()));
                       res.add(cad);
                       dejouerCoup(cad);
                    }
                }
            }
        }
    List<Coup> resultat= gardeLesMax(res);
    if(resultat!=null)return resultat;
    else return null; 
    }
    
    private List<Coup> gardeLesMax(List<Coup> cp){
          List<Coup> resultat= new ArrayList<Coup>();  
        if(cp.size()>0){
           int max=cp.get(0).getTaille();
          for (Coup c: cp){
          if(c.getTaille()>max)max=c.getTaille();
          } 
          for(Coup c:cp){
              if(c.getTaille()>=max)resultat.add(c);
          }
          return resultat;
       }
       return null;
        
        
    }
     
    
    //Fonction évaluation qui ne prend que des coups de Type deplacement
    
    public int evaluation(Coup coup){  //Deplacement sur les bords=2; Protéger un pion=4; attaquer un pion=5; deplacement normal=1; Faire une dame=10 ; manger une dame = 8;
       int coul;
       if(coup.getPion().getCouleur().equals("Blanc"))coul=0;
       else coul=1;
       
       jouerCoup(coup);
       int y=coup.getPion().getPosY();
       int x=coup.getPion().getPosX();
        
        if(coul==0) {
            if(x==9){dejouerCoup(coup);return 10;}//Si le déplacement engendre une dame
            if(attaquePionBlanc(coup.getPion())!=null){dejouerCoup(coup);return 5;} //Au prochain coup on peut manger un pion
            if(x<8 ){
                if(y<8){
                    if(plateau[x+1][y+1]!=null){                                    //Le pion en protège un autre
                        if(plateau[x+1][y+1].getCouleur().equals("Blanc")){
                            if(plateau[x+2][y+2]!=null){
                                if(plateau[x+2][y+2].getCouleur().equals("Noir")){
                                    dejouerCoup(coup);
                                    return 4;
                                }
                            }       
                        }
                    }
                }
                if(y>2){
                    if(plateau[x+1][y-1]!=null){                                    
                        if(plateau[x+1][y-1].getCouleur().equals("Blanc")){
                            if(plateau[x+2][y-2]!=null){
                                if(plateau[x+2][y-2].getCouleur().equals("Noir")){
                                    dejouerCoup(coup);
                                    return 4;
                                }
                            }       
                        }
                    }
                }
            }
        } 
        else{
            if(x==0){dejouerCoup(coup);return 10; }
            if(attaquePionNoir(coup.getPion())!=null){dejouerCoup(coup);return 5;}
            if(x>2){
                if(y<8){
                    if(plateau[x-1][y+1]!=null){                                    
                        if(plateau[x-1][y+1].getCouleur().equals("Noir")){
                            if(plateau[x-2][y+2]!=null){
                                if(plateau[x-2][y+2].getCouleur().equals("Blanc")){
                                    dejouerCoup(coup);
                                    return 4;
                                }
                            }       
                        }
                    }
                }if(y>2){
                    if(plateau[x-1][y-1]!=null){                                    
                        if(plateau[x-1][y-1].getCouleur().equals("Noir")){
                            if(plateau[x-2][y-2]!=null){
                                if(plateau[x-2][y-2].getCouleur().equals("Blanc")){
                                    dejouerCoup(coup);
                                    return 4;
                                }
                            }       
                        }
                    }
                }
            }
        }
            
        if(y==0 || y==9){dejouerCoup(coup); return 2; } 
            
        dejouerCoup(coup);
        return 1;
    }
     
     //Getter
     public Pion getPion(int x, int y ){
         return plateau[x][y];
     }
     
     // Méthodes pour les tests
     
     public void placerPion(int couleur,int x, int y){  //0-> Blanc et 1-> Noir
         plateau[x][y]=new Pion("Blanc",x,y);
         if(couleur==1){
             plateau[x][y].setCouleur("Noir");
         }
     }
     
     public void supprimerPion(int x, int y ){
         plateau[x][y]=null;
     }
     
     public void viderPlateau(){
         for (int i=0;i<10;i++){
             for(int j=0;j<10;j++){
                 plateau[i][j]=null;
             }
         }
     }

     public List<Coup> deplacementDameBlanche(){
         int dist;
         List<Coup> dep=new ArrayList<Coup>();
         for(int i=0;i<9;i++){
             for(int j=0;j<9;j++){
                 Pion p =plateau[i][j];
                 if(p.getDame()==true && p.getCouleur().equals("Blanc")){
                     dist=deplacementDroitDame(p);
                     if(dist!=0)dep.add(new Coup(p,0,1,0,dist));
                     dist=deplacementGaucheDame(p);
                     if(dist!=0)dep.add(new Coup(p,0,0,0,dist));
                 }
             }
         }
         return dep;
     }
     
     public List<Coup> deplacementDameNoir(){
         int dist;
         List<Coup> dep=new ArrayList<Coup>();
         for(int i=0;i<9;i++){
             for(int j=0;j<9;j++){
                 Pion p =plateau[i][j];
                 if(p.getDame()==true && p.getCouleur().equals("Noir")){
                     dist=deplacementDroitDame(p);
                     if(dist!=0)dep.add(new Coup(p,0,1,0,dist));
                     dist=deplacementGaucheDame(p);
                     if(dist!=0)dep.add(new Coup(p,0,0,0,dist));
                 }
             }
         }
         return dep;
     }
     
     
     public int deplacementDroitDame(Pion p){
         if(p.getCouleur().equals("Blanc")){
            int x=p.getPosX()+1;
            int y=p.getPosY()+1;
            int dist=0;
            while(y<=10&&x<=10&&plateau[x][y]==null){
                dist+=1; 
                x+=1;
                y+=1;
            }
            return dist;
        }
        else{
            int x=p.getPosX()-1;
            int y=p.getPosY()+1;
            int dist=0;
            while(y<=10&&x>=0&&plateau[x][y]==null){
                dist+=1; 
                x-=1;
                y+=1;
            }
            return dist;
        }
         
     }
      public int deplacementGaucheDame(Pion p){
         if(p.getCouleur().equals("Blanc")){
             int x=p.getPosX()+1;
             int y=p.getPosY()-1;
             int dist=0;
             while(y>=0&&x<=10&&plateau[x][y]==null){
                 dist+=1; 
                 x+=1;
                 y-=1;
            }
             return dist;  
         }else{
            int x=p.getPosX()-1;
            int y=p.getPosY()-1;
            int dist=0;
            while(y>=0&&x>=10&&plateau[x][y]==null){
                dist+=1; 
                x-=1;
                y-=1;
           }
            return dist;  
        }
        
     }
     public List<Coup> attaqueDameBlanche(Dame temp){
        int x=temp.getPosX();
        int y=temp.getPosY();
        
       
        List<Coup> res= new ArrayList<Coup>();
         
       for(int i=x,j=y;i<9 && j>=0;i++,j--){
            if(plateau[i][j]!=null){
                if(plateau[i][j].getCouleur().equals("Noir")){
                    Coup cag= new Coup(temp,0,0,0);
                    Coup prec=cag;
                    int dist=i-x;
                    for(int cpt=2;cpt<=dist;cpt++){
                        List<Coup> dep=new ArrayList<Coup>();
                        dep.add(new Coup(temp,0,0,0));
                        prec.setNext(dep);
                       prec=prec.getNext().get(0); 
                    }
                    List<Coup> dep=new ArrayList<Coup>();
                    dep.add(new Coup(temp,1,0,0));
                    prec.setNext(dep);
                    prec=prec.getNext().get(0);
                    jouerCoup(cag);
                    dep=attaqueDameBlanche(temp);
                    prec.setNext(dep);
                    dejouerCoup(cag);
                }
            }
        }
       
        return res;
           }
}















