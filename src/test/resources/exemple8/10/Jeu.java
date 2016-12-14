
package PPC;

import PPC.*;
import PPC.Strategie.*;
import java.util.Scanner;


public class Jeu{
private int nbTours;
private Joueur j1;
private Joueur j2;


  /**	constructor Jeu
  *	cree un Jeu avec pour j1 le premier parametre, pour j2 le deuxieme parametre et pour nbTours le troisieme parametre
  *	@param j1
  *		Un premier joueur
  *	@param j2
  *		Un second joueur
  *	@param nbTours
  *		Le nombre de tours a jouer
  *	 
  */
public Jeu(int nbTours, Joueur j1, Joueur j2){
this.nbTours = nbTours;
this.j1 = j1;
this.j2 = j2;}

public void jouerUnTour(){
System.out.println("A "+this.j1.toString()+" de jouer :");
Coups c1 = this.j1.getCoup();
System.out.println("Et maintenant à "+this.j2.toString()+" de jouer :");
Coups c2 = this.j2.getCoup();
int res = c1.compare(c2);
    System.out.printf ("%s joue : %s \n%s joue : %s \n",this.j1,c1.toString(),this.j2,c2.toString());
    switch(res){
      case (-1) :
	this.j2.addPoint(1);
	nbTours--;
	System.out.printf ("%s gagne, récapitulatif des scores : \n   %s : %d\n   %s : %d\n",this.j2,this.j1,this.j1.getScore(),this.j2,this.j2.getScore());
	break;
      case 0 :
	this.j1.addPoint(1);
	this.j2.addPoint(1);
	nbTours--;
	System.out.printf ("egalite, récapitulatif des scores : \n   %s : %d\n   %s : %d\n",this.j1.toString(),this.j1.getScore(),this.j2.toString(),this.j2.getScore());
	break;
      case 1 :
	this.j1.addPoint(1);
	nbTours--;
	System.out.printf ("%s gagne, récapitulatif des scores : \n   %s : %d\n   %s : %d\n",this.j1.toString(),this.j1.toString(),this.j1.getScore(),this.j2.toString(),this.j2.getScore());
	break;
    }
  }


  private static Strategie choix() {
	System.out.println("strategie aleatoire : 0 | strategie papier : 1 | strategie pierre : 2 |strategie pierre papier : 3 | strategie humaine : 4");
	System.out.println("entrez un chiffre : ");
	Scanner sc1 = new Scanner(System.in);
	int aux = sc1.nextInt();
	Strategie s = new StrategiePierre();
	switch (aux) {
		case 2 : 
			break;
		case 1 : 
			s = new StrategiePapier();
			break;
		case 3 : 
			s = new StrategiePierrePapier();
			break;
		case 0 : 
			s = new StrategieAleatoire();
			break;
		case 4 :
			s = new StrategieHumaine();
			break;
		default : 
			System.out.println("entrez un chiffre valide");
			choix();
			break;
	}
	return s;
  } 
	
	
  /** main*/
  public static void main (String[] args) {
    Scanner sc1 = new Scanner(System.in);
    System.out.println("Entrez le nom du premier joueur");
    String joueur1 = sc1.nextLine();
    Scanner sc2 = new Scanner(System.in);
    System.out.println("Entre le nom du second joueur");
    String joueur2 = sc2.nextLine();
    System.out.println(""+joueur1+" choisissez votre strategie :");
    Joueur j1 = new Joueur(joueur1, choix());
    System.out.println(""+joueur2+" choisissez votre strategie :");
    Joueur j2 = new Joueur(joueur2, choix());
    Scanner sc3 = new Scanner(System.in);
    System.out.println("Indiquez le nombre de tours a jouer :");
    int nb = sc3.nextInt();
    System.out.println("--Début de la partie--");
    Jeu game = new Jeu(nb,j1,j2);
    for (int i = 0; i < nb; i++){
      try {	
	game.jouerUnTour();
      }
      catch (StrategieHumaine.MauvaisChiffre e){
	System.out.println("Entrez un chiffre correct");
	game.jouerUnTour();
      }
    }
    if ((j1.getScore()) > (j2.getScore()) )
	System.out.println(""+joueur1+" gagne la partie");
	else if ((	j1.getScore() )< (j2.getScore()) )
	System.out.println(""+joueur2+" gagne la partie");
		else
			System.out.println("Egalite "+joueur1+" et "+joueur2+" gagnent la partie");
    System.out.println("--Fin de la partie--");
  }
}

